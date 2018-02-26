package com.ivzb.encrypted_chat.users.ui;

import com.ivzb.encrypted_chat._base.ui._contracts.action_handlers.BaseAdapterActionHandler;
import com.ivzb.encrypted_chat._base.ui._contracts.presenters.BaseEndlessAdapterPresenter;
import com.ivzb.encrypted_chat._base.ui._contracts.view_models.BaseEndlessAdapterViewModel;
import com.ivzb.encrypted_chat._base.ui._contracts.views.BaseEndlessAdapterView;
import com.ivzb.encrypted_chat.users.data.UserEntity;

public class UsersContract {

    public interface View
            extends BaseEndlessAdapterView<UserEntity, Presenter, ViewModel>,
            BaseAdapterActionHandler<UserEntity> {

    }

    public interface Presenter extends BaseEndlessAdapterPresenter<UserEntity> {

    }

    public interface ViewModel extends BaseEndlessAdapterViewModel<UserEntity> {

    }
}
