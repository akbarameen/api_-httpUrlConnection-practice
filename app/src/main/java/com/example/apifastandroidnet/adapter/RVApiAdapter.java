package com.example.apifastandroidnet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apifastandroidnet.R;
import com.example.apifastandroidnet.model.RVApiModel;

import java.util.ArrayList;

public class RVApiAdapter extends RecyclerView.Adapter<RVApiAdapter.ViewHolder> {
    Context context;
    ArrayList<RVApiModel> dataItemList;

    public RVApiAdapter(Context context, ArrayList<RVApiModel> dataItemList) {
        this.context = context;
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvId.setText(dataItemList.get(position).getId());
        holder.tvName.setText(dataItemList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}