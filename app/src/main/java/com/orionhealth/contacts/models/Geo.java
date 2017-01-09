package com.orionhealth.contacts.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by irineul on 04/01/17.
 */
public class Geo implements Parcelable {

    public double lat;
    public double lng;


    public Geo(){}

    /* everything below here is for implementing Parcelable */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeDouble(lat);
        out.writeDouble(lng);
    }

    public static final Parcelable.Creator<Geo> CREATOR = new Parcelable.Creator<Geo>() {
        public Geo createFromParcel(Parcel in) {
            return new Geo(in);
        }

        public Geo[] newArray(int size) {
            return new Geo[size];
        }
    };

    private Geo(Parcel in) {
        lat = in.readDouble();
        lng = in.readDouble();
    }
}
