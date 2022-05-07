package com.example.apprestful.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.example.apprestful.banco.Conexao;
import com.example.apprestful.models.MusicaComTraducao;

import java.util.ArrayList;

public class MusicaDAL
{   private Conexao con;
    private Context context;
    private final String TABLE="musica";

    public MusicaDAL(Context context) {
        this.context=context;
        con = new Conexao(context);
        try {
            con.conectar();
        }
        catch(Exception e)
        {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public boolean salvar(MusicaComTraducao o)
    {
        ContentValues dados=new ContentValues();
        dados.put("mus_id",o.mus_id);
        dados.put("mus_nome",o.mus_nome);
        dados.put("mus_artista",o.mus_artista);
        dados.put("mus_letraOriginal",o.mus_letraOriginal);
        dados.put("mus_letraTraduzida",o.mus_letraTraduzida);
        return con.inserir(TABLE,dados)>0;
    }

//    public boolean alterar(Musica o)
//    {
//        ContentValues dados=new ContentValues();
//        dados.put("mus_id",o.getId());
//        dados.put("mus_ano",o.getAno());
//        dados.put("mus_titulo",o.getTitulo());
//        dados.put("mus_interprete",o.getInterprete());
//        dados.put("mus_genero",o.getGenero().getId());
//        dados.put("mus_duracao",o.getDuracao());
//        return con.alterar(TABLE,dados,"mus_id="+o.getId())>0;
//    }
//    public boolean apagar(long chave)
//    {
//        return con.apagar(TABLE,"mus_id="+chave)>0;
//    }
//
    public MusicaComTraducao get(String id)
    {   MusicaDAL mdal=new MusicaDAL(context);
        MusicaComTraducao o = null;
        Cursor cursor=con.consultar("select * from "+TABLE+" where mus_id="+id);
        if(cursor.moveToFirst())
            o=new MusicaComTraducao(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        cursor.close();;
        return o;
    }
    public ArrayList<MusicaComTraducao> getList(String filtro)
    {
        ArrayList <MusicaComTraducao> objs = new ArrayList<MusicaComTraducao>();
        String sql="select * from "+TABLE;
        if (!filtro.equals(""))
            sql+=" where "+filtro;

        Cursor cursor=con.consultar(sql);
        if(cursor.moveToFirst())
            while (!cursor.isAfterLast()) {
                objs.add(this.get(cursor.getString(0)));
                cursor.moveToNext();
            }
        cursor.close();;
        return objs;
    }
}
