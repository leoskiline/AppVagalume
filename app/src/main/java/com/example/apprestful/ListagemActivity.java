package com.example.apprestful;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.apprestful.adapters.ListaMusicaAdapter;
import com.example.apprestful.adapters.ListaMusicaTraducaoAdapter;
import com.example.apprestful.banco.MusicaDAL;
import com.example.apprestful.models.MusicaComTraducao;

import java.util.ArrayList;

public class ListagemActivity extends AppCompatActivity {
    ListView lvListagem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);
        lvListagem = findViewById(R.id.lvListagem);
        MusicaDAL mdal = new MusicaDAL(ListagemActivity.this);
        ArrayList<MusicaComTraducao> musicas = mdal.getList("");
        ListaMusicaTraducaoAdapter adapter = new ListaMusicaTraducaoAdapter(ListagemActivity.this,R.layout.items_musica,musicas);
        lvListagem.setAdapter(adapter);
        lvListagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent traducao = new Intent(ListagemActivity.this,LetraActivity.class);
                MusicaComTraducao mu = (MusicaComTraducao)adapterView.getAdapter().getItem(i);
                traducao.putExtra("musica",mu.mus_nome);
                traducao.putExtra("artista",mu.mus_artista);
                traducao.putExtra("letraOriginal",mu.mus_letraOriginal);
                traducao.putExtra("letraTraduzida",mu.mus_letraTraduzida);
                startActivity(traducao);
            }
        });
    }
}