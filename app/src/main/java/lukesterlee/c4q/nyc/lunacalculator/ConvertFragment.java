package lukesterlee.c4q.nyc.lunacalculator;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;

/**
 * Created by Luke on 5/21/2015.
 */
public class ConvertFragment extends Fragment {

    EditText editText;
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


        final InputStream file = getResources().openRawResource(R.raw.mass);



        editText = (EditText) result.findViewById(R.id.editText_converter);
        answerPanel = (TextView) result.findViewById(R.id.textView_answer);
        submit = (Button) result.findViewById(R.id.button_submit);
        send = (Button) result.findViewById(R.id.button_send);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search = editText.getText().toString();
                answer = Converter.getAnswer(file, search);
                answerPanel.setText(answer);
            }
        });

        editText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if ( (actionId == EditorInfo.IME_ACTION_DONE) || ((keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (keyEvent.getAction() == KeyEvent.ACTION_DOWN ))){
                    search = editText.getText().toString();

                    answer = Converter.getAnswer(file, search);
                    answerPanel.setText(answer);
                    return true;
                }
                else{
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
}
