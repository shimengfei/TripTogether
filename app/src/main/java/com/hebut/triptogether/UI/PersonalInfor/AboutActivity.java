package com.hebut.triptogether.UI.PersonalInfor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hebut.triptogether.R;

public class AboutActivity extends Activity {
    private WebView webView;

    @SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        webView = (WebView) findViewById(R.id.wv_help);

        final String appVersion = "1.0";

        webView.getSettings().setJavaScriptEnabled(true); // 开启javascript支持
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setAllowFileAccess(true);
        webView.addJavascriptInterface(this, "changeVersionJs");

        // 根据语言加载不同的页面，实现多语言适配
        if (getResources().getConfiguration().locale.getLanguage().equals("zh")) {
            webView.loadUrl("file:///android_asset/about.html");
        } else {
            webView.loadUrl("file:///android_asset/about_es.html");
        }
        // 等待page load 完毕，再去改变版本号，动态改变版本号
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.loadUrl("javascript:changeVersion('" + appVersion + "')");
            }
        });
    }
}