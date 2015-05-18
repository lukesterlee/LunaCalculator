package lukesterlee.c4q.nyc.lunacalculator;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * Created by Luke on 5/11/2015.
 */

public class ButtonClickListener implements View.OnClickListener {

    TextView panel;

    String ans;

    int lastCode;
    int open;
    int close;

    boolean isSinOpened;
    boolean isRadian;

    Stack<String> input;
    Stack<String> display;
    Stack<String> history;

    public ButtonClickListener(TextView panel) {
        input = new Stack<String>();
        display = new Stack<String>();
        history = new Stack<String>();

        this.panel = panel;
        ans = "";
        lastCode = 0;
        open = 0;
        close = 0;

        isRadian = false;
        isSinOpened = false;
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        switch (button.getId()) {

            // cases for numbers
            case R.id.button0 :
                handleNumbers("0");
                break;
            case R.id.button1 :
                handleNumbers("1");
                break;
            case R.id.button2 :
                handleNumbers("2");
                break;
            case R.id.button3 :
                handleNumbers("3");
                break;
            case R.id.button4 :
                handleNumbers("4");
                break;
            case R.id.button5 :
                handleNumbers("5");
                break;
            case R.id.button6 :
                handleNumbers("6");
                break;
            case R.id.button7 :
                handleNumbers("7");
                break;
            case R.id.button8 :
                handleNumbers("8");
                break;
            case R.id.button9 :
                handleNumbers("9");
                break;


            // cases for operators (+,-,*,/)
            case R.id.buttonAdd :
                handleOperators("+");
                break;
            case R.id.buttonMinus :
                handleOperators("-");
                break;
            case R.id.buttonMultiple :
                handleOperators("*");
                break;
            case R.id.buttonDivided :
                handleOperators("/");
                break;


            case R.id.buttonDot :
                handleDot();
                break;



            case R.id.buttonNega :

                break;
            case R.id.buttonParenthesis :
                handleParenthesis();
                break;



            case R.id.buttonExp :
                handleExp();
                break;
            case R.id.buttonFactorial :
                handleFactorial();
                break;




            case R.id.buttonSin :
                handleFunction("sin(", "sin(");
                break;
            case R.id.buttonCos :
                handleFunction("cos(", "cos(");
                break;
            case R.id.buttonTan :
                handleFunction("tan(", "tan(");
                break;
            case R.id.buttonLn :
                handleFunction("log(", "ln(");
                break;
            case R.id.buttonLog :
                handleFunction("log10(", "Log(");
                break;
            case R.id.buttonRoot :
                handleFunction("sqrt(", "√(");
                break;
            case R.id.buttonPi :
                handleFunction("PI", "π");
                break;
            case R.id.buttonE :
                handleFunction("e", "e");
                break;



            case R.id.buttonAns :
                break;


            case R.id.buttonDEL :
                handleDel();
                break;
            case R.id.buttonAC :
                handleAC();
                break;


            case R.id.buttonEqual :
                handleEqual();
                break;

        }

        String print = "";
        Object[] objects = display.toArray();
        for (Object object : objects) {
            print += object.toString();
        }
        panel.setText(print);
    }

    /*
     * return : 0 for empty
     *          1 for numbers
     *          2 for dot
     *          3 for operators
     *          4 for sin( cos( tan(
     *          5 for ln( log( √(  (
     *          6 for )
     *          7 for e,pi,!,%
     *          8 for ^
     */
    public int lastDetection() {

        if (input.empty()) {
            return 0;
        }

        String last = display.peek();

        if (Character.isDigit(last.charAt(0))) {
            return 1;
        } else if (last.equals(".")) {
            return 2;
        } else if (last.equals("+") || last.equals("-") || last.equals("*") || last.equals("/")) {
            return 3;
        } else if (last.equals("sin(") || last.equals("cos(") || last.equals("tan(")) {
            return 4;
        }
        // ln( , log( , √( , (
        else if (last.endsWith("(")) {
            return 5;
        } else if (last.equals(")")) {
            return 6;
        } else if (last.equals("e") || last.equals("π") || last.equals("!") || last.equals("%")) {
            return 7;
        } else if (last.equals("^")) {
            return 8;
        }


        else
            return -1;
    }


    // handling numbers.
    public void handleNumbers(String number) {

        lastCode = lastDetection();
        switch (lastCode) {

            case 0 :
                input.push(number);
                display.push(number);
                break;

            // 0~9
            case 1 :
                String newNumber = input.pop() + number;
                input.push(newNumber);
                display.pop();
                display.push(newNumber);
                break;

            // .
            case 2 :
                input.push(number);
                display.push(number);
                break;

            // + - * /
            case 3 :
                input.push(number);
                display.push(number);
                break;

            // sin( , cos( , tan(
            case 4 :
                input.push(number);
                display.push(number);
                break;

            // ln( , log( , (
            case 5 :
                input.push(number);
                display.push(number);
                break;

            // )
            case 6 :
                input.push("*");
                input.push(number);
                display.push("*");
                display.push(number);
                break;

            // e, pi, !, %
            case 7 :
                input.push("*");
                input.push(number);
                display.push("*");
                display.push(number);
                break;

            // ^
            case 8 :
                input.push(number);
                display.push(number);
                break;

        }

    }

