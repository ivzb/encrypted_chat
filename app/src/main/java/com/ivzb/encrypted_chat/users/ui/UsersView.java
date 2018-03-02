package com.ivzb.encrypted_chat.users.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivzb.encrypted_chat._base.data.DataSources;
import com.ivzb.encrypted_chat.R;
import com.ivzb.encrypted_chat._base.ui._contracts.BaseEntityActionHandler;
import com.ivzb.encrypted_chat._base.ui.endless.DefaultEndlessScrollView;
import com.ivzb.encrypted_chat.users.data.UserEntity;
import com.ivzb.encrypted_chat.utils.ui.ScrollChildSwipeRefreshLayout;
import com.ivzb.encrypted_chat.utils.ui.SwipeRefreshLayoutUtils;

public class UsersView
        extends DefaultEndlessScrollView<UserEntity, UsersContract.Presenter, UsersContract.ViewModel>
        implements UsersContract.View,
        BaseEntityActionHandler<UserEntity>,
        SwipeRefreshLayout.OnRefreshListener {

    private static final String USERS_STATE = "users_state";
    private static final String LAYOUT_MANAGER_STATE = "layout_manager_state";
    private static final String PAGE_STATE = "page_state";

    private RecyclerView mRvUsers;
    private ScrollChildSwipeRefreshLayout mRefreshLayout;

    private ImageView mIvNoUsers;
    private TextView mTvNoUsers;

    public UsersView() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.users_frag, container, false);

        if (mViewModel == null) {
            mViewModel = new UsersViewModel();
        }

        if (mPresenter == null) {
            mPresenter = new UsersPresenter(
                    getContext(),
                    this,
                    DataSources.getInstance().users());
        }

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(PAGE_STATE)) {
                int page = savedInstanceState.getInt(PAGE_STATE);
                setPage(page);
            }
        }

        mRvUsers = view.findViewById(R.id.rvUsers);
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mIvNoUsers = view.findViewById(R.id.ivNoUsers);
        mTvNoUsers = view.findViewById(R.id.tvNoUsers);

        initEndlessAdapter(
                getContext(),
                new UsersAdapter(getContext(), this, null, null),
                mRvUsers);

        SwipeRefreshLayoutUtils.setup(
                getContext(),
                mRefreshLayout,
                mRvUsers,
                this);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(USERS_STATE)) {
                Parcelable usersState = savedInstanceState.getParcelable(USERS_STATE);
                mAdapter.onRestoreInstanceState(usersState);
            }

            if (savedInstanceState.containsKey(LAYOUT_MANAGER_STATE)) {
                Parcelable layoutManagerState = savedInstanceState.getParcelable(LAYOUT_MANAGER_STATE);
                mRvUsers.getLayoutManager().onRestoreInstanceState(layoutManagerState);
            }
        } else {
            mPresenter.refresh(mViewModel.getContainerId());
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mViewModel != null) {
            outState.putInt(PAGE_STATE, mViewModel.getPage());

            if (mAdapter != null) {
                Parcelable usersState = mAdapter.onSaveInstanceState();
                outState.putParcelable(USERS_STATE, usersState);
            }
        }

        if (mRvUsers != null && mRvUsers.getLayoutManager() != null) {
            Parcelable layoutManagerState = mRvUsers.getLayoutManager().onSaveInstanceState();
            outState.putParcelable(LAYOUT_MANAGER_STATE, layoutManagerState);
        }
    }

    @Override
    public void openUi(UserEntity user) {
        //Intent intent = new Intent(getContext(), UserActivity.class);
        //intent.putExtra(UserActivity.EXTRA_USER_ID, user.getId());
        //startActivity(intent);
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (!isActive()) return;

        SwipeRefreshLayoutUtils.setLoading(mRefreshLayout, active);
    }

    @Override
    public void showEntities(boolean show) {
        mRvUsers.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showNoEntities(boolean show) {
        int visibility = show ? View.VISIBLE : View.GONE;
        mIvNoUsers.setVisibility(visibility);
        mTvNoUsers.setVisibility(visibility);
    }

    @Override
    public void onAdapterEntityClick(UserEntity user) {
        mPresenter.click(user);
    }
}
