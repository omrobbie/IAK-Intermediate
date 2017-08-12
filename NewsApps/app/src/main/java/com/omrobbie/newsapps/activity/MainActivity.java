package com.omrobbie.newsapps.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.omrobbie.newsapps.BuildConfig;
import com.omrobbie.newsapps.R;
import com.omrobbie.newsapps.adapter.NewsAdapter;
import com.omrobbie.newsapps.model.APIResponse;
import com.omrobbie.newsapps.model.ArticlesItem;
import com.omrobbie.newsapps.rest.APIClient;
import com.omrobbie.newsapps.rest.APIService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;
    private NewsAdapter mAdapterDummy;
    private NewsAdapter mAdapterAPI;

    // TODO: (16) Buat variabel untuk sumber portal berita
    private static final String NEWS_SOURCE = "techcrunch";
    private List<ArticlesItem> mListArticle = new ArrayList<>();

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
        mAdapterDummy = new NewsAdapter(getDummyData());
        // mRecyclerView.setAdapter(mAdapterDummy);

        // TODO: (17) Set adapter dan recycler view
        mAdapterAPI = new NewsAdapter(mListArticle);
        mRecyclerView.setAdapter(mAdapterAPI);

        getData();
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

   // TODO: (15) Buat method getData
    public void getData() {
        APIService apiService = APIClient.getmRetrofitClient().create(APIService.class);
        Call<APIResponse> apiResponseCall = apiService.getArticle(
                NEWS_SOURCE,
                BuildConfig.API_KEY
        );

        apiResponseCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse apiResponse = response.body();

                if (apiResponse != null) {
                    mListArticle = apiResponse.getArticles();
                    mAdapterAPI.setData(mListArticle);
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call failed : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
