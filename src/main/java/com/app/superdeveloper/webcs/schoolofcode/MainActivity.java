package com.app.superdeveloper.webcs.schoolofcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MyWebChromeClient.ProgressListener {

    private WebView wv;
    SwipeRefreshLayout mySwipeRefreshLayout;
    private ProgressBar mProgressBar;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        wv = (WebView) findViewById(R.id.webview);
        mySwipeRefreshLayout = (SwipeRefreshLayout)this.findViewById(R.id.swipeContainer);
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        wv.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wv.getSettings().setAppCacheEnabled(true);
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        settings.setDomStorageEnabled(true);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        settings.setJavaScriptEnabled(true);
        settings.getLoadsImagesAutomatically();
        settings.getBuiltInZoomControls();
        settings.setSaveFormData(true);
        settings.setEnableSmoothTransition(true);
        settings.setNeedInitialFocus(true);
        settings.setSavePassword(true);
        settings.setDomStorageEnabled(true);
        settings.setPluginState(WebSettings.PluginState.ON);

        // add progress bar
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        wv.loadUrl("https://www.w3schools.com/");
        wv.setWebChromeClient(new MyWebChromeClient(this));
        wv.setWebViewClient(new MyWebViewClient());

        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                wv.reload();
            }
        });

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-2581556657067782/2678843355");
        AdRequest request = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(request);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            wv.loadUrl("https://www.w3schools.com/about/default.asp");
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        } else if(id == R.id.action_home){
            wv.loadUrl("https://www.w3schools.com/");
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }else if(id == R.id.action_exit){
           finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_html5) {
            wv.loadUrl("https://www.w3schools.com/html/default.asp");
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        } else if (id == R.id.nav_css) {
            wv.loadUrl("https://www.w3schools.com/css/default.asp");
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        } else if (id == R.id.nav_js) {
            wv.loadUrl("https://www.w3schools.com/js/default.asp");
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        } else if (id == R.id.nav_bootstrap) {
            wv.loadUrl("https://www.w3schools.com/bootstrap/default.asp");
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        } else if (id == R.id.nav_sql) {
            wv.loadUrl("https://www.w3schools.com/sql/default.asp");
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        } else if (id == R.id.nav_php) {
            wv.loadUrl("https://www.w3schools.com/php/default.asp");
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }else if (id == R.id.nav_asp) {
            wv.loadUrl("https://www.w3schools.com/asp/default.asp");
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }else if (id == R.id.nav_node) {
            wv.loadUrl("https://www.w3schools.com/nodejs/default.asp");
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }else if (id == R.id.nav_xml) {
            wv.loadUrl("https://www.w3schools.com/xml/default.asp");
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }else if (id == R.id.nav_jquery) {
            wv.loadUrl("https://www.w3schools.com/jquery/default.asp");
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }else if (id == R.id.nav_anjs) {
            wv.loadUrl("https://www.w3schools.com/angular/default.asp");
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }else if (id == R.id.nav_json) {
            wv.loadUrl("https://www.w3schools.com/js/js_json_intro.asp");
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }else if (id == R.id.nav_ajax) {
            wv.loadUrl("https://www.w3schools.com/js/js_ajax_intro.asp");
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }else if (id == R.id.nav_w3js) {
            wv.loadUrl("https://www.w3schools.com/w3js/default.asp");
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }else if (id == R.id.nav_appml) {
            wv.loadUrl("https://www.w3schools.com/appml/default.asp");
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }else if (id == R.id.nav_more) {
            wv.loadUrl("https://www.w3schools.com/howto/default.asp");
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onUpdateProgress(int progressValue) {
        mProgressBar.setProgress(progressValue);
        if(progressValue == 100){
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("www.w3schools.com")){
                return false;
            }else{
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(intent);
                return true;
            }
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            mProgressBar.setVisibility(View.VISIBLE);
            super.onPageStarted(view, url ,favicon);
        }


        @Override
        public void onPageFinished(WebView view, String url) {
            mProgressBar.setVisibility(View.GONE);
            mySwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch (keyCode){
                case KeyEvent.KEYCODE_BACK:
                    if(wv.canGoBack()){
                        wv.goBack();
                    }else{
                        finish();
                    }

                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
