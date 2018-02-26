package com.ivzb.encrypted_chat.add_user.ui;

import com.ivzb.encrypted_chat._base.ui._contracts.BasePresenter;
import com.ivzb.encrypted_chat._base.ui._contracts.BaseView;
import com.ivzb.encrypted_chat._base.ui._contracts.BaseViewModel;

public class AddUserContract {

    interface View extends BaseView<Presenter, ViewModel> {

        void add(String userId);
        void finish();
    }

    interface Presenter extends BasePresenter {

        void saveUser(String userId);
    }

    public interface ViewModel extends BaseViewModel {

        String getUserId();
        void setUserId(String userId);
    }
}