package com.ivzb.semaphore._base.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivzb.semaphore._base.ui._contracts.BasePresenter;
import com.ivzb.semaphore._base.ui._contracts.BaseView;
import com.ivzb.semaphore._base.ui._contracts.BaseViewModel;
import com.ivzb.semaphore.utils.KeyboardUtils;

public abstract class DefaultView<P extends BasePresenter, VM extends BaseViewModel>
        extends Fragment
        implements BaseView<P, VM> {

    protected Context mContext;

    protected P mPresenter;
    protected VM mViewModel;

    private Snackbar mErrorMessage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = container.getContext();

        setHasOptionsMenu(true);
        setRetainInstance(true);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mViewModel != null) {
            mViewModel.saveInstanceState(outState);
        }
    }

    @Override
    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setViewModel(VM viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void hideKeyboard() {
        KeyboardUtils.hideKeyboard(getActivity());
    }

    @Override
    public void setErrorMessage(String message) {
        if (getView() == null || !isActive()) return;

        mErrorMessage = Snackbar.make(getView(), message, Snackbar.LENGTH_LONG);
    }

    @Override
    public void showErrorMessage(boolean show) {
        if (getView() == null || !isActive() || mErrorMessage == null) return;

        mErrorMessage.show();
    }

    @Override
    public void setSuccessMessage(String message) {
        if (getView() == null || !isActive()) return;

        mErrorMessage = Snackbar.make(getView(), message, Snackbar.LENGTH_LONG);
    }

    @Override
    public void showSuccessMessage(boolean show) {
        if (getView() == null || !isActive() || mErrorMessage == null) return;

        mErrorMessage.show();
    }
}