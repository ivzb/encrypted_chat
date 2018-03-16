package com.ivzb.semaphore._base.ui.endless_scroll;

import com.ivzb.semaphore._base.data.Result;
import com.ivzb.semaphore._base.data._contracts.entities.BaseEntity;
import com.ivzb.semaphore._base.data._contracts.sources.ReceiveDataSource;
import com.ivzb.semaphore._base.data.callbacks.LoadCallback;
import com.ivzb.semaphore._base.ui.DefaultPresenterTest;
import com.ivzb.semaphore._base.ui._contracts.endless_scroll.BaseEndlessScrollPresenter;
import com.ivzb.semaphore._base.ui._contracts.endless_scroll.BaseEndlessScrollPresenterTest;
import com.ivzb.semaphore._base.ui._contracts.endless_scroll.BaseEndlessScrollView;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public abstract class DefaultEndlessScrollPresenterTest<E extends BaseEntity, P extends BaseEndlessScrollPresenter, V extends BaseEndlessScrollView, DS extends ReceiveDataSource<E>>
        extends DefaultPresenterTest<P, V, DS>
        implements BaseEndlessScrollPresenterTest<E, P, V, DS> {

    @Captor protected ArgumentCaptor<List<E>> mAddEntitiesCaptor;

    protected String mId;

    protected List<E> mExpectedAddEntities;
    protected boolean mExpectedShowEntities;

    protected static final String sLoadFailure = "Could not load entities";

    @Test
    public void refresh() {
        // arrange
        when(getView().isActive()).thenReturn(true);
        int page = 0;

        arrangeLoad(
                mId,
                true,
                true,
                true,
                page);

        // act
        mPresenter.refresh(mId);

        // assert
        verify(getView()).clearEntities();
        verify(getView(), times(1)).setMore(anyBoolean());
        assertSuccessfulLoad(mId, page);
    }

    @Test
    public void load_firstPage_successful() {
        int page = 0;

        arrangeLoad(
                mId,
                true,
                true,
                true,
                page);

        actLoad(mId, page);

        verify(getView()).setMore(anyBoolean());
        assertSuccessfulLoad(mId, page);
    }

    @Test
    public void load_firstPage_failure() {
        int page = 0;

        arrangeLoad(
                mId,
                false,
                true,
                true,
                page);

        actLoad(mId, page);

        assertFailureLoad(mId, page);
    }

    @Test
    public void load_thirdPage_successful() {
        int page = 9;

        arrangeLoad(
                mId,
                true,
                true,
                true,
                page);

        actLoad(mId, page);

        verify(getView()).setMore(anyBoolean());
        assertSuccessfulLoad(mId, page);
    }

    @Test
    public void load_thirdPage_failure() {
        int page = 9;

        arrangeLoad(
                mId,
                false,
                true,
                true,
                page);

        actLoad(mId, page);

        assertFailureLoad(mId, page);
    }

    @Test
    public void load_initiallyInactiveView() {
        int page = 0;

        arrangeLoad(
                mId,
                null,
                false,
                null,
                page);

        actLoad(mId, page);

        // assert
        verify(getView()).isActive();
        verifyNoMoreInteractions(getView());
    }

    @Test
    public void load_callbackInactiveView() {
        int page = 0;

        arrangeLoad(
                mId,
                true,
                true,
                false,
                page);

        actLoad(mId, page);

        // assert
        verify(getView()).setLoadingIndicator(true);
        verify(getView()).setPage(eq(page));

        verify(getDataSource()).load(eq(mId), eq(page), any(LoadCallback.class));
        verify(getView()).setMore(anyBoolean());
        verify(getView(), times(2)).isActive();
        verifyNoMoreInteractions(getView());
    }

    protected void arrangeLoad(
            final String id,
            final Boolean isSuccessful,
            final Boolean initiallyInactiveView,
            final Boolean callbackInactiveView,
            final int page) {

        mAddEntitiesCaptor = ArgumentCaptor.forClass(List.class);

        when(getView().isActive()).thenReturn(initiallyInactiveView);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                LoadCallback<E> callback = (LoadCallback<E>) invocation.getArguments()[2];

                when(getView().isActive()).thenReturn(callbackInactiveView);

                if (isSuccessful) {
                    mExpectedAddEntities = generate(page);
                    Result<List<E>> result = new Result<>(mExpectedAddEntities, "message here");
                    callback.onSuccess(result);

                    mExpectedShowEntities = true;
                    return null;
                }

                callback.onFailure(sLoadFailure);
                return null;
            }
        }).when(getDataSource()).load(
                eq(id),
                any(int.class),
                any(LoadCallback.class));
    }

    protected void actLoad(String id, int page) {
        mPresenter.load(id, page);
    }

    protected void assertSuccessfulLoad(String id, int page) {
        verify(getView()).setLoadingIndicator(true);
        verify(getView()).setMore(true);
        verify(getView()).setPage(eq(page));

        verify(getDataSource()).load(eq(id), eq(page), any(LoadCallback.class));

        verify(getView()).setLoadingIndicator(eq(false));

        verify(getView()).setErrorMessage(eq(""));
        verify(getView()).showErrorMessage(false);
        verify(getView()).showNoEntities(false);
        verify(getView()).showEntities(true);
        verify(getView()).addEntities(mAddEntitiesCaptor.capture());
        verify(getView(), times(2)).isActive();
        verifyNoMoreInteractions(getView());

        List<E> actualAddEntities = mAddEntitiesCaptor.getValue();
        assertTrue(mExpectedAddEntities == actualAddEntities);
    }

    protected void assertFailureLoad(String id, int page) {
        verify(getView()).setLoadingIndicator(true);
        verify(getView()).setPage(eq(page));

        verify(getDataSource()).load(eq(id), eq(page), any(LoadCallback.class));

        verify(getView()).setLoadingIndicator(false);
        verify(getView()).setMore(false);

        verify(getView()).showEntities(false);
        verify(getView()).showNoEntities(false);

        verify(getView()).setErrorMessage(anyString());
        verify(getView()).showErrorMessage(true);

        verify(getView(), times(2)).setMore(anyBoolean());
        verify(getView(), times(2)).isActive();
        verifyNoMoreInteractions(getView());
    }

    protected List<E> generate(int page) {
        List<E> entities = new ArrayList<>();
        int end = 9 * page;

        for (int id = 0; id < end; id++) {
            E entity = initEntity(String.valueOf(id));
            entities.add(entity);
        }

        return entities;
    }
}

