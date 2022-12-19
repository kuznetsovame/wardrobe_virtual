package com.example.virtual_wardrobe.di;


import com.example.virtual_wardrobe.network.WardrobeApi;
import com.example.virtual_wardrobe.repository.UserReopository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UserRepositoryModule {

    @Provides
    public UserReopository userReopository(WardrobeApi wardrobeApi) {
        return new UserReopository(wardrobeApi);
    }
}
