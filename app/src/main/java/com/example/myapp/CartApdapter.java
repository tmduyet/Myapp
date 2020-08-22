package com.example.myapp;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class CartApdapter extends BaseAdapter {

     private Context context;
     private int layout;
     private List<Cart> cartList;


     DatabaseReference fdata;
     FirebaseUser currentuser;
     int sl = 0;
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

                    fdata = FirebaseDatabase.getInstance().getReference();
                    currentuser = FirebaseAuth.getInstance().getCurrentUser();
                    ImageView imageView = view.findViewById(R.id.cuslisthinh);
                    TextView custen = view.findViewById(R.id.cuslistten);
                    TextView cusgia = view.findViewById(R.id.cuslistgia);
                    Button cusbot = view.findViewById(R.id.cuslistbot);
                    Button custhem = view.findViewById(R.id.cuslistthem);
                    TextView  cussoluong = view.findViewById(R.id.cuslistsoluong);
                    Button cusxoa = view.findViewById(R.id.cuslistxoa);

                        Cart cart = cartList.get(i);
                        custen.setText(cart.getTensach());
                        cusgia.setText(String.valueOf(cart.getGia()));
                        cussoluong.setText(String.valueOf(cart.getSoluong()));
                        Glide.with(context)
                                .load(cart.getAnh())
                                .into(imageView);
                        
                        cusbot.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(cart.getSoluong() == 1)
                                {
                                    Toast.makeText(context, "Vui long chọn xóa", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    sl = cart.getSoluong() ;
                                    sl -=1;
                                    cart.setSoluong(sl);
                                    cussoluong.setText(String.valueOf(sl));
                                    fdata.child("Cart").child(currentuser.getUid()).child(cart.getTensach()).setValue(cart);
                                }
                            }
                        });
        custhem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    sl = cart.getSoluong() ;
                    sl +=1;
                    cart.setSoluong(sl);
                    cussoluong.setText(String.valueOf(sl));
                    fdata.child("Cart").child(currentuser.getUid()).child(cart.getTensach()).setValue(cart);

            }
        });

        cusxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fdata.child("Cart").child(currentuser.getUid()).child(cart.getTensach()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot datanapshot: snapshot.getChildren())
                        {
                            Cart cart = snapshot.getValue(Cart.class);
                            if(cart.getTensach().equals(custen.getText().toString()))
                            {
                                fdata.child("Cart").child(currentuser.getUid()).child(snapshot.getKey()).removeValue();
                            }
                        }
                        Toast.makeText(context, "Xóa Thành công !", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(context, "Error !!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
                        
        return view;

    }
}
