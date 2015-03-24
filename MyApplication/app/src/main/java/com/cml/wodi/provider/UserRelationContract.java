package com.cml.wodi.provider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class UserRelationContract {
    public static final String AUTHORITY = "com.cml.wodi.provider.user.relation";

    public static final String PATH = "user";
    public static final String PATH_FILTER = PATH + "/*";

    public static final Uri BASE_URI = new Uri.Builder()
            .scheme(ContentResolver.SCHEME_CONTENT).authority(AUTHORITY)
            .build();
    public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI,
            PATH);

    public static final int CODE_RELATION = 1;
    public static final int CODE_RELATION_FILTER = 2;

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/" + PATH;
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/" + PATH;

    public static final String TABLLE = "t_user_relation";

    public static interface Columns extends BaseColumns {

    }

    public static class Builder {

        public static Uri buildUri(long newsId) {
            return ContentUris.withAppendedId(CONTENT_URI, newsId);
        }

        public static long parseId(Uri uri) {
            return ContentUris.parseId(uri);
        }

    }
}
