package com.omrobbie.newsapps.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.omrobbie.newsapps.R;
import com.omrobbie.newsapps.adapter.NewsAdapter;
import com.omrobbie.newsapps.model.ArticlesItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;
    private NewsAdapter mNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        // TODO: (8) Setting layout manager
        mLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        );
        mRecyclerView.setLayoutManager(mLayoutManager);

        // TODO: (9) Setting list adapter
        mNewsAdapter = new NewsAdapter(getDummyData());
        mRecyclerView.setAdapter(mNewsAdapter);
    }

    // TODO: (10) Setting dummy data
    private List<ArticlesItem> getDummyData() {
        List<ArticlesItem> dummyList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ArticlesItem dummyNews = new ArticlesItem();
            dummyNews.setUrlToImage("http://images.pcworld.com/images/article/2012/05/android_superman-11359833.jpg");
            dummyNews.setTitle(getString(R.string.lorem_title));
            dummyNews.setDescription(getString(R.string.lorem_description));
            dummyList.add(dummyNews);
        }
        return dummyList;
    }
}
