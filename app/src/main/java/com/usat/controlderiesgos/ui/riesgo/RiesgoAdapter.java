package com.usat.controlderiesgos.ui.riesgo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usat.controlderiesgos.Model.Riesgo;
import com.usat.controlderiesgos.Model.RiesgoGET;
import com.usat.controlderiesgos.R;

import java.util.ArrayList;

public class RiesgoAdapter extends RecyclerView.Adapter<RiesgoAdapter.ViewHolder>{

    private ArrayList<RiesgoGET> riesgoArrayList;

    private Context context;

    private RiesgoAdapter.RiesgoClickInterface riesgoClickInterface;

    public RiesgoAdapter(ArrayList<RiesgoGET> riesgoArrayList, Context context, RiesgoAdapter.RiesgoClickInterface riesgoClickInterface) {
        this.riesgoArrayList = riesgoArrayList;
        this.context = context;
        this.riesgoClickInterface = riesgoClickInterface;
    }

    @NonNull
    @Override
    public RiesgoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_description_value,parent,false);
        return new RiesgoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiesgoAdapter.ViewHolder holder, int position) {
        RiesgoGET riesgo = riesgoArrayList.get(position);

        holder.activotxt.setVisibility(View.VISIBLE);
        holder.amenazatxt.setVisibility(View.VISIBLE);


        holder.riesgoidtxt.setText(String.valueOf(riesgo.getRiesgoid()));
        holder.descripciontxt.setText(riesgo.getDescripcion());
        holder.vulnerabilidadtxt.setText("Vulneb.: "+String.valueOf(riesgo.getVulnerabilidad()));
        holder.amenazatxt.setText("Amenaza: "+String.valueOf(riesgo.getAmenaza()));
        holder.activotxt.setText("Activo.: "+String.valueOf(riesgo.getActivo()));

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                riesgoClickInterface.onRiesgoClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return riesgoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView riesgoidtxt,descripciontxt, vulnerabilidadtxt,amenazatxt,activotxt;

        private LinearLayout itemLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            riesgoidtxt=itemView.findViewById(R.id.tvId);
            descripciontxt=itemView.findViewById(R.id.tvDescripcion);
            vulnerabilidadtxt =itemView.findViewById(R.id.tvValor);
            amenazatxt=itemView.findViewById(R.id.tvAmenaza);
            activotxt=itemView.findViewById(R.id.tvActivo);
            itemLayout = itemView.findViewById(R.id.itemLl);
        }
    }

    public interface RiesgoClickInterface{
        void onRiesgoClick(int position);
    }
    
}
