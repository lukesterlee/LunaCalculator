package lukesterlee.c4q.nyc.lunacalculator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by Luke on 5/21/2015.
 */
public class Converter {

    public static String getAnswer(InputStream file, String search) {

        Double rate = 0.0;
        Double amount = 0.0;
        String answer;

        Scanner input = null;
        input = new Scanner(file);


        String[] searchWords = new String[]{};
        String[] csvWords = new String[]{};
        searchWords = getSearchArray(search);
        //System.out.println(array[0]);


        while(input.hasNextLine()) {
            //System.out.println(input.nextLine());
            csvWords = input.nextLine().split(",");
            if (csvWords[0].equals(searchWords[1]) && csvWords[1].equals(searchWords[3])) {

                rate = Double.parseDouble(csvWords[2]);
                amount = Double.parseDouble(searchWords[0]);

            }
        }
        DecimalFormat df = new DecimalFormat("#.000");
        answer = df.format(rate*amount);
        return answer;
    }
    public static String[] getSearchArray(String input) {
        return input.split(" ");
    }
}
