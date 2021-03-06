package com.ivzb.semaphore.home.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ivzb.semaphore.R;
import com.ivzb.semaphore._base.data.DataSources;
import com.ivzb.semaphore._base.ui.DefaultActivity;
import com.ivzb.semaphore.conversations.ui.ConversationsPresenter;
import com.ivzb.semaphore.conversations.ui.ConversationsView;
import com.ivzb.semaphore.conversations.ui.ConversationsViewModel;
import com.ivzb.semaphore.user_search.ui.UserSearchActivity;
import com.ivzb.semaphore.users.ui.UsersPresenter;
import com.ivzb.semaphore.users.ui.UsersView;
import com.ivzb.semaphore.users.ui.UsersViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.ivzb.semaphore.utils.Preconditions.checkNotNull;

public class HomeActivity
        extends DefaultActivity
        implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_act);

        FloatingActionButton fab = findViewById(R.id.fabAddUser);
        fab.setOnClickListener(this);

        ViewPagerMetadata achievementsMetadata = initMetadata();

        ViewPager viewPager = findViewById(R.id.view_pager);

        HomeAdapter adapter = new HomeAdapter(
                getSupportFragmentManager(),
                achievementsMetadata.getFragments());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        achievementsMetadata.setupTabLayout(this, tabLayout);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, UserSearchActivity.class);
        startActivityForResult(intent, UserSearchActivity.REQUEST_ADD_USER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == UserSearchActivity.REQUEST_ADD_USER && resultCode == Activity.RESULT_OK) {
            String message = data.getStringExtra(UserSearchActivity.MESSAGE_EXTRA);

            if (message == null || message.equals("")) return;

            Snackbar.make(findViewById(R.id.fabAddUser), message, Snackbar.LENGTH_LONG).show();
        }
    }

    private ViewPagerMetadata initMetadata() {
        ViewPagerMetadata metadata = new ViewPagerMetadata(2);

        metadata.add(
                initUsersView(),
                "Users",
                R.drawable.ic_users);

        metadata.add(
                initConversationsView(),
                "Conversations",
                R.drawable.ic_chat);

        return metadata;
    }

    private Fragment initUsersView() {
        UsersView usersView = new UsersView();

        usersView.setViewModel(new UsersViewModel());
        usersView.setPresenter(new UsersPresenter(
                this,
                usersView,
                DataSources.getInstance().users()));

        return usersView;
    }

    private Fragment initConversationsView() {
        ConversationsView conversationsView = new ConversationsView();

        conversationsView.setViewModel(new ConversationsViewModel());
        conversationsView.setPresenter(new ConversationsPresenter(
                this,
                conversationsView,
                DataSources.getInstance().conversations()));

        return conversationsView;
    }

    private class ViewPagerMetadata {

        private List<Fragment> mFragments;
        private List<String> mTitles;
        private List<Integer> mIcons;

        ViewPagerMetadata(int initialCapacity) {
            mFragments = new ArrayList<>(initialCapacity);
            mTitles = new ArrayList<>(initialCapacity);
            mIcons = new ArrayList<>(initialCapacity);
        }

        void add(Fragment fragment, String title, int icon) {
            mFragments.add(fragment);
            mTitles.add(title);
            mIcons.add(icon);
        }

        List<Fragment> getFragments() {
            return mFragments;
        }

        void setupTabLayout(Context context, TabLayout tabLayout) {
            checkNotNull(context);

            for (int i = 0; i < mFragments.size(); i++) {
                TabLayout.Tab tab = checkNotNull(tabLayout.getTabAt(i));

                tab.setText(mTitles.get(i));
                tab.setIcon(mIcons.get(i));
            }
        }
    }
}