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
                    String messageBody = "This is a test SMS";
                    SmsManager smsManager = SmsManager.getDefault();
                    String testStr = "test";

                    Intent sentIntent = new Intent(getApplicationContext(), SmsSendActivity.class);
//
                    PendingIntent sentPendingIntent = PendingIntent.getActivity(MainActivity.this, 10, sentIntent, PendingIntent.FLAG_MUTABLE);

                    smsManager.sendDataMessage("***REMOVED***", null, (short) 5497, testStr.getBytes(StandardCharsets.UTF_8), sentPendingIntent, sentPendingIntent);

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