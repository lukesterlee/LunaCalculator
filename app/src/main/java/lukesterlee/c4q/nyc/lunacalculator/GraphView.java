package lukesterlee.c4q.nyc.lunacalculator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.math.BigDecimal;

/**
 * Created by Luke on 5/20/2015.
 */
public class GraphView extends View {

    String formula;

    int maxX;
    int maxY;

    public GraphView(Context context, String formula, int maxX, int maxY) {
        super(context);

        this.formula = formula;
        this.maxX = maxX;
        this.maxY = maxY;
    }
    
    @Override
    protected void onDraw(Canvas canvas) {


        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.black));
        canvas.drawColor(Color.WHITE);
        canvas.drawLine(canvas.getWidth()/2,0,canvas.getWidth()/2,canvas.getHeight(), paint);
        canvas.drawLine(0,canvas.getHeight()/2,canvas.getWidth(),canvas.getHeight()/2, paint);

        for (float x = -maxX; x <= maxX; x+=0.1) {
            float y = getY(x);

            canvas.drawLine(getCanvasX(canvas, x), getCanvasY(canvas, y), getCanvasX(canvas, x+1), getCanvasY(canvas, getY(x + 1)), paint);
        }

        canvas.drawText("(0,0)", getCanvasX(canvas, 0), getCanvasY(canvas, 0), paint);
        paint.setTextSize(50);
        canvas.drawText("y = " + formula, getWidth()/2, getHeight()*2/3, paint);
    }

    public float getY(float x) {

        Expression expression = new Expression(formula);
        BigDecimal answer = expression.with("x", new BigDecimal(x)).eval();

        float result = Float.parseFloat(answer.toPlainString());
        return result;
    }

    public float getCanvasX(Canvas canvas, float x) {
        float scale = canvas.getWidth()/(maxX*2);
        return x*scale + (canvas.getWidth()/2);
    }

    public float getCanvasY(Canvas canvas, float y) {
        float scale = canvas.getHeight()/(maxY*2);
        return -y*scale + (canvas.getHeight()/2);
    }


}
