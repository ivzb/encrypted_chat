package com.ivzb.semaphore._base.ui._contracts;

public interface BaseViewTest<E, V extends BaseView, P extends BasePresenter, VM extends BaseViewModel> {

    V getView();
    P getPresenter();
    VM getViewModel();

    E initEntity(String id);
    V initView();
    VM.Builder initViewModelBuilder();
}
