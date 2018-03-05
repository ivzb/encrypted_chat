package com.ivzb.encrypted_chat._base.ui.endless;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ivzb.encrypted_chat._base.data.Result;
import com.ivzb.encrypted_chat._base.data._contracts.sources.ReceiveDataSource;
import com.ivzb.encrypted_chat._base.data.callbacks.LoadCallback;
import com.ivzb.encrypted_chat._base.ui.DefaultPresenter;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollPresenter;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollView;

import java.util.List;

import static com.ivzb.encrypted_chat._base.data.config.DefaultConfig.INITIAL_PAGE;
import static com.ivzb.encrypted_chat.utils.Preconditions.checkNotNull;

public abstract class DefaultEndlessScrollPresenter<M, V extends BaseEndlessScrollView, DS extends ReceiveDataSource<M>>
        extends DefaultPresenter<V>
        implements BaseEndlessScrollPresenter<M> {

    protected final DS mDataSource;

    public DefaultEndlessScrollPresenter(
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
    public void result(int requestCode, int resultCode, String message) {

    }

    @Override
    public void refresh(String id) {
        mView.clearEntities();
        mView.setMore(true);
        mView.setPage(0);

        load(id, INITIAL_PAGE);
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
            mView.setErrorMessage("Missing entity.");
            mView.showErrorMessage(true);
            return;
        }

        mView.openUi(entity);
    }

    @Override
    public void clickError() {
        if (!mView.isActive()) return;

        mView.showErrorMessage(false);
        mView.setErrorMessage("");
    }

    private LoadCallback<M> mLoadCallback = new LoadCallback<M>() {
        @Override
        public void onSuccess(Result<List<M>> result, int page) {
            if (!mView.isActive()) return;

            mView.setPage(page);
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