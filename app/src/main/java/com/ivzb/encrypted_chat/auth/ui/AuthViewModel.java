package com.ivzb.encrypted_chat.auth.ui;

public class AuthViewModel implements AuthContract.ViewModel {

    private String mEmail;
    private String mPassword;

    @Override
    public String getEmail() {
        return mEmail;
    }

    @Override
    public void setEmail(String email) {
        mEmail = email;
    }

    @Override
    public String getPassword() {
        return mPassword;
    }

    @Override
    public void setPassword(String password) {
        mPassword = password;
    }
}