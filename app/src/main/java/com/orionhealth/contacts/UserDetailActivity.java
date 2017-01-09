package com.orionhealth.contacts;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orionhealth.contacts.models.User;
import com.orionhealth.contacts.presenters.UserDetailPresenter;
import com.orionhealth.contacts.utils.SlideAnimationUtil;
import com.orionhealth.contacts.views.UserDetailView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class UserDetailActivity extends BaseActivity implements UserDetailView{

    @InjectView(R.id.tvUsername)
    TextView mUsername;

    @InjectView(R.id.tvPhone)
    TextView mPhone;

    @InjectView(R.id.tvAddress)
    TextView mAddress;

    @InjectView(R.id.tvWebsite)
    TextView mWebsite;

    @InjectView(R.id.tvEmail)
    TextView mEmail;

    /* Company Details Init */

    @InjectView(R.id.tvCompanyName)
    TextView mCompanyName;

    @InjectView(R.id.tvCompanyCatchPhrase)
    TextView mCompanyCatchPhrase;

    @InjectView(R.id.tvCompanyBs)
    TextView mCompanyBs;

    @InjectView(R.id.transitions_container)
    ViewGroup mTransitionsContainer;
    private UserDetailPresenter mUserDetailPresenter;

    /* Company Details End */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.inject(this);

        User user = getIntent().getParcelableExtra("user");

        super.setActionBarTitle(user.name);
        mUserDetailPresenter = new UserDetailPresenter(this);

        mUserDetailPresenter.displayUser(user);

        SlideAnimationUtil.slideInFromLeft(this, mTransitionsContainer);
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    public void setUserToInterface(User user) {
        mUsername.setText(user.username.toUpperCase());
        mPhone.setText(user.phone);
        mAddress.setText(user.address.toString());
        mWebsite.setText(user.website.toUpperCase());
        mEmail.setText(user.email.toUpperCase());

        // Company Details
        mCompanyName.setText(user.company.name.toUpperCase());
        mCompanyCatchPhrase.setText(user.company.catchPhrase.toUpperCase());
        mCompanyBs.setText(user.company.bs.toUpperCase());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @OnClick(R.id.tvWebsite)
    public void onWebsiteClick(TextView textView){
        mUserDetailPresenter.onWebSiteClick(textView.getText().toString());

    }

    @OnClick(R.id.tvEmail)
    public void onEmailClick(TextView textView){
        mUserDetailPresenter.onEmailClick(textView.getText().toString());

    }

    public void openWebsite(String url){
        super.openSite(url);
    }

    public void openEmail(String url){
        super.openEmail(url);
    }

    @Override
    public void showInvalidWebsiteError(int resId) {
        super.showToastText(resId);
    }

    @Override
    public void showInvalidEmailError(int resId) {
        super.showToastText(resId);
    }

    @Override
    public void showUserIsNullError(int resId) {
        super.showToastText(resId);
    }

}
