package com.example.apprestful.models;

public class MusicaComTraducao {
    public String mus_id;
    public String mus_nome;
    public String mus_artista;
    public String mus_letraOriginal;
    public String mus_letraTraduzida;

    public MusicaComTraducao(String mus_id, String mus_nome, String mus_artista, String mus_letraOriginal, String mus_letraTraduzida) {
        this.mus_id = mus_id;
        this.mus_nome = mus_nome;
        this.mus_artista = mus_artista;
        this.mus_letraOriginal = mus_letraOriginal;
        this.mus_letraTraduzida = mus_letraTraduzida;
    }
}
