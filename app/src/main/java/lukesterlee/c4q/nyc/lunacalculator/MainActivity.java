package lukesterlee.c4q.nyc.lunacalculator;


import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
    private EditText Scr; //textbox screen
    private float NumberBf; //save screen before press operation button
    private String operation;
    private ButtonClickListener btnClick;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Scr = (EditText) findViewById(R.id.editText);
        Scr.setEnabled(false);

        int idList[]= {R.id.button0,R.id.button1,R.id.button2,R.id.button3,R.id.button4,
                R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9,
                R.id.buttonAC,R.id.buttonDivided,R.id.buttonDot,R.id.buttonEqual,
                R.id.buttonMinus,R.id.buttonAdd,R.id.,};
        for (int id:idList){
            View v = (View) findViewById(id);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void mMath(String str){
        NumberBf = Float.parseFloat(Scr.getText().toString());
        operation = str;
        Scr.setText("0");
    }

    public void getKeyboard (String str){
        String ScrCurrent = Scr.getText().toString();
        if (ScrCurrent.equals("0"))
            ScrCurrent = "";
        ScrCurrent += str;
        Scr.setText(ScrCurrent);
    }

    public void mResult(){
        float NumAf = Float.parseFloat(Scr.getText().toString());
        float result = 0;
        if (operation.equals("+")){
            result=NumAf+NumberBf;
        }
        if (operation.equals("-")){
            result=NumberBf-NumAf;
        }
        if (operation.equals("*")){
            result=NumAf*NumberBf;
        }
        if (operation.equals("/")){
            result=NumberBf / NumAf;
        }
        Scr.setText(String.valueOf(result));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private class ButtonClickListener implements View.OnClickListener{
        public void onClick (View v){
            switch (v.getId()){
                case R.id.buttonC:
                    Scr.setText("0");
                    NumberBf = 0;
                    operation = "";
                    break;
                case R.id.buttonPlus:
                    mMath("+");
                    break;
                case R.id.buttonMinus:
                    mMath("-");
                    break;
                case R.id.buttonDivide:
                    mMath("/");
                    break;
                case R.id.buttonMultiply:
                    mMath("*");
                    break;
                case R.id.buttonEqual:
                    mResult();
                    break;
                default:
                    String numb = ((Button) v).getText().toString();
                    getKeyboard(numb);
                    break;
            }
        }
    }
}
