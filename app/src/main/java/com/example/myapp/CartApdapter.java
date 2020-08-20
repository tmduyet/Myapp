package com.example.myapp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CartApdapter extends BaseAdapter {

     private Context context;
     private int layout;
     private List<Cart> cartList;

    public CartApdapter(Context context, int layout, List<Cart> cartLinst) {
        this.context = context;
        this.layout = layout;
        this.cartList = cartLinst;
    }

    @Override
    public int getCount() {
        return cartList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        ImageView imageView = view.findViewById(R.id.cuslisthinh);
        TextView custen = view.findViewById(R.id.cuslistten);
        TextView cusgia = view.findViewById(R.id.cuslistgia);
        Button cusbot = view.findViewById(R.id.cuslistbot);
        Button custhem = view.findViewById(R.id.cuslistthem);
        TextView cussoluong = view.findViewById(R.id.cuslistsoluong);
        Button cusxoa = view.findViewById(R.id.cuslistxoa);


        Cart cart = cartList.get(i);
         custen.setText(cart.getTensach());
         cusgia.setText(String.valueOf(cart.getGia()));
         cussoluong.setText(String.valueOf(cart.getSoluong()));
        Glide.with(context)
                .load(cart.getAnh())
                .into(imageView);
        return view;






    }
}
