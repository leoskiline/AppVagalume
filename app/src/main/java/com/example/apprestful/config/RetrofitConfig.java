package com.example.apprestful.config;

import com.example.apprestful.models.Musica;
import com.example.apprestful.services.ListaMusicaService;
import com.example.apprestful.services.MusicaService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit=new Retrofit.Builder().baseUrl("https://api.vagalume.com.br/").
                addConverterFactory(GsonConverterFactory.create()).
                build();
    }
    public ListaMusicaService getListaMusicaService() {
        return this.retrofit.create(ListaMusicaService.class);
    }

    public MusicaService getMusicaService() {
        return this.retrofit.create(MusicaService.class);
    }

}
