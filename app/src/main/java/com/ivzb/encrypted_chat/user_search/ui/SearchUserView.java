package com.ivzb.encrypted_chat.user_search.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ivzb.encrypted_chat.R;
import com.ivzb.encrypted_chat._base.ui._contracts.BaseEntityActionHandler;
import com.ivzb.encrypted_chat._base.ui.endless.DefaultEndlessScrollView;
import com.ivzb.encrypted_chat.users.data.UserEntity;
import com.ivzb.encrypted_chat.users.ui.RemoveUserDialogFragment;
import com.ivzb.encrypted_chat.users.ui.UsersAdapter;
import com.ivzb.encrypted_chat.utils.ui.SwipeRefreshLayoutUtils;

public class SearchUserView
        extends DefaultEndlessScrollView<UserEntity, UserSearchContract.Presenter, UserSearchContract.ViewModel>
        implements UserSearchContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.user_search_frag, container, false);

        mViewModel.init(view);
        mViewModel.setErrorClickListener(mErrorClickListener);
        mViewModel.restoreInstanceState(savedInstanceState);

        initEndlessAdapter(
                getContext(),
                new UsersAdapter(
                        getContext(),
                        mUserClickListener,
                        mAddUserClickListener,
                        mRemoveUserClickListener),
                mViewModel.getRvUsers());

        mViewModel.setAdapter(mAdapter);

        SwipeRefreshLayoutUtils.setup(
                getContext(),
                mViewModel.getRefreshLayout(),
                mViewModel.getRvUsers(),
                this);

        if (savedInstanceState != null) {
            Parcelable usersState = mViewModel.getUsersState();
            mAdapter.onRestoreInstanceState(usersState);

            Parcelable layoutManagerState = mViewModel.getLayoutManagerState();
            mViewModel.getRvUsers().getLayoutManager().onRestoreInstanceState(layoutManagerState);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mViewModel != null) {
            mViewModel.saveInstanceState(outState);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                String email = mViewModel.getEtEmail().getText().toString();
                mPresenter.searchUser(email);
                break;
        }

        return true;
    }

    @Override
    public void onRefresh() {
        String email = mViewModel.getEtEmail().getText().toString();
        mPresenter.refresh(email);
    }

    @Override
    public void onUserClick(UserEntity user) {
        mPresenter.clickUser(user);
    }

    private BaseEntityActionHandler<UserEntity> mUserClickListener = new BaseEntityActionHandler<UserEntity>() {
        @Override
        public void onAdapterEntityClick(UserEntity user) {
            onUserClick(user);
        }
    };

    @Override
    public void onAddUserClick(UserEntity user) {
        mPresenter.clickAddUser(user);
    }

    private BaseEntityActionHandler<UserEntity> mAddUserClickListener = new BaseEntityActionHandler<UserEntity>() {
        @Override
        public void onAdapterEntityClick(UserEntity user) {
            onAddUserClick(user);
        }
    };

    @Override
    public void onRemoveUserClick(UserEntity user) {
        mPresenter.clickRemoveUser(user);
    }

    private BaseEntityActionHandler<UserEntity> mRemoveUserClickListener = new BaseEntityActionHandler<UserEntity>() {
        @Override
        public void onAdapterEntityClick(final UserEntity user) {
            RemoveUserDialogFragment dialogFragment = new RemoveUserDialogFragment();

            dialogFragment.setListener(new RemoveUserDialogFragment.NoticeDialogListener() {
                @Override
                public void onDialogPositiveClick() {
                    onRemoveUserClick(user);
                }

                @Override
                public void onDialogNegativeClick() {
                    // no action needed
                }
            });

            dialogFragment.show(getFragmentManager(), "remove_user_dialog");
        }
    };

    @Override
    public void onErrorClick() {
        mPresenter.clickErrorMessage();
    }

    private View.OnClickListener mErrorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onErrorClick();
        }
    };

    @Override
    public void setLoadingIndicator(boolean active) {
        if (!isActive()) return;

        SwipeRefreshLayoutUtils.setLoading(mViewModel.getRefreshLayout(), active);
    }

    @Override
    public void showEntities(boolean show) {
        mViewModel.getRvUsers().setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showNoEntities(boolean show) {
        int visibility = show ? View.VISIBLE : View.GONE;

        mViewModel.getIvNoUsers().setVisibility(visibility);
        mViewModel.getTvNoUsers().setVisibility(visibility);
    }

    @Override
    public void showErrorMessage(boolean show) {
        mViewModel.getCvError().setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setErrorMessage(String message) {
        mViewModel.getTvError().setText(message);
    }
}