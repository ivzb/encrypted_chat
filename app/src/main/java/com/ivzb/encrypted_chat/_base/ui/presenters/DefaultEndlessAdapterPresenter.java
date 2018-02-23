package com.ivzb.encrypted_chat._base.ui.presenters;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ivzb.encrypted_chat._base.data.Result;
import com.ivzb.encrypted_chat._base.data._contracts.ReceiveDataSource;
import com.ivzb.encrypted_chat._base.data.callbacks.LoadCallback;
import com.ivzb.encrypted_chat._base.ui.DefaultPresenter;
import com.ivzb.encrypted_chat._base.ui._contracts.presenters.BaseEndlessAdapterPresenter;
import com.ivzb.encrypted_chat._base.ui._contracts.views.BaseEndlessAdapterView;

import java.util.List;

import static com.ivzb.encrypted_chat._base.DefaultConfig.PAGE;
import static com.ivzb.encrypted_chat.utils.Preconditions.checkNotNull;

public abstract class DefaultEndlessAdapterPresenter<M, V extends BaseEndlessAdapterView, DS extends ReceiveDataSource<M>>
        extends DefaultPresenter<V>
        implements BaseEndlessAdapterPresenter<M> {

    protected final DS mDataSource;

    public DefaultEndlessAdapterPresenter(
            @NonNull Context context,
            @NonNull V view,
            @NonNull DS dataSource) {

        mContext = checkNotNull(context, "context cannot be null");
        mView = checkNotNull(view, "view cannot be null");
        mDataSource = checkNotNull(dataSource, "dataSource cannot be null");
    }

    @Override
    public void start() {

    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void refresh(String id) {
        mView.clear();
        mView.setMore(true);
        load(id, PAGE);
    }

    @Override
    public void load(String id, final int page) {
        if (!mView.isActive()) return;

        mView.setLoadingIndicator(true);
        mView.setMore(true);

        mDataSource.load(id, page, mLoadCallback);
    }

    @Override
    public void click(M entity) {
        if (!mView.isActive()) return;

        if (entity == null) {
            mView.showErrorMessage("Missing achievement");
            return;
        }

        mView.openUi(entity);
    }

    protected LoadCallback<M> mLoadCallback = new LoadCallback<M>() {
        @Override
        public void onSuccess(Result<List<M>> result, int page) {
            if (!mView.isActive()) return;

            mView.setPage(page);
            mView.setLoadingIndicator(false);

            List<M> entities = result.getResults();
            mView.show(entities);
        }

        @Override
        public void onNoMore() {
            if (!mView.isActive()) return;

            mView.setLoadingIndicator(false);
            mView.setMore(false);
        }

        @Override
        public void onFailure(String message) {
            if (!mView.isActive()) return;

            mView.setLoadingIndicator(false);
            mView.setMore(false);
            mView.showErrorMessage(message);
        }
    };
}