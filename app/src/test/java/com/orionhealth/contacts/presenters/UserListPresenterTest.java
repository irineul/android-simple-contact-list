package com.orionhealth.contacts.presenters;

import com.orionhealth.contacts.R;
import com.orionhealth.contacts.helpers.StringHelper;
import com.orionhealth.contacts.models.Address;
import com.orionhealth.contacts.models.Company;
import com.orionhealth.contacts.models.Geo;
import com.orionhealth.contacts.models.User;
import com.orionhealth.contacts.services.UserService;
import com.orionhealth.contacts.views.UserListView;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import retrofit.Callback;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by irineul on 07/01/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserListPresenterTest {
    @Mock
    private UserListView mUserListView;
    @Mock
    private UserService mUserService;
    private UserListPresenter mUserPresenter;

    protected static final int RANDOM_STRING_LENGTH = 20;
    protected static final int QUANTITY_USERS_TO_TEST = 10;

    @Captor
    private ArgumentCaptor<Callback<List<User>>> cb;


    @Before
    public void setUp() throws Exception {
        mUserService = new UserService();
        mUserPresenter = new UserListPresenter(mUserListView, mUserService);
    }


    @Test
    public void shouldOrderTheListAsAscendingWhenUserSelectedTheOption() throws Exception {
        List<User> users = generateRandomUsers(QUANTITY_USERS_TO_TEST);


        mUserPresenter.displayUsers(new ArrayList(users)); // avoid reference
        mUserPresenter.sortUsers(true);

        // sort the users to compare
        Collections.sort(users, new Comparator<User>() {
            public int compare(User lhs, User rhs) {
                return lhs.name.compareToIgnoreCase(rhs.name);
            }
        });


        assertThat(mUserPresenter.getUsers(),
                IsIterableContainingInOrder.contains(users.toArray()));
    }

    @Test
    public void shouldOrderTheListAsDescendingWhenUserSelectedTheOption() throws Exception {
        List<User> users = generateRandomUsers(QUANTITY_USERS_TO_TEST);

        mUserPresenter.displayUsers(new ArrayList(users)); // avoid reference
        mUserPresenter.sortUsers(false);

        // sort the users to compare
        Collections.sort(users, new Comparator<User>() {
            public int compare(User lhs, User rhs) {
                return rhs.name.compareToIgnoreCase(lhs.name);
            }
        });

        assertThat(mUserPresenter.getUsers(),
                IsIterableContainingInOrder.contains(users.toArray()));
    }

    @Test
    public void shouldShowErrorMessageWhenSelectedUserIsNull()  throws Exception {
        mUserPresenter.onUserSelect(null);
        verify(mUserListView).showUserNullError(R.string.error_user_is_null);
    }

    @Test
    public void shouldShowUserDetailIfTheSelectedUserIsNotNull() throws Exception {
        User user = generateRandomUser(1);
        mUserPresenter.onUserSelect(user);
        verify(mUserListView).showDetailUser(user);
    }

    @Test
    public void shoudShowErrorMessageWhenUserDoesntHaveInternetConnection() throws Exception {
        when(mUserListView.isInternetAvailable()).thenReturn(false);
        mUserPresenter.loadUsers();
        verify(mUserListView).showNoConnectionError(R.string.error_no_internet_connection);
    }

    /* Helper Methods */
    private List<User> generateRandomUsers(int quantity){
        List<User> users = new ArrayList<User>();
        for (int i = 0; i< quantity; i++){
            users.add(generateRandomUser(i));
        }
        return users;
    }

    private User generateRandomUser(int number){
        User u = new User();
        u.id = number;
        u.name = StringHelper.randomString(RANDOM_STRING_LENGTH);
        u.username = StringHelper.randomString(RANDOM_STRING_LENGTH);
        u.email = StringHelper.randomString(RANDOM_STRING_LENGTH);
        u.phone = StringHelper.randomString(RANDOM_STRING_LENGTH);
        u.website = StringHelper.randomString(RANDOM_STRING_LENGTH);
        u.company = generateRandomCompany();
        u.address = generateRandomAddress();
        u.address.geo = generateRandomGeo();

        return u;
    }

    private Address generateRandomAddress(){
        Address a = new Address();
        a.city = StringHelper.randomString(RANDOM_STRING_LENGTH);
        a.suite = StringHelper.randomString(RANDOM_STRING_LENGTH);
        a.city = StringHelper.randomString(RANDOM_STRING_LENGTH);
        a.zipcode = StringHelper.randomString(RANDOM_STRING_LENGTH);

        return a;
    }

    private Geo generateRandomGeo(){
        Geo g = new Geo();
        g.lat = new Random().nextDouble();
        g.lng = new Random().nextDouble();

        return g;
    }

    private Company generateRandomCompany(){
        Company c = new Company();
        c.name = StringHelper.randomString(RANDOM_STRING_LENGTH);
        c.bs = StringHelper.randomString(RANDOM_STRING_LENGTH);
        c.catchPhrase = StringHelper.randomString(RANDOM_STRING_LENGTH);

        return c;
    }


}