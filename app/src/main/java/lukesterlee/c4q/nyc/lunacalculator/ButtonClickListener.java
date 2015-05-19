package lukesterlee.c4q.nyc.lunacalculator;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Created by Luke on 5/11/2015.
 */

public class ButtonClickListener implements View.OnClickListener {

    InputStream file;

    TextView panelHistory;
    TextView panel;

    String print;
    String ans;
    String randomMessage;

    lastInputType lastCode;
    int open;
    int close;

    boolean is2ndOn;
    boolean isRadian;
    boolean isRadStarted;

    Button sin;
    Button cos;
    Button tan;
    Button deg;
    Button rad;

    Stack<String> expression;
    Stack<String> display;
    String history;

    public ButtonClickListener(TextView panel, TextView panelHistory, Button sin, Button cos, Button tan, Button deg, Button rad, InputStream file) {
        expression = new Stack<String>();
        display = new Stack<String>();
        history = "";

        this.sin = sin;
        this.cos = cos;
        this.tan = tan;
        this.deg = deg;
        this.rad = rad;

        this.panel = panel;
        this.panelHistory = panelHistory;

        this.file = file;

        randomMessage = "";
        print = "";
        ans = "0";
        open = 0;
        close = 0;

        is2ndOn = false;
        isRadian = false;
        isRadStarted = false;
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
                handlePercentage();
                break;

            case R.id.buttonExp :
                handleExp();
                break;
            case R.id.buttonFactorial :
                handleFactorial();
                break;

            case R.id.buttonSin :
                if (is2ndOn) {
                    if (isRadian) {
                        handleFunction("asin(deg(", "asin(", true);
                        isRadStarted = true;
                    } else {
                        handleFunction("asin(", "asin(", true);
                    }
                }
                else {
                    if (isRadian) {
                        handleFunction("sin(deg(", "sin(", true);
                        isRadStarted = true;
                    } else {
                        handleFunction("sin(", "sin(", true);
                    }
                }
                break;
            case R.id.buttonCos :
                if (is2ndOn) {
                    if (isRadian) {
                        handleFunction("acos(deg(", "acos(", true);
                        isRadStarted = true;
                    } else {
                        handleFunction("acos(", "acos(", true);
                    }
                }
                else {
                    if (isRadian) {
                        handleFunction("cos(deg(", "cos(", true);
                        isRadStarted = true;
                    } else {
                        handleFunction("cos(", "cos(", true);
                    }
                }
            case R.id.buttonTan :
                if (is2ndOn) {
                    if (isRadian) {
                        handleFunction("atan(deg(", "atan(", true);
                        isRadStarted = true;
                    } else {
                        handleFunction("atan(", "atan(", true);
                    }
                }
                else {
                    if (isRadian) {
                        handleFunction("tan(deg(", "tan(", true);
                        isRadStarted = true;
                    } else {
                        handleFunction("tan(", "tan(", true);
                    }
                }
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
                    sin.setBackgroundResource(R.drawable.button_lightgray);
                    cos.setBackgroundResource(R.drawable.button_lightgray);
                    tan.setBackgroundResource(R.drawable.button_lightgray);
                    sin.setTextColor(Color.parseColor("#000000"));
                    cos.setTextColor(Color.parseColor("#000000"));
                    tan.setTextColor(Color.parseColor("#000000"));
                } else {
                    is2ndOn = true;
                    sin.setText("asin");
                    cos.setText("acos");
                    tan.setText("atan");
                    sin.setBackgroundResource(R.drawable.button_green2);
                    cos.setBackgroundResource(R.drawable.button_green2);
                    tan.setBackgroundResource(R.drawable.button_green2);
                    sin.setTextColor(Color.parseColor("#FFFFFF"));
                    cos.setTextColor(Color.parseColor("#FFFFFF"));
                    tan.setTextColor(Color.parseColor("#FFFFFF"));
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
                handleAC();
                break;

            case R.id.buttonEqual :
                try {
                    handleEqual();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
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
        } else if (last.equals(".")) {
            return lastInputType.DOT;
        } else if (last.equals("+") || last.equals("-") || last.equals("*") || last.equals("/")) {
            return lastInputType.OPERATOR;
        } else if (last.endsWith("(")) {
            return lastInputType.LPARENT;
        } else if (last.equals(")")) {
            return lastInputType.RPARENT;
        } else if (last.equals("e") || last.equals("π") || last.equals("!") || last.equals("%")) {
            return lastInputType.CONSTANT_UNARY;
        } else if (last.equals("^")) {
            return lastInputType.EXP;
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

    public void handleAC() {
        expression.clear();
        display.clear();
        print = "";
        open = 0;
        close = 0;
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
                if (expression.empty())
                    expression.push(display.peek());
                add("*");
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
                if (expression.empty())
                    expression.push(display.peek());
                expression.push("*");
                expression.push("0");
                expression.push(".");
                expression.push("01");
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
                if (isRadian && isRadStarted) {
                    expression.push(")");
                }
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

    public void handleNegative() {
        String last;
        String secondLast;
        String thirdLast;
        lastCode = lastDetection();
        switch (lastCode) {
            case DIGIT:
                if (expression.empty()) {
                    expression.push("-");
                    expression.push(display.pop());
                    display.push("-");
                    display.push(expression.peek());
                } else {
                    if (expression.size() == 1) {
                        last = expression.pop();
                        display.pop();
                        add("-", last);
                    } else {
                        last = expression.pop();
                        secondLast = expression.pop();
                        thirdLast = expression.pop();
                        display.pop();
                        display.pop();
                        display.pop();
                        if (secondLast.equals(".")) {
                            add("(", "-");
                            add(thirdLast, secondLast, last);
                        } else {
                            add(thirdLast, secondLast);
                            add("(", "-", last);
                        }
                        open++;
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
            case LPARENT: case EMPTY:
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
                expression.push(Long.toString(factorial(Long.parseLong(ans))));
                display.push("Ans");
                display.push("!");
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

    public void handleEqual() throws FileNotFoundException {
        // test if the user misses parenthesis.
        if (!expression.empty()) {
            while (open > close) {
                expression.push(")");
                display.push(")");
                close++;
            }
            // if the test is passed
            submit(stackToString(expression));
        }

    }

    public void submit(String inputExpression) throws FileNotFoundException {
        Expression expression = new Expression(inputExpression);
        try {
            BigDecimal parse = expression.eval();
            ans = parse.toPlainString();
            history = stackToString(display) + " = " + ans;
            display.clear();
            this.expression.clear();
            display.push(ans);
            if (panelHistory != null) {
                panelHistory.setText(history);
            }
        } catch(Exception e) {
            this.expression.clear();
            display.clear();
            randomMessage = randomErrorMessage();
            display.push(randomMessage);
        }
    }

    public void setInputStream(InputStream file) {
        this.file = file;
    }

    public String randomErrorMessage() throws FileNotFoundException {
        ArrayList<String> lines = GetLines.readLinesFromFiles(file);
        Random random = new Random();
        int index = random.nextInt(lines.size());
        return  lines.get(index);
    }
}
