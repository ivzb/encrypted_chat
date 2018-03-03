package com.ivzb.encrypted_chat._base.ui._contracts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface BaseView<P extends BasePresenter, VM extends BaseViewModel> {

    View inflateFragment(LayoutInflater inflater, ViewGroup container);

    void setPresenter(P presenter);
    void setViewModel(VM viewModel);

    void showErrorMessage(boolean show);
    void setErrorMessage(String message);

    void showSuccessMessage(boolean show);
    void setSuccessMessage(String message);

    void hideKeyboard();

    boolean isActive();
}