package com.ivzb.semaphore._base.ui.endless_scroll;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivzb.semaphore._base.data._contracts.entities.BaseEntity;
import com.ivzb.semaphore._base.ui.DefaultActionHandlerAdapter;
import com.ivzb.semaphore._base.ui.DefaultAdapter;
import com.ivzb.semaphore._base.ui.DefaultViewTest;
import com.ivzb.semaphore._base.ui._contracts.BaseAdapter;
import com.ivzb.semaphore._base.ui._contracts.BaseViewModel;
import com.ivzb.semaphore._base.ui._contracts.BaseViewTest;
import com.ivzb.semaphore._base.ui._contracts.endless.BaseEndlessScrollPresenter;
import com.ivzb.semaphore._base.ui._contracts.endless.BaseEndlessScrollView;
import com.ivzb.semaphore._base.ui._contracts.endless.BaseEndlessScrollViewModel;
import com.ivzb.semaphore.utils.ui.ScrollChildSwipeRefreshLayout;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.ivzb.semaphore._base.data.config.DefaultConfig.NO_ID;
import static junit.framework.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public abstract class EndlessScrollViewTest<E extends BaseEntity, V extends BaseEndlessScrollView, P extends BaseEndlessScrollPresenter, VM extends BaseEndlessScrollViewModel>
        extends DefaultViewTest<E, V, P, VM>
        implements BaseViewTest<E, V, P, VM> {

    @Before
    public void before() throws Exception {
        super.before();

        verify(getViewModel()).getPage();
        verify(getViewModel()).getContainerId();

        verify(getPresenter()).refresh(eq(NO_ID));
    }

    @Override
    protected void setupViewModel(BaseViewModel vm) {
        super.setupViewModel(vm);

        BaseEndlessScrollViewModel viewModel = (BaseEndlessScrollViewModel) vm;
        BaseEndlessScrollViewModel.Builder builder = (BaseEndlessScrollViewModel.Builder) mViewModelBuilder;

        when(builder.setAdapter(isA(BaseAdapter.class))).thenReturn(builder);
        when(builder.setSwipeRefreshListener(isA(SwipeRefreshLayout.OnRefreshListener.class))).thenReturn(builder);
        when(builder.setLayoutManager(isA(LinearLayoutManager.class))).thenReturn(builder);
        when(builder.setRecyclerScrollListener(isA(EndlessScrollListener.class))).thenReturn(builder);

        RecyclerView recyclerView = mock(RecyclerView.class);
        when(viewModel.getRecyclerView()).thenReturn(recyclerView);

        ScrollChildSwipeRefreshLayout refreshLayout = mock(ScrollChildSwipeRefreshLayout.class);
        when(viewModel.getRefreshLayout()).thenReturn(refreshLayout);

        when(viewModel.getContainerId()).thenReturn(NO_ID);
    }

    @Override
    public BaseViewModel.Builder initViewModelBuilder() {
        return mock(BaseEndlessScrollViewModel.Builder.class);
    }

    @Test
    public void initEndlessAdapter() {
        // act
        BaseAdapter adapter = getView().initEndlessAdapter();

        // assert
        assertTrue(adapter instanceof DefaultActionHandlerAdapter);
    }

    @Test
    public void addEntities() {
        // arrange
        List<E> entities = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            entities.add(initEntity(String.valueOf(i)));
        }

        DefaultAdapter<E> adapter = mock(DefaultAdapter.class);
        when(getViewModel().getAdapter()).thenReturn(adapter);

        // act
        getView().addEntities(entities);

        // assert
        verify(getViewModel()).getAdapter();
        verify(adapter).append(eq(entities));
    }

    @Test
    public void showEntities_true() {
        // act
        getView().showEntities(true);

        // assert
        verify(getViewModel()).getRecyclerView();
    }

    @Test
    public void showEntities_false() {
        // act
        getView().showEntities(false);

        // assert
        verify(getViewModel()).getRecyclerView();
    }

    @Test
    public void showNoEntities_true_withLoadedEntities() {
        showNoEntities(5, true, View.GONE);
    }

    @Test
    public void showNoEntities_true_withoutLoadedEntities() {
        showNoEntities(0, true, View.VISIBLE);
    }

    @Test
    public void showNoEntities_false_withLoadedEntities() {
        showNoEntities(5, false, View.GONE);
    }

    @Test
    public void showNoEntities_false_withoutLoadedEntities() {
        showNoEntities(0, false, View.GONE);
    }

    private void showNoEntities(
            int page,
            boolean noEntities,
            int visibility) {

        // arrange
        when(getViewModel().getPage()).thenReturn(page);

        ImageView ivNoEntities = mock(ImageView.class);
        when(getViewModel().getIvNoEntities()).thenReturn(ivNoEntities);

        TextView tvNoEntities = mock(TextView.class);
        when(getViewModel().getTvNoEntities()).thenReturn(tvNoEntities);

        // act
        getView().showNoEntities(noEntities);

        // assert
        verify(getViewModel(), times(2)).getPage();
        verify(getViewModel()).getIvNoEntities();
        verify(getViewModel()).getTvNoEntities();

        verify(ivNoEntities).setVisibility(eq(visibility));
        verify(tvNoEntities).setVisibility(eq(visibility));
    }

    @Test
    public void clearEntities() {
        // arrange
        BaseAdapter adapter = mock(BaseAdapter.class);
        when(getViewModel().getAdapter()).thenReturn(adapter);

        EndlessScrollListener recyclerScrollListener = mock(EndlessScrollListener.class);
        when(getViewModel().getRecyclerScrollListener()).thenReturn(recyclerScrollListener);

        // act
        getView().clearEntities();

        // assert
        verify(getViewModel()).getAdapter();
        verify(getViewModel()).getRecyclerScrollListener();
    }

    @Test
    public void setLoadingIndicator_active() {
        // act
        getView().setLoadingIndicator(true);

        // assert
        verify(getViewModel()).getRefreshLayout();
    }

    @Test
    public void setLoadingIndicator_inactive() {
        // act
        getView().setLoadingIndicator(false);

        // assert
        verify(getViewModel()).getRefreshLayout();
    }

    @Test
    public void getPage() {
        // act
        getView().getPage();

        // assert
        verify(getViewModel(), times(2)).getPage();
    }

    @Test
    public void setPage() {
        // arrange
        int page = 5;

        // act
        getView().setPage(page);

        // assert
        verify(getViewModel()).setPage(eq(5));
    }

    @Test
    public void setMore_true_emptyAdapter() {
        // arrange
        BaseAdapter adapter = mock(BaseAdapter.class);
        when(adapter.size()).thenReturn(0);
        when(getViewModel().getAdapter()).thenReturn(adapter);

        // act
        getView().setMore(true);

        // assert
        verify(getViewModel()).setMore(eq(true));
        verify(getViewModel()).getAdapter();
        verify(getViewModel()).getRecyclerView();
    }

    @Test
    public void setMore_true_adapterWithEntities() {
        // arrange
        BaseAdapter adapter = mock(BaseAdapter.class);
        when(adapter.size()).thenReturn(5);
        when(getViewModel().getAdapter()).thenReturn(adapter);

        // act
        getView().setMore(true);

        // assert
        verify(getViewModel()).setMore(eq(true));
        verify(getViewModel()).getAdapter();
    }

    @Test
    public void setMore_false_emptyAdapter() {
        // arrange
        BaseAdapter adapter = mock(BaseAdapter.class);
        when(adapter.size()).thenReturn(0);
        when(getViewModel().getAdapter()).thenReturn(adapter);

        // act
        getView().setMore(false);

        // assert
        verify(getViewModel()).setMore(eq(false));
        verify(getViewModel()).getAdapter();
        verify(getViewModel()).getRecyclerView();
    }

    @Test
    public void setMore_false_adapterWithEntities() {
        // arrange
        BaseAdapter adapter = mock(BaseAdapter.class);
        when(adapter.size()).thenReturn(5);
        when(getViewModel().getAdapter()).thenReturn(adapter);

        // act
        getView().setMore(false);

        // assert
        verify(getViewModel()).setMore(eq(false));
        verify(getViewModel()).getAdapter();
    }

    @Test
    public void onRefresh() {
        // act
        getView().onRefresh();

        // assert
        verify(getViewModel(), times(2)).getContainerId();
        verify(getPresenter(), times(2)).refresh(eq(NO_ID));
    }

    @Test
    public void onActivityResult() {
        // arrange
        int requestCode = 5;
        int resultCode = Activity.RESULT_OK;

        // act
        getView().onActivityResult(requestCode, resultCode, null);

        // assert
        verify(getPresenter()).result(eq(requestCode), eq(resultCode), isNull(String.class));
    }
}
