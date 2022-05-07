package com.example.apprestful.services;

import com.example.apprestful.models.ListaMusicas;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ListaMusicaService {
    @GET("search.excerpt")
    Call<ListaMusicas> buscarMusicas(@Query("q") String artistmusic, @Query("apikey") String apikey);
}
