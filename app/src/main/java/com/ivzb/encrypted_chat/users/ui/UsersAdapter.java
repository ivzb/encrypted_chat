package com.ivzb.encrypted_chat.users.ui;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ivzb.encrypted_chat.R;
import com.ivzb.encrypted_chat._base.ui._contracts.action_handlers.BaseAdapterActionHandler;
import com.ivzb.encrypted_chat._base.ui.adapters.DefaultActionHandlerAdapter;
import com.ivzb.encrypted_chat.users.data.UserEntity;

import org.w3c.dom.Text;

public class UsersAdapter
        extends DefaultActionHandlerAdapter<UserEntity> {

    private BaseAdapterActionHandler<UserEntity> mAddUserActionHandler;

    public UsersAdapter(
            Context context,
            BaseAdapterActionHandler<UserEntity> actionHandler,
            BaseAdapterActionHandler<UserEntity> addUserActionHandler) {

        super(context, actionHandler);

        mAddUserActionHandler = addUserActionHandler;
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
        cvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseAdapterActionHandler<UserEntity> actionHandler;

                switch (view.getId()) {
                    case R.id.ibAddUser:
                        actionHandler = mAddUserActionHandler;
                        break;
                    default:
                        actionHandler = mActionHandler;
                }

                actionHandler.onAdapterEntityClick(user);
            }
        });

        TextView tvUser = binding.findViewById(R.id.tvUser);
        tvUser.setText(user.getEmail());

        ImageButton ibAddUser = binding.findViewById(R.id.ibAddUser);

        if (!user.isFriend()) {
            ibAddUser.setVisibility(View.VISIBLE);
        }
    }
}