package com.usat.controlderiesgos.ui.criterioprobabilidad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usat.controlderiesgos.Model.CriterioProbabilidad;
import com.usat.controlderiesgos.Model.CriterioProbabilidad;
import com.usat.controlderiesgos.R;

import java.util.ArrayList;

public class CriterioProbabilidadAdapter extends RecyclerView.Adapter<CriterioProbabilidadAdapter.ViewHolder> {

    private ArrayList<CriterioProbabilidad> criterioprobabilidadArrayList;

    private Context context;

    private CriterioProbabilidadAdapter.CriterioProbabilidadClickInterface criterioprobabilidadClickInterface;

    public CriterioProbabilidadAdapter(ArrayList<CriterioProbabilidad> criterioprobabilidadArrayList, Context context, CriterioProbabilidadAdapter.CriterioProbabilidadClickInterface criterioprobabilidadClickInterface) {
        this.criterioprobabilidadArrayList = criterioprobabilidadArrayList;
        this.context = context;
        this.criterioprobabilidadClickInterface = criterioprobabilidadClickInterface;
    }

    @NonNull
    @Override
    public CriterioProbabilidadAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_description_value,parent,false);
        return new CriterioProbabilidadAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CriterioProbabilidadAdapter.ViewHolder holder, int position) {
        CriterioProbabilidad criterioprobabilidad = criterioprobabilidadArrayList.get(position);
        holder.criterioprobabilidadidtxt.setText(String.valueOf(criterioprobabilidad.getCriterioprobabilidadid()));
        holder.descripciontxt.setText(criterioprobabilidad.getDescripcion());
        holder.valortxt.setText("Valor: "+String.valueOf(criterioprobabilidad.getValor()));
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criterioprobabilidadClickInterface.onCriterioProbabilidadClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return criterioprobabilidadArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView criterioprobabilidadidtxt,descripciontxt,valortxt;

        private LinearLayout itemLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            criterioprobabilidadidtxt=itemView.findViewById(R.id.tvId);
            descripciontxt=itemView.findViewById(R.id.tvDescripcion);
            valortxt=itemView.findViewById(R.id.tvValor);
            itemLayout = itemView.findViewById(R.id.itemLl);
        }
    }

    public interface CriterioProbabilidadClickInterface{
        void onCriterioProbabilidadClick(int position);
    }

}
