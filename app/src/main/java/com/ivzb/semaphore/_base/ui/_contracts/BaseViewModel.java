package com.ivzb.semaphore._base.ui._contracts;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

public interface BaseViewModel {

    interface Builder {

        Builder setView(View view);
        Builder setSavedInstanceState(Bundle savedInstanceState);

        void build();
    }

    Builder builder(Context context);

    void saveInstanceState(Bundle savedInstanceState);
}
