package com.usat.controlderiesgos.ui.criterioimpacto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usat.controlderiesgos.Model.CriterioImpacto;
import com.usat.controlderiesgos.R;

import java.util.ArrayList;

public class CriterioImpactoAdapter extends RecyclerView.Adapter<CriterioImpactoAdapter.ViewHolder> {

    private ArrayList<CriterioImpacto> criterioimpactoArrayList;

    private Context context;

    private CriterioImpactoAdapter.CriterioImpactoClickInterface criterioimpactoClickInterface;

    public CriterioImpactoAdapter(ArrayList<CriterioImpacto> criterioimpactoArrayList, Context context, CriterioImpactoAdapter.CriterioImpactoClickInterface criterioimpactoClickInterface) {
        this.criterioimpactoArrayList = criterioimpactoArrayList;
        this.context = context;
        this.criterioimpactoClickInterface = criterioimpactoClickInterface;
    }

    @NonNull
    @Override
    public CriterioImpactoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_description_value,parent,false);
        return new CriterioImpactoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CriterioImpactoAdapter.ViewHolder holder, int position) {
        CriterioImpacto criterioimpacto = criterioimpactoArrayList.get(position);
        holder.criterioimpactoidtxt.setText(String.valueOf(criterioimpacto.getCriterioimpactoid()));
        holder.descripciontxt.setText(criterioimpacto.getDescripcion());
        holder.valortxt.setText("Valor: "+String.valueOf(criterioimpacto.getValor()));
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criterioimpactoClickInterface.onCriterioImpactoClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return criterioimpactoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView criterioimpactoidtxt,descripciontxt,valortxt;

        private LinearLayout itemLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            criterioimpactoidtxt=itemView.findViewById(R.id.tvId);
            descripciontxt=itemView.findViewById(R.id.tvDescripcion);
            valortxt=itemView.findViewById(R.id.tvValor);
            itemLayout = itemView.findViewById(R.id.itemLl);
        }
    }

    public interface CriterioImpactoClickInterface{
        void onCriterioImpactoClick(int position);
    }


}
