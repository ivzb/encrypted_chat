package com.ivzb.encrypted_chat._base.ui.endless;

import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollViewModel;

public abstract class DefaultEndlessScrollViewModel<T>
        implements BaseEndlessScrollViewModel<T> {

    private int mPage;
    private boolean mHasMore;

    public DefaultEndlessScrollViewModel() {
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