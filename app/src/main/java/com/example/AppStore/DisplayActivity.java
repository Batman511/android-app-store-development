package com.example.AppStore;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {
    private WebView browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        browser = (WebView) findViewById(R.id.webBrowser);
        WebSettings ws = browser.getSettings();
        ws.setJavaScriptEnabled(true);
        browser.loadUrl("https://amarketproject.000webhostapp.com/uploads/?C=N;O=A");
        browser.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {
        if (browser.canGoBack())
            browser.goBack();
        else
            super.onBackPressed();
    }
}
