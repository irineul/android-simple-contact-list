package com.orionhealth.contacts.presenters;

import com.orionhealth.contacts.R;
import com.orionhealth.contacts.helpers.StringHelper;
import com.orionhealth.contacts.views.UserDetailView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by irineul on 08/01/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDetailPresenterTest {

    @Mock
    private UserDetailView mUserView;

    private UserDetailPresenter mUserDetailPresenter;

    @Before
    public void setUp() throws Exception {
        mUserDetailPresenter = new UserDetailPresenter(mUserView);
    }

    @Test
    public void shouldShowErrorMessageWhenWebsiteIsEmptyAndUserClickedOn() throws Exception {
        mUserDetailPresenter.onWebSiteClick(Mockito.anyString());
        verify(mUserView).showInvalidWebsiteError(R.string.error_website_invalid);
    }

    @Test
    public void shouldShowErrorMessageWhenWebsiteIsInvalidAndUserClickedOn() throws Exception {
        mUserDetailPresenter.onWebSiteClick(StringHelper.randomString(10));
        verify(mUserView).showInvalidWebsiteError(R.string.error_website_invalid);
    }

    @Test
    public void shouldOpenWebsiteIfUrlIsValidAndUserClickedOn() throws Exception {
        String validUrl = "http://www.orionhealth.com";
        mUserDetailPresenter.onWebSiteClick(validUrl);
        verify(mUserView).openWebsite(validUrl);
    }


    @Test
    public void shouldShowErrorMessageWhenEmailIsEmptyAndUserClickedOn() throws Exception {
        mUserDetailPresenter.onEmailClick(Mockito.anyString());
        verify(mUserView).showInvalidEmailError(R.string.error_email_invalid);
    }

    @Test
    public void shouldShowErrorMessageWhenEmailIsInvalidAndUserClickedOn() throws Exception {
        mUserDetailPresenter.onEmailClick(StringHelper.randomString(10));
        verify(mUserView).showInvalidEmailError(R.string.error_email_invalid);
    }

    @Test
    public void shouldOpenEmailIfEmailIsValid() throws Exception {
        String validEmail = "irineu.licks@gmail.com";
        mUserDetailPresenter.onEmailClick(validEmail);
        verify(mUserView).openEmail(validEmail);
    }

    @Test
    public void shouldShowErrorMessageIfUserIsNull() throws Exception {
        mUserDetailPresenter.displayUser(null);
        verify(mUserView).showUserIsNullError(R.string.error_user_is_null);
    }

}