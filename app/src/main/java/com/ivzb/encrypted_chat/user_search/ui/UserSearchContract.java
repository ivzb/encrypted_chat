package com.ivzb.encrypted_chat.user_search.ui;

import com.ivzb.encrypted_chat._base.ui._contracts.presenters.BaseEndlessAdapterPresenter;
import com.ivzb.encrypted_chat._base.ui._contracts.view_models.BaseEndlessAdapterViewModel;
import com.ivzb.encrypted_chat._base.ui._contracts.views.BaseEndlessAdapterView;
import com.ivzb.encrypted_chat.users.data.UserEntity;

class UserSearchContract {

    interface View extends BaseEndlessAdapterView<UserEntity, Presenter, ViewModel> {

        void hideErrorMessage();

        void onUserClick(UserEntity user);
        void onAddUserClick(UserEntity user);

        void showUsers();
        void hideUsers();
    }

    interface Presenter extends BaseEndlessAdapterPresenter<UserEntity> {

        void searchUser(String email);
        void clickUser(UserEntity user);
        void clickAddUser(UserEntity user);
    }

    public interface ViewModel extends BaseEndlessAdapterViewModel<UserEntity> {

        String getEmail();
        void setEmail(String email);

        boolean isEmpty();
        void setEmpty(boolean empty);
    }
}