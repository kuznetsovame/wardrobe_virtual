package com.example.virtual_wardrobe.di;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.virtual_wardrobe.R;
import com.example.virtual_wardrobe.network.WardrobeApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class WardrobeModule {
    @Provides
    @Singleton
    public SharedPreferences providesSharedPreferences(@ApplicationContext Context context) {
        return context.getSharedPreferences(
                context.getString(R.string.app_name),
                Context.MODE_PRIVATE
        );
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        return logging;
    }


    @Provides
    @Singleton
    public OkHttpClient httpClient(
            HttpLoggingInterceptor httpLoggingInterceptor,
            SharedPreferences sharedPreferences) {
        return new OkHttpClient.Builder()
//            .addInterceptor { chain ->
//                val request = chain.request().newBuilder()
//                    .addHeader(
//                        "Authorization",
//                        "Bearer ${sharedPreferences.getString("API_KEY", "").orEmpty()}"
//                    )
//                    .build()
//
//                return@addInterceptor chain.proceed(request)
//            }
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    public Retrofit wardrobeApi(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("http://192.168.178.30:8081")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public WardrobeApi wardrobeProvide(Retrofit retrofit) {
        return retrofit.create(WardrobeApi.class);
    }


}
