package com.ivzb.encrypted_chat.users.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ivzb.encrypted_chat.R;
import com.ivzb.encrypted_chat._base.ui._contracts.action_handlers.BaseAdapterActionHandler;
import com.ivzb.encrypted_chat._base.ui.adapters.DefaultActionHandlerAdapter;
import com.ivzb.encrypted_chat.users.data.UserEntity;

public class UsersAdapter extends DefaultActionHandlerAdapter<UserEntity> {

    public UsersAdapter(
            Context context,
            BaseAdapterActionHandler<UserEntity> actionHandler) {

        super(context, actionHandler);
    }

    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.users_recycler_item, parent, false);

        return new ViewHolder<>(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        UserEntity user = mEntities.get(position);

        TextView tvUser = viewHolder.getBinding().findViewById(R.id.tvUser);
        tvUser.setText(user.getEmail());
    }
}