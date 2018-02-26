package com.ivzb.encrypted_chat._base.ui._contracts.presenters;

import com.ivzb.encrypted_chat._base.ui._contracts.BasePresenter;

public interface BaseEndlessAdapterPresenter<M>
        extends BasePresenter {

    void refresh(String id);

    void load(String id, int page);

    void click(M entity);

    void result(int requestCode, int resultCode);
}