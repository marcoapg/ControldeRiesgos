package com.usat.controlderiesgos.ui.amenaza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usat.controlderiesgos.Model.Amenaza;
import com.usat.controlderiesgos.R;

import java.util.ArrayList;

public class AmenazaAdapter extends RecyclerView.Adapter<AmenazaAdapter.ViewHolder> {

    private ArrayList<Amenaza> amenazaArrayList;
    private Context context;


    private AmenazaClickInterface amenazaClickInterface;

    public AmenazaAdapter(ArrayList<Amenaza> amenazaArrayList, Context context, AmenazaClickInterface amenazaClickInterface) {
        this.amenazaArrayList = amenazaArrayList;
        this.context = context;
        this.amenazaClickInterface = amenazaClickInterface;
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
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amenazaClickInterface.onAmenazaClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return amenazaArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView amenazaidtxt,descripciontxt;

        private LinearLayout itemLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            amenazaidtxt=itemView.findViewById(R.id.tvId);
            descripciontxt=itemView.findViewById(R.id.tvDescripcion);
            itemLayout = itemView.findViewById(R.id.itemLl);
        }
    }

    public interface AmenazaClickInterface{
        void onAmenazaClick(int position);
    }

}
