package com.cml.wodi.async;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;


/**
 * AsyncTaskLoader基础实现类，子类只需要实现onLoadInBackground()方法即可，如需重写其他方法，请参考此类,
 * 如果需要注册广播监听，需要重写receiverAction，registerReceiver方法
 *
 * @param <D>
 * @author teamlab
 */
public abstract class BaseAsyncLoader<D> extends AsyncTaskLoader<D> {

    public static final String EXTRA_PAGE_NO = "pageNo";
    public static final String EXTRA_PAGE_SIZE = "pageSize";

    public Integer pageNo = 1;
    public Integer pageSize = 10;

    private DataChangeReceiver<D> receiver;

    // loader查询的参数，可为null
    protected Bundle params;

    /**
     * 缓存数据，作为以后拓展使用
     */
    private D cacheData;

    public BaseAsyncLoader(Context context, Bundle params) {
        super(context);
        this.params = params;
    }

    /* Runs on the UI thread */
    @Override
    public void deliverResult(D data) {
        if (isReset()) {
            if (data != null) {
                onReleaseResources(data);
            }
            return;
        }

        D oldCache = cacheData;
        cacheData = data;

        if (isStarted()) {
            super.deliverResult(data);
        }

        // At this point we can release the resources associated with
        // 'oldApps' if needed; now that the new result is delivered we
        // know that it is no longer in use.
        if (oldCache != null) {
            onReleaseResources(oldCache);
        }
    }

    /**
     * Starts an asynchronous load of the contacts list data. When the result is
     * ready the callbacks will be called on the UI thread. If a previous load
     * has been completed and is still valid the result may be passed to the
     * callbacks immediately.
     * <p/>
     * Must be called from the UI thread
     */
    @Override
    protected void onStartLoading() {

        // 注册数据变化广播
        if (registerReceiver() && null == receiver) {

            receiver = new DataChangeReceiver<D>(this);

            IntentFilter filter = new IntentFilter();
            filter.addAction(receiverAction());

            getContext().registerReceiver(receiver, filter);
        }

        if (cacheData != null) {
            deliverResult(cacheData);
        }

        if (takeContentChanged() || cacheData == null) {
            forceLoad();
        }
    }

    /**
     * Must be called from the UI thread
     */
    @Override
    protected void onStopLoading() {

        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        // Ensure the loader is stopped
        onStopLoading();

        // 取消广播监听
        if (registerReceiver() && null != receiver) {
            getContext().unregisterReceiver(receiver);
            receiver = null;
        }

        // At this point we can release the resources associated with 'apps'
        // if needed.
        if (cacheData != null) {
            onReleaseResources(cacheData);
        }
    }

    @Override
    public void onCanceled(D data) {
        super.onCanceled(data);
        // At this point we can release the resources associated with 'apps'
        // if needed.
        onReleaseResources(data);
    }

    private void onReleaseResources(D data) {
        data = null;
    }

    /**
     * 是否将本loader注册数据变化监听器,默认不监听
     *
     * @return
     */
    public boolean registerReceiver() {
        return false;
    }

    public String receiverAction() {
        return null;
    }

    /**
     * 通知loader数据变化
     *
     * @author teamlab
     */
    static class DataChangeReceiver<D> extends BroadcastReceiver {

        private BaseAsyncLoader<D> loader;

        public DataChangeReceiver(BaseAsyncLoader<D> loader) {
            super();
            this.loader = loader;
        }

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(loader.receiverAction())) {

                // 页码
                if (intent.hasExtra(EXTRA_PAGE_NO)) {
                    loader.pageNo = intent.getIntExtra(
                            EXTRA_PAGE_NO, loader.pageNo);
                }

                // 每页数量
                if (intent.hasExtra(EXTRA_PAGE_NO)) {
                    loader.pageSize = intent.getIntExtra(
                            EXTRA_PAGE_NO, loader.pageSize);
                }

                loader.onContentChanged();
            }
        }

    }
}
