package com.ivzb.encrypted_chat._base.ui._contracts;

public interface BaseView<P extends BasePresenter, VM extends BaseViewModel> {

    void setPresenter(P presenter);
    void setViewModel(VM viewModel);

    void showErrorMessage(boolean show);
    void setErrorMessage(String message);

    void hideKeyboard();

    boolean isActive();
}