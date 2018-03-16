package com.ivzb.semaphore._base.ui.endless_scroll;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivzb.semaphore._base.data._contracts.entities.BaseEntity;
import com.ivzb.semaphore._base.ui.DefaultView;
import com.ivzb.semaphore._base.ui._contracts.BaseAdapter;
import com.ivzb.semaphore._base.ui._contracts.endless_scroll.BaseEndlessScrollPresenter;
import com.ivzb.semaphore._base.ui._contracts.endless_scroll.BaseEndlessScrollView;
import com.ivzb.semaphore._base.ui._contracts.endless_scroll.BaseEndlessScrollViewModel;
import com.ivzb.semaphore.utils.ui.SwipeRefreshLayoutUtils;

import java.util.List;

import static com.ivzb.semaphore._base.data.config.DefaultConfig.INITIAL_PAGE;

public abstract class EndlessScrollView<M extends BaseEntity, P extends BaseEndlessScrollPresenter<M>, VM extends BaseEndlessScrollViewModel<M>>
        extends DefaultView<P, VM>
        implements BaseEndlessScrollView<M, P, VM> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflateFragment(inflater, container);

        buildViewModel(view, savedInstanceState).build();

        return view;
    }

    @Override
    public BaseEndlessScrollViewModel.Builder buildViewModel(View view, Bundle savedInstanceState) {
        BaseAdapter<M> adapter = initEndlessAdapter();
        LinearLayoutManager layoutManager = initLayoutManager(mContext);
        EndlessScrollListener recyclerScrollListener = initEndlessScrollListener(layoutManager);

        BaseEndlessScrollViewModel.Builder builder = (BaseEndlessScrollViewModel.Builder) mViewModel.builder(mContext);
        builder.setView(view);
        builder.setErrorClickListener(mErrorClickListener);
        builder.setSavedInstanceState(savedInstanceState);
        builder.setAdapter(adapter);
        builder.setSwipeRefreshListener(this);
        builder.setLayoutManager(layoutManager);
        builder.setRecyclerScrollListener(recyclerScrollListener);

        return builder;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode, null);
    }

    @Override
    public void addEntities(List<M> entities) {
        mViewModel.getAdapter().append(entities);
    }

    @Override
    public void clearEntities() {
        mViewModel.getAdapter().clear();
        mViewModel.getRecyclerScrollListener().resetState();
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh(mViewModel.getContainerId());
    }

    @Override
    public int getPage() {
        return mViewModel.getPage();
    }

    @Override
    public void setPage(int page) {
        mViewModel.setPage(page);
    }

    @Override
    public void setMore(boolean more) {
        mViewModel.setMore(more);

        if (mViewModel.getAdapter().size() == 0) {
            showEntities(false);
        }
    }

    protected LinearLayoutManager initLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    private EndlessScrollListener initEndlessScrollListener(LinearLayoutManager layoutManager) {
        return new EndlessScrollListener(layoutManager, mViewModel.getPage()) {

            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (!mViewModel.hasMore()) {
                    return;
                }

                mPresenter.load(mViewModel.getContainerId(), page);
            }
        };
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        SwipeRefreshLayoutUtils.setLoading(mViewModel.getRefreshLayout(), active);
    }

    @Override
    public void showEntities(boolean show) {
        mViewModel.getRecyclerView().setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showNoEntities(boolean show) {
        show &= !(mViewModel.getPage() > INITIAL_PAGE); // don't show "no entities" if already loaded entities
        int visibility = show ? View.VISIBLE : View.GONE;

        mViewModel.getIvNoEntities().setVisibility(visibility);
        mViewModel.getTvNoEntities().setVisibility(visibility);
    }

    @Override
    public void showErrorMessage(boolean show) {
        mViewModel.getCvError().setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setErrorMessage(String message) {
        mViewModel.getTvError().setText(message);
    }
}