package com.orionhealth.contacts.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by irineul on 1/4/15.
 */
public class User implements Parcelable {

    public int id;
    public String name;
    public String username;
    public String email;
    public String phone;
    public String website;
    public Address address;
    public Company company;


    public User(){}

    /* everything below here is for implementing Parcelable */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(name);
        out.writeString(username);
        out.writeString(email);
        out.writeString(phone);
        out.writeString(website);
        out.writeParcelable(address, flags);
        out.writeParcelable(company, flags);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private User(Parcel in) {
        id = in.readInt();
        name = in.readString();
        username = in.readString();
        email = in.readString();
        phone = in.readString();
        website = in.readString();
        address = in.readParcelable(Address.class.getClassLoader());
        company = in.readParcelable(Company.class.getClassLoader());
    }

}
