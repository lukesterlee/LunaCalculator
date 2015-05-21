package lukesterlee.c4q.nyc.lunacalculator;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.getbase.floatingactionbutton.AddFloatingActionButton;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created by Luke on 5/20/2015.
 */
public class GraphicActivity extends Activity {

    Canvas c;

    GraphView mGraphView;
    String formula = "x";
    int maxX = 10;
    int maxY = 30;

    AddFloatingActionButton zoomIn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        formula = getIntent().getExtras().getString("formula");
        maxX = getIntent().getExtras().getInt("maxX");
        maxY = getIntent().getExtras().getInt("maxY");

        zoomIn = (AddFloatingActionButton) findViewById(R.id.zoom_in);
        mGraphView = (GraphView) findViewById(R.id.graphView);
        mGraphView.setFormula(formula, maxX, maxY);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        c = new Canvas(b);

        mGraphView.invalidate();
        mGraphView.onDraw(c);


        zoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGraphView.setFormula(formula, maxX*2, maxY*2);
                mGraphView.invalidate();
                mGraphView.onDraw(c);
            }
        });

    }


}
