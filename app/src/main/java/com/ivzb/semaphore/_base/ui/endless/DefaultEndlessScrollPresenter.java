package com.ivzb.semaphore._base.ui.endless;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ivzb.semaphore._base.data.Result;
import com.ivzb.semaphore._base.data._contracts.sources.ReceiveDataSource;
import com.ivzb.semaphore._base.data.callbacks.LoadCallback;
import com.ivzb.semaphore._base.ui.DefaultPresenter;
import com.ivzb.semaphore._base.ui._contracts.endless.BaseEndlessScrollPresenter;
import com.ivzb.semaphore._base.ui._contracts.endless.BaseEndlessScrollView;

import java.util.List;

import static com.ivzb.semaphore._base.data.config.DefaultConfig.INITIAL_PAGE;
import static com.ivzb.semaphore.utils.Preconditions.checkNotNull;

public abstract class DefaultEndlessScrollPresenter<M, V extends BaseEndlessScrollView, DS extends ReceiveDataSource<M>>
        extends DefaultPresenter<V>
        implements BaseEndlessScrollPresenter<M> {

    protected final DS mDataSource;

    public DefaultEndlessScrollPresenter(
            @NonNull Context context,
            @NonNull V view,
            @NonNull DS dataSource) {

        super(context, view);
        mDataSource = checkNotNull(dataSource, "dataSource cannot be null");
    }

    @Override
    public void start() {

    }

    @Override
    public void result(int requestCode, int resultCode, String message) {

    }

    @Override
    public void refresh(String id) {
        mView.clearEntities();

        load(id, INITIAL_PAGE);
    }

    @Override
    public void load(String id, final int page) {
        if (!mView.isActive()) return;

        mView.setLoadingIndicator(true);
        mView.setMore(true);
        mView.setPage(page);

        mDataSource.load(id, page, mLoadCallback);
    }

    private LoadCallback<M> mLoadCallback = new LoadCallback<M>() {
        @Override
        public void onSuccess(Result<List<M>> result) {
            if (!mView.isActive()) return;

            mView.setLoadingIndicator(false);

            List<M> entities = result.getResults();

            mView.showErrorMessage(false);
            mView.setErrorMessage("");

            mView.showNoEntities(false);

            mView.showEntities(true);
            mView.addEntities(entities);
        }

        @Override
        public void onNoMore() {
            if (!mView.isActive()) return;

            mView.showErrorMessage(false);
            mView.setErrorMessage("");

            mView.setLoadingIndicator(false);
            mView.setMore(false);

            mView.showNoEntities(true);
        }

        @Override
        public void onFailure(String message) {
            if (!mView.isActive()) return;

            mView.setLoadingIndicator(false);
            mView.setMore(false);

            mView.showEntities(false);
            mView.showNoEntities(false);

            mView.setErrorMessage(message);
            mView.showErrorMessage(true);
        }
    };
}