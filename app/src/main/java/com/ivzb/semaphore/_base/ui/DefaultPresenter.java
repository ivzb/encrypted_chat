package com.ivzb.semaphore._base.ui;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ivzb.semaphore._base.ui._contracts.BaseView;

import static com.ivzb.semaphore.utils.Preconditions.checkNotNull;

public abstract class DefaultPresenter<V extends BaseView> {

    protected Context mContext;
    protected V mView;

    public DefaultPresenter(
            @NonNull Context context,
            @NonNull V view) {

        mContext = checkNotNull(context, "context cannot be null");
        mView = checkNotNull(view, "view cannot be null");
    }
}