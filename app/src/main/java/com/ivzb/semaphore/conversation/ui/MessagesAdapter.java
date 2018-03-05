package com.ivzb.semaphore.conversation.ui;

import android.content.Context;
import android.opengl.Visibility;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ivzb.semaphore.R;
import com.ivzb.semaphore._base.ui.DefaultActionHandlerAdapter;
import com.ivzb.semaphore._base.ui._contracts.BaseEntityActionHandler;
import com.ivzb.semaphore.conversation.data.MessageEntity;
import com.ivzb.semaphore.utils.DateUtils;

public class MessagesAdapter
        extends DefaultActionHandlerAdapter<MessageEntity> {

    private boolean mIsPreviousOwn;

    public MessagesAdapter(
            Context context,
            BaseEntityActionHandler<MessageEntity> actionHandler) {

        super(context, actionHandler);
    }

    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.messages_recycler_item, parent, false);

        return new ViewHolder<>(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final MessageEntity message = mEntities.get(position);
        View binding = viewHolder.getBinding();

        ConstraintLayout clMessage = binding.findViewById(R.id.clMessage);

        CardView cvMessage = binding.findViewById(R.id.cvMessage);
        cvMessage.invalidate();
        cvMessage.setOnClickListener(
                new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         mActionHandler.onAdapterEntityClick(message);
                     }
                 });

        TextView tvMessage = binding.findViewById(R.id.tvMessage);
        tvMessage.setText(message.getMessage());

        TextView tvCreatedAt = binding.findViewById(R.id.tvCreatedAt);
        String createdAt = DateUtils.format(message.getCreatedAt(), DateUtils.PATTERN_TIME);
        tvCreatedAt.setText(createdAt);

        int gravity;
        int constraint;
        int color;
        int createdAtVisibility = View.VISIBLE;

        if (mIsPreviousOwn == message.isOwn() && position > 0) {
            createdAtVisibility = View.GONE;
        }

        if (message.isOwn()) {
            gravity = Gravity.END;
            constraint = ConstraintSet.END;
            color = R.color.me;
        } else {
            gravity = Gravity.START;
            constraint = ConstraintSet.START;
            color = R.color.friend;
        }

        tvMessage.setGravity(gravity);
        tvCreatedAt.setVisibility(createdAtVisibility);
        tvCreatedAt.setGravity(gravity);
        cvMessage.setCardBackgroundColor(mContext.getResources().getColor(color));

        ConstraintSet set = new ConstraintSet();
        set.clone(clMessage);
        set.clear(cvMessage.getId(), ConstraintSet.START);
        set.clear(cvMessage.getId(), ConstraintSet.END);
        set.connect(cvMessage.getId(), constraint, ConstraintSet.PARENT_ID, constraint,8);
        set.applyTo(clMessage);

        mIsPreviousOwn = message.isOwn();
    }
}
