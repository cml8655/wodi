package com.cml.wodi.provider;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.cml.wodi.db.SQLiteConnectionHelper;

public abstract class BaseContentProvider extends ContentProvider {

    protected SQLiteOpenHelper mSqliteHelper;

    @Override
    public boolean onCreate() {
        mSqliteHelper = new SQLiteConnectionHelper(getContext());
        return true;
    }

    /**
     * Apply the given set of {@link android.content.ContentProviderOperation}, executing inside
     * a {@link android.database.sqlite.SQLiteDatabase} transaction. All changes will be rolled back if
     * any single one fails.
     */
    @Override
    public ContentProviderResult[] applyBatch(
            ArrayList<ContentProviderOperation> operations)
            throws OperationApplicationException {
        final SQLiteDatabase db = mSqliteHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            final int numOperations = operations.size();
            final ContentProviderResult[] results = new ContentProviderResult[numOperations];
            for (int i = 0; i < numOperations; i++) {
                results[i] = operations.get(i).apply(this, results, i);
            }
            db.setTransactionSuccessful();
            return results;
        } finally {
            db.endTransaction();
        }
    }

    protected void notifyChange(Uri uri, boolean syncToNetwork) {
        Context context = getContext();
        context.getContentResolver().notifyChange(uri, null, syncToNetwork);
    }

    /**
     * 获取uri中的分页参数，pageNo/pageSize
     *
     * @param uri
     * @param pageNoIndex
     * @param pageSizeIndex
     * @return
     */
    public PageBean parsePageBean(Uri uri, int pageNoIndex, int pageSizeIndex) {

        List<String> segments = uri.getPathSegments();

        PageBean page = new PageBean(Long.parseLong(segments.get(pageNoIndex)),
                Long.parseLong(segments.get(pageNoIndex)));

        if (page.pageNo < 1) {
            page.pageNo = 1;
        }

        if (page.pageSize < 1) {
            page.pageSize = 1;
        }

        return page;
    }

    class PageBean {
        public long pageNo;
        public long pageSize;

        public PageBean(long pageNo, long pageSize) {
            super();
            this.pageNo = pageNo;
            this.pageSize = pageSize;
        }

    }
}
