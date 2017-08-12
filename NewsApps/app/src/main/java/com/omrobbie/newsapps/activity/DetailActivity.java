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

    public static void start(Context context, String newsJson) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY_EXTRA_NEWS, newsJson);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // TODO: (27) Tampilkan data
        if (getIntent().hasExtra(KEY_EXTRA_NEWS)) {
            String newsJson = getIntent().getStringExtra(KEY_EXTRA_NEWS);
            articlesItem = new ArticlesItem().fromJson(newsJson);
            Toast.makeText(this, articlesItem.getTitle(), Toast.LENGTH_SHORT).show();
        } else finish();
    }
}
