package com.ivzb.semaphore._base.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.ivzb.semaphore._base.ui._contracts.BaseViewModel;

public abstract class DefaultViewModel
        implements BaseViewModel {

    public abstract class Builder implements BaseViewModel.Builder {

        protected Context mContext;
        protected View mView;
        protected Bundle mSavedInstanceState;

        public Builder(Context context) {
            mContext = context;
        }

        @Override
        public Builder setView(View view) {
            mView = view;
            return this;
        }

        @Override
        public Builder setSavedInstanceState(Bundle savedInstanceState) {
            mSavedInstanceState = savedInstanceState;
            return this;
        }
    }
}

