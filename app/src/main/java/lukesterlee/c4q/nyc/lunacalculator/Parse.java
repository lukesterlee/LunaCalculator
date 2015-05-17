package lukesterlee.c4q.nyc.lunacalculator;

import java.math.BigDecimal;

/**
 * Created by Luke on 5/11/2015.
 */
public class Parse {

    public static String parse(String input) {
        Expression expression = new Expression(input);
        String result;
        BigDecimal parse;
        try {
            parse = expression.eval();
            result = parse.toPlainString();
        } catch(Exception e) {
            result = "syntax error";
        }
        return result;
    }

    public static boolean isNumber(String input) {
        if(Character.isDigit(input.charAt(input.length()-1)))
            return true;
        else
            return false;
    }

    public static String addOperation(String input, String operation) {
        if (input.length() == 0) {
            return input;
        }
        if(!isNumber(input))
            input = input.substring(0, input.length()-1);
        input += operation;
        return input;
    }

    public static String addNegative(String input) {

        return "";
    }
}
