package com.ivzb.encrypted_chat.user_search.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ivzb.encrypted_chat.R;
import com.ivzb.encrypted_chat.users.ui.UsersViewModel;

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
