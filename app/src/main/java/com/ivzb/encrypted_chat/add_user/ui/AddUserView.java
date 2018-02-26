package com.ivzb.encrypted_chat.add_user.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ivzb.encrypted_chat.R;
import com.ivzb.encrypted_chat._base.ui.DefaultView;

public class AddUserView
        extends DefaultView<AddUserContract.Presenter, AddUserContract.ViewModel>
        implements AddUserContract.View {

    private EditText mEtEmail;

    public AddUserView() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.add_user_frag, container, false);

        mEtEmail = view.findViewById(R.id.etEmail);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_view_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                String email = mEtEmail.getText().toString();
                mPresenter.saveUser(email);
                break;
        }

        return true;
    }

    @Override
    public void finish() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }
}