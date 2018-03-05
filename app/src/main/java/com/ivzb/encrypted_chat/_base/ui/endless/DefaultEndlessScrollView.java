package com.ivzb.encrypted_chat._base.ui.endless;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivzb.encrypted_chat._base.data._contracts.entities.BaseEntity;
import com.ivzb.encrypted_chat._base.data.config.DefaultConfig;
import com.ivzb.encrypted_chat._base.ui.DefaultView;
import com.ivzb.encrypted_chat._base.ui._contracts.BaseAdapter;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollPresenter;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollViewModel;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollView;
import com.ivzb.encrypted_chat._base.ui.DefaultActionHandlerAdapter;
import com.ivzb.encrypted_chat.utils.ui.SwipeRefreshLayoutUtils;

import java.util.List;

import static com.ivzb.encrypted_chat._base.data.config.DefaultConfig.INITIAL_PAGE;

public abstract class DefaultEndlessScrollView<M extends BaseEntity, P extends BaseEndlessScrollPresenter<M>, VM extends BaseEndlessScrollViewModel<M>>
        extends DefaultView<P, VM>
        implements BaseEndlessScrollView<M, P, VM> {

    protected BaseAdapter<M> mAdapter;

    private DefaultEndlessScrollListener mScrollListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflateFragment(inflater, container);

        mViewModel.init(view);
        mViewModel.setErrorClickListener(mErrorClickListener);
        mViewModel.restoreInstanceState(savedInstanceState);

        initEndlessAdapter();

        mViewModel.setAdapter(mAdapter);

        SwipeRefreshLayoutUtils.setup(
                getContext(),
                mViewModel.getRefreshLayout(),
                mViewModel.getRecyclerView(),
                this);

        if (savedInstanceState != null) {
            Parcelable entitiesState = mViewModel.getEntitiesState();
            mAdapter.onRestoreInstanceState(entitiesState);

            Parcelable layoutManagerState = mViewModel.getLayoutManagerState();
            mViewModel.getRecyclerView().getLayoutManager().onRestoreInstanceState(layoutManagerState);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mViewModel != null) {
            mViewModel.saveInstanceState(outState);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode, null);
    }

    @Override
    public void addEntities(List<M> entities) {
        mAdapter.add(entities);
    }

    @Override
    public void clearEntities() {
        mAdapter.clear();
        mScrollListener.resetState();
    }

    @Override
    public void openUi(M entity) {

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

        if (mAdapter.size() == 0) {
            showEntities(false);
        }
    }

    @Override
    public RecyclerView.LayoutManager instantiateLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    protected void initEndlessAdapter(
            Context context,
            DefaultActionHandlerAdapter<M> adapter,
            final RecyclerView recyclerView) {

        mAdapter = adapter;
        LinearLayoutManager layoutManager = (LinearLayoutManager) instantiateLayoutManager(context);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        mScrollListener = new DefaultEndlessScrollListener(
                layoutManager,
                mViewModel.getPage()) {

            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (!mViewModel.hasMore()) {
                    return;
                }

                mPresenter.load(mViewModel.getContainerId(), page);
            }
        };

        recyclerView.addOnScrollListener(mScrollListener);
    }

    @Override
    public void onClickError() {
        mPresenter.clickError();
    }

    private View.OnClickListener mErrorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onClickError();
        }
    };

    @Override
    public void setLoadingIndicator(boolean active) {
        if (!isActive()) return;

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