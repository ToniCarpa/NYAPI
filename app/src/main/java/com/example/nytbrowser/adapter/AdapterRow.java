package com.example.nytbrowser.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nytbrowser.R;
import com.example.nytbrowser.model.Result;
import com.example.nytbrowser.view.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRow extends RecyclerView.Adapter<AdapterRow.MyViewHolder> {
    private Context context;
    private List<Result> listResult;

    public AdapterRow(Context context, List<Result> listResult) {
        this.context = context;
        this.listResult = listResult;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_result, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRow.MyViewHolder holder, int position) {
        holder.textTit.setText(listResult.get(position).getDisplay_title());
        holder.textAut.setText(listResult.get(position).getByline());
        holder.textDesc.setText(listResult.get(position).getHeadline());
        holder.textDate.setText(listResult.get(position).getPublication_date());

       Picasso.get().load(listResult.get(position).getMultiString().charAt(1))
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listResult.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textTit;
        TextView textAut;
        TextView textDesc;
        TextView textDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.rowImg);
            textTit = itemView.findViewById(R.id.rowTit);
            textAut = itemView.findViewById(R.id.rowTit);
            textDesc = itemView.findViewById(R.id.rowTit);
            textDate = itemView.findViewById(R.id.rowTit);
        }

    }
}
