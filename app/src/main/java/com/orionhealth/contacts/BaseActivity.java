package com.orionhealth.contacts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.orionhealth.contacts.helpers.CheckNetworkHelper;

/**
 * Created by irineul on 1/5/17.
 */
public class BaseActivity extends ActionBarActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle(this.getTitle().toString());

    }

    protected void setActionBarTitle(String title){
        this.getSupportActionBar().setDisplayShowCustomEnabled(true);
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);

        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.layout_title_view, null);
        ((TextView)v.findViewById(R.id.title)).setText(title);

        // assign the view to the actionbar
        this.getSupportActionBar().setCustomView(v);


        // set background color
        this.getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(getResources().getColor(R.color.color_blue)));
    }

    public void showAlertDialog(int message){
        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .create()
                .show();
    }

    public void showToastText(int message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    public boolean isInternetAvailable(){
        return CheckNetworkHelper.isInternetAvailable(this);
    }

    public void openSite(String url){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    public void openEmail(String email){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, R.string.email_subject);
        intent.putExtra(Intent.EXTRA_TEXT, R.string.email_body);
        Intent mailer = Intent.createChooser(intent, null);
        startActivity(mailer);
    }

}
