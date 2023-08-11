package com.example.callsave;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] permissions = { Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE };
        for (String permission : permissions) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, permission + " already granted", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permission}, 0);
            }
        }

        setContentView(R.layout.activity_main);

        int[] ids = { R.id.button_zero, R.id.button_one, R.id.button_two,
                R.id.button_three, R.id.button_four, R.id.button_five,
                R.id.button_six, R.id.button_seven, R.id.button_eight,
                R.id.button_nine, R.id.button_star, R.id.button_pound };

        EditText result = findViewById(R.id.resultphonetext);

        for (int id : ids) {
            Button button = findViewById(id);
            button.setOnClickListener(v -> {
                result.setText(result.getText() + button.getText().toString());
            });
        }

        Button callbutton = findViewById(R.id.button_call);
        callbutton.setOnClickListener(v->{
            if(result.getText().length() > 0) {
                String phStr = "tel:" + result.getText().toString();
                makePhoneCall(phStr);
            } else {
                Toast.makeText(MainActivity.this, "Unable to call",Toast.LENGTH_SHORT).show();
            }
        });

        Button clearbutton = findViewById(R.id.button_clear);
        clearbutton.setOnClickListener(v -> {
            if(result.getText().length() > 0) {
                CharSequence currentText = result.getText();
                result.setText(currentText.subSequence(0, currentText.length()-1));
            }
            else {
                result.setText("");
            }
        });

        Button savebutton = findViewById(R.id.button_save);
        savebutton.setOnClickListener(v -> {
            if(!result.getText().toString().isEmpty()){
                String phoneNumber = result.getText().toString();
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumber);
                startActivity(intent);
            }
        });
    }
    private void makePhoneCall(String PhStr){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(PhStr));
        startActivity(callIntent);
    }
}
