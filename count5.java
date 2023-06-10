package com.example.count;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textcounter = findViewById(R.id.count);
        Button startbutton = findViewById(R.id.start);
        Button stopbutton = findViewById(R.id.stop);
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(!stopbutton.isPressed())
                        {
                            try{
                                Thread.sleep(500);
                            }catch (InterruptedException e)
                            {
                                e.printStackTrace();
                                return;
                            }
                            textcounter.post(new Runnable() {
                                @Override
                                public void run() {
                                    counter++;
                                    textcounter.setText(""+counter);
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        stopbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter==0)
                {
                    textcounter.setText(""+counter);
                }
                try{
                    Thread.sleep(500);
                    Thread.interrupted();
                    counter=0;
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
