package com.ivzb.encrypted_chat._base.ui.view_model;

import com.ivzb.encrypted_chat._base.ui._contracts.view_models.BaseEndlessAdapterViewModel;

public abstract class DefaultEndlessAdapterViewModel<T>
        implements BaseEndlessAdapterViewModel<T> {

    private int mPage;
    private boolean mHasMore;

    public DefaultEndlessAdapterViewModel() {
        mHasMore = true;
    }

    @Override
    public int getPage() {
        return mPage;
    }

    @Override
    public void setPage(int page) {
        mPage = page;
    }

    @Override
    public boolean hasMore() {
        return mHasMore;
    }

    @Override
    public void setMore(boolean more) {
        mHasMore = more;
    }
}