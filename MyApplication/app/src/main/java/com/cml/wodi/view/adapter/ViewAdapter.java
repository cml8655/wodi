package com.cml.wodi.view.adapter;

import android.database.DataSetObservable;
import android.database.DataSetObserver;

import com.cml.wodi.view.model.GameViewItem;

import java.util.ArrayList;
import java.util.List;

public abstract class ViewAdapter<T> {
    private List<T> adapter = new ArrayList<>();
    private DataSetObserver dataSetObserver;

    public T getItem(int position) {
        return adapter.get(position);
    }

    public void onDatasetChanged() {
        if (null != dataSetObserver) {
            dataSetObserver.onChanged();
        }
    }

    public int getCount() {
        return adapter.size();
    }

    public void remoteItem(int position) {
        adapter.remove(position);
    }

    public void add(T t) {
        this.adapter.add(t);
    }

    public void setDataSetObserver(DataSetObserver dataSetObserver) {
        this.dataSetObserver = dataSetObserver;
    }
}
