package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class HomeFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private EditText editTextSearch;
    ArrayList<Sach> arrayList;
    DatabaseReference fdata;
    ShopAdapter shopAdapter ;
    SearchView searchView;
    Button btnSearch;
    //private SliderAdapterHF adapter;
    public HomeFragment() {
        // Required empty public constructor
    }
    void init(View v)
    {
        recyclerView = (RecyclerView) v.findViewById(R.id.recycleView);
        //editTextSearch = (EditText)v.findViewById(R.id.editsearch);
        searchView = (SearchView)v.findViewById(R.id.searchView);
        //btnSearch = (Button)v.findViewById(R.id.btnsearch);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fdata = FirebaseDatabase.getInstance().getReference();
        arrayList = new ArrayList<Sach>();

        fdata.child("Sach").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Sach p = snapshot.getValue(Sach.class);
                arrayList.add(p);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
                shopAdapter = new ShopAdapter(arrayList,getContext());
                recyclerView.setAdapter(shopAdapter);
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
        /*if(fdata != null){
            fdata.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        arrayList = new ArrayList<>();
                        for(DataSnapshot ds : snapshot.getChildren()){
                            arrayList.add(ds.getValue(Sach.class));
                        }
                        ShopAdapter adaptershop = new ShopAdapter(arrayList, getActivity().getApplicationContext());
                        recyclerView.setAdapter(adaptershop);
                        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }*/
        if(searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
        /*editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().isEmpty()){
                    search(editable.toString());
                }
                else{
                    search("");
                }
            }
        });
//        fdata.child("Sach").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot :snapshot.getChildren())
//                {
//
//                    Sach p = dataSnapshot.getValue(Sach.class);
//                    arrayList.add(p);
//                }
//                shopAdapter = new ShopAdapter(arrayList,getContext());
//                recyclerView.setAdapter(shopAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });*/
        return view;
    }
    private void search(String s){
        ArrayList<Sach> listsach = new ArrayList<>();
        for(Sach obj : arrayList){
            if(obj.getTensach().toLowerCase().contains(s.toLowerCase())){
                listsach.add(obj);
            }
        }
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        ShopAdapter adapterShop2 = new ShopAdapter(listsach, getActivity().getApplicationContext());
        recyclerView.setAdapter(adapterShop2);
    }
    /*private void searchfirebase(String s) {
        String quary = s.toLowerCase();
        Query query = fdata.orderByChild("Sach").startAt(s).endAt(s + "\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    arrayList.clear();
                    for(DataSnapshot dss : snapshot.getChildren()){
                        final Sach sach = dss.getValue(Sach.class);
                        arrayList.add(sach);
                    }
                    ShopAdapter adaptershop = new ShopAdapter(arrayList, getActivity().getApplicationContext());
                    recyclerView.setAdapter(adaptershop);
                    adaptershop.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/

    /*@Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.sach_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query){
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText){
                shopAdapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }*/
}