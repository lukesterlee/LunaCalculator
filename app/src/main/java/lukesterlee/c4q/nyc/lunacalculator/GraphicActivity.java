package lukesterlee.c4q.nyc.lunacalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.getbase.floatingactionbutton.AddFloatingActionButton;

/**
 * Created by Luke on 5/20/2015.
 */
public class GraphicActivity extends Activity {


    GraphView mGraphView;
    String formula = "x";
    int maxX = 10;
    int maxY = 30;


    AddFloatingActionButton zoomOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        formula = getIntent().getExtras().getString("formula");
        maxX = getIntent().getExtras().getInt("maxX");
        maxY = getIntent().getExtras().getInt("maxY");


        zoomOut = (AddFloatingActionButton) findViewById(R.id.zoom_out);
        mGraphView = (GraphView) findViewById(R.id.graphView);
        mGraphView.setFormula(formula, maxX, maxY);


        zoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maxX += 1;
                maxY += 3;
                mGraphView.drawAgain(maxX, maxY);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mGraphView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGraphView.pause();
    }
}
