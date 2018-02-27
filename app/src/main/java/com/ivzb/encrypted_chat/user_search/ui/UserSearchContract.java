package com.ivzb.encrypted_chat.user_search.ui;

import com.ivzb.encrypted_chat._base.ui._contracts.action_handlers.BaseAdapterActionHandler;
import com.ivzb.encrypted_chat._base.ui._contracts.presenters.BaseEndlessAdapterPresenter;
import com.ivzb.encrypted_chat._base.ui._contracts.view_models.BaseEndlessAdapterViewModel;
import com.ivzb.encrypted_chat._base.ui._contracts.views.BaseEndlessAdapterView;
import com.ivzb.encrypted_chat.users.data.UserEntity;

class UserSearchContract {

    interface View extends BaseEndlessAdapterView<UserEntity, Presenter, ViewModel>, BaseAdapterActionHandler<UserEntity> {

        void hideErrorMessage();
    }

    interface Presenter extends BaseEndlessAdapterPresenter<UserEntity> {

        void clickUser(UserEntity user);
        //void saveUser(String userId);
    }

    public interface ViewModel extends BaseEndlessAdapterViewModel<UserEntity> {

        String getEmail();
        void setEmail(String email);
    }
}