package com.ivzb.encrypted_chat.add_user.ui;

import com.ivzb.encrypted_chat._base.ui._contracts.BasePresenter;
import com.ivzb.encrypted_chat._base.ui._contracts.BaseView;
import com.ivzb.encrypted_chat._base.ui._contracts.BaseViewModel;

public class AddUserContract {

    interface View extends BaseView<Presenter, ViewModel> {

        void finish();
    }

    interface Presenter extends BasePresenter {

        void saveUser(String email);
    }

    public interface ViewModel extends BaseViewModel {

        String getEmail();
        void setEmail(String email);
    }
}