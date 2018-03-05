package com.ivzb.encrypted_chat._base.ui._contracts.endless;

import com.ivzb.encrypted_chat._base.ui._contracts.BasePresenter;

public interface BaseEndlessScrollPresenter<M>
        extends BasePresenter {

    void refresh(String id);

    void load(String id, int page);

    void clickError();

    void result(int requestCode, int resultCode, String message);
}