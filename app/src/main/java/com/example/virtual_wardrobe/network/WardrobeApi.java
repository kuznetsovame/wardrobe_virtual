package com.example.virtual_wardrobe.network;

import com.example.virtual_wardrobe.model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WardrobeApi {

    @POST("./user/reg")
    Single<User> regUser(@Body User user);

    @POST("./user/auth")
    Single<User> authUser(@Body User user);

    @POST("/user/getUserById/{id}")
    User getUserById(int id);

    @POST("./friend/get")
    Single<List<User>> getFriends(@Body User user);

    @POST("/friend/sendRequest")
    void sendFriedRequest(User user1, User user2);

    @POST("/friend/acceptRequest")
    void acceptFriendRequest(User user1, User user2);

    @POST("/friend/denyRequest")
    void denyFriendRequest(User user1, User user2);

    @GET("./user/all")
    Single<List<User>> getAllUsers();

}
