package com.orionhealth.contacts.views;

import com.orionhealth.contacts.models.User;

/**
 * Created by irineul on 08/01/17.
 */

public interface UserDetailView {
    void openWebsite(String url);
    void openEmail(String email);
    void showInvalidWebsiteError(int resId);
    void showInvalidEmailError(int resId);
    void showUserIsNullError(int resId);
    void setUserToInterface(User user);
}
