package com.ivzb.semaphore._base.ui._contracts;

import com.ivzb.semaphore._base.data._contracts.entities.BaseEntity;
import com.ivzb.semaphore._base.ui._contracts.endless.BaseEndlessScrollPresenter;
import com.ivzb.semaphore._base.ui._contracts.endless.BaseEndlessScrollView;
import com.ivzb.semaphore._base.ui._contracts.endless.BaseEndlessScrollViewModel;

public interface BaseViewTest<E extends BaseEntity, V extends BaseEndlessScrollView, P extends BaseEndlessScrollPresenter, VM extends BaseEndlessScrollViewModel> {

    V getView();
    P getPresenter();
    VM getViewModel();

    E initEntity(int id);
}
