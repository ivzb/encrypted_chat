package com.ivzb.encrypted_chat.user_search.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ivzb.encrypted_chat.R;
import com.ivzb.encrypted_chat.users.ui.UsersView;

public class UserSearchView
        extends UsersView
        implements UserSearchContract.View {

    @Override
    public View inflateFragment(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.user_search_frag, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                String email = ((UserSearchViewModel) mViewModel).getEtEmail().getText().toString();
                ((UserSearchPresenter) mPresenter).searchUser(email);
                break;
        }

        return true;
    }

    @Override
    public void onRefresh() {
        String email = ((UserSearchViewModel) mViewModel).getEtEmail().getText().toString();
        mPresenter.refresh(email);
    }

    @Override
    public void finish(String message) {
        Intent intent = new Intent();
        intent.putExtra(UserSearchActivity.MESSAGE_EXTRA, message);

        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }
}