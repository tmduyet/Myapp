package com.example.myapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.ArrayList;


import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFragment() {
        // Required empty public constructor
    }


    private Button btngallery;
    private Button btnsave;
    private Button btnclear;
    private ImageView imageView;
    private EditText edittensach;
    private EditText edittacgia;
    private Spinner sptheloai;
    private EditText editgia;
    private EditText editsoluong;
    private EditText editmota;
    private static final int IMAGE_PICK_CODE = 102;
    private static final int PERMISSION_CODE = 103;
    DatabaseReference fdata;
    FirebaseStorage fstorage;
    ArrayList <String> arrayList;
    ArrayAdapter arrayAdapter = null;

    void init(View view)
    {
        btnsave = (Button) view.findViewById(R.id.btnsave);
        btnclear = (Button) view.findViewById(R.id.btnclear);
        btngallery = (Button) view.findViewById(R.id.btnchonanh);
        imageView = (ImageView) view.findViewById(R.id.imgsach);
        edittensach = (EditText) view.findViewById(R.id.edittensach);
        edittacgia = (EditText) view.findViewById(R.id.edittacgia);
        sptheloai = (Spinner) view.findViewById(R.id.sptheloai);
        editgia = (EditText)view.findViewById(R.id.editgia);
        editsoluong =(EditText) view.findViewById(R.id.editsl);
        editmota = (EditText)view.findViewById(R.id.editmota);

    }

    boolean check()
    {
        if(imageView.getDrawable() == null || edittensach.getText().toString().matches("") || editgia.getText().toString().matches("")
                || editgia.getText().toString().matches("")
                || editsoluong.getText().toString().matches("") || editmota.getText().toString().matches(""))
            return false;
        return true;
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
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

    private void Pickimage()
    {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE)
        {
            imageView.setImageURI(data.getData());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case PERMISSION_CODE:
            {
                if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Pickimage();
                }
                else{
                    Toast.makeText(getContext(), "Yêu cầu bị từ chối", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_add, container, false);
        init(v);
        fdata = FirebaseDatabase.getInstance().getReference();
        fstorage = FirebaseStorage.getInstance();
        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sptheloai.setAdapter(arrayAdapter);
        fdata.child("Theloai").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                arrayList.add(snapshot.getValue().toString());
                arrayAdapter.notifyDataSetChanged();
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
        final StorageReference storageRef = fstorage.getReference();
        final Sach sach = new Sach();
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the data from an ImageView as bytes
                if(!check())
                {
                    Toast.makeText(getContext(), "Bạn đã bỏ trống 1 số thông tin", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    final StorageReference mountainsRef = storageRef.child(edittensach.getText().toString());
                    imageView.setDrawingCacheEnabled(true);
                    imageView.buildDrawingCache();
                    Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] data = baos.toByteArray();

                    final UploadTask uploadTask = mountainsRef.putBytes(data);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(getContext(), "Lỗi upload hình ảnh", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            mountainsRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    sach.setTensach(edittensach.getText().toString());
                                    sach.setTacgia(edittacgia.getText().toString());
                                    sach.setTheloai(sptheloai.getSelectedItem().toString());
                                    sach.setGia(Integer.parseInt(editgia.getText().toString()));
                                    sach.setSoluong(Integer.parseInt(editsoluong.getText().toString()));
                                    sach.setMota(editmota.getText().toString());
                                    sach.setAnh(uri.toString());
                                    fdata.child("Sach").child(edittensach.getText().toString()).child(edittensach.getText().toString()).setValue(sach);
                                    Toast.makeText(getContext(), "Tải thông tin thành công", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    });

                }
            }
        });



        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setImageBitmap(null);
                edittensach.setText("");
                edittacgia .setText("");
                editgia.setText("");
                editsoluong.setText("");
                editmota.setText("");
            }
        });
        btngallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if(ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED)
                    {
                        String[] permisson = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permisson, PERMISSION_CODE);
                    }
                    else
                        {
                             Pickimage();
                        }

                }
                else
                {
                    Pickimage();
                }
            }
        });
        return v;
    }
}