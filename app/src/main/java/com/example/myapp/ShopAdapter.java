package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder>{

    ArrayList<Sach> datasach;

    Context context;

    public ShopAdapter(ArrayList<com.example.myapp.Sach> sach, Context context) {
        datasach = sach;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.item_home,parent,false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtten.setText(datasach.get(position).getTensach());
        holder.txtgia.setText(Integer.toString(datasach.get(position).getGia()));
        Picasso.get().load(datasach.get(position).getAnh()).into(holder.imageitem);

    }

    @Override
    public int getItemCount() {
        return datasach.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        ImageView imageitem;
        TextView txtten;
        TextView txtgia;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageitem = (ImageView) itemView.findViewById(R.id.imagesp);
            txtgia = (TextView) itemView.findViewById(R.id.itemgia);
            txtten = (TextView) itemView.findViewById(R.id.itemten);
        }
    }
}