    public void handleOperators(String operator) {

        lastCode = lastDetection();
        switch (lastCode) {

            // empty
            case 0 :
                break;

            // 0~9
            case 1 :
                input.push(operator);
                display.push(operator);
                break;

            // .
            case 2 :
                break;

            // + - * /
            case 3 :
                input.pop();
                input.push(operator);
                display.pop();
                display.push(operator);
                break;

            // sin( , cos( , tan(
            case 4 :
                break;

            // ln( , log( , (
            case 5 :
                break;

            // )
            case 6 :
                input.push(operator);
                display.push(operator);
                break;

            // e, pi, !, %
            case 7 :
                input.push(operator);
                display.push(operator);
                break;

            // ^
            case 8 :
                break;

        }


    }

    public void handleAC() {
        input.clear();
        open = 0;
        close = 0;
    }

    public void handleDel() {

        lastCode = lastDetection();
        switch (lastCode) {

            // empty
            case 0 :
                break;

            // 0~9
            case 1 :
                String delNumber = input.pop();
                display.pop();
                delNumber = delNumber.substring(0, delNumber.length()-1);
                input.push(delNumber);
                display.push(delNumber);
                break;

            // .
            case 2 :
                input.pop();
                display.pop();
                break;

            // + - * /
            case 3 :
                input.pop();
                display.pop();
                break;

            // sin( , cos( , tan(
            case 4 :
                input.pop();
                display.pop();
                open--;
                break;

            // ln( , log( , (
            case 5 :
                input.pop();
                display.pop();
                open--;
                break;

            // )
            case 6 :
                input.pop();
                display.pop();
                close--;
                break;

            // e, pi, !, %
            case 7 :
                input.pop();
                display.pop();
                break;

            // ^
            case 8 :
                input.pop();
                display.pop();
                break;

        }
    }

    public void handleDot() {
        lastCode = lastDetection();
        switch (lastCode) {

            // empty
            case 0 :
                input.push("0");
                input.push(".");
                display.push("0");
                display.push(".");
                break;

            // 0~9
            case 1 :
                input.push(".");
                display.push(".");
                break;

            // .
            case 2 :
                break;

            // + - * /
            case 3 :
                input.push("0");
                input.push(".");
                display.push("0");
                display.push(".");
                break;

            // sin( , cos( , tan(
            case 4 :
                input.push("0");
                input.push(".");
                display.push("0");
                display.push(".");
                break;

            // ln( , log( , (
            case 5 :
                input.push("0");
                input.push(".");
                display.push("0");
                display.push(".");
                break;

            // )
            case 6 :
                input.push("*");
                input.push("0");
                input.push(".");
                display.push("*");
                display.push("0");
                display.push(".");
                break;

            // e, pi, !, %
            case 7 :
                input.push("*");
                input.push("0");
                input.push(".");
                display.push("*");
                display.push("0");
                display.push(".");
                break;

            // ^
            case 8 :
                input.push("0");
                input.push(".");
                display.push("0");
                display.push(".");
                break;

        }

    }

