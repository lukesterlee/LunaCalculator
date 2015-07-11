package lukesterlee.c4q.nyc.lunacalculator;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Luke on 5/19/2015.
 */

// TODO: change this to activity
public class CalculatorFragment extends Fragment implements GraphCallbacks{



    TextView history;
    TextView panel;
    ArrayList<Button> buttons;

    String ans;
    Button sin, cos, tan;
    Button deg, rad;

    Button buttonFactorial;
    Button exp;
    Button buttonPercentage;
    Button equal;

    public static final String PANEL_KEY = "panel";
    public static final String HISTORY_KEY = "history";
    public static final String ANSWER_KEY = "answer";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_calculator, container, false);

        panel = (TextView) result.findViewById(R.id.panel);
        history = (TextView) result.findViewById(R.id.history);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        panel.setText(sharedPref.getString(PANEL_KEY, ""));
        history.setText(sharedPref.getString(HISTORY_KEY, ""));
        ans = sharedPref.getString(ANSWER_KEY, "");


        sin = (Button) result.findViewById(R.id.buttonSin);

        cos = (Button) result.findViewById(R.id.buttonCos);
        tan = (Button) result.findViewById(R.id.buttonTan);

        buttonFactorial = (Button) result.findViewById(R.id.buttonFactorial);
        exp = (Button) result.findViewById(R.id.buttonExp);

        deg = (Button) result.findViewById(R.id.buttonDegree);
        rad = (Button) result.findViewById(R.id.buttonRadian);

        buttonPercentage = (Button) result.findViewById(R.id.buttonPercentage);

        equal = (Button) result.findViewById(R.id.buttonEqual);

        buttons = new ArrayList<Button>();
        buttons.add((Button) result.findViewById(R.id.button0));
        buttons.add((Button) result.findViewById(R.id.button1));
        buttons.add((Button) result.findViewById(R.id.button2));
        buttons.add((Button) result.findViewById(R.id.button3));
        buttons.add((Button) result.findViewById(R.id.button4));
        buttons.add((Button) result.findViewById(R.id.button5));
        buttons.add((Button) result.findViewById(R.id.button6));
        buttons.add((Button) result.findViewById(R.id.button7));
        buttons.add((Button) result.findViewById(R.id.button8));
        buttons.add((Button) result.findViewById(R.id.button9));
        buttons.add((Button) result.findViewById(R.id.buttonDot));
        buttons.add((Button) result.findViewById(R.id.buttonDEL));
        buttons.add((Button) result.findViewById(R.id.buttonAC));
        buttons.add((Button) result.findViewById(R.id.buttonAdd));
        buttons.add((Button) result.findViewById(R.id.buttonMinus));
        buttons.add((Button) result.findViewById(R.id.buttonMultiple));
        //buttons.add((Button) result.findViewById(R.id.buttonEqual));
        buttons.add((Button) result.findViewById(R.id.buttonDivided));
        buttons.add((Button) result.findViewById(R.id.buttonRoot));
        buttons.add(sin);
        buttons.add(cos);
        buttons.add(tan);
        buttons.add((Button) result.findViewById(R.id.buttonParenthesis));
        buttons.add((Button) result.findViewById(R.id.buttonE));
        buttons.add((Button) result.findViewById(R.id.buttonPi));

        buttons.add((Button) result.findViewById(R.id.buttonLn));
        buttons.add((Button) result.findViewById(R.id.buttonLog));
        buttons.add((Button) result.findViewById(R.id.buttonAns));
        buttons.add((Button) result.findViewById(R.id.button2nd));
        buttons.add((Button) result.findViewById(R.id.buttonNega));

        buttons.add(deg);
        buttons.add(rad);
        buttons.add(buttonFactorial);
        buttons.add(exp);
        buttons.add(buttonPercentage);
        buttons.add(equal);

        ButtonClickListener listener = new ButtonClickListener(panel, history, this, ans, getActivity());


        for (Button button : buttons) {
            if (button != null) {
                button.setOnClickListener(listener);
            }
        }

        listener.setEqual(equal);

        if (sin != null) {
            listener.set2nd(sin, cos, tan, deg, rad, buttonFactorial, exp, buttonPercentage);
        }

        return result;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt("position"));
    }


    @Override
    public void graphButtonClicked(String formula, int x, int y) {
        Intent intent = new Intent(getActivity(), GraphicActivity.class);
        intent.putExtra("formula", formula);
        intent.putExtra("minX", x);
        intent.putExtra("maxX", y);
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PANEL_KEY, panel.getText().toString());
        editor.putString(HISTORY_KEY, history.getText().toString());
        editor.putString(ANSWER_KEY, ans);
        editor.commit();
    }
}
