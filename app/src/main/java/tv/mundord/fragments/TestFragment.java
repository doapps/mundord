package tv.mundord.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import tv.mundord.R;

/**
 * Created by jonathan on 10/12/2014.
 */
public class TestFragment extends Fragment {
    private WebView web_view;


    public static final TestFragment newInstance(){
        return new TestFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        web_view = (WebView)getView().findViewById(R.id.web_view);
        web_view.loadUrl(getResources().getString(R.string.home));
    }
}
