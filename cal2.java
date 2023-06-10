package com.calcooo.calculations;

//MainActivity.java

 //       package com.ssk.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class MainActivity extends AppCompatActivity {


    String a = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView input = findViewById(R.id.input);
        TextView result = findViewById(R.id.result);
        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonminus = findViewById(R.id.buttonminus);
        Button buttonplus = findViewById(R.id.buttonplus);
        Button buttondiv = findViewById(R.id.buttondiv);
        Button buttonmul = findViewById(R.id.buttonmul);
        Button buttonclear = findViewById(R.id.buttonclear);
        Button buttondot = findViewById(R.id.buttondot);
        Button buttoneq = findViewById(R.id.buttoneq);
        button0.setOnClickListener(new buttonListener(button0, input));
        button1.setOnClickListener(new buttonListener(button1, input));
        button2.setOnClickListener(new buttonListener(button2, input));
        button3.setOnClickListener(new buttonListener(button3, input));
        button4.setOnClickListener(new buttonListener(button4, input));
        button5.setOnClickListener(new buttonListener(button5, input));
        button6.setOnClickListener(new buttonListener(button6, input));
        button7.setOnClickListener(new buttonListener(button7, input));
        button8.setOnClickListener(new buttonListener(button8, input));
        button9.setOnClickListener(new buttonListener(button9, input));
        buttonplus.setOnClickListener(new buttonListener(buttonplus, input));
        buttonminus.setOnClickListener(new buttonListener(buttonminus, input));
        buttondiv.setOnClickListener(new buttonListener(buttondiv, input));
        buttondot.setOnClickListener(new buttonListener(buttondot, input));
        buttonmul.setOnClickListener(new buttonListener(buttonmul, input));

        buttoneq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
                try {
                    Object r = engine.eval(input.getText().toString());
                    result.setText(r.toString());

                } catch (ScriptException e) {
                    result.setText("Error");

                }
                //input.setText("");
            }
        });

        buttonclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                input.setText("");
                result.setText("");
            }
        });

    }
}

class buttonListener implements View.OnClickListener {
    Button button;
    TextView input;
    buttonListener(Button b, TextView r) {
        button = b;
        input = r;
    }
    @Override
    public void onClick(View view) {
        StringBuilder current = new StringBuilder();
        current.append(input.getText());
        current.append(button.getText());
        input.setText(current.toString());

    }
}
