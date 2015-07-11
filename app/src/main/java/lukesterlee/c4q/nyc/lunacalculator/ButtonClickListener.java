package lukesterlee.c4q.nyc.lunacalculator;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

/**
 * Created by Luke on 5/11/2015.
 */

public class ButtonClickListener implements View.OnClickListener {


    HashMap<String, MediaPlayer> error;

    Context activity;

    MediaPlayer m1;
    MediaPlayer m2;
    MediaPlayer m3;
    MediaPlayer m4;

    final String errorMessage1 = "seriously, bro?";
    final String errorMessage2 = "come on, you are better than this";
    final String errorMessage3 = "you are embarrassing yourself";
    final String errorMessage4 = "hell no";

    GraphCallbacks mFragment;



    TextView panelHistory;
    TextView panel;

    String print;
    String ans;
    String randomMessage;
    String formula;

    lastInputType lastCode;
    int open;
    int close;

    boolean is2ndOn;
    boolean isRadian;

    boolean isInputXOn;

    Button sin;
    Button cos;
    Button tan;
    Button deg;
    Button rad;
    Button buttonFactorial;
    Button exp;
    Button buttonPercentage;
    Button equal;


    Stack<String> expression;
    Stack<String> display;
    String history;

    public ButtonClickListener(TextView panel, TextView panelHistory, GraphCallbacks mFragment, String ans, Context activity) {

        this.activity = activity;

        expression = new Stack<String>();
        display = new Stack<String>();
        history = "";

        this.mFragment = mFragment;

        this.panel = panel;
        this.panelHistory = panelHistory;

        randomMessage = "";
        formula = "";
        print = "";
        this.ans = ans;
        ans = "0";
        open = 0;
        close = 0;


        is2ndOn = false;
        isRadian = false;
        isInputXOn = false;

        error = new HashMap<String, MediaPlayer>();

    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        switch (button.getId()) {
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

            case R.id.buttonPercentage :
                if (is2ndOn) {
                    if (isInputXOn) {
                        add(",");
                    } else {
                        xInputMode("draw", "Enter a formula and x's minimum and maximum values separated by commas : ");
                    }

                } else {
                    handlePercentage();
                }
                break;


            case R.id.buttonFactorial :
                if (is2ndOn) {
                    if (isInputXOn) {
                        handleFunction("x", "x", false);
                    } else {
                        xInputMode("Eval", "Enter a formula and a value separated by comma : ");
                    }


                } else {
                    handleFactorial();

                }

                break;

            case R.id.buttonSin :
                if (is2ndOn) {
                    if (isRadian) {
                        handleFunction("asinrad(", "asin(", true);

                    } else {
                        handleFunction("asin(", "asin(", true);
                    }
                }
                else {
                    if (isRadian) {
                        handleFunction("sinrad(", "sin(", true);

                    } else {
                        handleFunction("sin(", "sin(", true);
                    }
                }
                break;
            case R.id.buttonCos :
                if (is2ndOn) {
                    if (isRadian) {
                        handleFunction("acosrad(", "acos(", true);

                    } else {
                        handleFunction("acos(", "acos(", true);
                    }
                }
                else {
                    if (isRadian) {
                        handleFunction("cosrad(", "cos(", true);

                    } else {
                        handleFunction("cos(", "cos(", true);
                    }
                }
                break;
            case R.id.buttonTan :
                if (is2ndOn) {
                    if (isRadian) {
                        handleFunction("atanrad(", "atan(", true);

                    } else {
                        handleFunction("atan(", "atan(", true);
                    }
                }
                else {
                    if (isRadian) {
                        handleFunction("tanrad(", "tan(", true);

                    } else {
                        handleFunction("tan(", "tan(", true);
                    }
                }
                break;
            case R.id.buttonLn :
                handleFunction("log(", "ln(", true);
                break;
            case R.id.buttonLog :
                handleFunction("log10(", "Log(", true);
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
                    buttonFactorial.setText("!");
                    buttonPercentage.setText("%");

                } else {
                    is2ndOn = true;
                    sin.setText("asin");
                    cos.setText("acos");
                    tan.setText("atan");
                    if (isInputXOn) {
                        buttonFactorial.setText("x");
                        buttonPercentage.setText(",");
                    } else {
                        buttonFactorial.setText("Eval");
                        buttonPercentage.setText("Draw");
                    }


                }
                break;

            case R.id.buttonDegree :
                isRadian = false;
                deg.setBackgroundResource(R.drawable.button_green2);
                deg.setTextColor(Color.parseColor("#FFFFFF"));
                rad.setBackgroundResource(R.drawable.button_gray);
                rad.setTextColor(Color.parseColor("#D7D7D7"));
                break;
            case R.id.buttonRadian :
                isRadian = true;
                rad.setBackgroundResource(R.drawable.button_green2);
                rad.setTextColor(Color.parseColor("#FFFFFF"));
                deg.setBackgroundResource(R.drawable.button_gray);
                deg.setTextColor(Color.parseColor("#D7D7D7"));
                break;

            case R.id.buttonAns :
                handleAns();
                break;

            case R.id.buttonDEL :
                handleDel();
                break;
            case R.id.buttonAC :
                allClear();
                break;

            case R.id.buttonRoot :
                handleFunction("sqrt(", "√(", true);
                break;


            case R.id.buttonExp :

                handleExp();
                break;

            case R.id.buttonEqual :
                if (equal.getText().toString().equals("=")) {
                    handleEqual();
                } else if (equal.getText().toString().equalsIgnoreCase("eval")) {
                    handleEval();
                } else if (equal.getText().toString().equalsIgnoreCase("draw")) {
                    handleGraph();
                }


                break;

        }
        print = stackToString(display);
        panel.setText(print);
    }

    private enum lastInputType {
        EMPTY,
        DIGIT,
        DOT,
        OPERATOR,
        LPARENT,
        RPARENT,
        CONSTANT_UNARY,
        EXP,
        COMMA,
        ERROR
    }

    public String stackToString(Stack<String> stack) {
        String print = "";
        Object[] objects = stack.toArray();
        for (Object object : objects) {
            print += object.toString();
        }
        return print;
    }

    public lastInputType lastDetection() {
        if (display.empty()) {
            return lastInputType.EMPTY;
        }
        String last = display.peek();
        if (Character.isDigit(last.charAt(0))) {
            return lastInputType.DIGIT;
        } else if (last.length() > 1 && last.startsWith("-")) {
            return lastInputType.DIGIT;
        } else if (last.equals(".")) {
            return lastInputType.DOT;
        } else if (last.equals("+") || last.equals("-") || last.equals("*") || last.equals("/")) {
            return lastInputType.OPERATOR;
        } else if (last.endsWith("(")) {
            return lastInputType.LPARENT;
        } else if (last.equals(")")) {
            return lastInputType.RPARENT;
        } else if (last.equals("e") || last.equals("π") || last.equals("!") || last.equals("%") || last.equals("x")) {
            return lastInputType.CONSTANT_UNARY;
        } else if (last.equals("^")) {
            return lastInputType.EXP;
        } else if (last.equals(",")) {
            return lastInputType.COMMA;
        } else if (last.equals(randomMessage)) {
            display.clear();
            return lastInputType.EMPTY;
        }
        return lastInputType.ERROR;
    }

    public void add(String input) {
        expression.add(input);
        display.add(input);
    }

    public void add(String first, String second) {
        add(first);
        add(second);
    }

    public void add(String first, String second, String third) {
        add(first, second);
        add(third);
    }

    public void delete() {
        expression.pop();
        display.pop();
    }

    public void concatenate(String number) {
        String newNumber = expression.pop() + number;
        expression.push(newNumber);
        display.pop();
        display.push(newNumber);
    }

    public void handleNumbers(String number) {
        lastCode = lastDetection();
        switch (lastCode) {
            case DIGIT:
                if (!expression.empty())
                    concatenate(number);
                else {
                    display.clear();
                    add(number);
                }
                break;
            case RPARENT: case CONSTANT_UNARY:
                add("*", number);
                break;
            default:
                add(number);
        }
    }

    public void handleOperators(String operator) {
        lastCode = lastDetection();
        switch (lastCode) {
            case EMPTY:
                expression.push(ans);
                display.push("Ans");
                add(operator);
                break;
            case DIGIT:
                if (expression.empty())
                    expression.push(display.peek());
                add(operator);
                break;
            case OPERATOR:
                delete();
                add(operator);
                break;
            case RPARENT: case CONSTANT_UNARY:
                add(operator);
                break;
        }
    }

    public void allClear() {
        expression.clear();
        display.clear();
        open = 0;
        close = 0;
        print = "";
        history = "";
        panelHistory.setText("");
        ans = "0";
        if (buttonFactorial != null) {
            clear2nd();
        }

        equal.setBackgroundResource(R.drawable.button_green2);
        equal.setText("=");

    }


    public void handleDel() {
        lastCode = lastDetection();
        switch (lastCode) {
            case DIGIT:
                if (expression.empty())
                    expression.push(display.peek());
                String delNumber = expression.pop();
                display.pop();
                if(delNumber.length() != 1) {
                    delNumber = delNumber.substring(0, delNumber.length()-1);
                    expression.push(delNumber);
                    display.push(delNumber);
                }
                break;
            case DOT: case OPERATOR: case CONSTANT_UNARY: case EXP:
                delete();
                break;
            case LPARENT:
                delete();
                open--;
                break;
            case RPARENT:
                delete();
                close--;
                break;
        }
    }

    public void handleDot() {
        lastCode = lastDetection();
        switch (lastCode) {
            case DIGIT:
                if (expression.empty()) {
                    display.pop();
                    add("0", ".");
                } else
                    add(".");
                break;
            case OPERATOR: case LPARENT: case EMPTY: case EXP:
                add("0", ".");
                break;
            case RPARENT: case CONSTANT_UNARY:
                add("*", "0", ".");
                break;
        }
    }

    public void handleFunction(String symbol, String symbolDisplay, boolean isParenthesis) {
        lastCode = lastDetection();
        switch (lastCode) {
            case DIGIT:
                if (expression.empty()) {
                    expression.push(display.peek());
                }
                expression.push("*");
                //display.push("Ans");
                display.push("*");
                expression.push(symbol);
                display.push(symbolDisplay);
                if (isParenthesis)
                    open++;
                break;
            case OPERATOR: case LPARENT: case EXP: case EMPTY:
                expression.push(symbol);
                display.push(symbolDisplay);
                if (isParenthesis)
                    open++;
                break;
            case RPARENT: case CONSTANT_UNARY:
                add("*");
                expression.push(symbol);
                display.push(symbolDisplay);
                if (isParenthesis)
                    open++;
                break;
        }
    }

    public void handleExp() {
        lastCode = lastDetection();
        switch (lastCode) {
            case EMPTY:
                expression.push(ans);
                display.push("Ans");
                add("^");
                break;
            case DIGIT:
                if (expression.empty())
                    expression.push(display.peek());
                add("^");
                break;
            case RPARENT: case CONSTANT_UNARY:
                add("^");
                break;
        }
    }

    public void handlePercentage() {

        String last;
        String secondToLast;
        String thirdToLast;

        lastCode = lastDetection();
        switch (lastCode) {
            case EMPTY:
                expression.push("0");
                display.push("0%");
                break;
            case DIGIT:
                if (expression.empty()) {
                    expression.push(display.peek());
                    expression.push("*");
                    expression.push("0");
                    expression.push(".");
                    expression.push("01");
                }
                else if (expression.size() >= 3) {
                    last = expression.pop();
                    secondToLast = expression.pop();
                    thirdToLast = expression.pop();

                    if (Character.isDigit(thirdToLast.charAt(0)) && secondToLast.equals("+")) {
                        expression.push(thirdToLast + "*(1+" + last + "*0.01)");

                    } else if (Character.isDigit(thirdToLast.charAt(0)) && secondToLast.equals("-")) {
                        expression.push(thirdToLast + "*(1-" + last + "*0.01)");
                    } else if (Character.isDigit(thirdToLast.charAt(0)) && secondToLast.equals("*")) {
                        expression.push(thirdToLast + "*" + last + "*0.01");
                    } else if (Character.isDigit(thirdToLast.charAt(0)) && secondToLast.equals("/")) {
                        expression.push(thirdToLast + "/" + "(" + last + "*0.01)");
                    }
                } else {
                    expression.push("*");
                    expression.push("0");
                    expression.push(".");
                    expression.push("01");

                }
                display.push("%");

                break;
            case RPARENT: case CONSTANT_UNARY:
                expression.push("*");
                expression.push("0");
                expression.push(".");
                expression.push("01");
                display.push("%");
                break;
        }
    }

    public void handleParenthesis() {
        lastCode = lastDetection();
        switch (lastCode) {
            case DIGIT:
                if (expression.empty()) {
                    display.clear();
                    add("(");
                    open++;
                } else if (open > close) {
                    add(")");
                    close++;
                } else {
                    add("*", "(");
                    open++;
                }
                break;
            case OPERATOR: case LPARENT: case EMPTY:
                add("(");
                open++;
                break;
            case RPARENT:
                if (open == close) {
                    add("*", "(");
                    open++;
                } else if (open > close) {
                    add(")");
                    close++;
                }
                break;
            case CONSTANT_UNARY:
                if (open > close)
                    add(")");
                else
                    add("*", "(");
                close++;
                break;
            case EXP:
                add("(");
                open++;
                break;
        }
    }

    public void insertBeforeLast() {
        String last = expression.pop();
        display.pop();
        add("-", last);
    }

    public void insertPlusBeforeLast() {
        String last = expression.pop();
        display.pop();
        add("+", last);
    }


    public void insertBeforeThirdToLast() {
        String last = expression.pop();
        String secondToLast = expression.pop();
        String thirdToLast = expression.pop();
        display.pop();
        display.pop();
        display.pop();
        add("-");
        add(thirdToLast, secondToLast, last);
    }

    public void replaceBeforeLast() {
        String last = expression.pop();
        String secondToLast = expression.pop();
        display.pop();
        display.pop();
        if (secondToLast.equals("+")) {
            add("-", last);
        } else if (secondToLast.equals("-")) {
            add("+", last);
        }
    }

    public void replaceBeforeThirdToLast() {
        String last = expression.pop();
        String secondToLast = expression.pop();
        String thirdToLast = expression.pop();
        String fourthToLast = expression.pop();
        display.pop();
        display.pop();
        display.pop();
        display.pop();
        if (fourthToLast.equals("+")) {
            add("-");
        } else if (fourthToLast.equals("-")) {
            add("+");
        }
        add(thirdToLast, secondToLast, last);
    }

    public void handleNegative() {
        String last;
        String secondLast;
        String thirdLast;
        lastCode = lastDetection();

        switch (lastCode) {
            case DIGIT:
                if (expression.empty()) {
                    expression.push(display.peek());
                }
                int size = expression.size();
                switch (size) {

                    case 1 :
                        if (expression.peek().contains("-")) {
                            last = expression.pop().substring(1);
                            expression.push(last);
                            display.pop();
                            display.push(last);
                            insertPlusBeforeLast();
                        } else {
                            insertBeforeLast();
                        }

                        break;
                    case 2 :
                        last = expression.pop();
                        secondLast = expression.peek();
                        expression.push(secondLast);
                        expression.push(last);
                        if (secondLast.equals("-")) {
                            replaceBeforeLast();
                        } else if (secondLast.equals("(")) {
                            insertBeforeLast();
                        }
                        break;
                    case 3:
                        last = expression.pop();
                        secondLast = expression.pop();
                        expression.push(secondLast);
                        expression.push(last);
                        if (secondLast.equals(".")) {
                            insertBeforeThirdToLast();
                        } else {
                            replaceBeforeLast();
                        }
                        break;
                    default:
                        last = expression.pop();
                        secondLast = expression.pop();
                        expression.push(secondLast);
                        expression.push(last);
                        if (secondLast.equals(".")) {
                            replaceBeforeThirdToLast();
                        } else {
                            replaceBeforeLast();
                        }

                }

                break;
            case DOT:
                last = expression.pop();
                secondLast = expression.pop();
                display.pop();
                display.pop();
                add("-", secondLast, last);
                break;
            case OPERATOR: case EXP:
                add("(", "-");
                open++;
                break;
            case LPARENT: case EMPTY: case COMMA:
                add("-");
                break;
            case RPARENT: case CONSTANT_UNARY:
                add("*", "(", "-");
                open++;
                break;
        }
    }

    public long factorial(long number) {
        if (number == 1 || number == 0)
            return 1;
        return factorial(number-1) * number;
    }

    public void handleFactorial() {
        String last;
        String secondToLast;
        lastCode = lastDetection();
        switch (lastCode) {
            case EMPTY:
//                expression.push(Long.toString(factorial(Long.parseLong(ans))));
//                display.push("Ans");
//                display.push("!");
                break;
            case DIGIT:
                // handle the case of 0.45
                if (expression.empty()) {
                    expression.push(display.peek());
                }
                int size = expression.size();
                if (size == 1) {
                    last = expression.pop();
                    display.pop();
                    expression.push(Long.toString(factorial(Long.parseLong(last))));
                    display.push(last + "!");
                } else {
                    last = expression.pop();
                    secondToLast = expression.pop();
                    if (secondToLast.equals(".")) {
                        expression.push(secondToLast);
                        expression.push(last);
                    } else {
                        expression.push(secondToLast);
                        display.pop();
                        expression.push(Long.toString(factorial(Long.parseLong(last))));
                        display.push(last + "!");
                    }
                }
                break;
            case RPARENT:
                break;
            case CONSTANT_UNARY:
                break;
        }
    }

    public void handleAns() {
        lastCode = lastDetection();
        switch (lastCode) {
            case DIGIT:
                if (expression.empty())
                    expression.push(ans);
                add("*");
                expression.push(ans);
                display.push("Ans");
                break;
            case RPARENT: case CONSTANT_UNARY:
                add("*");
                expression.push(ans);
                display.push("Ans");
                break;
            default:
                expression.push(ans);
                display.push("Ans");
        }
    }

    public void handleEqual() {

        lastCode = lastDetection();

        // it the user didn't put anything, or end with operators, equal button is disabled.
        if (!expression.empty() && lastCode != lastInputType.OPERATOR) {
            // test if the user misses parenthesis.
            while (open > close) {
                expression.push(")");
                display.push(")");
                close++;
            }

            formula = stackToString(expression);
            submit(formula);


        }

    }


    public void handleEval() {

        buttonFactorial.setText("!");
        buttonFactorial.setBackgroundResource(R.drawable.button_lightgray);
        buttonFactorial.setTextColor(Color.parseColor("#000000"));

        buttonPercentage.setText("%");
        buttonPercentage.setBackgroundResource(R.drawable.button_lightgray);
        buttonPercentage.setTextColor(Color.parseColor("#000000"));

        equal.setBackgroundResource(R.drawable.button_green2);
        equal.setText("=");

        isInputXOn = false;

        String eval = stackToString(expression);
        int index = eval.indexOf(",");
        formula = eval.substring(0, index);
        String value = eval.substring(index+1);

        Expression express = new Expression(formula);
        try {
            BigDecimal answer = express.with("x", new BigDecimal(value)).eval();

            allClear();
            String result = answer.toPlainString();
            ans = result;
            history = formula + " = " + result;
            display.push(ans);

            if (panelHistory != null) {
                panelHistory.setText(history);
            }
        } catch(Exception e) {
            allClear();
            randomMessage = "can't evaluate";
            display.push(randomMessage);
        }









    }

    public void submit(String inputExpression)  {
        Expression express = new Expression(inputExpression);
        try {
            BigDecimal answer = express.eval();

            // this is for sin(0), sin(180), cos(90), tan(180) because java Math.sin sucks.
//            BigDecimal sinCheck = new Expression(inputExpression + "< 0.00000000000001").eval();
//            if (sinCheck.toPlainString().equals("1")) {
//                answer = new BigDecimal("0");
//            }
            if (answer.toPlainString().startsWith("0.00000000000000")) {
                answer = new BigDecimal("0");
            }

            ans = answer.toPlainString();
            history = stackToString(display) + " = " + ans;
            display.clear();
            expression.clear();
            open = 0;
            close = 0;
            display.push(ans);
            if (panelHistory != null) {
                panelHistory.setText(history);
            }
        } catch(Exception e) {
            allClear();
                // print error here
            randomErrorPlay();


        }
    }


    public void randomErrorPlay() {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(errorMessage1);
        lines.add(errorMessage2);
        lines.add(errorMessage3);
        lines.add(errorMessage4);
        Random random = new Random();
        int index = random.nextInt(lines.size());

        randomMessage = lines.get(index);

        error = new HashMap<String, MediaPlayer>();

        m1 = MediaPlayer.create(activity, R.raw.seriously);
        m2 = MediaPlayer.create(activity, R.raw.come_on);
        m3 = MediaPlayer.create(activity, R.raw.embarrassing);
        m4 = MediaPlayer.create(activity, R.raw.hell_no);

        error.put(errorMessage1, m1);
        error.put(errorMessage2, m2);
        error.put(errorMessage3, m3);
        error.put(errorMessage4, m4);

        display.push(randomMessage);

        MediaPlayer current = null;
        current = error.get(randomMessage);
        current.start();

    }

    public String randomErrorMessage() throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(errorMessage1);
        lines.add(errorMessage2);
        lines.add(errorMessage3);
        lines.add(errorMessage4);
        Random random = new Random();
        int index = random.nextInt(lines.size());
        return  lines.get(index);
    }


    public void handleGraph() {


        String[] line = stackToString(expression).split(",");
        
        formula = line[0];
        int min = Integer.parseInt(line[1]);
        int max = Integer.parseInt(line[2]);



        allClear();
        print = formula;
        panelHistory.setText("y = " + print);
        mFragment.graphButtonClicked(formula, min, max);


        equal.setBackgroundResource(R.drawable.button_green2);
        equal.setText("=");

        isInputXOn = false;
    }

    public void xInputMode(String text, String message) {
        isInputXOn = true;

        buttonFactorial.setText("x");
        //buttonFactorial.setBackgroundResource(R.drawable.button_red);
        //buttonFactorial.setTextColor(Color.parseColor("#FFFFFF"));

        buttonPercentage.setText(",");
        //buttonPercentage.setBackgroundResource(R.drawable.button_red);
        //buttonPercentage.setTextColor(Color.parseColor("#FFFFFF"));

        equal.setText(text);
        equal.setBackgroundResource(R.drawable.button_red);
        //equal.setTextColor();

        panelHistory.setText(message);


    }

    public void clear2nd() {
        is2ndOn = false;
        isInputXOn = false;
        sin.setText("sin");
        cos.setText("cos");
        tan.setText("tan");

        buttonFactorial.setText("!");
        buttonFactorial.setBackgroundResource(R.drawable.button_lightgray);
        buttonFactorial.setTextColor(Color.parseColor("#000000"));

        buttonPercentage.setText("%");
        buttonPercentage.setBackgroundResource(R.drawable.button_lightgray);
        buttonPercentage.setTextColor(Color.parseColor("#000000"));
    }

    public void set2nd(Button sin, Button cos, Button tan, Button deg, Button rad, Button buttonFactorial, Button exp, Button buttonPercentage) {
        this.sin = sin;
        this.cos = cos;
        this.tan = tan;
        this.deg = deg;
        this.rad = rad;
        this.buttonFactorial = buttonFactorial;
        this.exp = exp;
        this.buttonPercentage = buttonPercentage;

    }

    public void setEqual(Button equal) {
        this.equal = equal;
    }
}
