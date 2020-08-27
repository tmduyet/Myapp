package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Donhangadapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Donhang> donhangList;

    public Donhangadapter(Context context, int layout, List<Donhang> donhangList) {
        this.context = context;
        this.layout = layout;
        this.donhangList = donhangList;
    }

    @Override
    public int getCount() {
        return donhangList.size();
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

        TextView ten = view.findViewById(R.id.donhangten);
        TextView gia =  view.findViewById(R.id.donhanggia);
        TextView ngaydat =  view.findViewById(R.id.donhangngaydat);
        TextView soluong =  view.findViewById(R.id.donhangsoluong);

        Donhang donhang = donhangList.get(i);

       ten.setText(donhang.getTensach());
       gia.setText(String.valueOf(donhang.getTongtienct()));
       ngaydat.setText(donhang.getNgaydat());
       soluong.setText(String.valueOf(donhang.getSoluong()));




        return view;
    }
}
