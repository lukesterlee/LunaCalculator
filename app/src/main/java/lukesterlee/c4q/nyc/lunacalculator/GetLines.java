package lukesterlee.c4q.nyc.lunacalculator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Luke Lee on 5/19/2015.
 */
public class GetLines {

    public static ArrayList<String> readLinesFromFiles(InputStream file) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));
        String csvLine;
        try {
            while ((csvLine = reader.readLine()) != null) {
                lines.add(csvLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
