package com.ivzb.semaphore._base.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.ivzb.semaphore.R;
import com.ivzb.semaphore._base.ui._contracts.BaseViewModel;

import static com.ivzb.semaphore.utils.Preconditions.checkNotNull;

public abstract class DefaultViewModel
        implements BaseViewModel {

    private static final String ERROR_TEXT_STATE = "error_text_state";
    private static final String ERROR_VISIBILITY_STATE = "error_visibility_state";

    private CardView mCvError;
    private TextView mTvError;

    protected void initViews(View view) {
        checkNotNull(view);

        mCvError = view.findViewById(R.id.cvError);
        mTvError = view.findViewById(R.id.tvError);
    }

    @Override
    public void saveInstanceState(Bundle outState) {
        saveErrorState(outState);
    }

    private void saveErrorState(Bundle outState) {
        String errorText = mTvError.getText().toString();
        outState.putString(ERROR_TEXT_STATE, errorText);

        int errorVisibility = mCvError.getVisibility();
        outState.putInt(ERROR_VISIBILITY_STATE, errorVisibility);
    }

    protected void restoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null) return;

        restoreErrorState(savedInstanceState);
    }

    private void restoreErrorState(Bundle state) {
        if (state.containsKey(ERROR_TEXT_STATE)) {
            String error = state.getString(ERROR_TEXT_STATE);
            getTvError().setText(error);
        }

        if (state.containsKey(ERROR_VISIBILITY_STATE)) {
            int visibility = state.getInt(ERROR_VISIBILITY_STATE);
            getCvError().setVisibility(visibility);
        }
    }

    protected void setErrorClickListener(View.OnClickListener listener) {
        if (mCvError == null) return;

        mCvError.setOnClickListener(listener);
    }

    @Override
    public CardView getCvError() {
        return mCvError;
    }

    @Override
    public TextView getTvError() {
        return mTvError;
    }

    public abstract class Builder implements BaseViewModel.Builder {

        protected Context mContext;
        protected View mView;
        protected Bundle mSavedInstanceState;
        protected View.OnClickListener mErrorClickListener;

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

        @Override
        public Builder setErrorClickListener(View.OnClickListener listener) {
            mErrorClickListener = listener;
            return this;
        }
    }
}

