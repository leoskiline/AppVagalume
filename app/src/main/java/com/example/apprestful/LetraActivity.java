package com.example.apprestful;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class LetraActivity extends AppCompatActivity {
    TextView letraOriginal;
    TextView letraTraduzida;
    TextView tvInfoMusica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letra);
        Intent traducao = getIntent();
        String musica = traducao.getStringExtra("musica");
        String artista = traducao.getStringExtra("artista");
        letraOriginal = findViewById(R.id.tvLetraOriginal);
        letraTraduzida = findViewById(R.id.tvLetraTraduzida);
        tvInfoMusica = findViewById(R.id.tvInfoMusica);
        letraOriginal.setText(traducao.getStringExtra("letraOriginal"));
        letraTraduzida.setText(traducao.getStringExtra("letraTraduzida"));
        letraOriginal.setMovementMethod(new ScrollingMovementMethod());
        letraTraduzida.setMovementMethod(new ScrollingMovementMethod());
        tvInfoMusica.setText("Musica: "+musica+" - Artista: "+artista);
    }
}