package lukesterlee.c4q.nyc.lunacalculator;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Luke on 5/11/2015.
 */

public class ButtonClickListener implements View.OnClickListener {

    TextView textView;
    String input;

    public ButtonClickListener(TextView textView) {
        this.textView = textView;
        input = "";

    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        switch (button.getId()) {
            case R.id.button0 :
                input += "0";
                break;
            case R.id.button1 :
                input += "1";
                break;
            case R.id.button2 :
                input += "2";
                break;
            case R.id.button3 :
                input += "3";
                break;
            case R.id.button4 :
                input += "4";
                break;
            case R.id.button5 :
                input += "5";
                break;
            case R.id.button6 :
                input += "6";
                break;
            case R.id.button7 :
                input += "7";
                break;
            case R.id.button8 :
                input += "8";
                break;
            case R.id.button9 :
                input += "9";
                break;
            case R.id.buttonDot :
                if(Parse.isNumber(input))
                    input += ".";
                break;
            case R.id.buttonDEL :
                // This prevents the app from crashing when the user press DEL button when there is nothing.
                if (input.length() != 0) {
                    input = input.substring(0, input.length()-1);
                }
                break;
            case R.id.buttonAC :
                input = "";
                break;
            case R.id.buttonAdd :
                input = Parse.addOperation(input, "+");
                break;
            case R.id.buttonMinus :
                input = Parse.addOperation(input, "-");
                break;
            case R.id.buttonMultiple :
                input = Parse.addOperation(input, "*");
                break;
            case R.id.buttonDivided :
                input = Parse.addOperation(input, "/");
                break;
            case R.id.buttonEqual :
                input = Parse.parse(input);
                break;
        }
        textView.setText(input);
    }
}
