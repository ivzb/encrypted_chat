package com.ivzb.encrypted_chat._base.ui.view_model;

import com.ivzb.encrypted_chat._base.ui._contracts.adapters.BaseAdapter;
import com.ivzb.encrypted_chat._base.ui._contracts.view_models.BaseEndlessAdapterViewModel;

public abstract class DefaultEndlessAdapterViewModel<T>
        implements BaseEndlessAdapterViewModel<T> {

    private int mPage;
    private boolean mHasMore;
    private BaseAdapter<T> mAdapter;

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

    @Override
    public BaseAdapter<T> getAdapter() {
        return mAdapter;
    }

    @Override
    public void setAdapter(BaseAdapter<T> adapter) {
        mAdapter = adapter;
    }
}