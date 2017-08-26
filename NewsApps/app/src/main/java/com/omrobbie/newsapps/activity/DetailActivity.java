package com.omrobbie.newsapps.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.omrobbie.newsapps.R;
import com.omrobbie.newsapps.model.ArticlesItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    // TODO: (26) Buat method baru untuk memanggil intent

    private static final String KEY_EXTRA_NEWS = "news";
    private static final String TAG = DetailActivity.class.getSimpleName();

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.webView)
    WebView webView;
    private ArticlesItem articlesItem;

    public static void start(Context context, ArticlesItem articlesItem) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY_EXTRA_NEWS, articlesItem);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        // TODO: (27) Tampilkan data
        if (getIntent().hasExtra(KEY_EXTRA_NEWS)) {
            articlesItem = getIntent().getParcelableExtra(KEY_EXTRA_NEWS);
            Toast.makeText(this, articlesItem.getTitle(), Toast.LENGTH_SHORT).show();
            setupWebView();
            webView.loadUrl(articlesItem.getUrl());
            progressBar.setMax(100);
        } else finish();
    }

    private void setupWebView() {
        webView.clearCache(true);
        webView.clearHistory();
        webView.setHorizontalScrollBarEnabled(false);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.d(TAG, "onProgressChanged: " + String.valueOf(newProgress));
                progressBar.setProgress(newProgress);
                progressBar.setVisibility(newProgress == 100 ? View.GONE : View.VISIBLE);

                super.onProgressChanged(view, newProgress);
            }
        });
    }
}
