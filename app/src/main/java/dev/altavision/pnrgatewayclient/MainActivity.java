package dev.altavision.pnrgatewayclient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        requestSmsPermission();





        ((Button) findViewById(R.id.smsbutton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(intent);



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }



    //    private void requestSmsPermission() {
////        permission = Manifest.permission.RECEIVE_SMS;
//    }
}