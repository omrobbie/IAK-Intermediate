package com.omrobbie.newsapps.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.omrobbie.newsapps.R;
import com.omrobbie.newsapps.model.ArticlesItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    // TODO: (3) Setelah membuat model, deklarasikan dengan List
    List<ArticlesItem> mNewsList;

    // TODO: (4) Generate consrtructor
    public NewsAdapter(List<ArticlesItem> mNewsList) {
        this.mNewsList = mNewsList;
    }

    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // TODO: (2) Mebuat Inflater
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.NewsViewHolder holder, int position) {
        ArticlesItem news = mNewsList.get(position);
        holder.bind(news);
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    // TODO: (1) Membuat class untuk View Holder di dalam adapter (ini pertama kali yang harus dilakukan, dan diketik manual)
    static class NewsViewHolder extends RecyclerView.ViewHolder {

        // TODO: (5) Deklarasikan semua komponen dari item_news.xml
        @BindView(R.id.ivNewsPhoto) ImageView ivNewsPhoto;
        @BindView(R.id.tvNewsTitle) TextView tvNewsTitle;
        @BindView(R.id.tvNewsDescription) TextView tvNewsDescription;

        public NewsViewHolder(View itemView) {
            super(itemView);

            // TODO: (6) Binding komponen yang sudah di deklarasikan sebelumnya
            ButterKnife.bind(this, itemView);
        }

        // TODO: (7) Buat method untuk binding data
        public void bind(ArticlesItem articlesItem) {
            Glide.with(ivNewsPhoto.getContext())
                    .load(articlesItem.getUrlToImage())
                    .into(ivNewsPhoto);
            tvNewsTitle.setText(articlesItem.getTitle());
            tvNewsDescription.setText(articlesItem.getDescription());
        }
    }
}
