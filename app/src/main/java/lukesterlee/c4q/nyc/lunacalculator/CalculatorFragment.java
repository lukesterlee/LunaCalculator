package lukesterlee.c4q.nyc.lunacalculator;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Luke on 5/19/2015.
 */
public class CalculatorFragment extends Fragment{


    TextView history;
    TextView panel;
    ArrayList<Button> buttons;

    Button sin, cos, tan;
    Button deg, rad;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_calculator, container, false);


        panel = (TextView) result.findViewById(R.id.panel);
        history = (TextView) result.findViewById(R.id.history);

        if (savedInstanceState != null) {
            panel.setText(savedInstanceState.getString("panel"));
            history.setText(savedInstanceState.getString("history"));
        }

        sin = (Button) result.findViewById(R.id.buttonSin);
        cos = (Button) result.findViewById(R.id.buttonCos);
        tan = (Button) result.findViewById(R.id.buttonTan);

        deg = (Button) result.findViewById(R.id.buttonDegree);
        rad = (Button) result.findViewById(R.id.buttonRadian);

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
        buttons.add((Button) result.findViewById(R.id.buttonEqual));
        buttons.add((Button) result.findViewById(R.id.buttonDivided));
        buttons.add(sin);
        buttons.add(cos);
        buttons.add(tan);
        buttons.add((Button) result.findViewById(R.id.buttonParenthesis));
        buttons.add((Button) result.findViewById(R.id.buttonE));
        buttons.add((Button) result.findViewById(R.id.buttonPi));
        buttons.add((Button) result.findViewById(R.id.buttonRoot));
        buttons.add((Button) result.findViewById(R.id.buttonFactorial));
        buttons.add((Button) result.findViewById(R.id.buttonLn));
        buttons.add((Button) result.findViewById(R.id.buttonLog));
        buttons.add((Button) result.findViewById(R.id.buttonAns));
        buttons.add((Button) result.findViewById(R.id.buttonExp));
        buttons.add((Button) result.findViewById(R.id.button2nd));
        buttons.add((Button) result.findViewById(R.id.buttonNega));
        buttons.add((Button) result.findViewById(R.id.buttonPercentage));
        buttons.add(deg);
        buttons.add(rad);

        InputStream file = getResources().openRawResource(R.raw.error_messages);
        ButtonClickListener listener = new ButtonClickListener(panel, history, sin, cos, tan, deg, rad, file);
        //listener.setInputStream(file);

        for (Button button : buttons) {
            if (button != null) {
                button.setOnClickListener(listener);
            }

        }


        return result;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt("position"));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("panel", panel.getText().toString());
        outState.putString("history", history.getText().toString());
    }
}