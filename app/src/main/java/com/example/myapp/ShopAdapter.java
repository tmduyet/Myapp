package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
        holder.txtgia.setText(Integer.toString(datasach.get(position).getGia())) ;
        Picasso.get().load(datasach.get(position).getAnh()).into(holder.imageitem);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ProductActivity.class);
                intent.putExtra("image_url",datasach.get(position).getAnh());
                intent.putExtra("tensach", datasach.get(position).getTensach());
                intent.putExtra("tacgia", datasach.get(position).getTacgia());
                intent.putExtra("gia", Integer.toString(datasach.get(position).getGia()));
                intent.putExtra("mota",datasach.get(position).getMota());
                intent.putExtra("theloai",datasach.get(position).getTheloai());
                context.startActivity(intent);

            }
        });

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
        CardView parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageitem = (ImageView) itemView.findViewById(R.id.imagesp);
            txtgia = (TextView) itemView.findViewById(R.id.itemgia);
            txtten = (TextView) itemView.findViewById(R.id.itemten);
            parentLayout = itemView.findViewById(R.id.splll);



        }
    }
}
