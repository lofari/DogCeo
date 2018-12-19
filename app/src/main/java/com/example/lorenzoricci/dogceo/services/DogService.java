package com.example.lorenzoricci.dogceo.services;

import com.example.lorenzoricci.dogceo.models.BreedResponse;
import com.example.lorenzoricci.dogceo.models.ImageResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DogService {

    @GET("breeds/list")
    Call<BreedResponse> getBreed();

    @GET("breed/{breed}/images/random")
    Call<ImageResponse> getImage(@Path("breed") String breed);


}