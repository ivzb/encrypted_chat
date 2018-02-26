package com.ivzb.encrypted_chat.auth.ui;

import com.ivzb.encrypted_chat._base.ui._contracts.BasePresenter;
import com.ivzb.encrypted_chat._base.ui._contracts.BaseView;
import com.ivzb.encrypted_chat._base.ui._contracts.BaseViewModel;

public class AuthContract {

    public interface View
            extends BaseView<Presenter, ViewModel> {

        void showLoading(boolean loading);
        void navigateToHome();
        void hideErrorMessage();
    }

    public interface Presenter extends BasePresenter {

        void login(String email, String password);
        void register(String email, String password);
    }

    public interface ViewModel extends BaseViewModel {

        String getEmail();
        void setEmail(String email);

        String getPassword();
        void setPassword(String password);
    }
}