package dev.altavision.pnrgatewayclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //This runs whenever an SMS is received--i.e. to capture the incoming REG-REQ message from the iPhone so
        //  we can forward it on to the gateway address (the Apple registration phone number)
        Log.d("SMSRECEIVER", "Received intent!");

        SmsMessage[] extractMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent);

        for (int i = 0; i < extractMessages.length; i++) {

            //Loops through each message and prints out some basic information for debugging
            Log.d("SMSRECEIVER", extractMessages[i].toString());
            Log.d("SMSRECEIVER", "\tMessage body: "+extractMessages[i].getMessageBody());
            Log.d("SMSRECEIVER", "\tMessage class: "+String.valueOf(extractMessages[i].getMessageClass()));
            Log.d("SMSRECEIVER", "\tMessage PDU: "+extractMessages[i].getPdu().toString());
            Log.d("SMSRECEIVER", "\tDisplay message body: "+extractMessages[i].getDisplayMessageBody());

            Log.d("SMSRECEIVER", "\tOriginating address: "+extractMessages[i].getOriginatingAddress());
            Log.d("SMSRECEIVER", "\tDisplay originating address: "+extractMessages[i].getDisplayOriginatingAddress());
            Log.d("SMSRECEIVER", "\tEmail body: "+extractMessages[i].getEmailBody());
            Log.d("SMSRECEIVER", "\tEmail from: "+extractMessages[i].getEmailFrom());

            Log.d("SMSRECEIVER", "\tPseudo-subject: "+extractMessages[i].getPseudoSubject());
            Log.d("SMSRECEIVER", "\tService center address: "+extractMessages[i].getServiceCenterAddress());
            Log.d("SMSRECEIVER", "\tIndex on ICC: "+String.valueOf(extractMessages[i].getIndexOnIcc()));

            Log.d("SMSRECEIVER", "\tProtocol identifier: "+String.valueOf(extractMessages[i].getProtocolIdentifier()));
            Log.d("SMSRECEIVER", "\tStatus: "+String.valueOf(extractMessages[i].getStatus()));
            Log.d("SMSRECEIVER", "\tUser data: "+String.valueOf(extractMessages[i].getUserData()));


            if (extractMessages[i].getOriginatingAddress().equals(PDUReceiver.IPHONE_NUMBER)) {
                //Any message coming from the iPhone gets forwarded to the gateway address
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(PDUReceiver.GATEWAY_ADDRESS, null, extractMessages[i].getMessageBody(), null, null);

            }

        }

    }
}
