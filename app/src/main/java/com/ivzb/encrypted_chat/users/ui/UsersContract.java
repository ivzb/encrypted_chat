package com.ivzb.encrypted_chat.users.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivzb.encrypted_chat._base.ui._contracts.BaseAdapter;
import com.ivzb.encrypted_chat._base.ui._contracts.BaseEntityActionHandler;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollPresenter;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollViewModel;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollView;
import com.ivzb.encrypted_chat.users.data.UserEntity;
import com.ivzb.encrypted_chat.utils.ui.ScrollChildSwipeRefreshLayout;

public class UsersContract {

    public interface View extends BaseEndlessScrollView<UserEntity, Presenter, ViewModel> {

        void onUserClick(UserEntity user);
        void onAddUserClick(UserEntity user);
        void onRemoveUserClick(UserEntity user);
        void onErrorClick();
    }

    public interface Presenter extends BaseEndlessScrollPresenter<UserEntity> {

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
