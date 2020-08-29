package com.example.myapp;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DonhangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DonhangFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DonhangFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DonhangFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DonhangFragment newInstance(String param1, String param2) {
        DonhangFragment fragment = new DonhangFragment();
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

    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter <String> arrayAdapter;
    DatabaseReference fdata;
    FirebaseUser currentUser;

    Donhangadapter donhangadapter;
    ArrayList<Donhang> donhangArrayList;
    void init(View v)
    {
        listView = v.findViewById(R.id.listdonhang);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_donhang, container, false);

        init(v);
        arrayList = new ArrayList<>();
        fdata = FirebaseDatabase.getInstance().getReference();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        fdata.child("Donhang").child(currentUser.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getValue() != null)
                {
                    arrayList.add(snapshot.getKey().toString());
                    arrayAdapter.notifyDataSetChanged();

                }
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


//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Dialog d = new Dialog(getActivity());
//                d.setContentView(R.layout.dialog);
//
//               Button btnxoa = (Button)d.findViewById(R.id.button);
//               btnxoa.setOnClickListener(new View.OnClickListener() {
//                   @Override
//                   public void onClick(View view) {
//                       String selectedFromList =(listView.getItemAtPosition(i).toString());
//                       Toast.makeText(getContext(), selectedFromList, Toast.LENGTH_SHORT).show();
//                       fdata.child("Donhang").child(currentUser.getUid()).child(selectedFromList).removeValue();
//                       arrayAdapter.notifyDataSetChanged();
//                   }
//               });
//
//                d.show();
//                return true;
//            }
//        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String selectedFromList =(listView.getItemAtPosition(i).toString());
                Dialog d = new Dialog(getActivity());
                d.setContentView(R.layout.dialog);

                ListView listchitiet = (ListView)d.findViewById(R.id.listchitiet);
               donhangArrayList = new ArrayList<>();
               donhangadapter = new Donhangadapter(getContext(),R.layout.laychuanodaivcl,donhangArrayList);
                listchitiet.setAdapter(donhangadapter);
                fdata.child("Donhang").child(currentUser.getUid()).child(selectedFromList).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Donhang donhang = snapshot.getValue(Donhang.class);
                        donhangArrayList.add(donhang);
                        donhangadapter.notifyDataSetChanged();
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
                Button btnxoa = (Button)d.findViewById(R.id.button6);
                btnxoa.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {

                       Toast.makeText(getContext(),"Huy thanh cong !", Toast.LENGTH_SHORT).show();
                       fdata.child("Donhang").child(currentUser.getUid()).child(selectedFromList).removeValue();
                       arrayAdapter.notifyDataSetChanged();
                   }
               });


                d.show();
            }
        });
        return v;
    }
}