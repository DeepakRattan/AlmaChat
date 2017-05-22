package com.almabay.almachat.apiServiceInterface;


import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;


/**
 * Created by deepakr on 2/2/2016.
 */
public class ServiceGenerator {

    public static final String API_BASE_URL = "http://phpstack-11819-25991-62288.cloudwaysapps.com/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}