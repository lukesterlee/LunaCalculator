package lukesterlee.c4q.nyc.lunacalculator;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * Created by Luke on 5/11/2015.
 */

public class ButtonClickListener implements View.OnClickListener {

    TextView panelHistory;
    TextView panel;

    String print;
    String ans;

    inputType lastCode;
    int open;
    int close;

    boolean is2ndOn;
    boolean isRadian;

    Button sin;
    Button cos;
    Button tan;

    Stack<String> input;
    Stack<String> display;
    String history;

    public ButtonClickListener(TextView panel, TextView panelHistory, Button sin, Button cos, Button tan) {
        input = new Stack<String>();
        display = new Stack<String>();
        history = "";

        this.sin = sin;
        this.cos = cos;
        this.tan = tan;

        this.panel = panel;
        this.panelHistory = panelHistory;

        print = "";
        ans = "0";
        open = 0;
        close = 0;

        is2ndOn = false;
        isRadian = false;
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
                handleNegative();
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
                if (is2ndOn) {
                    handleFunction("asin(", "asin(", true);
                } else {
                    handleFunction("sin(", "sin(", true);
                }
                break;
            case R.id.buttonCos :
                if (is2ndOn) {
                    handleFunction("acos(", "acos(", true);
                } else {
                    handleFunction("cos(", "cos(", true);
                }
                break;
            case R.id.buttonTan :
                if (is2ndOn) {
                    handleFunction("atan(", "atan(", true);
                } else {
                    handleFunction("tan(", "tan(", true);
                }
                break;
            case R.id.buttonLn :
                handleFunction("log(", "ln(", true);
                break;
            case R.id.buttonLog :
                handleFunction("log10(", "Log(", true);
                break;
            case R.id.buttonRoot :
                handleFunction("sqrt(", "√(", true);
                break;
            case R.id.buttonPi :
                handleFunction("PI", "π", false);
                break;
            case R.id.buttonE :
                handleFunction("e", "e", false);
                break;



            case R.id.button2nd :
                if (is2ndOn) {
                    is2ndOn = false;
                    sin.setText("sin");
                    cos.setText("cos");
                    tan.setText("tan");
                } else {
                    is2ndOn = true;
                    sin.setText("asin");
                    cos.setText("acos");
                    tan.setText("atan");
                }

                break;
            case R.id.buttonAns :
                handleAns();
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



        print = stackToString(display);
        panel.setText(print);
    }

