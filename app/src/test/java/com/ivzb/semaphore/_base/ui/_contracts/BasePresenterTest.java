package com.ivzb.semaphore._base.ui._contracts;

import android.content.Context;

import com.ivzb.semaphore._base.data._contracts.entities.BaseEntity;
import com.ivzb.semaphore._base.data._contracts.sources.ReceiveDataSource;

public interface BasePresenterTest<M extends BaseEntity, P extends BasePresenter, V extends BaseView, DS extends ReceiveDataSource> {

    Context getContext();
    V getView();
    DS getDataSource();

    M initEntity(String id);
    P initPresenter(Context context, V view, DS dataSource);
}

