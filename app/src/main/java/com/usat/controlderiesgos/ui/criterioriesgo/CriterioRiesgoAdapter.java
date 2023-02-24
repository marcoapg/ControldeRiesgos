package com.usat.controlderiesgos.ui.criterioriesgo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usat.controlderiesgos.Model.CriterioRiesgo;
import com.usat.controlderiesgos.R;

import java.util.ArrayList;

public class CriterioRiesgoAdapter extends RecyclerView.Adapter<CriterioRiesgoAdapter.ViewHolder> {

    private ArrayList<CriterioRiesgo> criterioriesgoArrayList;

    private Context context;

    private CriterioRiesgoAdapter.CriterioRiesgoClickInterface criterioriesgoClickInterface;

    public CriterioRiesgoAdapter(ArrayList<CriterioRiesgo> criterioriesgoArrayList, Context context, CriterioRiesgoAdapter.CriterioRiesgoClickInterface criterioriesgoClickInterface) {
        this.criterioriesgoArrayList = criterioriesgoArrayList;
        this.context = context;
        this.criterioriesgoClickInterface = criterioriesgoClickInterface;
    }

    @NonNull
    @Override
    public CriterioRiesgoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_description_value,parent,false);
        return new CriterioRiesgoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CriterioRiesgoAdapter.ViewHolder holder, int position) {
        CriterioRiesgo criterioriesgo = criterioriesgoArrayList.get(position);

        holder.colorlay.setVisibility(View.VISIBLE);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//        //if you need three fix imageview in width
        int devicewidth = displaymetrics.widthPixels -330;

        //if you need 4-5-6 anything fix imageview in height
        holder.infoLayout.getLayoutParams().width=devicewidth;

        holder.criterioriesgoidtxt.setText(String.valueOf(criterioriesgo.getCriterioriesgoid()));
        holder.descripciontxt.setText(criterioriesgo.getDescripcion());
        holder.valortxt.setText("Valor: "+ criterioriesgo.getValor());
        int color = 0xFFFFFFFF;

        if(criterioriesgo.getColor().startsWith("#")){
            color = Color.parseColor(criterioriesgo.getColor());

            holder.colorlay.setBackgroundColor(color);

        }else{
            holder.colorlay.setBackgroundColor(color);
        }

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criterioriesgoClickInterface.onCriterioRiesgoClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return criterioriesgoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView criterioriesgoidtxt,descripciontxt,valortxt;

        private LinearLayout itemLayout,colorlay,infoLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            criterioriesgoidtxt=itemView.findViewById(R.id.tvId);
            descripciontxt=itemView.findViewById(R.id.tvDescripcion);
            valortxt=itemView.findViewById(R.id.tvValor);
            colorlay=itemView.findViewById(R.id.layoutCardColor);
            itemLayout = itemView.findViewById(R.id.itemLl);
            infoLayout = itemView.findViewById(R.id.infoLl);


        }
    }

    public interface CriterioRiesgoClickInterface{
        void onCriterioRiesgoClick(int position);
    }


}