    public void handleFunction(String symbol, String symbolDisplay) {

        lastCode = lastDetection();
        switch (lastCode) {

            // empty
            case 0 :
                input.push(symbol);
                display.push(symbolDisplay);
                break;

            // 0~9
            case 1 :
                input.push("*");
                input.push(symbol);
                display.push("*");
                display.push(symbolDisplay);
                break;

            // .
            case 2 :
                break;

            // + - * /
            case 3 :
                input.push(symbol);
                display.push(symbolDisplay);
                break;

            // sin( , cos( , tan(
            case 4 :
                input.push(symbol);
                display.push(symbolDisplay;
                break;

            // ln( , log( , (
            case 5 :
                input.push(symbol);
                display.push(symbolDisplay);
                break;

            // )
            case 6 :
                input.push("*");
                input.push(symbol);
                display.push("*");
                display.push(symbolDisplay);
                break;

            // e, pi, !, %
            case 7 :
                input.push("*");
                input.push(symbol);
                display.push("*");
                display.push(symbolDisplay);
                break;

            // ^
            case 8 :
                input.push(symbol);
                display.push(symbolDisplay);
                break;

        }
    }


    public void handleExp() {
        lastCode = lastDetection();
        switch (lastCode) {

            // empty
            case 0 :
                break;

            // 0~9
            case 1 :
                input.push("^");
                display.push("^");
                break;

            // .
            case 2 :
                break;

            // + - * /
            case 3 :
                break;

            // sin( , cos( , tan(
            case 4 :
                break;

            // ln( , log( , (
            case 5 :
                break;

            // )
            case 6 :
                input.push("^");
                display.push("^");
                break;

            // e, pi, !, %
            case 7 :
                input.push("^");
                display.push("^");
                break;

            // ^
            case 8 :
                break;

        }
    }

    public void handleParenthesis() {
        lastCode = lastDetection();
        switch (lastCode) {

            // empty
            case 0 :
                input.push("(");
                display.push("(");
                open++;
                break;

            // 0~9
            case 1 :
                input.push(")");
                display.push(")");
                close++;
                break;

            // .
            case 2 :
                break;

            // + - * /
            case 3 :
                input.push("(");
                display.push("(");
                open++;
                break;

            // sin( , cos( , tan(
            case 4 :
                input.push("(");
                display.push("(");
                open++;
                break;

            // ln( , log( , (
            case 5 :
                input.push("(");
                display.push("(");
                open++;
                break;

            // )
            case 6 :
                if (open == close) {
                    input.push("*");
                    input.push("(");
                    display.push("*");
                    display.push("(");
                    open++;
                } else if (open > close) {
                    input.push(")");
                    display.push(")");
                    close++;
                }
                break;

            // e, pi, !, %
            case 7 :
                input.push("^");
                display.push("^");
                break;

            // ^
            case 8 :
                input.push("(");
                display.push("(");
                open++;
                break;

        }
    }

    public void handleNegative() {
        String last;
        String secondLast;
        String thirdLast;

        lastCode = lastDetection();
        switch (lastCode) {

            // empty
            case 0 :
                input.push("-");
                display.push("-");
                break;

            // 0~9
            case 1 :
                last = input.pop();
                secondLast = input.pop();
                thirdLast = input.pop();
                display.pop();
                display.pop();
                display.pop();

                if (secondLast.equals(".")) {
                    input.push("(");
                    input.push("-");
                    input.push(thirdLast);
                    input.push(secondLast);
                    input.push(last);
                    display.push("(");
                    display.push("-");
                    display.push(thirdLast);
                    display.push(secondLast);
                    display.push(last);
            
                } else {
                    input.push(thirdLast);
                    input.push(secondLast);
                    input.push("(");
                    input.push("-");
                    input.push(last);
                    display.push(thirdLast);
                    display.push(secondLast);
                    display.push("(");
                    display.push("-");
                    display.push(last);

                }
                open++;
                break;

            // .
            case 2 :
                last = input.pop();
                secondLast = input.pop();
                display.pop();
                display.pop();
                input.push("-");
                display.push("-");
                input.push(secondLast);
                display.push(secondLast);
                input.push(last);
                display.push(last);
                break;

            // + - * /
            case 3 :
                input.push("(");
                display.push("(");
                input.push("-");
                display.push("-");
                open++;
                break;

            // sin( , cos( , tan(
            case 4 :
                input.push("-");
                display.push("-");
                break;

            // ln( , log( , (
            case 5 :
                input.push("-");
                display.push("-");
                break;

            // )
            case 6 :
                input.push("*");
                display.push("*");
                input.push("(");
                display.push("(");
                input.push("-");
                display.push("-");
                break;

            // e, pi, !, %
            case 7 :
                input.push("^");
                display.push("^");
                break;

            // ^
            case 8 :
                break;

        }
    }


    public String factorial(String number) {
        BigDecimal number
    }

    public void handleFactorial() {

        lastCode = lastDetection();
        switch (lastCode) {

            // empty
            case 0 :
                break;

            // 0~9
            case 1 :

                break;

            // .
            case 2 :
                break;

            // + - * /
            case 3 :
                input.push(symbol);
                display.push(symbolDisplay);
                break;

            // sin( , cos( , tan(
            case 4 :
                input.push(symbol);
                display.push(symbolDisplay;
                break;

            // ln( , log( , (
            case 5 :
                input.push(symbol);
                display.push(symbolDisplay);
                break;

            // )
            case 6 :
                input.push("*");
                input.push(symbol);
                display.push("*");
                display.push(symbolDisplay);
                break;

            // e, pi, !, %
            case 7 :
                input.push("*");
                input.push(symbol);
                display.push("*");
                display.push(symbolDisplay);
                break;

            // ^
            case 8 :
                input.push(symbol);
                display.push(symbolDisplay);
                break;

        }
    }

    public void handleEqual() {
        // test if the user misses parenthesis.
        while (open > close) {
            input += ")";
            close++;
        }

        // if the test is passed
        submit(input);
    }

    public void submit(String input) {
        Expression expression = new Expression(input);
        try {
            BigDecimal parse = expression.eval();
            ans = parse.toPlainString();
            display = ans;
            input = "";
        } catch(Exception e) {
            display = "syntax error";
        }
    }

}
