package lukesterlee.c4q.nyc.lunacalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Luke on 5/21/2015.
 */
public class ConverterActivity extends ActionBarActivity {

    EditText editText;
    TextView textView;
    Button submit;

    String search = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final InputStream file = getResources().openRawResource(R.raw.mass);

        setContentView(R.layout.activity_converter);

        editText = (EditText) findViewById(R.id.editText_converter);
        textView = (TextView) findViewById(R.id.textView_answer);
        submit = (Button) findViewById(R.id.button_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search = editText.getText().toString();
                Double answer = Converter.getAnswer(file, search);
                textView.setText(answer + "");
            }
        });


    }
}
