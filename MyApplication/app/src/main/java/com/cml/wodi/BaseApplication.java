package com.cml.wodi;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.cml.wodi.api.FakeX509TrustManager;
import com.cml.wodi.crash.CrashHandler;
import com.cml.wodi.volley.ImageCacheManager;
import com.cml.wodi.volley.RequestManager;

/**
 * Created by teamlab on 2015/3/6.
 */
public class BaseApplication extends Application {
    private static Context context;

    private static int DISK_IMAGECACHE_SIZE = 1024 * 1024 * 10;
    private static Bitmap.CompressFormat DISK_IMAGECACHE_COMPRESS_FORMAT = Bitmap.CompressFormat.PNG;
    private static int DISK_IMAGECACHE_QUALITY = 100;  //PNG is lossless so quality is ignored but must be provided

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        //初始化异常捕获log信息
        new CrashHandler().init(context);
        RequestManager.init(context);
        //设置图片缓存
        createImageCache();
        /**
         * filter.xml中定义是否需要验证ssl证书（test、staging环境没有申请正式的ssl证书）
         */
        if (context.getResources().getBoolean(R.bool.needSll)) {
            FakeX509TrustManager.allowAllSSL();
        }
    }

    /**
     * 默认使用磁盘缓存
     */
    private void createImageCache() {
        ImageCacheManager.getInstance().init(this,
                this.getPackageCodePath()
                , DISK_IMAGECACHE_SIZE
                , DISK_IMAGECACHE_COMPRESS_FORMAT
                , DISK_IMAGECACHE_QUALITY
                , ImageCacheManager.CacheType.DISK);
    }

    public static Context getAppContext() {
        return context;
    }
}
