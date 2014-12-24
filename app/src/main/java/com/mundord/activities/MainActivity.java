package com.mundord.activities;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.mundord.dialogs.Dialog_Internet;
import com.mundord.fragments.HomeFragment;
import com.mundord.fragments.TestFragment;

import com.mundord.R;
import com.mundord.utils.InternetUtil;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    /*Admob*/
    private InterstitialAd interstitial;
    private InternetUtil internet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        internet = new InternetUtil(MainActivity.this);
        if (!internet.isConnectingToInternet()) {
            Dialog_Internet dialog = new Dialog_Internet(MainActivity.this);
            dialog.show();
        } else {

        /*admob*/
            interstitial = new InterstitialAd(this);
            interstitial.setAdUnitId(getResources().getString(R.string.admob_interstitial));
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitial.loadAd(adRequest);
        /**/

            mNavigationDrawerFragment = (NavigationDrawerFragment)
                    getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
            mTitle = getTitle();

            // Set up the drawer.
            mNavigationDrawerFragment.setUp(
                    R.id.navigation_drawer,
                    (DrawerLayout) findViewById(R.id.drawer_layout));
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, HomeFragment.newInstance(getResources().getString(R.string.home)), "home_fragment").commit();
                mTitle = getString(R.string.label_home);
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, HomeFragment.newInstance(getResources().getString(R.string.videos)), "home_fragment").commit();
                mTitle = getString(R.string.label_videos);
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, HomeFragment.newInstance(getResources().getString(R.string.photos)), "home_fragment").commit();
                mTitle = getString(R.string.label_photos);
                break;
            case 4:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, HomeFragment.newInstance(getResources().getString(R.string.music_videos)), "home_fragment").commit();
                mTitle = getString(R.string.label_music_videos);
                break;
            case 5:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, HomeFragment.newInstance(getResources().getString(R.string.latest_songs)), "home_fragment").commit();
                mTitle = getString(R.string.label_latest_songs);
                break;
            case 6:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, HomeFragment.newInstance(getResources().getString(R.string.contact_us)), "home_fragment").commit();
                mTitle = getString(R.string.label_contact_us);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }


    @Override
    public void onBackPressed() {
        interstitial.show();
        super.onBackPressed();
    }

}
