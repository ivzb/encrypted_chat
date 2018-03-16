package com.ivzb.semaphore.users.ui;

import com.ivzb.semaphore._base.ui._contracts.endless_scroll.BaseEndlessScrollPresenter;
import com.ivzb.semaphore._base.ui._contracts.endless_scroll.BaseEndlessScrollView;
import com.ivzb.semaphore._base.ui._contracts.endless_scroll.BaseEndlessScrollViewModel;
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
