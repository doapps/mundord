package tv.mundord.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import tv.mundord.R;
import tv.mundord.dialogs.Dialog_Internet;
import tv.mundord.utils.InternetUtil;

/**
 * Created by jonathan on 22/12/2014.
 */
public class MundoActivity extends ActionBarActivity {
    private WebView web_view;
    private String web_url;

    private InternetUtil internet;
    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        internet = new InternetUtil(MundoActivity.this);
        if (!internet.isConnectingToInternet()) {
            Dialog_Internet dialog = new Dialog_Internet(MundoActivity.this);
            dialog.show();
        } else {
            web_url = getResources().getString(R.string.home);

            web_view = (WebView) findViewById(R.id.web_view);
            web_view.setWebChromeClient(new WebChromeClient());
            web_view.getSettings().setPluginState(WebSettings.PluginState.ON);
            web_view.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
            web_view.setWebViewClient(new WebViewClient());
            web_view.getSettings().setJavaScriptEnabled(true);

            web_view.loadUrl(web_url);

             /*admob*/
            interstitial = new InterstitialAd(this);
            interstitial.setAdUnitId(getResources().getString(R.string.admob_interstitial));
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitial.loadAd(adRequest);
        /**/
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (web_view.canGoBack()) {
                        web_view.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        interstitial.show();
        super.onBackPressed();
    }
}
