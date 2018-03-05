package com.ivzb.semaphore.conversations.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivzb.semaphore.R;
import com.ivzb.semaphore._base.ui._contracts.BaseAdapter;
import com.ivzb.semaphore._base.ui.endless.DefaultEndlessScrollViewModel;
import com.ivzb.semaphore.conversations.data.ConversationEntity;
import com.ivzb.semaphore.utils.ui.ScrollChildSwipeRefreshLayout;

import static com.ivzb.semaphore.utils.Preconditions.checkNotNull;

public class ConversationsViewModel
        extends DefaultEndlessScrollViewModel<ConversationEntity>
        implements ConversationsContract.ViewModel {

    private static final String PAGE_STATE = "page_state";

    private static final String USERS_ADAPTER_STATE = "conversations_adapter_state";
    private static final String USERS_VISIBILITY_STATE = "conversations_visibility_state";
    private static final String LAYOUT_MANAGER_STATE = "layout_manager_state";

    private static final String NO_USERS_VISIBILITY_STATE = "no_conversations_visibility_state";

    private static final String ERROR_TEXT_STATE = "error_text_state";
    private static final String ERROR_VISIBILITY_STATE = "error_visibility_state";

    private BaseAdapter<ConversationEntity> mAdapter;
    private Parcelable mConversationsState;
    private Parcelable mLayoutManagerState;

    private ScrollChildSwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRvConversations;

    private ImageView mIvNoConversations;
    private TextView mTvNoConversations;

    private CardView mCvError;
    private TextView mTvError;

    @Override
    public void init(android.view.View view) {
        checkNotNull(view);

        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mCvError = view.findViewById(R.id.cvError);
        mTvError = view.findViewById(R.id.tvError);
        mRvConversations = view.findViewById(R.id.rvConversations);
        mIvNoConversations = view.findViewById(R.id.ivNoConversations);
        mTvNoConversations = view.findViewById(R.id.tvNoConversations);
    }

    @Override
    public void saveInstanceState(Bundle outState) {
        savePageState(outState);
        saveConversationsState(outState);
        saveNoConversationsState(outState);
        saveErrorState(outState);
    }

    private void savePageState(Bundle outState) {
        outState.putInt(PAGE_STATE, getPage());
    }

    private void saveConversationsState(Bundle outState) {
        if (mAdapter != null) {
            Parcelable conversationsState = mAdapter.onSaveInstanceState();
            outState.putParcelable(USERS_ADAPTER_STATE, conversationsState);
        }

        if (mRvConversations != null) {
            if (mRvConversations.getLayoutManager() != null) {
                Parcelable layoutManagerState = mRvConversations.getLayoutManager().onSaveInstanceState();
                outState.putParcelable(LAYOUT_MANAGER_STATE, layoutManagerState);
            }

            int conversationsVisibility = mRvConversations.getVisibility();
            outState.putInt(USERS_VISIBILITY_STATE, conversationsVisibility);
        }
    }

    private void saveNoConversationsState(Bundle outState) {
        // get least visible
        int noConversationsVisibility = Math.max(mIvNoConversations.getVisibility(), mTvNoConversations.getVisibility());
        outState.putInt(NO_USERS_VISIBILITY_STATE, noConversationsVisibility);
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
        restoreConversationsState(savedInstanceState);
        restoreNoConversationsState(savedInstanceState);
        restoreErrorState(savedInstanceState);
    }

    private void restorePageState(Bundle state) {
        if (state.containsKey(PAGE_STATE)) {
            int page = state.getInt(PAGE_STATE);
            setPage(page);
        }
    }

    private void restoreConversationsState(Bundle state) {
        if (state.containsKey(USERS_ADAPTER_STATE)) {
            Parcelable conversationsState = state.getParcelable(USERS_ADAPTER_STATE);
            setConversationsState(conversationsState);
        }

        if (state.containsKey(LAYOUT_MANAGER_STATE)) {
            Parcelable layoutManagerState = state.getParcelable(LAYOUT_MANAGER_STATE);
            setLayoutManagerState(layoutManagerState);
        }

        if (state.containsKey(USERS_VISIBILITY_STATE)) {
            int visibility = state.getInt(USERS_VISIBILITY_STATE);
            getRvConversations().setVisibility(visibility);
        }
    }

    private void restoreNoConversationsState(Bundle state) {
        if (state.containsKey(NO_USERS_VISIBILITY_STATE)) {
            int visibility = state.getInt(NO_USERS_VISIBILITY_STATE);

            getIvNoConversations().setVisibility(visibility);
            getTvNoConversations().setVisibility(visibility);
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
    public void setAdapter(BaseAdapter<ConversationEntity> adapter) {
        mAdapter = adapter;
    }

    @Override
    public Parcelable getConversationsState() {
        return mConversationsState;
    }

    @Override
    public void setConversationsState(Parcelable state) {
        mConversationsState = state;
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
    public RecyclerView getRvConversations() {
        return mRvConversations;
    }

    @Override
    public TextView getTvNoConversations() {
        return mTvNoConversations;
    }

    @Override
    public ImageView getIvNoConversations() {
        return mIvNoConversations;
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
    public String getContainerId() {
        return null;
    }
}
