package com.cml.wodi;

import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.cml.wodi.provider.UserRelationContract;

public class MainActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the content view
        setContentView(R.layout.activity_main);
        ContentValues values = new ContentValues();
        values.put(UserRelationContract.Columns.NAME, "11" + System.currentTimeMillis());
        Uri result = getContentResolver().insert(UserRelationContract.CONTENT_URI, values);
        Toast.makeText(this, "返回结果：" + ContentUris.parseId(result), Toast.LENGTH_LONG).show();
    }
}