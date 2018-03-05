package com.ivzb.semaphore._base.ui;

import android.content.Context;

import com.ivzb.semaphore._base.ui._contracts.BaseEntityActionHandler;

public abstract class DefaultActionHandlerAdapter<T>
        extends DefaultAdapter<T> {

    protected BaseEntityActionHandler<T> mActionHandler;

    public DefaultActionHandlerAdapter(
            Context context,
            BaseEntityActionHandler<T> actionHandler) {

        super(context);
        mActionHandler = actionHandler;
    }
}