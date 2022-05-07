package com.example.apprestful.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apprestful.R;
import com.example.apprestful.models.Doc;
import com.example.apprestful.models.MusicaComTraducao;

import java.util.List;

public class ListaMusicaTraducaoAdapter extends ArrayAdapter<MusicaComTraducao> {
    private int layout;
    public ListaMusicaTraducaoAdapter(@NonNull Context context, int resource, @NonNull List<MusicaComTraducao> objects) {
        super(context, resource, objects);
        this.layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.layout,parent,false);
        }
        TextView musica = (TextView)convertView.findViewById(R.id.tvMusica);

        MusicaComTraducao item = this.getItem(position);
        musica.setText(item.mus_nome+" - "+item.mus_artista);
        return convertView;
    }
}
