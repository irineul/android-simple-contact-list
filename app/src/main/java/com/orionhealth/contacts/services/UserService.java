package com.orionhealth.contacts.services;

import com.orionhealth.contacts.models.User;

import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by irineul on 1/4/17.
 */
public class UserService {

    private static final String USER_SERVER_URL = "http://jsonplaceholder.typicode.com";
    private UserApi mUserApi;

    public UserService() {


        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(USER_SERVER_URL )
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mUserApi = restAdapter.create(UserApi.class);
    }

    public UserApi getApi() {

        return mUserApi;
    }

    public interface UserApi {

        @GET("/users")
        public void getUsers(
                Callback<List<User>> callback
        );

        @GET("/users")
        public Observable<List<User>>
            getUsers();

    }
}
