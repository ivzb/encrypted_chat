package com.ivzb.encrypted_chat._base.ui.endless;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivzb.encrypted_chat.R;
import com.ivzb.encrypted_chat._base.data._contracts.entities.BaseEntity;
import com.ivzb.encrypted_chat._base.ui._contracts.BaseAdapter;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollViewModel;
import com.ivzb.encrypted_chat.utils.ui.ScrollChildSwipeRefreshLayout;

import static com.ivzb.encrypted_chat._base.data.config.DefaultConfig.NO_PAGE;
import static com.ivzb.encrypted_chat.utils.Preconditions.checkNotNull;

public abstract class DefaultEndlessScrollViewModel<T extends BaseEntity>
        implements BaseEndlessScrollViewModel<T> {

    private static final String PAGE_STATE = "page_state";

    private static final String ENTITIES_ADAPTER_STATE = "entities_adapter_state";
    private static final String ENTITIES_VISIBILITY_STATE = "entities_visibility_state";
    private static final String LAYOUT_MANAGER_STATE = "layout_manager_state";

    private static final String NO_ENTITIES_VISIBILITY_STATE = "no_entities_visibility_state";

    private static final String ERROR_TEXT_STATE = "error_text_state";
    private static final String ERROR_VISIBILITY_STATE = "error_visibility_state";

    private BaseAdapter<T> mAdapter;
    private Parcelable mEntitiesState;
    private Parcelable mLayoutManagerState;

    private ScrollChildSwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    private ImageView mIvNoEntities;
    private TextView mTvNoEntities;

    private CardView mCvError;
    private TextView mTvError;

    private int mPage;
    private boolean mHasMore;

    public DefaultEndlessScrollViewModel() {
        mPage = NO_PAGE;
        mHasMore = true;
    }

    @Override
    public void init(View view) {
        checkNotNull(view);

        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mCvError = view.findViewById(R.id.cvError);
        mTvError = view.findViewById(R.id.tvError);
        mRecyclerView = view.findViewById(R.id.rvUsers);
        mIvNoEntities = view.findViewById(R.id.ivNoUsers);
        mTvNoEntities = view.findViewById(R.id.tvNoUsers);
    }

    @Override
    public void saveInstanceState(Bundle outState) {
        savePageState(outState);
        saveEntitiesState(outState);
        saveNoEntitiesState(outState);
        saveErrorState(outState);
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

    private void saveErrorState(Bundle outState) {
        String errorText = mTvError.getText().toString();
        outState.putString(ERROR_TEXT_STATE, errorText);

        int errorVisibility = mCvError.getVisibility();
        outState.putInt(ERROR_VISIBILITY_STATE, errorVisibility);
    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null) return;

        restorePageState(savedInstanceState);
        restoreEntitiesState(savedInstanceState);
        restoreNoEntitiesState(savedInstanceState);
        restoreErrorState(savedInstanceState);
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

    private void restoreErrorState(Bundle state) {
        if (state.containsKey(ERROR_TEXT_STATE)) {
            String error = state.getString(ERROR_TEXT_STATE);
            getTvError().setText(error);
        }

        if (state.containsKey(ERROR_VISIBILITY_STATE)) {
            int visibility = state.getInt(ERROR_VISIBILITY_STATE);
            getCvError().setVisibility(visibility);
        }
    }

    @Override
    public void setErrorClickListener(View.OnClickListener listener) {
        if (mCvError == null) return;

        mCvError.setOnClickListener(listener);
    }

    @Override
    public void setAdapter(BaseAdapter<T> adapter) {
        mAdapter = adapter;
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
    public CardView getCvError() {
        return mCvError;
    }

    @Override
    public TextView getTvError() {
        return mTvError;
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
        return null;
    }
}