package com.ivzb.semaphore._base.ui._contracts;

import android.content.Context;

import com.ivzb.semaphore._base.data._contracts.entities.BaseEntity;
import com.ivzb.semaphore._base.data._contracts.sources.BaseDataSource;
import com.ivzb.semaphore._base.data._contracts.sources.ReceiveDataSource;

public interface BasePresenterTest<P extends BasePresenter, V extends BaseView, DS> {

    Context getContext();
    V getView();
    DS getDataSource();

    P initPresenter(Context context, V view, DS dataSource);
}

