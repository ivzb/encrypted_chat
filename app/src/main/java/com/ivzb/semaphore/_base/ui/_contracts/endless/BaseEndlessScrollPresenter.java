package com.ivzb.semaphore._base.ui._contracts.endless;

import com.ivzb.semaphore._base.ui._contracts.BasePresenter;

public interface BaseEndlessScrollPresenter<M>
        extends BasePresenter {

    void refresh(String id);

    void load(String id, int page);

    void result(int requestCode, int resultCode, String message);
}