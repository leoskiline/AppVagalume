package com.example.apprestful.services;

import com.example.apprestful.models.Musica;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MusicaService {
    @GET("search.php")
    Call<Musica> buscarLetraTraducao(@Query("musid") String musicID, @Query("apikey") String apikey);
}
