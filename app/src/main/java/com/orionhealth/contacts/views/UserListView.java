package com.orionhealth.contacts.views;

import com.orionhealth.contacts.models.User;

import java.util.List;

/**
 * Created by irineul on 07/01/17.
 */
public interface UserListView {
    void displayUsers(List<User> users);
    void showProgress();
    void hideProgress();
    void showNoUsersError(int resId);
    void showUserNullError(int resId);
    void showCantFetchApiError(int resId);
    void showNoConnectionError(int resId);
    void showDetailUser(User user);
    boolean isInternetAvailable();
}
