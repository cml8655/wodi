package com.cml.wodi.provider;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;


public class UserRelationProvicer extends BaseContentProvider {

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
        return db.query(UserRelationContract.TABLLE, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public String getType(Uri uri) {
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
        return null;
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
