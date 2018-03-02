package com.ivzb.encrypted_chat.users.ui;

import com.ivzb.encrypted_chat._base.ui.endless.DefaultEndlessScrollViewModel;
import com.ivzb.encrypted_chat.users.data.UserEntity;

class UsersViewModel
        extends DefaultEndlessScrollViewModel<UserEntity>
        implements UsersContract.ViewModel {

    @Override
    public String getContainerId() {
        return null;
    }
}