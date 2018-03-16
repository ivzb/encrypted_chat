package com.ivzb.semaphore._base.ui._contracts.endless_scroll;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.ivzb.semaphore._base.ui._contracts.BaseAdapter;
import com.ivzb.semaphore._base.ui._contracts.BasePresenter;
import com.ivzb.semaphore._base.ui._contracts.BaseView;

import java.util.List;

public interface BaseEndlessScrollView<M, P extends BasePresenter, VM extends BaseEndlessScrollViewModel<?>>
        extends BaseView<P, VM>, SwipeRefreshLayout.OnRefreshListener {

    void onActivityResult(int requestCode, int resultCode, Intent data);

    BaseAdapter<M> initEndlessAdapter();

    BaseEndlessScrollViewModel.Builder buildViewModel(android.view.View view, Bundle savedInstanceState);

    void addEntities(List<M> entities);
    void showEntities(boolean show);
    void showNoEntities(boolean show);
    void clearEntities();

    void setLoadingIndicator(boolean active);

    int getPage();
    void setPage(int page);

    void setMore(boolean more);
}