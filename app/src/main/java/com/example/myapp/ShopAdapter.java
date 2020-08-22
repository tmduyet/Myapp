package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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
import java.util.Collection;
import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder>{

    ArrayList<Sach> datasach;
    //private List<Sach> listSach;
    //private List<Sach> listSachFull;
    Context context;


    /*public ShopAdapter(List<Sach> listSach){
        this.listSach = listSach;
        this.listSachFull = new ArrayList<>(listSach);
    }*/


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
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
    /*@Override
    public Filter getFilter() {
        return sachFilter;
    }
    private Filter sachFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Sach>filteredList = new ArrayList<>();
            if(charSequence.toString().isEmpty()){
                filteredList.addAll(listSachFull);
            }
            else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Sach item : listSachFull){
                    if(item.getTensach().toLowerCase().contains(filterPattern));{
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listSach.clear();
            listSach.addAll((Collection<? extends Sach>) filterResults.values);
            notifyDataSetChanged();
        }
    };*/

}
