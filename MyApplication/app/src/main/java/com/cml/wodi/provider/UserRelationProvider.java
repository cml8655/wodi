package com.cml.wodi.provider;

import android.app.SearchManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;


public class UserRelationProvider extends BaseContentProvider {

    private SQLiteDatabase db;


    @Override
    public boolean onCreate() {
        super.onCreate();
        db = mSqliteHelper.getWritableDatabase();
        return true;
    }

    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        matcher.addURI(UserRelationContract.AUTHORITY, UserRelationContract.PATH, UserRelationContract.CODE_RELATION);
        matcher.addURI(UserRelationContract.AUTHORITY, UserRelationContract.PATH_FILTER, UserRelationContract.CODE_RELATION_FILTER);
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Log.i("SearchActivity", "type:" + matcher.match(uri) + uri.toString());

        if (null == projection) {
            projection = new String[]{"*"};
        }
        Log.i("SearchActivity", selection + "," + selectionArgs[0] + "," + projection);
//        Cursor result = db.rawQuery("select * from " + UserRelationContract.TABLLE + " where " + selection, selectionArgs);
//        Cursor c = db.rawQuery("select count(*) from t_user_relation", null);


        Cursor result = db.query(UserRelationContract.TABLLE, projection,
                "suggest_text_1 is not null", null, null, null, null);
        Log.i("SearchActivity", result.getCount() + ",," + result.getColumnCount());

        return result;
    }

    @Override
    public String getType(Uri uri) {

        Log.i("SearchActivity", "type:" + matcher.match(uri));
        switch (matcher.match(uri)) {
            case UserRelationContract.CODE_RELATION:
                return UserRelationContract.CONTENT_TYPE;
            case UserRelationContract.CODE_RELATION_FILTER:
                return UserRelationContract.CONTENT_TYPE;
        }
        throw new IllegalArgumentException("Unknown uri");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = db.insert(UserRelationContract.TABLLE, null, values);
        return UserRelationContract.Builder.buildUri(id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
