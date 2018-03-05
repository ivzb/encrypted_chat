package com.ivzb.semaphore.user_search.ui;

import android.widget.EditText;

import com.ivzb.semaphore.users.ui.UsersContract;

class UserSearchContract {

    interface View extends UsersContract.View {

        void finish(String message);
    }

    interface Presenter extends UsersContract.Presenter {

        void searchUser(String email);
    }

    public interface ViewModel extends UsersContract.ViewModel {

        EditText getEtEmail();
    }
}