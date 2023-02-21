package com.usat.controlderiesgos.ui.unidadorganizacional;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usat.controlderiesgos.Model.UnidadOrganizacional;
import com.usat.controlderiesgos.R;

import java.util.ArrayList;

public class UnidadOrganizacionalAdapter extends RecyclerView.Adapter<com.usat.controlderiesgos.ui.unidadorganizacional.UnidadOrganizacionalAdapter.ViewHolder> {

    private ArrayList<UnidadOrganizacional> unidadorganizacionalArrayList;
    private Context context;


    private com.usat.controlderiesgos.ui.unidadorganizacional.UnidadOrganizacionalAdapter.UnidadOrganizacionalClickInterface unidadorganizacionalClickInterface;

    public UnidadOrganizacionalAdapter(ArrayList<UnidadOrganizacional> unidadorganizacionalArrayList, Context context, com.usat.controlderiesgos.ui.unidadorganizacional.UnidadOrganizacionalAdapter.UnidadOrganizacionalClickInterface unidadorganizacionalClickInterface) {
        this.unidadorganizacionalArrayList = unidadorganizacionalArrayList;
        this.context = context;
        this.unidadorganizacionalClickInterface = unidadorganizacionalClickInterface;
    }

    @NonNull
    @Override
    public com.usat.controlderiesgos.ui.unidadorganizacional.UnidadOrganizacionalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleview,parent,false);
        return new com.usat.controlderiesgos.ui.unidadorganizacional.UnidadOrganizacionalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.usat.controlderiesgos.ui.unidadorganizacional.UnidadOrganizacionalAdapter.ViewHolder holder, int position) {
        UnidadOrganizacional unidadorganizacional = unidadorganizacionalArrayList.get(position);
        holder.unidadorganizacionalidtxt.setText(String.valueOf(unidadorganizacional.getUnidadorganizacionalid()));
        holder.descripciontxt.setText(unidadorganizacional.getDescripcion());
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unidadorganizacionalClickInterface.onUnidadOrganizacionalClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return unidadorganizacionalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView unidadorganizacionalidtxt,descripciontxt;

        private LinearLayout itemLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            unidadorganizacionalidtxt=itemView.findViewById(R.id.tvId);
            descripciontxt=itemView.findViewById(R.id.tvDescripcion);
            itemLayout = itemView.findViewById(R.id.itemLl);
        }
    }

    public interface UnidadOrganizacionalClickInterface{
        void onUnidadOrganizacionalClick(int position);
    }

}