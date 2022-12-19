package com.example.virtual_wardrobe.repository;

import com.example.virtual_wardrobe.model.User;
import com.example.virtual_wardrobe.network.WardrobeApi;
import com.example.virtual_wardrobe.screens.profile.ProfileType;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class UserReopository {

    private User mainUser = null;
    private List<User> userList;
    private List<User> friends;

    private WardrobeApi wardrobeApi;


    public UserReopository(WardrobeApi wardrobeApi) {
        this.wardrobeApi = wardrobeApi;
        updateUsers();

        userList = new ArrayList<>();
        friends = new ArrayList<>();
    }

    public User getMainUser() {
        return mainUser;
    }

    public void setMainUser(User mainUser) {
        this.mainUser = mainUser;
        updateMainFriends();
    }

    public List<User> getUserList() {
        return userList;
    }

    public void updateUsers() {
        wardrobeApi.getAllUsers()
                .subscribeOn(Schedulers.computation())
                .map(users -> {
                    for (User it : users) {
                        it.type = ProfileType.FRIEND;
                    }
                    return users;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(value -> {
                    userList.clear();
                    userList.addAll(value);

                }, throwable -> {

                });
    }

    public void updateMainFriends() {
        wardrobeApi.getFriends(mainUser)
                .subscribeOn(Schedulers.computation())
                .map(users -> {
                    for (User it : users) {
                        it.type = ProfileType.FRIEND;
                    }
                    return users;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                    friends.clear();
                    friends.addAll(users);
                });
    }

    public List<User> getMainUserFriends() {
        return friends;
    }

    public  Single<List<User>> getFriends(User user) {
        List<User> friends = new ArrayList<>();
        if (mainUser != null)
            return wardrobeApi.getFriends(mainUser)
                    .subscribeOn(Schedulers.computation())
                    .map(users -> {
//                        for (User it : users) {
//                            it.type = ProfileType.FRIEND;
//                        }
                        return users;
                    })
                    .observeOn(AndroidSchedulers.mainThread());


        return null;
    }
}
