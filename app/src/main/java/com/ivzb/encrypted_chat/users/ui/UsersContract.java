package com.ivzb.encrypted_chat.users.ui;

import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollPresenter;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollView;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollViewModel;
import com.ivzb.encrypted_chat.users.data.UserEntity;

public class UsersContract {

    public interface View extends BaseEndlessScrollView<UserEntity, Presenter, ViewModel> {

        void onOpenConversation(UserEntity user);
        void onAddUser(UserEntity user);
        void onRemoveUser(UserEntity user);

        void openConversation(UserEntity user);
    }

    public interface Presenter extends BaseEndlessScrollPresenter<UserEntity> {

        void clickUser(UserEntity user);
        void clickAddUser(UserEntity user);
        void clickRemoveUser(UserEntity user);
    }

    public interface ViewModel extends BaseEndlessScrollViewModel<UserEntity> {

    }
}
