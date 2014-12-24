package com.mundord.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jonathan on 11/12/2014.
 */
public class PushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String message = extras != null ? extras.getString("com.parse.Data") : "";
        JSONObject jObject;
        try {
            jObject = new JSONObject(message);
            Toast toast = Toast.makeText(context,"Llegó un sms de mundord"+jObject.getString("alert")+ jObject.getString("title")+jObject.getString("action"), Toast.LENGTH_SHORT);
            toast.show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
