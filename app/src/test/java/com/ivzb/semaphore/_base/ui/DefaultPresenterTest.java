package com.ivzb.semaphore._base.ui;

import android.content.Context;

import com.ivzb.semaphore._base.data._contracts.entities.BaseEntity;
import com.ivzb.semaphore._base.data._contracts.sources.BaseDataSource;
import com.ivzb.semaphore._base.data.generators.DefaultGeneratorConfig;
import com.ivzb.semaphore._base.ui._contracts.BasePresenter;
import com.ivzb.semaphore._base.ui._contracts.BasePresenterTest;
import com.ivzb.semaphore._base.ui._contracts.BaseView;
import com.ivzb.semaphore._base.ui._contracts.endless.BaseEndlessScrollPresenter;
import com.ivzb.semaphore._base.ui._contracts.endless.BaseEndlessScrollView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Random;

import io.bloco.faker.Faker;

import static org.mockito.Mockito.verifyNoMoreInteractions;

public abstract class DefaultPresenterTest<P extends BasePresenter, V extends BaseView, DS>
        implements BasePresenterTest<P, V, DS> {

    @Mock
    protected Context mContext;

    protected P mPresenter;

    @Override
    public Context getContext() {
        return mContext;
    }

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        DefaultGeneratorConfig.destroyInstance();
        DefaultGeneratorConfig.initialize(new Random(), new Faker());

        mPresenter = initPresenter(getContext(), getView(), getDataSource());
    }

    @After
    public void after() {
        verifyNoMoreInteractions(getContext());
        verifyNoMoreInteractions(getView());
        verifyNoMoreInteractions(getDataSource());
    }

    @Test(expected = NullPointerException.class)
    public void nullContext_shouldThrow() {
        initPresenter(null, getView(), getDataSource());
    }

    @Test(expected = NullPointerException.class)
    public void nullView_shouldThrow() {
        initPresenter(getContext(), null, getDataSource());
    }

    @Test(expected = NullPointerException.class)
    public void nullDataSource_shouldThrow() {
        initPresenter(getContext(), getView(), null);
    }

    @Test
    public void start_shouldDoNothing() {
        mPresenter.start();
    }
}

