package com.orionhealth.contacts;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.orionhealth.contacts.adapters.UsersAdapter;
import com.orionhealth.contacts.models.User;
import com.orionhealth.contacts.presenters.UserListPresenter;
import com.orionhealth.contacts.services.UserService;
import com.orionhealth.contacts.views.UserListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;


public class UserListActivity extends BaseActivity implements UserListView {

    @InjectView(R.id.listViewUsers)
    ListView mListViewUsers;

    @InjectView(R.id.progressBar1)
    ProgressBar mProgressBar;

    @InjectView(R.id.llProgress)
    LinearLayout mLayoutProgress;

    UsersAdapter mUsersAdapter;

    UserListPresenter mListUserPresenter;
    UserService mUserService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.inject(this);

        ArrayList<User> dummyUsers = new ArrayList<User>();
        mUsersAdapter = new UsersAdapter(this, dummyUsers);
        mListViewUsers.setAdapter(mUsersAdapter);

        mUserService = new UserService();
        mListUserPresenter = new UserListPresenter(this, mUserService);


        mProgressBar.setIndeterminate(true);
        mProgressBar.getIndeterminateDrawable()
                .setColorFilter(getResources().getColor(R.color.color_blue), PorterDuff.Mode.SRC_IN);
    }


    @Override
    public void onResume(){
        super.onResume();
        mListUserPresenter.onResume();
    }


    @OnItemClick(R.id.listViewUsers)
    public void onUserSelect(int position) {
        mListUserPresenter.onUserSelect(mUsersAdapter.getItem(position));
    }

    public void showDetailUser(User user){
        Intent detailIntent = new Intent(this, UserDetailActivity.class);
        detailIntent.putExtra("user", user);
        startActivity(detailIntent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void displayUsers(List<User> users) {
        mUsersAdapter.clear();
        mUsersAdapter.addAll(users);
        mUsersAdapter.notifyDataSetInvalidated();
    }

    @Override
    public void showProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mLayoutProgress.setVisibility(ProgressBar.VISIBLE);
                mListViewUsers.setVisibility(ListView.GONE);
            }
        });
    }

    @Override
    public void hideProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mLayoutProgress.setVisibility(ProgressBar.GONE);
                mListViewUsers.setVisibility(ListView.VISIBLE);
            }
        });
    }

    @Override
    public void showNoUsersError(int resId) {
        super.showToastText(resId);
    }

    @Override
    public void showUserNullError(int resId) {
        super.showToastText(resId);
    }

    @Override
    public void showNoConnectionError(int resId) {
        super.showAlertDialog(resId);
    }

    public void showCantFetchApiError(int resId) {
        super.showAlertDialog(resId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_order_ascending:
                mListUserPresenter.sortUsers(true);
                return true;

            case R.id.action_order_descending:
                mListUserPresenter.sortUsers(false);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public boolean isInternetAvailable(){
        return super.isInternetAvailable();
    }
}
