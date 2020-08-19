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

import java.util.List;

public class CartApdapter extends BaseAdapter {

     private Context context;
     private int layout;
     private List<Sach> sachList;

    public CartApdapter(Context context, int layout, List<Sach> sachList) {
        this.context = context;
        this.layout = layout;
        this.sachList = sachList;
    }

    @Override
    public int getCount() {
        return sachList.size();
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
        return null;
    }
}
