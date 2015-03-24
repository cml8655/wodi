package com.cml.wodi.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author guxiangguo
 */
public class SQLiteConnectionHelper extends SQLiteOpenHelper {

    private static final String TAG = SQLiteConnectionHelper.class
            .getSimpleName();

    private static final String DATABASE_NAME = "lawson2.db";

    /**
     * 在App版本升级情况下，如果DB的schema发生变更，需要对db的表定义进行升级，需要修改此版本号，以触发 onUpgrade方法，进行db变更
     */
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_SQL_PATH = "schema";

    public static final String DATABASE_CREATE_SQL = "schema.sql";

    private AssetManager assets = null;
    private Context mContext;

    public SQLiteConnectionHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
        assets = context.getAssets();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            int versionCode = mContext.getPackageManager().getPackageInfo(
                    "cn.com.lawson", PackageManager.GET_CONFIGURATIONS).versionCode;
            if (versionCode == 6) {
                // 创建db的时候如果Code=6 则可能是从ph1的code 5升级过来的 所以要清除ph1的用户信息
//				AccountUtil.clearAccountData(mContext);
            }
            Log.d(TAG, "versionCode:" + versionCode);
        } catch (NameNotFoundException e) {
            Log.d(TAG, "NameNotFoundException:", e);
        }
        executeSchema(db, DATABASE_CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading connection repository database from version "
                + oldVersion + "to " + newVersion);
        if (oldVersion == 1) {
            // 升级删除用户数据和原db
            // AccountUtil.clearAccountData(mContext);
            // mContext.deleteDatabase(DATABASE_NAME);
            // Log.d(TAG, "onUpgrade 清除用户数据");
        }
        // Upgrade database
        int changeCnt = newVersion - oldVersion;
        for (int i = 0; i < changeCnt; i++) {
            String schemaName = "update" + (oldVersion + i) + "_"
                    + (oldVersion + i + 1) + ".sql";
            executeSchema(db, schemaName);
        }

    }

    protected void executeSchema(SQLiteDatabase db, String schemaName) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    assets.open(DATABASE_SQL_PATH + "/" + schemaName)));
            String line;
            StringBuffer sqlb = new StringBuffer();
            while ((line = in.readLine()) != null) {
                if (line.trim().endsWith(";")) {
                    sqlb.append(line.trim().replace(";", ""));
                    String sql = sqlb.toString();
                    db.execSQL(sql);
                    sqlb = new StringBuffer();
                } else {
                    sqlb.append(line);
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "Failed executing " + schemaName, e);
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                Log.e(TAG, "", e);
            }
        }
    }

}
