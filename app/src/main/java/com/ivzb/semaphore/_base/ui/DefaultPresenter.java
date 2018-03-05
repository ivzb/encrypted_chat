package com.ivzb.semaphore._base.ui;

import android.content.Context;

import com.ivzb.semaphore._base.ui._contracts.BaseView;

public abstract class DefaultPresenter<V extends BaseView> {

    protected Context mContext;
    protected V mView;
}