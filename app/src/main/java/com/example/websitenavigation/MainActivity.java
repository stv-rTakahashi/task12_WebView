package com.example.websitenavigation;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    private ProgressBar progressBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    webView.loadUrl("http://www.google.co.jp/");
                    return true;
                case R.id.navigation_dashboard:
                    webView.loadUrl("http://splax.net/");
                    return true;
                case R.id.navigation_notifications:
                    webView.loadUrl("http://flowersburger.com/");
                    return true;
                case R.id.navigation_back:
                    //戻るボタンが押されたときの処理
                    webView.goBack();
                    return true;
                case R.id.navigation_forward:
                    //進むボタンが押されたときの処理
                    webView.goForward();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        webView = (WebView)findViewById(R.id.webView);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        //最初はprogress barを非表示にする
        progressBar.setVisibility(View.INVISIBLE);

        //タップしたときにブラウザを起動しないようにする
        webView.setWebViewClient(new WebViewClient(){

            //Webページの読み込みが始まったときの処理
            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
                progressBar.setVisibility(View.VISIBLE);
            }

            //Webページの読み込みが終わったときの処理
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

        //JavaScriptを有効にする
        webView.getSettings().setJavaScriptEnabled(true);
    }

}
