package com.ivzb.encrypted_chat.conversation.ui;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ivzb.encrypted_chat.R;
import com.ivzb.encrypted_chat._base.ui.DefaultActionHandlerAdapter;
import com.ivzb.encrypted_chat._base.ui._contracts.BaseEntityActionHandler;
import com.ivzb.encrypted_chat.conversation.data.MessageEntity;

public class MessagesAdapter
        extends DefaultActionHandlerAdapter<MessageEntity> {

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

        CardView cvMessage = binding.findViewById(R.id.cvMessage);
        cvMessage.setOnClickListener(
                new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         mActionHandler.onAdapterEntityClick(message);
                     }
                 });

        TextView tvMessage = binding.findViewById(R.id.tvMessage);
        tvMessage.setText(message.getMessage());
    }
}
