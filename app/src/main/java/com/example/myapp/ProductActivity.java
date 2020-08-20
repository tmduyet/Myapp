package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class ProductActivity extends AppCompatActivity {

    ImageView imageView;
    TextView txttensach,txtgia,txtsl, txttacgia, txttheloai, txtmota;
    Button btnthem,btnbot, btndat;


    DatabaseReference fdata;
    Cart cart;
    FirebaseUser currentFirebaseUser;

    int sl = 1;
    void init()
    {
        imageView = (ImageView) findViewById(R.id.imgsanpham);
        txttensach = (TextView) findViewById(R.id.txttensp);
        txtgia =  (TextView) findViewById(R.id.txtgia);
        txtsl = (TextView) findViewById(R.id.sl);
        txttacgia =(TextView)  findViewById(R.id.txttacgia);
        txttheloai = (TextView) findViewById(R.id.txttheloai);
        txtmota = (TextView) findViewById(R.id.txtmota);
        btnthem = (Button) findViewById(R.id.btnthem);
        btnbot = (Button)findViewById(R.id.btnbot);
        btndat  =(Button) findViewById(R.id.btndat);
    }
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        init();
        getIncomingIntent();

        fdata = FirebaseDatabase.getInstance().getReference();



        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sl +=1;
                String a = String.valueOf(sl);
               txtsl.setText(a);
            }
        });
        btnbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sl -=1;
                if(sl<1)
                {
                    Toast.makeText(ProductActivity.this, "Không thể giảm sản phẩm", Toast.LENGTH_SHORT).show();
                    sl=1;
                }
                else
                {
                    String a = String.valueOf(sl);
                    txtsl.setText(a);
                }

            }
        });
        btndat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (sl>0)
                {
                    cart = new Cart();
                    String Image = getIntent().getStringExtra("image_url");
                    cart.setTensach(txttensach.getText().toString());
                    cart.setAnh(Image);
                    cart.setGia(Integer.parseInt(txtgia.getText().toString()));
                    cart.setSoluong(Integer.parseInt(txtsl.getText().toString()));
                    currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    String a = currentFirebaseUser.getUid();

                    fdata.child("Cart").child(a).child(cart.getTensach()).setValue(cart);
                    Toast.makeText(ProductActivity.this, "Đặt sản phẩm thành công" , Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ProductActivity.this, "Sản Phẩm không được trống", Toast.LENGTH_SHORT).show();
                }
              

            }
        });


    }


    private void getIncomingIntent()
    {
        if(getIntent().hasExtra("image_url")&&getIntent().hasExtra("tensach"))
        {
            String Image = getIntent().getStringExtra("image_url");
            String tensach = getIntent().getStringExtra("tensach");
            String tacgia = getIntent().getStringExtra("tacgia");
            String gia = getIntent().getStringExtra("gia");
            String mota = getIntent().getStringExtra("mota");
            String theloai = getIntent().getStringExtra("theloai");
            setSach(Image, tensach,tacgia,gia,mota,theloai);
        }
    }
    private  void setSach(String simage, String tensachh, String tacgia, String gia, String mota,String theloai)
    {
        txttensach.setText(tensachh);
        Glide.with(this).asBitmap().load(simage).into(imageView);
        txttacgia.setText(tacgia);
        txtgia.setText(gia);
        txtmota.setText(mota);
        txttheloai.setText(theloai);
    }
}