    public String stackToString(Stack<String> stack) {
        String print = "";
        Object[] objects = stack.toArray();
        for (Object object : objects) {
            print += object.toString();
        }
        return print;
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
    public inputType lastDetection() throws Exception {

        if (display.empty()) {
            return inputType.EMPTY;
        }

        String last = display.peek();
        if (Character.isDigit(last.charAt(0))) {
            return inputType.DIGIT;
        } else if (last.equals(".")) {
            return inputType.DOT;
        } else if (last.equals("+") || last.equals("-") || last.equals("*") || last.equals("/")) {
            return inputType.OPERATOR;
        } else if (last.equals("sin(") || last.equals("cos(") || last.equals("tan(") || last.equals("asin(") || last.equals("acos(") || last.equals("atan(")) {
            return inputType.TRIG;
        }
        // ln( , log( , √( , (
        else if (last.endsWith("(")) {
            return inputType.LPARENT;
        } else if (last.equals(")")) {
            return inputType.RPARENT;
        } else if (last.equals("e") || last.equals("π") )
         {
            return inputType.CONSTANT;
        } else if(last.equals("!") || last.equals("%")) {
            return inputType.UNARY;
        }
        else if (last.equals("^")) {
            return inputType.EXP;
        } else if (last.equals("syntax error")) {
            display.clear();
            return inputType.EMPTY;
        }
        throw new Exception("did not recognize the input");
    }

    private enum inputType {
        EMPTY,
        DIGIT,
        DOT,
        OPERATOR,
        TRIG,
        LPARENT,
        RPARENT,
        CONSTANT,
        UNARY,
        EXP,
    }

    public void add(String first) {
        input.add(first);
        display.add(first);
    }

    public void add(String first, String second) {
        add(first);
        add(second);
    }

    public void add(String first, String second, String third) {
        add(first, second);
        add(third);
    }

    public void concatenate(String number) {
        String newNumber = input.pop() + number;
        input.push(newNumber);
        display.pop();
        display.push(newNumber);
    }


    // handling numbers.
    public void handleNumbers(String number) {

        lastCode = lastDetection();
        switch (lastCode) {

            case EMPTY:
                add(number);
                break;

            // 0~9
            case DIGIT :
                if (!input.empty()) {
                    concatenate(number);
                } else {
                    display.clear();
                    add(number);
                }
                break;

            // .
            case 2 :
                add(number);
                break;

            // + - * /
            case 3 :
                add(number);
                break;

            // sin( , cos( , tan(
            case 4 :
                add(number);
                break;

            // ln( , log( , (
            case 5 :
                add(number);
                break;

            // )
            case 6 :
                add("*", number);
                break;

            // e, pi, !, %
            case 7 :
                add("*", number);
                break;

            // ^
            case 8 :
                add(number);
                break;
        }
    }

    public void handleOperators(String operator) {

        lastCode = lastDetection();
        switch (lastCode) {

            // empty
            case 0 :
                input.push(ans);
                display.push("Ans");
                add(operator);
                break;

            // 0~9
            case 1 :
                if (input.empty()) {
                    input.push(display.peek());
                }
                add(operator);
                break;

            // .
            case 2 :
                break;

            // + - * /
            case 3 :
                input.pop();
                display.pop();
                add(operator);
                break;

            // sin( , cos( , tan(
            case 4 :
                break;

            // ln( , log( , (
            case 5 :
                break;

            // )
            case 6 :
                add(operator);
                break;

            // e, pi, !, %
            case 7 :
                add(operator);
                break;

            // ^
            case 8 :
                break;

        }
    }

    public void handleAC() {
        input.clear();
        display.clear();
        print = "";
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
                if (input.empty()) {
                    input.push(display.peek());
                }
                String delNumber = input.pop();
                display.pop();
                if(delNumber.length() != 1) {
                    delNumber = delNumber.substring(0, delNumber.length()-1);
                    input.push(delNumber);
                    display.push(delNumber);
                }
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
                add("0", ".");
                break;

            // 0~9
            case 1 :
                if (input.empty()) {
                    display.pop();
                    add("0", ".");
                } else {
                    add(".");
                }

                break;

            // .
            case 2 :
                break;

            // + - * /
            case 3 :
                add("0", ".");
                break;

            // sin( , cos( , tan(
            case 4 :
                add("0", ".");
                break;

            // ln( , log( , (
            case 5 :
                add("0", ".");
                break;

            // )
            case 6 :
                add("*", "0", ".");
                break;

            // e, pi, !, %
            case 7 :
                add("*", "0", ".");
                break;

            // ^
            case 8 :
                add("0", ".");
                break;

        }

    }

