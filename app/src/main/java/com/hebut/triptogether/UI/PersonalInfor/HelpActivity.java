package com.hebut.triptogether.UI.PersonalInfor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.hebut.triptogether.R;

public class HelpActivity extends AppCompatActivity {

    private WebView webView;

    @SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        webView = (WebView) findViewById(R.id.wv_help);


        webView.getSettings().setJavaScriptEnabled(true); // 开启javascript支持
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setAllowFileAccess(true);
        webView.addJavascriptInterface(this, "changeVersionJs");

        // 根据语言加载不同的页面，实现多语言适配
        if (getResources().getConfiguration().locale.getLanguage().equals("zh")) {
            webView.loadUrl("file:///android_asset/help.html");
        }

    }
}
