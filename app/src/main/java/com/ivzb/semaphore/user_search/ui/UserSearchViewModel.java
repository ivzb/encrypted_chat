package com.ivzb.semaphore.user_search.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ivzb.semaphore.R;
import com.ivzb.semaphore.users.ui.UsersViewModel;

class UserSearchViewModel
        extends UsersViewModel
        implements UserSearchContract.ViewModel {

    private static final String EMAIL_TEXT_STATE = "email_text_state";

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
    }

    @Override
    public EditText getEtEmail() {
        return mEtEmail;
    }
}
