package tv.mundord.broadcast;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import tv.mundord.activities.NoticeActivity;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jonathan on 11/12/2014.
 */
public class Receiver extends ParsePushBroadcastReceiver {

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        Log.e("Push", "Clicked");
        Bundle extras = intent.getExtras();
        String message = extras != null ? extras.getString("com.parse.Data") : "";
        Log.e("message", message);
        JSONObject jObject;
        try {
            jObject = new JSONObject(message);
            String temp_data = jObject.getString("alert");
            String temp_par = "";
            String notice_url = "";
            for (int i = 0; i < temp_data.length(); i++) {
                notice_url = notice_url + String.valueOf(temp_data.charAt(i));
                if(temp_data.charAt(i) == '$'){
                    notice_url = "";
                }
            }

            Intent i = new Intent(context, NoticeActivity.class);
            i.putExtra("notice_url", notice_url);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);

            //((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container, HomeFragment.newInstance(notice_url), "home_fragment").commit();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*Intent i = new Intent(context, MainActivity.class);
        i.putExtras(intent.getExtras());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);*/
    }
}
