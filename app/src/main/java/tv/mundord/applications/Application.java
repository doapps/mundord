package tv.mundord.applications;

import tv.mundord.R;
import tv.mundord.activities.MainActivity;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;

/**
 * Created by jonathan on 11/12/2014.
 */
public class Application extends android.app.Application {

    public Application(){}

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the Parse SDK.
        Parse.initialize(this, getResources().getString(R.string.application_id), getResources().getString(R.string.client_key));

        // Specify an Activity to handle all pushes by default.
        PushService.setDefaultPushCallback(this, MainActivity.class);
        //saving new installation
        ParseInstallation.getCurrentInstallation().saveInBackground();

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }
}
