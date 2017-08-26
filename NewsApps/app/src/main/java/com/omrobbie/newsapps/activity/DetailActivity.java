package com.omrobbie.newsapps.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.omrobbie.newsapps.R;
import com.omrobbie.newsapps.model.ArticlesItem;

public class DetailActivity extends AppCompatActivity {

    // TODO: (26) Buat method baru untuk memanggil intent

    private static final String KEY_EXTRA_NEWS = "news";
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

        // TODO: (27) Tampilkan data
        if (getIntent().hasExtra(KEY_EXTRA_NEWS)) {
            articlesItem = getIntent().getParcelableExtra(KEY_EXTRA_NEWS);
            Toast.makeText(this, articlesItem.getTitle(), Toast.LENGTH_SHORT).show();
        } else finish();
    }
}
