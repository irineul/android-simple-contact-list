package com.orionhealth.contacts.presenters;

import android.os.Handler;

import com.orionhealth.contacts.R;
import com.orionhealth.contacts.models.User;
import com.orionhealth.contacts.services.UserService;
import com.orionhealth.contacts.views.UserListView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by irineul on 1/4/17.
 */
public class UserListPresenter {

    UserListView mView;
    UserService mUser;
    Handler mHandler = new Handler();
    List<User> mUsers;

    public UserListPresenter(UserListView view, UserService user) {

        mView = view;
        mUser = user;
    }

    public void loadUsers(){
        if (mView.isInternetAvailable()) {
            new Thread(new Runnable() {
                public void run() {
                    mView.showProgress();
                    mUser.getApi()
                            .getUsers()
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<List<User>>() {
                                @Override
                                public void onCompleted() {
                                    mView.hideProgress();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    mView.showCantFetchApiError(R.string.error_cant_fetch_api);
                                }

                                @Override
                                public void onNext(List<User> users) {
                                    if (!users.isEmpty())
                                        displayUsers(users);
                                    else
                                        mView.showNoUsersError(R.string.error_no_users);
                                }

                            });


                    mHandler.post(new Runnable() {
                        public void run() {
                            mView.hideProgress();
                        }
                    });
                }

            }).start();
        }
        else{
            mView.showNoConnectionError(R.string.error_no_internet_connection);
        }
    }


    public void onUserSelect(User user){
        if (user != null)
            mView.showDetailUser(user);
        else
            mView.showUserNullError(R.string.error_user_is_null);

    }

    public void sortUsers(final boolean isAscending){
        Collections.sort(mUsers, new Comparator<User>() {
            public int compare(User lhs, User rhs) {
                return isAscending ? lhs.name.compareToIgnoreCase(rhs.name)
                        : rhs.name.compareToIgnoreCase(lhs.name);
            }
        });
        displayUsers(mUsers);
    }

    public List<User> getUsers(){
        return mUsers;
    }


    public void displayUsers(List<User> users) {
        mUsers = users;
        mView.displayUsers(mUsers);
    }

    public void onResume() {
        loadUsers();
    }
}
