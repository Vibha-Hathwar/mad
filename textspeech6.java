package com.example.textspeech;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech mTts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ed1=(EditText) findViewById(R.id.editText);
        Button b1= (Button) findViewById(R.id.button);

        mTts= new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(getApplicationContext(), "Language not supported", Toast.LENGTH_SHORT).show();
                    } else {
                        b1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String SpeakText = ed1.getText().toString();
                                speak_text(SpeakText);
                            }
                        });
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "INIT FALID", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void speak_text(String str){
        Bundle bundle = new Bundle();
        bundle.putInt(TextToSpeech.Engine.KEY_PARAM_STREAM,AudioManager.STREAM_MUSIC);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTts.speak(str, TextToSpeech.QUEUE_FLUSH, bundle, null);
        }

    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        mTts.stop();
        mTts.shutdown();
    }
}
