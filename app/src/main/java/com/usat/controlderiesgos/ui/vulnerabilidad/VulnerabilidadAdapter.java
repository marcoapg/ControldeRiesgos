package com.usat.controlderiesgos.ui.vulnerabilidad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usat.controlderiesgos.Model.Vulnerabilidad;
import com.usat.controlderiesgos.R;

import java.util.ArrayList;

public class VulnerabilidadAdapter extends RecyclerView.Adapter<VulnerabilidadAdapter.ViewHolder> {

    private ArrayList<Vulnerabilidad> vulnerabilidadArrayList;
    private Context context;


    private VulnerabilidadClickInterface vulnerabilidadClickInterface;

    public VulnerabilidadAdapter(ArrayList<Vulnerabilidad> vulnerabilidadArrayList, Context context, VulnerabilidadClickInterface vulnerabilidadClickInterface) {
        this.vulnerabilidadArrayList = vulnerabilidadArrayList;
        this.context = context;
        this.vulnerabilidadClickInterface = vulnerabilidadClickInterface;
    }

    @NonNull
    @Override
    public VulnerabilidadAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_only_description,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VulnerabilidadAdapter.ViewHolder holder, int position) {
        Vulnerabilidad vulnerabilidad = vulnerabilidadArrayList.get(position);
        holder.vulnerabilidadidtxt.setText(String.valueOf(vulnerabilidad.getVulnerabilidadid()));
        holder.descripciontxt.setText(vulnerabilidad.getDescripcion());
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vulnerabilidadClickInterface.onVulnerabilidadClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vulnerabilidadArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView vulnerabilidadidtxt,descripciontxt;

        private LinearLayout itemLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vulnerabilidadidtxt=itemView.findViewById(R.id.tvId);
            descripciontxt=itemView.findViewById(R.id.tvDescripcion);
            itemLayout = itemView.findViewById(R.id.itemLl);
        }
    }

    public interface VulnerabilidadClickInterface{
        void onVulnerabilidadClick(int position);
    }



}
