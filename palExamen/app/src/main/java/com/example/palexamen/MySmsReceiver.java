package com.example.palexamen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;

public class MySmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){
            SmsMessage[] message =    Telephony.Sms.Intents.getMessagesFromIntent(intent);
            for (SmsMessage smsMessage : message) {
                VistaSMS.tvSMS.setText(smsMessage.getMessageBody());
            }
        }
    }
}
