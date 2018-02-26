package com.ivzb.encrypted_chat.add_user.ui;

class AddUserViewModel implements AddUserContract.ViewModel {

    private String mEmail;

    @Override
    public String getEmail() {
        return mEmail;
    }

    @Override
    public void setEmail(String email) {
        mEmail = email;
    }
}
