package com.ivzb.encrypted_chat.users.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivzb.encrypted_chat.R;
import com.ivzb.encrypted_chat._base.data.config.DefaultConfig;
import com.ivzb.encrypted_chat._base.ui._contracts.BaseEntityActionHandler;
import com.ivzb.encrypted_chat._base.ui.endless.DefaultEndlessScrollView;
import com.ivzb.encrypted_chat._base.ui.prompt.DialogListener;
import com.ivzb.encrypted_chat._base.ui.prompt.PromptDialogFragment;
import com.ivzb.encrypted_chat.users.data.UserEntity;

public class UsersView
        extends DefaultEndlessScrollView<UserEntity, UsersContract.Presenter, UsersContract.ViewModel>
        implements UsersContract.View {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        mPresenter.refresh(DefaultConfig.NO_ID);

        return view;
    }

    @Override
    public View inflateFragment(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.users_frag, container, false);
    }

    @Override
    public void initEndlessAdapter() {
        initEndlessAdapter(
                getContext(),
                new UsersAdapter(
                        getContext(),
                        mUserClickListener,
                        mAddUserClickListener,
                        mRemoveUserClickListener),
                mViewModel.getRecyclerView());
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
    public void onOpenConversation(UserEntity user) {
        mPresenter.clickUser(user);
    }

    private BaseEntityActionHandler<UserEntity> mUserClickListener = new BaseEntityActionHandler<UserEntity>() {
        @Override
        public void onAdapterEntityClick(UserEntity user) {
            onOpenConversation(user);
        }
    };

    @Override
    public void onAddUser(UserEntity user) {
        mPresenter.clickAddUser(user);
    }

    private BaseEntityActionHandler<UserEntity> mAddUserClickListener = new BaseEntityActionHandler<UserEntity>() {
        @Override
        public void onAdapterEntityClick(UserEntity user) {
            onAddUser(user);
        }
    };

    @Override
    public void onRemoveUser(UserEntity user) {
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
                                                              onRemoveUser(user);
                                                                                      }

                    @Override
                    public void onDialogNegativeClick() {
                        // no action needed
                    }
                });

            dialogFragment.show(getFragmentManager(), "remove_user_dialog");
        }
    };

}
