package com.ivzb.encrypted_chat.utils.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.ivzb.encrypted_chat.R;

public class SwipeRefreshLayoutUtils {

    private SwipeRefreshLayoutUtils() {

    }

    public static void setLoading(
            final SwipeRefreshLayout refreshLayout,
            final boolean active) {

        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(active);
            }
        });
    }

    public static void setup(
            Context context,
            ScrollChildSwipeRefreshLayout refreshLayout,
            View scrollUpChild,
            SwipeRefreshLayout.OnRefreshListener listener) {

        refreshLayout.setColorSchemeColors(
                getColor(context, R.color.colorPrimary),
                getColor(context, R.color.colorAccent),
                getColor(context, R.color.colorPrimaryDark)
        );

        refreshLayout.setScrollUpChild(scrollUpChild);
        refreshLayout.setOnRefreshListener(listener);
    }

    private static int getColor(Context context, int color) {
        return ContextCompat.getColor(context, color);
    }
}