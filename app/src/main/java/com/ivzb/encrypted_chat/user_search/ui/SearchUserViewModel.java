package com.ivzb.encrypted_chat.user_search.ui;

import com.ivzb.encrypted_chat._base.ui.view_model.DefaultEndlessAdapterViewModel;
import com.ivzb.encrypted_chat.users.data.UserEntity;

class SearchUserViewModel
        extends DefaultEndlessAdapterViewModel<UserEntity>
        implements UserSearchContract.ViewModel {

    private String mEmail;
    private boolean mIsEmpty;

    public SearchUserViewModel() {
        mIsEmpty = true;
    }

    @Override
    public String getEmail() {
        return mEmail;
    }

    @Override
    public void setEmail(String email) {
        mEmail = email;
    }

    @Override
    public boolean isEmpty() {
        return mIsEmpty;
    }

    @Override
    public void setEmpty(boolean isEmpty) {
        mIsEmpty = isEmpty;
    }

    @Override
    public String getContainerId() {
        return null;
    }
}
