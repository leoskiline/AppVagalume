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

import java.util.ArrayList;

public class ListaMusicaAdapter extends ArrayAdapter<Doc> {
    private int layout;

    public ListaMusicaAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Doc> objects) {
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

        Doc item = this.getItem(position);
        musica.setText(item.title+" - "+item.band);
        return convertView;
    }
}