package com.ivzb.encrypted_chat._base.ui.adapters;

import android.content.Context;

import com.ivzb.encrypted_chat._base.ui._contracts.action_handlers.BaseAdapterActionHandler;

public abstract class DefaultActionHandlerAdapter<T>
        extends DefaultAdapter<T> {

    protected BaseAdapterActionHandler<T> mActionHandler;

    public DefaultActionHandlerAdapter(
            Context context,
            BaseAdapterActionHandler<T> actionHandler) {

        super(context);
        mActionHandler = actionHandler;
    }
}