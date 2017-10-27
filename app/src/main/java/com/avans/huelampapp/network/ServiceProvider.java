package com.avans.huelampapp.network;

import com.avans.huelampapp.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProvider {

    private static ServiceProvider instance;

    public static ServiceProvider instance() {
        if (instance == null) {
            instance = new ServiceProvider();
        }
        return instance;
    }

    private Retrofit.Builder builder;
    private Retrofit retrofit;
    private OkHttpClient.Builder httpClient;

    private ServiceProvider(){
        httpClient = new OkHttpClient.Builder();
        builder = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
    }

    public <S> S createService(
            Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
