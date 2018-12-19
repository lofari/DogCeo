package com.example.lorenzoricci.dogceo.services;



import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {

    private static final String URL = "https://dog.ceo/api/";

    private static HttpLoggingInterceptor logger =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient okHttp = new OkHttpClient.Builder().addInterceptor(logger).build();

    private static DogService buildClient() {
            return new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttp)
                    .build().create(DogService.class);
    }

    private static DogService client = buildClient();
    public static DogService getService(){
        return client;
    }

}
