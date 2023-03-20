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
import com.example.nytbrowser.model.Link;
import com.example.nytbrowser.model.Multimedia;
import com.example.nytbrowser.model.Result;
import com.example.nytbrowser.view.EndActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterDetail extends RecyclerView.Adapter<AdapterDetail.MyDetailViewHolder> {
    private Context context;
    private List<Result> resultList;
    private List<Multimedia> multimediaList;
    private List<Link> linkList;

    public AdapterDetail(Context context, List<Result> resultList) {
        this.context = context;
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public AdapterDetail.MyDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.activity_end, parent, false);
        return new MyDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDetail.MyDetailViewHolder holder, int position) {
        holder.textTit.setText(resultList.get(position).getDisplay_title());
        holder.textBy.setText(resultList.get(position).getByline());
        holder.textHead.setText(resultList.get(position).getHeadline());
        holder.textDate.setText(resultList.get(position).getPublication_date());
        holder.textSum.setText(resultList.get(position).getSummary_short());
        String img = String.valueOf(resultList.get(position).getMultiString());
        Picasso.get().load(img)
                .fit()
                .centerCrop()
                .into(holder.imageView);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EndActivity.class);

                intent.putExtra("display_title", resultList.get(holder.getAdapterPosition()).getDisplay_title());
                intent.putExtra("byline", resultList.get(holder.getAdapterPosition()).getByline());
                intent.putExtra("headline", resultList.get(holder.getAdapterPosition()).getHeadline());
                intent.putExtra("summary_short", resultList.get(holder.getAdapterPosition()).getSummary_short());
                intent.putExtra("publication_date", resultList.get(holder.getAdapterPosition()).getPublication_date());
                intent.putExtra("multimedia", resultList.get(holder.getAdapterPosition()).getMultiString().charAt(1));

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class MyDetailViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textTit;
        TextView textBy;
        TextView textHead;
        TextView textDate;
        TextView textSum;
        ConstraintLayout constraintLayout;

        public MyDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.endImage);
            textTit = itemView.findViewById(R.id.detailTextTit);
            textBy = itemView.findViewById(R.id.detailTextBy);
            textHead = itemView.findViewById(R.id.detailTextHead);
            textDate = itemView.findViewById(R.id.detailTextDate);
            textSum = itemView.findViewById(R.id.detailTextSum);
            constraintLayout = itemView.findViewById(R.id.endlayout);
        }
    }
}
