package com.omrobbie.newsapps.database;

import android.provider.BaseColumns;

// TODO: (37) Buatkan database dengan contract
public class DBContract {

    public static class NewsContract implements BaseColumns {
        public static final String TABLE_NAME = "news";

        public static final String URL = "url";
        public static final String TITLE = "title";
        public static final String DESC = "desc";
        public static final String IMG_URL = "img_url";
        public static final String AUTHOR = "author";
        public static final String PUBLISHED_AT = "published_at";

    }
}
