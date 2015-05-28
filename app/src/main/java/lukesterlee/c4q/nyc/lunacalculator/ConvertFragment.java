package lukesterlee.c4q.nyc.lunacalculator;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Filter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Luke on 5/21/2015.
 */
public class ConvertFragment extends Fragment {



    final String[] SUGGESTIONS = new String[]{
            "kg to lbs ",
            "lbs to kg ",
            "kg to oz ",
            "celsius to fahrenheit ",
            "fahrenheit to celsius ",
            "km to m ",
            "km to cm ",
            "km to mi ",
            "km to ft ",
            "km to in ",
            "km to yd ",
            "m to km ",
            "m to cm ",
            "m to mm ",
            "m to mi ",
            "m to ft ",
            "m to in ",
            "m to yd ",
            "mi to in ",
            "mi to ft ",
            "mi to m ",
            "mi to km ",
            "mi to yd ",
            "in to m ",
            "in to ft ",
            "in to yd ",
            "in to mm ",
            "in to m ",
            "in to cm ",
            "ft to yd ",
            "ft to m ",
            "ft to cm ",
            "ft to mm ",
            "ft to in ",
            "year to hour ",
            "year to day ",
            "year to min ",

    };

    final String[] UNIT = new String[] {
            "kg,lbs,2.20462",
            "lbs,kg,0.453592",
            "kg,oz,35.274",
            "km,m,1000",
            "km,cm,100000",
            "km,mi,0.621371",
            "km,ft,3280.84",
            "km,in,39370.1",
            "km,yd,1093.61",
            "m,km,0.001",
            "m,cm,100",
            "m,mm,1000",
            "m,mi,0.000621371",
            "m,ft,3.28084",
            "m,in,39.3701",
            "m,yd,1.09361",
            "mi,in,63360",
            "mi,ft,5280",
            "mi,m,1609.34",
            "mi,km,1.60934",
            "mi,yd,1760",
            "in,m,0.0254",
            "in,ft,0.083333",
            "in,yd,0.0277778",
            "in,mm,25.4",
            "in,m,0.0254",
            "in,cm,2.54",
            "ft,yd,0.33333",
            "ft,m,0.3048",
            "ft,cm,30.48",
            "ft,mm,304.8",
            "ft,in,12",
            "year,hour,8765.81",
            "year,day,365",
            "year,min,525949",




    };


    AutoCompleteTextView autoCompleteTextView;
    TextView answerPanel;
    Button submit;
    Button send;

    String search = "";
    String answer;

    public ConvertFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_converter, container, false);






        autoCompleteTextView = (AutoCompleteTextView) result.findViewById(R.id.autocomplete_units);
        answerPanel = (TextView) result.findViewById(R.id.textView_answer);
        submit = (Button) result.findViewById(R.id.button_submit);
        send = (Button) result.findViewById(R.id.button_send);

        ArrayList<String> list = new ArrayList<String>(Arrays.asList(SUGGESTIONS));


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, list) {
            @Override
            public Filter getFilter() {
                Filter myFilter = new Filter() {
                    @Override
                    protected FilterResults performFiltering(CharSequence constraint) {
                        FilterResults filterResults = new FilterResults();

                        return filterResults;
                    }


                    @Override
                    protected void publishResults(CharSequence constraint, FilterResults results) {

                    }
                };


                return myFilter;
            }
        };

        autoCompleteTextView.setAdapter(adapter);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search = autoCompleteTextView.getText().toString();
                answer = getAnswer();
                answerPanel.setText(answer);
            }
        });

        autoCompleteTextView.setOnEditorActionListener(new AutoCompleteTextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if ((actionId == EditorInfo.IME_ACTION_DONE) || ((keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (keyEvent.getAction() == KeyEvent.ACTION_DOWN))) {
                    search = autoCompleteTextView.getText().toString();

                    answer = getAnswer();
                    answerPanel.setText(answer);
                    return true;
                } else {
                    return false;
                }
            }
        });

        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("panel", answerPanel.getText().toString());
                editor.putString("history", answerPanel.getText().toString());
                editor.putString("ans", answerPanel.getText().toString());
                editor.commit();
                Fragment fragment = new CalculatorFragment();
                Bundle args = new Bundle();
                args.putInt("position", 0);
                fragment.setArguments(args);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();


                


            }
        });


        return result;
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    public String getAnswer() {

        Double rate = 0.0;
        Double amount = 0.0;
        Double celsius;
        Double fahrenheit;
        String result;

        DecimalFormat df = new DecimalFormat("#.00");

        String[] searchWords = new String[]{};
        searchWords = getSearchArray(search);



        if (search.startsWith("celsius")) {
            celsius = Double.parseDouble(searchWords[3]);
            fahrenheit = celsius*(9/5) + 32;
            result = df.format(fahrenheit);
            return result;
        } else if (search.startsWith("fahrenheit")) {
            fahrenheit = Double.parseDouble(searchWords[3]);
            celsius = (fahrenheit - 32)*(5/9);
            result = df.format(celsius);
            return result;
        } else {

            String[] csvWords = new String[]{};

            for (String line : UNIT) {
                csvWords = line.split(",");
                if (csvWords[0].equals(searchWords[0]) && csvWords[1].equals(searchWords[2])) {

                    rate = Double.parseDouble(csvWords[2]);
                    amount = Double.parseDouble(searchWords[3]);

                }
            }

            result = df.format(rate*amount);
            return result;

        }



    }
    public static String[] getSearchArray(String input) {
        return input.split(" ");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt("position"));
    }
}
