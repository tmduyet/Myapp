package com.example.myapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */




    ListView listView;
    ArrayList<Cart> cartArrayList, cartArrayList2;
    CartApdapter cartApdapter;
    DatabaseReference fdata;
    FirebaseUser currentUser;
    Button btntongtien;

    TextView txttongtien, textTimer;

    public int tongtien;
    void init(View v)
    {
        listView = (ListView) v.findViewById(R.id.liscart);
        btntongtien = (Button) v.findViewById(R.id.btntinhtien);
        txttongtien = (TextView) v.findViewById(R.id.txttongtien);
        //textTimer = (TextView) v.findViewById(R.id.textTimer);
    }
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_cart, container, false);
        init(v);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        fdata = FirebaseDatabase.getInstance().getReference();
        cartArrayList = new ArrayList<>();

        fdata.child("Cart").child(currentUser.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Cart cart = snapshot.getValue(Cart.class);
                cartApdapter  = new CartApdapter(getContext(),R.layout.cartlist_custom,cartArrayList);
                cartArrayList.add(cart);
                tongtien += (cart.getSoluong()*cart.getGia());
                txttongtien.setText(String.valueOf(tongtien));
                listView.setAdapter(cartApdapter);
                cartApdapter.notifyDataSetChanged();
                listView.invalidateViews();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        btntongtien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fdata.child("Donhang").child(currentUser.getUid()).setValue(tongtien);
            }
        });
        new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
                //textTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                cartArrayList.clear();
                //cartArrayList2 = new ArrayList<>();
                fdata.child("Cart").child(currentUser.getUid()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Cart cart = snapshot.getValue(Cart.class);
                        cartApdapter  = new CartApdapter(getContext(),R.layout.cartlist_custom, cartArrayList);
                        cartArrayList.add(cart);
                        tongtien += (cart.getSoluong()*cart.getGia());
                        txttongtien.setText(String.valueOf(tongtien));
                        listView.setAdapter(cartApdapter);
                        cartApdapter.notifyDataSetChanged();
                        listView.invalidateViews();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                start();
            }
        }.start();

        return  v;
    }
}