package com.ivzb.encrypted_chat.user_search.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivzb.encrypted_chat.R;
import com.ivzb.encrypted_chat._base.ui._contracts.BaseAdapter;
import com.ivzb.encrypted_chat._base.ui.endless.DefaultEndlessScrollViewModel;
import com.ivzb.encrypted_chat.users.data.UserEntity;
import com.ivzb.encrypted_chat.users.ui.UsersViewModel;
import com.ivzb.encrypted_chat.utils.ui.ScrollChildSwipeRefreshLayout;

import static com.ivzb.encrypted_chat.utils.Preconditions.checkNotNull;

class UserSearchViewModel
        extends UsersViewModel
        implements UserSearchContract.ViewModel {

    private static final String EMAIL_TEXT_STATE = "email_text_state";
    private static final String EMAIL_VISIBILITY_STATE = "email_visibility_state";


    private EditText mEtEmail;

    @Override
    public void init(View view) {
        super.init(view);

        mEtEmail = view.findViewById(R.id.etEmail);
    }

    @Override
    public void saveInstanceState(Bundle outState) {
        super.saveInstanceState(outState);

        saveEmailState(outState);
    }

    private void saveEmailState(Bundle outState) {
        String emailText = mEtEmail.getText().toString();
        outState.putString(EMAIL_TEXT_STATE, emailText);

        int emailVisibility = mEtEmail.getVisibility();
        outState.putInt(EMAIL_VISIBILITY_STATE, emailVisibility);
    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceState) {
        super.restoreInstanceState(savedInstanceState);

        if (savedInstanceState == null) return;

        restoreEmailState(savedInstanceState);
    }

    private void restoreEmailState(Bundle state) {
        if (state.containsKey(EMAIL_TEXT_STATE)) {
            String email = state.getString(EMAIL_TEXT_STATE);
            getEtEmail().setText(email);
        }

        if (state.containsKey(EMAIL_VISIBILITY_STATE)) {
            int visibility = state.getInt(EMAIL_VISIBILITY_STATE);
            getEtEmail().setVisibility(visibility);
        }
    }

    @Override
    public EditText getEtEmail() {
        return mEtEmail;
    }
}
