package com.orionhealth.contacts.presenters;

import com.orionhealth.contacts.R;
import com.orionhealth.contacts.helpers.StringHelper;
import com.orionhealth.contacts.models.User;
import com.orionhealth.contacts.views.UserDetailView;

/**
 * Created by irineul on 08/01/17.
 */

public class UserDetailPresenter {

    UserDetailView mView;

    public UserDetailPresenter(UserDetailView view) {
        mView = view;
    }

    public void onWebSiteClick(String website) {
        if (website.isEmpty()){
            mView.showInvalidWebsiteError(R.string.error_website_invalid);
            return;
        }
        else {
            // verify if contains http/https before the website
            boolean containsHttp = website.contains("http://") || website.contains("https://");
            if (!containsHttp) {
                website = "http://".concat(website);
            }

            // verify if is a valid url
            if (StringHelper.isValidUrl(website)) {
                mView.openWebsite(website);
            } else
                mView.showInvalidWebsiteError(R.string.error_website_invalid);
        }
    }

    public void onEmailClick(String email) {
        if (StringHelper.isValidEmail(email) && !email.isEmpty())
            mView.openEmail(email);
        else
            mView.showInvalidEmailError(R.string.error_email_invalid);
    }

    public void displayUser(User user){
        if (user != null)
            mView.setUserToInterface(user);
        else
            mView.showUserIsNullError(R.string.error_user_is_null);
    }
}
