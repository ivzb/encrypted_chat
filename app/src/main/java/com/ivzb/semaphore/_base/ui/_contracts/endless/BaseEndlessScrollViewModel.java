package com.ivzb.semaphore._base.ui._contracts.endless;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivzb.semaphore._base.data._contracts.entities.BaseEntity;
import com.ivzb.semaphore._base.ui._contracts.BaseAdapter;
import com.ivzb.semaphore._base.ui._contracts.BaseViewModel;
import com.ivzb.semaphore.utils.ui.ScrollChildSwipeRefreshLayout;

public interface BaseEndlessScrollViewModel<T extends BaseEntity>
        extends BaseViewModel {

    void init(View view);

    void saveInstanceState(Bundle savedInstanceState);
    void restoreInstanceState(Bundle savedInstanceState);

    void setErrorClickListener(View.OnClickListener listener);

    BaseAdapter<T> getAdapter();
    void setAdapter(BaseAdapter<T> adapter);

    Parcelable getEntitiesState();
    void setEntitiesState(Parcelable state);

    Parcelable getLayoutManagerState();
    void setLayoutManagerState(Parcelable state);

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