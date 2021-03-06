package com.ivzb.semaphore._base.ui._contracts.endless_scroll;

import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivzb.semaphore._base.data._contracts.entities.BaseEntity;
import com.ivzb.semaphore._base.ui._contracts.BaseAdapter;
import com.ivzb.semaphore._base.ui._contracts.BaseViewModel;
import com.ivzb.semaphore._base.ui.endless_scroll.EndlessScrollListener;
import com.ivzb.semaphore.utils.ui.ScrollChildSwipeRefreshLayout;

public interface BaseEndlessScrollViewModel<E extends BaseEntity>
        extends BaseViewModel {

    interface Builder extends BaseViewModel.Builder {

        Builder setAdapter(BaseAdapter adapter);
        Builder setLayoutManager(LinearLayoutManager layoutManager);
        Builder setRecyclerScrollListener(EndlessScrollListener listener);
        Builder setSwipeRefreshListener(SwipeRefreshLayout.OnRefreshListener listener);
    }

    BaseAdapter<E> getAdapter();

    Parcelable getEntitiesState();
    void setEntitiesState(Parcelable state);

    Parcelable getLayoutManagerState();
    void setLayoutManagerState(Parcelable state);

    EndlessScrollListener getRecyclerScrollListener();

    ScrollChildSwipeRefreshLayout getRefreshLayout();
    RecyclerView getRecyclerView();
    LinearLayoutManager getLayoutManager();

    TextView getTvNoEntities();
    ImageView getIvNoEntities();

    int getPage();
    void setPage(int page);

    boolean hasMore();
    void setMore(boolean more);

    String getContainerId();
}