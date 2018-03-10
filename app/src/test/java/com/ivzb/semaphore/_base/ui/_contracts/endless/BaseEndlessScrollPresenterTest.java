package com.ivzb.semaphore._base.ui._contracts.endless;

import android.content.Context;

import com.ivzb.semaphore._base.data._contracts.entities.BaseEntity;
import com.ivzb.semaphore._base.data._contracts.sources.ReceiveDataSource;
import com.ivzb.semaphore._base.ui._contracts.BasePresenter;
import com.ivzb.semaphore._base.ui._contracts.BasePresenterTest;
import com.ivzb.semaphore._base.ui._contracts.BaseView;

public interface BaseEndlessScrollPresenterTest<M extends BaseEntity, P extends BasePresenter, V extends BaseView, DS extends ReceiveDataSource>
    extends BasePresenterTest<P, V, DS> {

    DS getDataSource();

    M initEntity(String id);
    P initPresenter(Context context, V view, DS dataSource);
}
