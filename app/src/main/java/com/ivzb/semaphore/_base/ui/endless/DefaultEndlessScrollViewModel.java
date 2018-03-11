package com.ivzb.semaphore._base.ui.endless;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivzb.semaphore.R;
import com.ivzb.semaphore._base.data._contracts.entities.BaseEntity;
import com.ivzb.semaphore._base.data.config.DefaultConfig;
import com.ivzb.semaphore._base.ui.DefaultViewModel;
import com.ivzb.semaphore._base.ui._contracts.BaseAdapter;
import com.ivzb.semaphore._base.ui._contracts.endless.BaseEndlessScrollViewModel;
import com.ivzb.semaphore.utils.ui.ScrollChildSwipeRefreshLayout;
import com.ivzb.semaphore.utils.ui.SwipeRefreshLayoutUtils;

import static com.ivzb.semaphore._base.data.config.DefaultConfig.NO_PAGE;

public abstract class DefaultEndlessScrollViewModel<T extends BaseEntity>
        extends DefaultViewModel
        implements BaseEndlessScrollViewModel<T> {

    private static final String PAGE_STATE = "page_state";

    private static final String ENTITIES_ADAPTER_STATE = "entities_adapter_state";
    private static final String ENTITIES_VISIBILITY_STATE = "entities_visibility_state";
    private static final String LAYOUT_MANAGER_STATE = "layout_manager_state";

    private static final String NO_ENTITIES_VISIBILITY_STATE = "no_entities_visibility_state";

    private BaseAdapter<T> mAdapter;
    private LinearLayoutManager mLayoutManager;

    private Parcelable mEntitiesState;
    private Parcelable mLayoutManagerState;
    private DefaultEndlessScrollListener mRecyclerScrollListener;

    private ScrollChildSwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    private ImageView mIvNoEntities;
    private TextView mTvNoEntities;

    private int mPage;
    private boolean mHasMore;

    public DefaultEndlessScrollViewModel() {
        mPage = NO_PAGE;
        mHasMore = true;
    }

    @Override
    public BaseEndlessScrollViewModel.Builder builder(Context context) {
        return new DefaultEndlessScrollViewModel.Builder(context);
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);

        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mIvNoEntities = view.findViewById(R.id.ivNoEntities);
        mTvNoEntities = view.findViewById(R.id.tvNoEntities);
    }

    @Override
    public void saveInstanceState(Bundle outState) {
        super.saveInstanceState(outState);

        savePageState(outState);
        saveEntitiesState(outState);
        saveNoEntitiesState(outState);
    }

    private void savePageState(Bundle outState) {
        outState.putInt(PAGE_STATE, getPage());
    }

    private void saveEntitiesState(Bundle outState) {
        if (mAdapter != null) {
            Parcelable usersState = mAdapter.onSaveInstanceState();
            outState.putParcelable(ENTITIES_ADAPTER_STATE, usersState);
        }

        if (mRecyclerView != null) {
            if (mRecyclerView.getLayoutManager() != null) {
                Parcelable layoutManagerState = mRecyclerView.getLayoutManager().onSaveInstanceState();
                outState.putParcelable(LAYOUT_MANAGER_STATE, layoutManagerState);
            }

            int usersVisibility = mRecyclerView.getVisibility();
            outState.putInt(ENTITIES_VISIBILITY_STATE, usersVisibility);
        }
    }

    private void saveNoEntitiesState(Bundle outState) {
        // get least visible
        int noEntitiesVisibility = Math.max(mIvNoEntities.getVisibility(), mTvNoEntities.getVisibility());
        outState.putInt(NO_ENTITIES_VISIBILITY_STATE, noEntitiesVisibility);
    }

    @Override
    protected void restoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null) return;

        super.restoreInstanceState(savedInstanceState);

        restorePageState(savedInstanceState);
        restoreEntitiesState(savedInstanceState);
        restoreNoEntitiesState(savedInstanceState);
    }

    private void restorePageState(Bundle state) {
        if (state.containsKey(PAGE_STATE)) {
            int page = state.getInt(PAGE_STATE);
            setPage(page);
        }
    }

    private void restoreEntitiesState(Bundle state) {
        if (state.containsKey(ENTITIES_ADAPTER_STATE)) {
            Parcelable usersState = state.getParcelable(ENTITIES_ADAPTER_STATE);
            setEntitiesState(usersState);
        }

        if (state.containsKey(LAYOUT_MANAGER_STATE)) {
            Parcelable layoutManagerState = state.getParcelable(LAYOUT_MANAGER_STATE);
            setLayoutManagerState(layoutManagerState);
        }

        if (state.containsKey(ENTITIES_VISIBILITY_STATE)) {
            int visibility = state.getInt(ENTITIES_VISIBILITY_STATE);
            getRecyclerView().setVisibility(visibility);
        }
    }

    private void restoreNoEntitiesState(Bundle state) {
        if (state.containsKey(NO_ENTITIES_VISIBILITY_STATE)) {
            int visibility = state.getInt(NO_ENTITIES_VISIBILITY_STATE);

            getIvNoEntities().setVisibility(visibility);
            getTvNoEntities().setVisibility(visibility);
        }
    }

    @Override
    public BaseAdapter<T> getAdapter() {
        return mAdapter;
    }

    private void setAdapter(BaseAdapter<T> adapter) {
        mAdapter = adapter;
    }

    private LinearLayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    private void setLayoutManager(LinearLayoutManager layoutManager) {
        mLayoutManager = layoutManager;
    }

    @Override
    public DefaultEndlessScrollListener getRecyclerScrollListener() {
        return mRecyclerScrollListener;
    }

    private void setRecyclerScrollListener(DefaultEndlessScrollListener scrollListener) {
        mRecyclerScrollListener = scrollListener;
    }

    @Override
    public Parcelable getEntitiesState() {
        return mEntitiesState;
    }

    @Override
    public void setEntitiesState(Parcelable state) {
        mEntitiesState = state;
    }

    @Override
    public Parcelable getLayoutManagerState() {
        return mLayoutManagerState;
    }

    @Override
    public void setLayoutManagerState(Parcelable state) {
        mLayoutManagerState = state;
    }

    @Override
    public ScrollChildSwipeRefreshLayout getRefreshLayout() {
        return mRefreshLayout;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @Override
    public TextView getTvNoEntities() {
        return mTvNoEntities;
    }

    @Override
    public ImageView getIvNoEntities() {
        return mIvNoEntities;
    }

    @Override
    public int getPage() {
        return mPage;
    }

    @Override
    public void setPage(int page) {
        mPage = page;
    }

    @Override
    public boolean hasMore() {
        return mHasMore;
    }

    @Override
    public void setMore(boolean more) {
        mHasMore = more;
    }

    @Override
    public String getContainerId() {
        return DefaultConfig.NO_ID;
    }

    public class Builder
            extends DefaultViewModel.Builder
            implements BaseEndlessScrollViewModel.Builder {

        private BaseAdapter mAdapter;
        private LinearLayoutManager mLayoutManager;
        private DefaultEndlessScrollListener mRecyclerScrollListener;
        private SwipeRefreshLayout.OnRefreshListener mSwipeRefreshListener;

        public Builder(Context context) {
            super(context);
        }

        @Override
        public Builder setAdapter(BaseAdapter adapter) {
            mAdapter = adapter;
            return this;
        }

        @Override
        public Builder setLayoutManager(LinearLayoutManager layoutManager) {
            mLayoutManager = layoutManager;
            return this;
        }

        @Override
        public Builder setRecyclerScrollListener(DefaultEndlessScrollListener listener) {
            mRecyclerScrollListener = listener;
            return this;
        }

        @Override
        public Builder setSwipeRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
            mSwipeRefreshListener = listener;
            return this;
        }

        @Override
        public void build() {
            DefaultEndlessScrollViewModel viewModel = DefaultEndlessScrollViewModel.this;

            viewModel.initViews(mView);
            viewModel.setErrorClickListener(mErrorClickListener);
            viewModel.restoreInstanceState(mSavedInstanceState);
            viewModel.setAdapter(mAdapter);
            viewModel.setLayoutManager(mLayoutManager);
            viewModel.setRecyclerScrollListener(mRecyclerScrollListener);

            RecyclerView recyclerView = viewModel.getRecyclerView();

            recyclerView.setAdapter((RecyclerView.Adapter) viewModel.getAdapter());
            recyclerView.setLayoutManager(viewModel.getLayoutManager());
            recyclerView.addOnScrollListener(viewModel.getRecyclerScrollListener());

            SwipeRefreshLayoutUtils.setup(
                    mContext,
                    viewModel.getRefreshLayout(),
                    viewModel.getRecyclerView(),
                    mSwipeRefreshListener);

            if (mSavedInstanceState != null) {
                Parcelable entitiesState = viewModel.getEntitiesState();
                getAdapter().onRestoreInstanceState(entitiesState);

                Parcelable layoutManagerState = viewModel.getLayoutManagerState();
                viewModel.getRecyclerView().getLayoutManager().onRestoreInstanceState(layoutManagerState);
            }
        }
    }
}