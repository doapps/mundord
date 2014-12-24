package com.mundord.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.mundord.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static java.lang.System.currentTimeMillis;

/**
 * Created by jonathan on 11/12/2014.
 */
public class NoticeActivity extends ActionBarActivity {
    private WebView web_view;
    private String web_url;
    private static final String ARG_PARAM = "notice_url";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);


        Bundle bundle = getIntent().getExtras();
        web_url = bundle.getString(ARG_PARAM);


        web_view = (WebView) findViewById(R.id.web_view);
        web_view.setWebChromeClient(new WebChromeClient());
        web_view.getSettings().setPluginState(WebSettings.PluginState.ON);
        web_view.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        web_view.setWebViewClient(new WebViewClient());
        web_view.getSettings().setJavaScriptEnabled(true);

        web_view.loadUrl(web_url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode)
            {
                case KeyEvent.KEYCODE_BACK:
                    if(web_view.canGoBack()){
                        web_view.goBack();
                    }else{
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}
