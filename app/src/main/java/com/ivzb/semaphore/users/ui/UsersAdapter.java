package com.ivzb.semaphore.users.ui;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ivzb.semaphore.R;
import com.ivzb.semaphore._base.ui.DefaultActionHandlerAdapter;
import com.ivzb.semaphore._base.ui._contracts.BaseEntityActionHandler;
import com.ivzb.semaphore.users.data.UserEntity;

public class UsersAdapter
        extends DefaultActionHandlerAdapter<UserEntity> {

    private BaseEntityActionHandler<UserEntity> mAddUserActionHandler;
    private BaseEntityActionHandler<UserEntity> mRemoveUserActionHandler;

    public UsersAdapter(
            Context context,
            BaseEntityActionHandler<UserEntity> actionHandler,
            BaseEntityActionHandler<UserEntity> addUserActionHandler,
            BaseEntityActionHandler<UserEntity> removeUserActionHandler) {

        super(context, actionHandler);

        mAddUserActionHandler = addUserActionHandler;
        mRemoveUserActionHandler = removeUserActionHandler;
    }

    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.users_recycler_item, parent, false);

        return new ViewHolder<>(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final UserEntity user = mEntities.get(position);
        View binding = viewHolder.getBinding();

        CardView cvUser = binding.findViewById(R.id.cvUser);
        cvUser.setOnClickListener(actionHandlerListener(mActionHandler, user));

        TextView tvUser = binding.findViewById(R.id.tvUser);
        tvUser.setText(user.getEmail());

        ImageButton ibAddUser = binding.findViewById(R.id.ibAddUser);
        ibAddUser.setOnClickListener(actionHandlerListener(mAddUserActionHandler, user));

        ImageButton ibRemoveUser = binding.findViewById(R.id.ibRemoveUser);
        ibRemoveUser.setOnClickListener(actionHandlerListener(mRemoveUserActionHandler, user));

        updateUserButtonsVisibility(user, ibAddUser, ibRemoveUser);
    }

    private View.OnClickListener actionHandlerListener(
            final BaseEntityActionHandler<UserEntity> actionHandler,
            final UserEntity user) {

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionHandler.onAdapterEntityClick(user);
            }
        };
    }

    private void updateUserButtonsVisibility(
            final UserEntity user,
            final ImageButton ibAddUser,
            final ImageButton ibRemoveUser) {

        int addUserVisibility = View.VISIBLE;
        int removeUserVisibility = View.GONE;

        if (!user.isFriend()) {
            addUserVisibility = View.GONE;
            removeUserVisibility = View.VISIBLE;
        }

        ibAddUser.setVisibility(addUserVisibility);
        ibRemoveUser.setVisibility(removeUserVisibility);
    }
}