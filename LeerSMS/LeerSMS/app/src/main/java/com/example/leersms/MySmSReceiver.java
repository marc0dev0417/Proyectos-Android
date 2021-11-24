package com.example.leersms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MySmSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //  Log.d("Mira",Telephony.Sms.Intents.getMessagesFromIntent(intent));
        Log.d("Accion",intent.getAction());
        Log.d("AccionMia",Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
       // Telephony.Sms.Intents.SMS_RECEIVED_ACTION;
        if(intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){
            SmsMessage[] mensaje = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            for (int i = 0; i < mensaje.length; i++) {
                Log.d("Mira", mensaje[i].getMessageBody());
               MainActivity.tv.setText(mensaje[i].getMessageBody());
            }
        }
    }
}
