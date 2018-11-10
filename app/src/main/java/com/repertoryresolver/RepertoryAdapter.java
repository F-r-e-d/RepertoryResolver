package com.repertoryresolver;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class RepertoryAdapter extends ArrayAdapter {
    public RepertoryAdapter(Context context, List<Repertory> repertories) {
        super(context, com.repertoryresolver.R.layout.listview_layout, repertories);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Repertory repertory = (Repertory) getItem(position);

        if (repertory == null){
            throw new IllegalStateException("A null repertory finf in RestaurantAdapter");
        }

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(com.repertoryresolver.R.layout.listview_layout, parent, false);
        }

        TextView name = convertView.findViewById(com.repertoryresolver.R.id.nameTxt);
        TextView number = convertView.findViewById(com.repertoryresolver.R.id.numberTxt);
        name.setText(repertory.getName());
        number.setText(repertory.getNumber());


        int idItem =((Repertory) getItem(position)).getId();
         convertView.setTag(idItem);
        return convertView;
    }



}
