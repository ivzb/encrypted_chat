package com.ivzb.encrypted_chat.users.ui;

import com.ivzb.encrypted_chat._base.ui.view_model.DefaultEndlessAdapterViewModel;
import com.ivzb.encrypted_chat.users.data.UserEntity;

class UsersViewModel
        extends DefaultEndlessAdapterViewModel<UserEntity>
        implements UsersContract.ViewModel {

    @Override
    public String getContainerId() {
        return null;
    }
}