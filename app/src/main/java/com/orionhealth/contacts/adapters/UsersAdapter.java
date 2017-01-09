package com.orionhealth.contacts.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.orionhealth.contacts.CustomTextView;
import com.orionhealth.contacts.R;
import com.orionhealth.contacts.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by irineul on 1/4/15.
 */
public class UsersAdapter extends ArrayAdapter<User> {

    Context mContext;
    List<User> mUsers;

    public UsersAdapter(Context ctx, ArrayList<User> users) {
        super(ctx, 0, users);
        mContext = ctx;
        this.mUsers = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        User user = getItem(position);

        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_user_item, parent, false);

        // set user name
        CustomTextView name = (CustomTextView) convertView.findViewById(R.id.textViewUserName);
        name.setText(user.name.toUpperCase());

        // set user email
        CustomTextView email = (CustomTextView) convertView.findViewById(R.id.textViewUserEmail);
        email.setText(user.email.toUpperCase());


        return convertView;
    }

    public List<User> getItems(){
        return mUsers;
    }


}
