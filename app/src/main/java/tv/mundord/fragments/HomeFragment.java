package tv.mundord.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import tv.mundord.R;

import static java.lang.System.currentTimeMillis;

/**
 * Created by jonathan on 10/12/2014.
 */
public class HomeFragment extends android.support.v4.app.Fragment {
    private WebView web_view;
    private String web_url;
    private static final String ARG_PARAM1 = "web_url";


    private FrameLayout mContentView;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    private View mCustomView;


    public static final HomeFragment newInstance(String param1) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        homeFragment.setArguments(args);
        return homeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        web_url = getArguments().getString(ARG_PARAM1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        web_view = (WebView) view.findViewById(R.id.web_view);
        return view;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loadBanner();

        //enable Javascript
        web_view.setWebChromeClient(new WebChromeClient());
        web_view.getSettings().setPluginState(WebSettings.PluginState.ON);
        web_view.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        web_view.setWebViewClient(new WebViewClient());
        web_view.getSettings().setJavaScriptEnabled(true);


        web_view.loadUrl(web_url);
    }


    /**
     * inner Class*
     */
    private class MyWebViewClient_ extends WebViewClient {

        private long loadTime; // Web page loading time

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if (Uri.parse(url).getHost().equals(web_url)) {
                // This is my web site, so do not override; let my WebView load
                // the page
                return false;
            }

            // Otherwise, the link is not for a page on my site, so launch
            // another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            // Save start time
            this.loadTime = currentTimeMillis();

            // Show a toast
            Toast.makeText(getActivity(), "A page has started loading...", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            // Calculate load time
            this.loadTime = currentTimeMillis() - this.loadTime;

            // Convert milliseconds to date format
            String time = new SimpleDateFormat("mm:ss:SSS", Locale.getDefault())
                    .format(new Date(this.loadTime));

            // Show a toast
            Toast.makeText(getActivity(),
                    "Page has finished loading in " + time, Toast.LENGTH_SHORT)
                    .show();
        }
    }


    /****/
    private class MyBrowser extends WebViewClient{

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


    /****/


    /****/
    private void loadBanner() {
        AdView adView = (AdView) getView().findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    /**/
    //customize your web view client to open links from your own site in the
    //same web view otherwise just open the default browser activity with the URL
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("mundord.tv")) {
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }

    private class MyWebChromeClient extends WebChromeClient {

        //display alert message in Web View
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            new AlertDialog.Builder(view.getContext())
                    .setMessage(message).setCancelable(true).show();
            result.confirm();
            return true;
        }

    }

    public class JavaScriptInterface {
        Context mContext;

        // Instantiate the interface and set the context
        JavaScriptInterface(Context c) {
            mContext = c;
        }

        //using Javascript to call the finish activity
        public void closeMyActivity() {

        }

    }



}
