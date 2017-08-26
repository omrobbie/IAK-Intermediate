package com.omrobbie.newsapps.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
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
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.fabFavorite)
    FloatingActionButton fabFavorite;
    private ArticlesItem articlesItem;

    private boolean mIsFav = false;

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

            setupActionBar();
            setupFAB();

        } else finish();
    }

    // TODO: (29) Buatkan fungsi untuk menampilkan web view
    private void setupWebView() {
        webView.clearCache(true);
        webView.clearHistory();
        webView.setHorizontalScrollBarEnabled(false);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.d(TAG, "onProgressChanged: " + String.valueOf(newProgress));
                progressBar.setProgress(newProgress);
                progressBar.setVisibility(newProgress == 100 ? View.GONE : View.VISIBLE);

                super.onProgressChanged(view, newProgress);
            }
        });
    }

    // TODO: (30) Buatkan fungsi untuk merubah tampilan action bar
    private void setupActionBar() {

        // TODO: (34) Set support untuk action bar
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar == null) return;

        actionBar.setTitle(articlesItem.getTitle());
        actionBar.setSubtitle(articlesItem.getUrl());

        // TODO: (32) Aktifkan tombol back / home
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        // TODO: (31) Tambahkan icon di action bar
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_action_close);
        actionBar.setHomeAsUpIndicator(drawable);
    }

    // TODO: (35) Buatkan fungsi untuk merespon tombol floating action button
    private void setupFAB() {
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d(TAG, "onScrollChange: " + scrollY);
                fabFavorite.setVisibility(scrollY > oldScrollY ? View.GONE : View.VISIBLE);
            }
        });

        // TODO: (36) Tambahkan listener untuk FAB
        fabFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsFav = !mIsFav;
                fabFavorite.setImageResource(mIsFav ? R.drawable.ic_action_fav : R.drawable.ic_action_fav_border);
            }
        });
    }

    // TODO: (33) Atifkan ketika tombol back/home ditekan
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
