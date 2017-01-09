package com.orionhealth.contacts.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by irineul on 04/01/17.
 */
public class Address implements Parcelable {
    public String street;
    public String suite;
    public String city;
    public String zipcode;

    public Geo geo;


    public Address(){}


    /* everything below here is for implementing Parcelable */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(street);
        out.writeString(suite);
        out.writeString(city);
        out.writeString(zipcode);
        out.writeParcelable(geo, flags);
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    private Address(Parcel in) {
        street = in.readString();
        suite = in.readString();
        city = in.readString();
        zipcode = in.readString();
        geo = in.readParcelable(Geo.class.getClassLoader());
    }

    @Override
    public String toString()
    {
        return new StringBuffer()
                .append(suite)
                .append(", ")
                .append(street)
                .append(", ")
                .append(city)
                .append(", ")
                .append(zipcode).toString();
    }
}
