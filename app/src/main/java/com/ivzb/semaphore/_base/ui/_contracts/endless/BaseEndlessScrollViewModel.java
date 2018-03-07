package com.ivzb.semaphore._base.ui._contracts.endless;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivzb.semaphore._base.data._contracts.entities.BaseEntity;
import com.ivzb.semaphore._base.ui._contracts.BaseAdapter;
import com.ivzb.semaphore._base.ui._contracts.BaseViewModel;
import com.ivzb.semaphore._base.ui.endless.DefaultEndlessScrollListener;
import com.ivzb.semaphore.utils.ui.ScrollChildSwipeRefreshLayout;

public interface BaseEndlessScrollViewModel<T extends BaseEntity>
        extends BaseViewModel {

    interface Builder {

        Builder setView(View view);
        Builder setErrorClickListener(View.OnClickListener listener);
        Builder setSavedInstanceState(Bundle savedInstanceState);
        Builder setAdapter(BaseAdapter adapter);
        Builder setLayoutManager(LinearLayoutManager layoutManager);
        Builder setRecyclerScrollListener(DefaultEndlessScrollListener listener);
        Builder setSwipeRefreshListener(SwipeRefreshLayout.OnRefreshListener listener);

        void build();
    }

    Builder builder(Context context);

    void saveInstanceState(Bundle savedInstanceState);

    BaseAdapter<T> getAdapter();

    Parcelable getEntitiesState();
    void setEntitiesState(Parcelable state);

    Parcelable getLayoutManagerState();
    void setLayoutManagerState(Parcelable state);

    DefaultEndlessScrollListener getRecyclerScrollListener();

    ScrollChildSwipeRefreshLayout getRefreshLayout();
    RecyclerView getRecyclerView();

    TextView getTvNoEntities();
    ImageView getIvNoEntities();

    CardView getCvError();
    TextView getTvError();

    int getPage();
    void setPage(int page);

    boolean hasMore();
    void setMore(boolean more);

    String getContainerId();
}