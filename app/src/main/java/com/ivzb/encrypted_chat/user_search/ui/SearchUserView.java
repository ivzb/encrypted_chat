package com.ivzb.encrypted_chat.user_search.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivzb.encrypted_chat.R;
import com.ivzb.encrypted_chat._base.ui._contracts.action_handlers.BaseAdapterActionHandler;
import com.ivzb.encrypted_chat._base.ui.views.DefaultEndlessScrollView;
import com.ivzb.encrypted_chat.users.data.UserEntity;
import com.ivzb.encrypted_chat.users.ui.UsersAdapter;
import com.ivzb.encrypted_chat.utils.ui.ScrollChildSwipeRefreshLayout;
import com.ivzb.encrypted_chat.utils.ui.SwipeRefreshLayoutUtils;

public class SearchUserView
        extends DefaultEndlessScrollView<UserEntity, UserSearchContract.Presenter, UserSearchContract.ViewModel>
        implements UserSearchContract.View {

    //private static final String USERS_STATE = "users_state";
    //private static final String LAYOUT_MANAGER_STATE = "layout_manager_state";
    //private static final String PAGE_STATE = "page_state";

    private EditText mEtEmail;

    private CardView mCvError;
    private TextView mTvError;

    private ScrollChildSwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRvUsers;

    private ImageView mIvNoUsers;
    private TextView mTvNoUsers;

    public SearchUserView() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.user_search_frag, container, false);

        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mEtEmail = view.findViewById(R.id.etEmail);
        mCvError = view.findViewById(R.id.cvError);
        mTvError = view.findViewById(R.id.tvError);
        mRvUsers = view.findViewById(R.id.rvUsers);
        mIvNoUsers = view.findViewById(R.id.ivNoUsers);
        mTvNoUsers = view.findViewById(R.id.tvNoUsers);

        mCvError.setOnClickListener(mErrorListener);

//        if (savedInstanceState != null) {
//            if (savedInstanceState.containsKey(PAGE_STATE)) {
//                int page = savedInstanceState.getInt(PAGE_STATE);
//                setPage(page);
//            }
//        }

        super.setUpRecycler(
                getContext(),
                new UsersAdapter(getContext(), mUserClickListener, mAddUserClickListener),
                mRvUsers);

        SwipeRefreshLayoutUtils.setup(
                getContext(),
                mRefreshLayout,
                mRvUsers,
                this);

//        if (savedInstanceState != null) {
//            if (savedInstanceState.containsKey(USERS_STATE)) {
//                Parcelable usersState = savedInstanceState.getParcelable(USERS_STATE);
//                mViewModel.getAdapter().onRestoreInstanceState(usersState);
//            }
//
//            if (savedInstanceState.containsKey(LAYOUT_MANAGER_STATE)) {
//                Parcelable layoutManagerState = savedInstanceState.getParcelable(LAYOUT_MANAGER_STATE);
//                mRvUsers.getLayoutManager().onRestoreInstanceState(layoutManagerState);
//            }
//        } else {
//            mPresenter.refresh(mViewModel.getContainerId());
//        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                bindViewModel();
                mPresenter.searchUser(mViewModel.getEmail());
                break;
        }

        return true;
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh(mViewModel.getEmail());
    }

    @Override
    public void showErrorMessage(String message) {
        mTvError.setText(message);
        mCvError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorMessage() {
        mTvError.setText("");
        mCvError.setVisibility(View.GONE);
    }

    @Override
    public void showUsers() {
        mRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUsers() {
        mRefreshLayout.setVisibility(View.GONE);
    }

    private View.OnClickListener mErrorListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            hideErrorMessage();
        }
    };

    private void bindViewModel() {
        mViewModel.setEmail(mEtEmail.getText().toString());
    }

    @Override
    public void onUserClick(UserEntity user) {
        mPresenter.clickUser(user);
    }

    @Override
    public void onAddUserClick(UserEntity user) {
        mPresenter.clickAddUser(user);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (!isActive()) return;

        SwipeRefreshLayoutUtils.setLoading(mRefreshLayout, active);
    }

    @Override
    public void showEntities(boolean show) {
        int usersVisibility = View.VISIBLE;
        int noUsersVisibility = View.GONE;

        if (!show) {
            usersVisibility = View.GONE;
            noUsersVisibility = View.VISIBLE;
        }

        mRvUsers.setVisibility(usersVisibility);
        mIvNoUsers.setVisibility(noUsersVisibility);
        mTvNoUsers.setVisibility(noUsersVisibility);
    }

    private BaseAdapterActionHandler<UserEntity> mUserClickListener = new BaseAdapterActionHandler<UserEntity>() {
        @Override
        public void onAdapterEntityClick(UserEntity user) {
            onUserClick(user);
        }
    };

    private BaseAdapterActionHandler<UserEntity> mAddUserClickListener = new BaseAdapterActionHandler<UserEntity>() {
        @Override
        public void onAdapterEntityClick(UserEntity user) {
            onAddUserClick(user);
        }
    };
}