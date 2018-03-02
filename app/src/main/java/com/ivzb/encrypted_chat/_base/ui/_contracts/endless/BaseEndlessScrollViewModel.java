package com.ivzb.encrypted_chat._base.ui._contracts.endless;

import com.ivzb.encrypted_chat._base.ui._contracts.BaseViewModel;

public interface BaseEndlessScrollViewModel<T>
        extends BaseViewModel {

    int getPage();
    void setPage(int page);

    boolean hasMore();
    void setMore(boolean more);

//    BaseAdapter<T> getAdapter();
//    void setAdapter(BaseAdapter<T> adapter);

    String getContainerId();
}