package com.ivzb.encrypted_chat._base.ui._contracts.view_models;

import com.ivzb.encrypted_chat._base.ui._contracts.BaseViewModel;
import com.ivzb.encrypted_chat._base.ui._contracts.adapters.BaseAdapter;

public interface BaseEndlessAdapterViewModel<T>
        extends BaseViewModel {

    int getPage();
    void setPage(int page);

    boolean hasMore();
    void setMore(boolean more);

//    BaseAdapter<T> getAdapter();
//    void setAdapter(BaseAdapter<T> adapter);

    String getContainerId();
}