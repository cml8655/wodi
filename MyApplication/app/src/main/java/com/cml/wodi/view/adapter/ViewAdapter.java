package com.cml.wodi.view.adapter;

import android.database.DataSetObservable;
import android.database.DataSetObserver;

import java.util.List;

public abstract class ViewAdapter<T> {
    private List<T> adapter;
    private DataSetObserver dataSetObserver;

    public T getItem(int position) {
        return adapter.get(position);
    }

    public void onDatasetChanged() {
        if (null != dataSetObserver) {
            dataSetObserver.onChanged();
        }
    }

    public void remoteItem(int position) {
        adapter.remove(position);
    }

    public void setAdapter(List<T> adapter) {
        this.adapter = adapter;
    }

    public void setDataSetObserver(DataSetObserver dataSetObserver) {
        this.dataSetObserver = dataSetObserver;
    }
}
