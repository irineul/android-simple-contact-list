package com.orionhealth.contacts.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by irineul on 01/05/17.
 */
public class CheckNetworkHelper {


    private static final String TAG = CheckNetworkHelper.class.getSimpleName();



    public static boolean isInternetAvailable(Context context)
    {
        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        return info == null ? false : info.isConnected();
    }
}
