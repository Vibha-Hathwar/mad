package com.example.wallp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
public class MainActivity extends AppCompatActivity {
    int [] images;
    Timer timer= new Timer();
    TimerTask timerTask;
    int i=0,num1=0;
    int rNum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button wpButton=findViewById(R.id.button);
        View wallView=findViewById(R.id.wallView);
        images=new int[]{R.drawable.pic11,R.drawable.pic12,R.drawable.pic13};
        int imageLen=images.length;
        wpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // wallView.setBackground(ContextCompat.getDrawable(getApplicationContext(),images[rNum]));

                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {

                        Random random=new Random();
                        rNum=random.nextInt(imageLen);
                        if(num1==rNum)
                        {
                            rNum=(rNum+1)%imageLen;
                        }


//                        Log.d("madhu",""+rNum);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                wallView.setBackground(ContextCompat.getDrawable(getApplicationContext(),images[rNum]));
                                num1=rNum;
                                //  wallView.setBackground(ContextCompat.getDrawable((getApplicationContext(),images[rNum]));
//                                Log.d("abhi",""+i++);
                            }
                        });
                    }
                },  0,3000);
            }
        });
    }
}
