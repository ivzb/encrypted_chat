package com.ivzb.encrypted_chat._base.ui;

import android.content.Context;

import com.ivzb.encrypted_chat._base.ui._contracts.BaseView;

public abstract class DefaultPresenter<V extends BaseView> {

    protected Context mContext;
    protected V mView;
}