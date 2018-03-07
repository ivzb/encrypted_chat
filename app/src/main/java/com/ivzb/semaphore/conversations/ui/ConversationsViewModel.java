package com.ivzb.semaphore.conversations.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivzb.semaphore.R;
import com.ivzb.semaphore._base.ui._contracts.BaseAdapter;
import com.ivzb.semaphore._base.ui.endless.DefaultEndlessScrollViewModel;
import com.ivzb.semaphore.conversations.data.ConversationEntity;
import com.ivzb.semaphore.utils.ui.ScrollChildSwipeRefreshLayout;

import static com.ivzb.semaphore.utils.Preconditions.checkNotNull;

public class ConversationsViewModel
        extends DefaultEndlessScrollViewModel<ConversationEntity>
        implements ConversationsContract.ViewModel {

}
