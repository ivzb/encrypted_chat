package com.ivzb.semaphore.conversations.ui;

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
import com.ivzb.semaphore.conversations.data.ConversationEntity;

public class ConversationsAdapter
        extends DefaultActionHandlerAdapter<ConversationEntity> {

    private BaseEntityActionHandler<ConversationEntity> mRemoveConversationActionHandler;

    public ConversationsAdapter(
            Context context,
            BaseEntityActionHandler<ConversationEntity> actionHandler,
            BaseEntityActionHandler<ConversationEntity> removeConversationActionHandler) {

        super(context, actionHandler);

        mRemoveConversationActionHandler = removeConversationActionHandler;
    }

    @Override
    public ConversationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.conversations_recycler_item, parent, false);

        return new ViewHolder<>(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final ConversationEntity conversation = mEntities.get(position);
        View binding = viewHolder.getBinding();

        CardView cvConversation = binding.findViewById(R.id.cvConversation);
        cvConversation.setOnClickListener(actionHandlerListener(mActionHandler, conversation));

        TextView tvConversation = binding.findViewById(R.id.tvConversation);
        tvConversation.setText(conversation.getName());

        ImageButton ibRemoveConversation = binding.findViewById(R.id.ibRemoveConversation);
        ibRemoveConversation.setOnClickListener(actionHandlerListener(mRemoveConversationActionHandler, conversation));
    }

    private View.OnClickListener actionHandlerListener(
            final BaseEntityActionHandler<ConversationEntity> actionHandler,
            final ConversationEntity conversation) {

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionHandler.onAdapterEntityClick(conversation);
            }
        };
    }
}
