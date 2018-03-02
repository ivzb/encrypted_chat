package com.ivzb.encrypted_chat._base.ui._contracts;

import android.os.Parcelable;

import java.util.List;

public interface BaseAdapter<T> {

    void add(List<T> entities);
    void clear();
    int size();

    Parcelable onSaveInstanceState();
    void onRestoreInstanceState(Parcelable parcelable);
}