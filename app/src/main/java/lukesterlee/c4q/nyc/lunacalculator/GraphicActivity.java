package lukesterlee.c4q.nyc.lunacalculator;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.EditText;

/**
 * Created by Luke on 5/20/2015.
 */
public class GraphicActivity extends Activity {

    GraphView mGraphView;
    String formula = "0";
    int maxX = 10;
    int maxY = 10;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        formula = getIntent().getExtras().getString("formula");
        maxX = getIntent().getExtras().getInt("maxX");
        maxY = getIntent().getExtras().getInt("maxY");

        mGraphView = new GraphView(this, formula, maxX, maxY);



        setContentView(mGraphView);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);

        mGraphView.onDraw(c);


    }
}
