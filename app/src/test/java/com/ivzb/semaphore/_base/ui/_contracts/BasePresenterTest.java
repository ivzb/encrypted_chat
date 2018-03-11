package com.ivzb.semaphore._base.ui._contracts;

import android.content.Context;

public interface BasePresenterTest<P extends BasePresenter, V extends BaseView, DS> {

    Context getContext();
    V getView();
    DS getDataSource();

    P initPresenter(Context context, V view, DS dataSource);
}

