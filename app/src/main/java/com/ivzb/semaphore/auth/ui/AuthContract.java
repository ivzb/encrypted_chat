package com.ivzb.semaphore.auth.ui;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.ivzb.semaphore._base.ui._contracts.BasePresenter;
import com.ivzb.semaphore._base.ui._contracts.BaseView;
import com.ivzb.semaphore._base.ui._contracts.BaseViewModel;

public class AuthContract {

    public interface View
            extends BaseView<Presenter, ViewModel> {

        void showLoading(boolean loading);
        void navigateToHome();
    }

    public interface Presenter extends BasePresenter {

        void login(String email, String password);
        void register(String email, String password);
    }

    public interface ViewModel extends BaseViewModel {

        interface Builder extends BaseViewModel.Builder {

            Builder setLoginClickListener(android.view.View.OnClickListener listener);
            Builder setRegisterClickListener(android.view.View.OnClickListener listener);

            void build();
        }

        EditText getEtEmail();
        EditText getEtPassword();

        ProgressBar getPbLoading();
        Button getBtnLogin();
        Button getBtnRegister();
    }
}