package com.ivzb.semaphore._base.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivzb.semaphore._base.ui._contracts.BasePresenter;
import com.ivzb.semaphore._base.ui._contracts.BaseView;
import com.ivzb.semaphore._base.ui._contracts.BaseViewModel;
import com.ivzb.semaphore._base.ui._contracts.BaseViewTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public abstract class DefaultViewTest<E, V extends BaseView, P extends BasePresenter, VM extends BaseViewModel>
        implements BaseViewTest<E, V, P, VM> {

    protected BaseView mFragment;
    protected BaseViewModel.Builder mViewModelBuilder;
    protected Context mContext;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);

        BasePresenter presenter = getPresenter();
        BaseViewModel viewModel = getViewModel();

        mFragment = initView();
        mFragment.setPresenter(presenter);
        mFragment.setViewModel(viewModel);

        mViewModelBuilder = initViewModelBuilder();

        setupViewModel(getViewModel());
        ViewMocks viewMocks = setupView();

        ((Fragment)mFragment).onCreateView(viewMocks.getLayoutInflater(), viewMocks.getViewGroup(), null);
        ((Fragment)mFragment).onResume();

        verify(getViewModel()).builder(isA(Context.class));
        verify(getPresenter()).start();
    }

    protected ViewMocks setupView() {
        LayoutInflater layoutInflater = mock(LayoutInflater.class);

        View view = mock(View.class);
        when(layoutInflater.inflate(anyInt(), isA(ViewGroup.class), anyBoolean())).thenReturn(view);

        ViewGroup viewGroup = mock(ViewGroup.class);

        mContext = mock(Context.class);
        when(viewGroup.getContext()).thenReturn(mContext);

        return new ViewMocks(layoutInflater, viewGroup);
    }

    protected void setupViewModel(BaseViewModel viewModel) {
        BaseViewModel.Builder builder = mViewModelBuilder;

        when(viewModel.builder(isA(Context.class))).thenReturn(builder);

        when(builder.setView(isA(View.class))).thenReturn(builder);
        when(builder.setErrorClickListener(isA(View.OnClickListener.class))).thenReturn(builder);

        when(builder.setSavedInstanceState(isA(Bundle.class))).thenReturn(builder);
        when(builder.setSavedInstanceState(null)).thenReturn(builder);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(getViewModel());
        verifyNoMoreInteractions(getPresenter());
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(getView());
    }

    @Test
    public void onSaveInstanceState() {
        // act
        ((Fragment) getView()).onSaveInstanceState(null);

        // assert
        verify(getViewModel()).saveInstanceState(null);
    }

    @Test
    public void onClickError() {
        // act
        getView().onClickError();

        // assert
        verify(getPresenter()).clickError();
    }

    private class ViewMocks {

        private LayoutInflater mLayoutInflater;
        private ViewGroup mViewGroup;

        public ViewMocks(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            mLayoutInflater = layoutInflater;
            mViewGroup = viewGroup;
        }

        public LayoutInflater getLayoutInflater() {
            return mLayoutInflater;
        }

        public ViewGroup getViewGroup() {
            return mViewGroup;
        }
    }
}

