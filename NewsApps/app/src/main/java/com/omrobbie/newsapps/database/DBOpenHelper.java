package com.omrobbie.newsapps.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.omrobbie.newsapps.database.DBContract.NewsContract;
import com.omrobbie.newsapps.model.ArticlesItem;

import static android.content.ContentValues.TAG;

// TODO: (38) Buatkan helper untuk database
public class DBOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "newsapp";
    public static final int DATABASE_VERSION = 1;

    // TODO: (39) Buatkan sql untuk action pada database
    private static final String SQL_CREATE_TABLE_NEWS =
            "CREATE TABLE " + NewsContract.TABLE_NAME + " (" +
                    NewsContract._ID + " INTEGER PRIMARY KEY, " +
                    NewsContract.URL + " TEXT, " +
                    NewsContract.TITLE + " TEXT," +
                    NewsContract.DESC + " TEXT," +
                    NewsContract.IMG_URL + " TEXT," +
                    NewsContract.PUBLISHED_AT + " TEXT," +
                    NewsContract.AUTHOR + " TEXT" +
                    "); ";

    private static final String SQL_DROP_TABLE_NEWS  =
            "DROP TABLE IF EXISTS " + NewsContract.TABLE_NAME;

    // tambahkan database name dan version manual
    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_NEWS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DROP_TABLE_NEWS);
        onCreate(sqLiteDatabase);
    }

    // TODO: (40) Buatkan fungsi untuk menyimpan perubahan data
    public boolean saveNewsItem(ArticlesItem articlesItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NewsContract.URL, articlesItem.getUrl());
        cv.put(NewsContract.TITLE, articlesItem.getTitle());
        cv.put(NewsContract.DESC, articlesItem.getDescription());
        cv.put(NewsContract.IMG_URL, articlesItem.getUrlToImage());
        cv.put(NewsContract.AUTHOR, articlesItem.getAuthor());
        cv.put(NewsContract.PUBLISHED_AT, articlesItem.getPublishedAt());

        long rowId = db.insert(NewsContract.TABLE_NAME, null, cv);
        db.close();

        Log.d(TAG, "saveNewsItem: isSave ? " + String.valueOf(rowId > 0));

        return rowId > 0;
    }
}
