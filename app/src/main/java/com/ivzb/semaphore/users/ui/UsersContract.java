package com.ivzb.semaphore.users.ui;

import com.ivzb.semaphore._base.ui._contracts.endless.BaseEndlessScrollPresenter;
import com.ivzb.semaphore._base.ui._contracts.endless.BaseEndlessScrollView;
import com.ivzb.semaphore._base.ui._contracts.endless.BaseEndlessScrollViewModel;
import com.ivzb.semaphore.users.data.UserEntity;

public class UsersContract {

    public interface View extends BaseEndlessScrollView<UserEntity, Presenter, ViewModel> {

        void onClickOpenConversation(UserEntity user);
        void onClickAddUser(UserEntity user);
        void onClickRemoveUser(UserEntity user);

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
