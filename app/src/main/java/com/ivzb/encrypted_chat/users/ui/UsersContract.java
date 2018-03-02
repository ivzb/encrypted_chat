package com.ivzb.encrypted_chat.users.ui;

import com.ivzb.encrypted_chat._base.ui._contracts.BaseEntityActionHandler;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollPresenter;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollViewModel;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollView;
import com.ivzb.encrypted_chat.users.data.UserEntity;

public class UsersContract {

    public interface View
            extends BaseEndlessScrollView<UserEntity, Presenter, ViewModel>,
            BaseEntityActionHandler<UserEntity> {

    }

    public interface Presenter extends BaseEndlessScrollPresenter<UserEntity> {

    }

    public interface ViewModel extends BaseEndlessScrollViewModel<UserEntity> {

    }
}
