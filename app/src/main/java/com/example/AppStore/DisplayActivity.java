package com.example.AppStore;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

        /*getActionBar().setTitle("Ваши приложения");
        getActionBar().setIcon(R.drawable.wolf1);
        getActionBar().setBackgroundDrawable(new
                ColorDrawable(Color.parseColor("#483D8B"))); */


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
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.display_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.close_website:
                Intent intent = new Intent(DisplayActivity.this, MainActivity.class);
                startActivity(intent);
                //("происходит переход в приложение");
                return true;
        }
        return super.onOptionsItemSelected(item);
    } */
}
