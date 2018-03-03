package com.ivzb.encrypted_chat.user_search.ui;

import android.widget.EditText;
import com.ivzb.encrypted_chat.users.ui.UsersContract;

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