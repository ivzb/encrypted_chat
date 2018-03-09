package com.ivzb.semaphore._base.ui._contracts;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

public interface BaseViewModel {

    interface Builder {

        Builder setView(View view);
        Builder setSavedInstanceState(Bundle savedInstanceState);
        Builder setErrorClickListener(View.OnClickListener listener);

        void build();
    }

    Builder builder(Context context);

    void saveInstanceState(Bundle savedInstanceState);

    CardView getCvError();
    TextView getTvError();
}
