package com.ivzb.encrypted_chat._base.ui.endless;

import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollViewModel;

import static com.ivzb.encrypted_chat._base.data.config.DefaultConfig.NO_PAGE;

public abstract class DefaultEndlessScrollViewModel<T>
        implements BaseEndlessScrollViewModel<T> {

    private int mPage;
    private boolean mHasMore;

    public DefaultEndlessScrollViewModel() {
        mPage = NO_PAGE;
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