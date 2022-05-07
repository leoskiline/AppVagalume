package com.example.apprestful;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apprestful.adapters.ListaMusicaAdapter;
import com.example.apprestful.banco.MusicaDAL;
import com.example.apprestful.config.RetrofitConfig;
import com.example.apprestful.models.Doc;
import com.example.apprestful.models.ListaMusicas;
import com.example.apprestful.models.Mu;
import com.example.apprestful.models.Musica;
import com.example.apprestful.models.MusicaComTraducao;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText etNome;
    private EditText etArtista;
    private Button btnAdicionar;
    private static final String _APIKEY = "581946e995511b1c6bbe50b2546b1fb4";
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNome = findViewById(R.id.etNome);
        etArtista = findViewById(R.id.etArtista);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        listView = findViewById(R.id.lvMusicas);

        btnAdicionar.setOnClickListener(e-> {
            CarregarVagalume();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionsmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;

        switch(item.getItemId())
        {
            case R.id.miCadastrar:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.miMusicas:
                intent = new Intent(this, ListagemActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void CarregarVagalume() {
        try {
            RetrofitConfig rc = new RetrofitConfig();
            Call<ListaMusicas> call = new RetrofitConfig().getListaMusicaService().buscarMusicas(etArtista.getText().toString()+" "+etNome.getText().toString(),_APIKEY);
            call.enqueue(new Callback<ListaMusicas>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(Call<ListaMusicas> call, Response<ListaMusicas> response) {
                    ListaMusicas listaMusicas = response.body();
                    if(listaMusicas != null && listaMusicas.response != null && listaMusicas.response.docs != null)
                    {
                        ListaMusicaAdapter adapter = new ListaMusicaAdapter(MainActivity.this,R.layout.items_musica,listaMusicas.response.docs);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Doc obj = (Doc)adapterView.getAdapter().getItem(i);
                                Call<Musica> callMusica = new RetrofitConfig().getMusicaService().buscarLetraTraducao(obj.id,_APIKEY);
                                callMusica.enqueue(new Callback<Musica>() {
                                    @Override
                                    public void onResponse(Call<Musica> call, Response<Musica> response) {
                                        Musica musica = response.body();
                                        if(musica != null && musica.mus != null && musica.art != null && musica.mus.get(0) != null)
                                        {
                                            String traducao = "";
                                            if(musica.mus.get(0).translate != null && musica.mus.get(0).translate.get(0) != null)
                                            {
                                                traducao += musica.mus.get(0).translate.get(0).text;
                                            }
                                            MusicaComTraducao mst = new MusicaComTraducao(musica.mus.get(0).id,musica.mus.get(0).name,musica.art.name,musica.mus.get(0).text,traducao);
                                            MusicaDAL mdal = new MusicaDAL(MainActivity.this);
                                            MusicaComTraducao musicaExiste = mdal.get(mst.mus_id);
                                            if(musicaExiste == null)
                                            {
                                                if(mdal.salvar(mst))
                                                {
                                                    Toast.makeText(MainActivity.this,"Musica salva com sucesso!",Toast.LENGTH_LONG).show();
                                                    new android.os.Handler().postDelayed(
                                                            new Runnable() {
                                                                public void run() {
                                                                    Intent intent = new Intent(MainActivity.this, ListagemActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            }, 3000);

                                                }else{
                                                    Toast.makeText(MainActivity.this,"Não foi possivel salvar musica!",Toast.LENGTH_LONG).show();
                                                }
                                            }else{
                                                Toast.makeText(MainActivity.this,"Musica já consta na lista, escolha outra!",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Musica> call, Throwable t) {

                                    }
                                });
                            }
                        });
                    }

                }

                @Override
                public void onFailure(Call<ListaMusicas> call, Throwable t) {
                    Toast.makeText(MainActivity.this,"Ocorreu um erro ao consultar API Vagalume",Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.i("ERROR",e.getMessage());
        }
    }
}