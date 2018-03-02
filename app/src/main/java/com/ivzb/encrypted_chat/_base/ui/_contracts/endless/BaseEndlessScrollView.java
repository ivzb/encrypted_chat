package com.ivzb.encrypted_chat._base.ui._contracts.endless;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.ivzb.encrypted_chat._base.ui._contracts.BasePresenter;
import com.ivzb.encrypted_chat._base.ui._contracts.BaseView;

import java.util.List;

public interface BaseEndlessScrollView<M, P extends BasePresenter, VM extends BaseEndlessScrollViewModel<?>>
        extends BaseView<P, VM>, SwipeRefreshLayout.OnRefreshListener {

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void setLoadingIndicator(boolean active);

    void addEntities(List<M> entities);
    void showEntities(boolean show);
    void showNoEntities(boolean show);
    void clearEntities();

    void openUi(M entity);

    int getPage();
    void setPage(int page);

    void setMore(boolean more);

    RecyclerView.LayoutManager instantiateLayoutManager(Context context);
}