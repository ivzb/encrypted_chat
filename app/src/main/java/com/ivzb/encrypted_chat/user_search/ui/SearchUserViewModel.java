package com.ivzb.encrypted_chat.user_search.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivzb.encrypted_chat.R;
import com.ivzb.encrypted_chat._base.ui._contracts.BaseAdapter;
import com.ivzb.encrypted_chat._base.ui.endless.DefaultEndlessScrollViewModel;
import com.ivzb.encrypted_chat.users.data.UserEntity;
import com.ivzb.encrypted_chat.utils.ui.ScrollChildSwipeRefreshLayout;

import static com.ivzb.encrypted_chat.utils.Preconditions.checkNotNull;

class SearchUserViewModel
        extends DefaultEndlessScrollViewModel<UserEntity>
        implements UserSearchContract.ViewModel {

    private static final String EMAIL_TEXT_STATE = "email_text_state";
    private static final String EMAIL_VISIBILITY_STATE = "email_visibility_state";

    private static final String PAGE_STATE = "page_state";

    private static final String USERS_ADAPTER_STATE = "users_adapter_state";
    private static final String USERS_VISIBILITY_STATE = "users_visibility_state";
    private static final String LAYOUT_MANAGER_STATE = "layout_manager_state";

    private static final String NO_USERS_VISIBILITY_STATE = "no_users_visibility_state";

    private static final String ERROR_TEXT_STATE = "error_text_state";
    private static final String ERROR_VISIBILITY_STATE = "error_visibility_state";

    private BaseAdapter<UserEntity> mAdapter;
    private Parcelable mUsersState;
    private Parcelable mLayoutManagerState;

    private EditText mEtEmail;

    private ScrollChildSwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRvUsers;

    private ImageView mIvNoUsers;
    private TextView mTvNoUsers;

    private CardView mCvError;
    private TextView mTvError;


    @Override
    public void init(android.view.View view) {
        checkNotNull(view);

        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mEtEmail = view.findViewById(R.id.etEmail);
        mCvError = view.findViewById(R.id.cvError);
        mTvError = view.findViewById(R.id.tvError);
        mRvUsers = view.findViewById(R.id.rvUsers);
        mIvNoUsers = view.findViewById(R.id.ivNoUsers);
        mTvNoUsers = view.findViewById(R.id.tvNoUsers);
    }

    @Override
    public void saveInstanceState(Bundle outState) {
        saveEmailState(outState);
        savePageState(outState);
        saveUsersState(outState);
        saveNoUsersState(outState);
        saveErrorState(outState);
    }

    private void saveEmailState(Bundle outState) {
        String emailText = mEtEmail.getText().toString();
        outState.putString(EMAIL_TEXT_STATE, emailText);

        int emailVisibility = mEtEmail.getVisibility();
        outState.putInt(EMAIL_VISIBILITY_STATE, emailVisibility);
    }

    private void savePageState(Bundle outState) {
        outState.putInt(PAGE_STATE, getPage());
    }

    private void saveUsersState(Bundle outState) {
        if (mAdapter != null) {
            Parcelable usersState = mAdapter.onSaveInstanceState();
            outState.putParcelable(USERS_ADAPTER_STATE, usersState);
        }

        if (mRvUsers != null) {
            if (mRvUsers.getLayoutManager() != null) {
                Parcelable layoutManagerState = mRvUsers.getLayoutManager().onSaveInstanceState();
                outState.putParcelable(LAYOUT_MANAGER_STATE, layoutManagerState);
            }

            int usersVisibility = mRvUsers.getVisibility();
            outState.putInt(USERS_VISIBILITY_STATE, usersVisibility);
        }
    }

    private void saveNoUsersState(Bundle outState) {
        // get least visible
        int noUsersVisibility = Math.max(mIvNoUsers.getVisibility(), mTvNoUsers.getVisibility());
        outState.putInt(NO_USERS_VISIBILITY_STATE, noUsersVisibility);
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

        restoreEmailState(savedInstanceState);
        restorePageState(savedInstanceState);
        restoreUsersState(savedInstanceState);
        restoreNoUsersState(savedInstanceState);
        restoreErrorState(savedInstanceState);
    }

    private void restoreEmailState(Bundle state) {
        if (state.containsKey(EMAIL_TEXT_STATE)) {
            String email = state.getString(EMAIL_TEXT_STATE);
            getEtEmail().setText(email);
        }

        if (state.containsKey(EMAIL_VISIBILITY_STATE)) {
            int visibility = state.getInt(EMAIL_VISIBILITY_STATE);
            getEtEmail().setVisibility(visibility);
        }
    }

    private void restorePageState(Bundle state) {
        if (state.containsKey(PAGE_STATE)) {
            int page = state.getInt(PAGE_STATE);
            setPage(page);
        }
    }

    private void restoreUsersState(Bundle state) {
        if (state.containsKey(USERS_ADAPTER_STATE)) {
            Parcelable usersState = state.getParcelable(USERS_ADAPTER_STATE);
            setUsersState(usersState);
        }

        if (state.containsKey(LAYOUT_MANAGER_STATE)) {
            Parcelable layoutManagerState = state.getParcelable(LAYOUT_MANAGER_STATE);
            setLayoutManagerState(layoutManagerState);
        }

        if (state.containsKey(USERS_VISIBILITY_STATE)) {
            int visibility = state.getInt(USERS_VISIBILITY_STATE);
            getRvUsers().setVisibility(visibility);
        }
    }

    private void restoreNoUsersState(Bundle state) {
        if (state.containsKey(NO_USERS_VISIBILITY_STATE)) {
            int visibility = state.getInt(NO_USERS_VISIBILITY_STATE);

            getIvNoUsers().setVisibility(visibility);
            getTvNoUsers().setVisibility(visibility);
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
    public void setAdapter(BaseAdapter<UserEntity> adapter) {
        mAdapter = adapter;
    }

    @Override
    public EditText getEtEmail() {
        return mEtEmail;
    }

    @Override
    public Parcelable getUsersState() {
        return mUsersState;
    }

    @Override
    public void setUsersState(Parcelable state) {
        mUsersState = state;
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
    public RecyclerView getRvUsers() {
        return mRvUsers;
    }

    @Override
    public TextView getTvNoUsers() {
        return mTvNoUsers;
    }

    @Override
    public ImageView getIvNoUsers() {
        return mIvNoUsers;
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
