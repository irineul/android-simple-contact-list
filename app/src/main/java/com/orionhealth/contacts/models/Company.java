package com.orionhealth.contacts.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by irineul on 04/01/17.
 */
public class Company implements Parcelable {
    public String name;
    public String catchPhrase;
    public String bs;

    public Company(){}

    /* everything below here is for implementing Parcelable */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(catchPhrase);
        out.writeString(bs);
    }

    public static final Parcelable.Creator<Company> CREATOR = new Parcelable.Creator<Company>() {
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    private Company(Parcel in) {
        name = in.readString();
        catchPhrase = in.readString();
        bs = in.readString();
    }
}