    public void handleFunction(String symbol, String symbolDisplay, boolean isParenthesis) {



        lastCode = lastDetection();
        switch (lastCode) {

            // empty
            case 0 :
                input.push(symbol);
                display.push(symbolDisplay);
                if (isParenthesis)
                    open++;
                break;

            // 0~9
            case 1 :
                if (input.empty()) {
                    input.push(display.peek());
                }
                input.push("*");
                input.push(symbol);
                display.push("*");
                display.push(symbolDisplay);
                if (isParenthesis)
                    open++;
                break;

            // .
            case 2 :
                break;

            // + - * /
            case 3 :
                input.push(symbol);
                display.push(symbolDisplay);
                if (isParenthesis)
                    open++;
                break;

            // sin( , cos( , tan(
            case 4 :
                input.push(symbol);
                display.push(symbolDisplay);
                if (isParenthesis)
                    open++;
                break;

            // ln( , log( , (
            case 5 :
                input.push(symbol);
                display.push(symbolDisplay);
                if (isParenthesis)
                    open++;
                break;

            // )
            case 6 :
                input.push("*");
                input.push(symbol);
                display.push("*");
                display.push(symbolDisplay);
                if (isParenthesis)
                    open++;
                break;

            // e, pi, !, %
            case 7 :
                input.push("*");
                input.push(symbol);
                display.push("*");
                display.push(symbolDisplay);
                if (isParenthesis)
                    open++;
                break;

            // ^
            case 8 :
                input.push(symbol);
                display.push(symbolDisplay);
                if (isParenthesis)
                    open++;
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
                if (input.empty()) {
                    input.push(display.peek());
                }
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
                if (input.empty()) {
                    display.clear();
                    input.push("(");
                    display.push("(");
                    open++;
                } else if (open > close) {
                    input.push(")");
                    display.push(")");
                    close++;
                } else {
                    input.push("*");
                    display.push("*");
                    input.push("(");
                    display.push("(");
                    open++;
                }
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
                input.push(")");
                display.push(")");
                close--;
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
                if (input.empty()) {
                    input.push("-");
                    input.push(display.pop());
                    display.push("-");
                    display.push(input.peek());
                } else {
                    if (input.size() == 1) {
                        last = input.pop();
                        input.push("-");
                        input.push(last);
                        display.pop();
                        display.push("-");
                        display.push(last);
                    } else {
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
                    }

                }

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
                add("(", "-");
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
                add("*", "(", "-");
                break;

            // e, pi, !, %
            case 7 :
                add("*", "(", "-");
                break;

            // ^
            case 8 :
                break;

        }
    }


    public long factorial(long number) {
        if (number == 1 || number == 0) {
            return 1;
        }
        return factorial(number-1) * number;
    }

    public void handleFactorial() {

        String last;
        String secondToLast;

        lastCode = lastDetection();
        switch (lastCode) {

            // empty
            case 0 :
                break;

            // 0~9
            case 1 :
                // handle the case of 0.45

                if (input.empty()) {
                    input.push(display.peek());
                }

                int size = input.size();

                if (size == 1) {
                    last = input.pop();
                    display.pop();
                    input.push(Long.toString(factorial(Long.parseLong(last))));
                    display.push(last + "!");
                } else {
                    last = input.pop();
                    secondToLast = input.pop();
                    if (secondToLast.equals(".")) {
                        input.push(secondToLast);
                        input.push(last);
                    } else {
                        input.push(secondToLast);
                        display.pop();
                        input.push(Long.toString(factorial(Long.parseLong(last))));
                        display.push(last + "!");
                    }
                }


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

                break;

            // e, pi, !, %
            case 7 :
                break;

            // ^
            case 8 :
                break;

        }
    }

    public void handleAns() {
        lastCode = lastDetection();
        switch (lastCode) {

            // empty
            case 0 :
                input.push(ans);
                display.push("Ans");
                break;

            // 0~9
            case 1 :
                input.push("*");
                display.push("*");
                input.push(ans);
                display.push("Ans");
                break;

            // .
            case 2 :
                break;

            // + - * /
            case 3 :
                input.push(ans);
                display.push("Ans");
                break;

            // sin( , cos( , tan(
            case 4 :
                input.push(ans);
                display.push("Ans");
                break;

            // ln( , log( , (
            case 5 :
                input.push(ans);
                display.push("Ans");
                break;

            // )
            case 6 :
                input.push("*");
                display.push("*");
                input.push(ans);
                display.push("Ans");
                break;

            // e, pi, !, %
            case 7 :
                input.push("*");
                display.push("*");
                input.push(ans);
                display.push("Ans");
                break;

            // ^
            case 8 :
                input.push(ans);
                display.push("Ans");
                break;

        }
    }

    public void handleEqual() {
        // test if the user misses parenthesis.
        while (open > close) {
            input.push(")");
            display.push(")");
            close++;
        }

        // if the test is passed

        submit(stackToString(input));
    }

    public void submit(String inputExpression) {
        Expression expression = new Expression(inputExpression);
        try {
            BigDecimal parse = expression.eval();
            ans = parse.toPlainString();
            history = stackToString(display) + " = " + ans;
            display.clear();
            input.clear();
            display.push(ans);
            if (panelHistory != null) {
                panelHistory.setText(history);
            }

        } catch(Exception e) {
            input.clear();
            display.clear();
            display.push("syntax error");
        }
    }

}
