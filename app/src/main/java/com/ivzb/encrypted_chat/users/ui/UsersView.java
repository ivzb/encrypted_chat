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
import com.ivzb.encrypted_chat._base.data.config.DefaultConfig;
import com.ivzb.encrypted_chat._base.ui._contracts.BaseEntityActionHandler;
import com.ivzb.encrypted_chat._base.ui.endless.DefaultEndlessScrollView;
import com.ivzb.encrypted_chat._base.ui.prompt.DialogListener;
import com.ivzb.encrypted_chat._base.ui.prompt.PromptDialogFragment;
import com.ivzb.encrypted_chat.users.data.UserEntity;
import com.ivzb.encrypted_chat.utils.ui.ScrollChildSwipeRefreshLayout;
import com.ivzb.encrypted_chat.utils.ui.SwipeRefreshLayoutUtils;

import static com.ivzb.encrypted_chat._base.data.config.DefaultConfig.INITIAL_PAGE;

public class UsersView
        extends DefaultEndlessScrollView<UserEntity, UsersContract.Presenter, UsersContract.ViewModel>
        implements UsersContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflateFragment(inflater, container);

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

        mPresenter.refresh(DefaultConfig.NO_ID);

        return view;
    }

    @Override
    public View inflateFragment(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.users_frag, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mViewModel != null) {
            mViewModel.saveInstanceState(outState);
        }
    }

    @Override
    public void openUi(UserEntity user) {
        //Intent intent = new Intent(getContext(), UserActivity.class);
        //intent.putExtra(UserActivity.EXTRA_USER_ID, user.getId());
        //startActivity(intent);
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh(DefaultConfig.NO_ID);
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
            PromptDialogFragment dialogFragment = new PromptDialogFragment()
                .setTitle(getResources().getString(R.string.remove_user))
                .setListener(new DialogListener() {
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
        show &= !(mViewModel.getPage() > INITIAL_PAGE); // don't show "no users" if already loaded entities
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
