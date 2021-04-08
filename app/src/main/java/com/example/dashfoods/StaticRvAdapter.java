package com.example.dashfoods;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StaticRVViewHolder> {

    private final ArrayList<StaticRvMode> items;
    int row_index=-1;

    public StaticRvAdapter(ArrayList<StaticRvMode> items) {
        this.items = items;

    }

    

    @NonNull
    @Override
    public StaticRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item,parent,false);
        return new StaticRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRVViewHolder holder, int position) {
        StaticRvMode currentItem=items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());
        holder.linearLayout.setOnClickListener(v -> {
            row_index=position;
            notifyDataSetChanged();
        });

        if (row_index==position){
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv);
        }
        else {
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticRVViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;
        public StaticRVViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.image1);
            textView=itemView.findViewById(R.id.text0);
            linearLayout=itemView.findViewById(R.id.linearLayout);


        }
    }
}
