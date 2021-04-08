package com.example.dashfoods;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

public class ConfirmOrderActivityAdapter extends RecyclerView.Adapter<ConfirmOrderActivityAdapter.ConfirmOrderViewHolder> {

    private final ArrayList<OrderListModel> orderListModels;
    Activity activity;

    public ConfirmOrderActivityAdapter(Activity activity){
        this.activity=activity;
        orderListModels=((UpdateSelectedItem) ApplicationMain.getMyContext()).getItems();
    }

    public static class ConfirmViewHolder extends RecyclerView.ViewHolder{

        TextView name,price;

        public ConfirmViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    @NonNull
    @Override
    public ConfirmOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false);
        return new ConfirmOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfirmOrderViewHolder holder, int position) {

        OrderListModel currentItem=orderListModels.get(position);
        holder.name.setText(currentItem.getName());

    }

    @Override
    public int getItemCount() {
        return orderListModels.size();
    }


    public static class ConfirmOrderViewHolder extends RecyclerView.ViewHolder {
        public BreakIterator name;

        public ConfirmOrderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
