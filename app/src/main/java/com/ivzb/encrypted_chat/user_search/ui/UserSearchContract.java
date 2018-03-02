package com.ivzb.encrypted_chat.user_search.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivzb.encrypted_chat._base.ui._contracts.BaseAdapter;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollPresenter;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollViewModel;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollView;
import com.ivzb.encrypted_chat.users.data.UserEntity;
import com.ivzb.encrypted_chat.utils.ui.ScrollChildSwipeRefreshLayout;

class UserSearchContract {

    interface View extends BaseEndlessScrollView<UserEntity, Presenter, ViewModel> {

        void onUserClick(UserEntity user);
        void onAddUserClick(UserEntity user);
        void onRemoveUserClick(UserEntity user);
        void onErrorClick();
    }

    interface Presenter extends BaseEndlessScrollPresenter<UserEntity> {

        void searchUser(String email);

        void clickUser(UserEntity user);
        void clickAddUser(UserEntity user);
        void clickRemoveUser(UserEntity user);

        void clickErrorMessage();
    }

    public interface ViewModel extends BaseEndlessScrollViewModel<UserEntity> {

        void init(android.view.View view);

        void saveInstanceState(Bundle savedInstanceState);
        void restoreInstanceState(Bundle savedInstanceState);

        void setErrorClickListener(android.view.View.OnClickListener listener);

        void setAdapter(BaseAdapter<UserEntity> adapter);

        EditText getEtEmail();

        Parcelable getUsersState();
        void setUsersState(Parcelable state);

        Parcelable getLayoutManagerState();
        void setLayoutManagerState(Parcelable state);

        ScrollChildSwipeRefreshLayout getRefreshLayout();
        RecyclerView getRvUsers();

        TextView getTvNoUsers();
        ImageView getIvNoUsers();

        CardView getCvError();
        TextView getTvError();
    }
}