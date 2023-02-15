package com.usat.controlderiesgos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usat.controlderiesgos.Model.Amenaza;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AmenazaAdapter extends RecyclerView.Adapter<AmenazaAdapter.ViewHolder> {

    private ArrayList<Amenaza> amenazaArrayList;
    private Context context;

    public AmenazaAdapter(ArrayList<Amenaza> amenazaArrayList, Context context) {
        this.amenazaArrayList = amenazaArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AmenazaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AmenazaAdapter.ViewHolder holder, int position) {
        Amenaza amenaza = amenazaArrayList.get(position);
        holder.amenazaidtxt.setText(String.valueOf(amenaza.getAmenazaid()));
        holder.descripciontxt.setText(amenaza.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return amenazaArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView amenazaidtxt,descripciontxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            amenazaidtxt=itemView.findViewById(R.id.tvAmenazaId);
            descripciontxt=itemView.findViewById(R.id.tvDescripcion);
        }
    }
}
