package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ProductActivity extends AppCompatActivity {

    ImageView imageView;
    TextView txttensach,txtgia,txtsl, txttacgia, txttheloai, txtmota;
    Button btnthem,btnbot, btndat;

    int sl = 0;
    void init()
    {
        imageView = findViewById(R.id.imgsanpham);
        txttensach = findViewById(R.id.txttensp);
        txtgia = findViewById(R.id.txtgia);
        txtsl = findViewById(R.id.sl);
        txttacgia = findViewById(R.id.txttacgia);
        txttheloai = findViewById(R.id.txttheloai);
        txtmota = findViewById(R.id.txtmota);
        btnthem = findViewById(R.id.btnthem);
        btnbot = findViewById(R.id.btnbot);
        btndat  = findViewById(R.id.btndat);
    }
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        init();
        getIncomingIntent();
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
                if(sl<0)
                {
                    Toast.makeText(ProductActivity.this, "Không thể giảm sản phẩm", Toast.LENGTH_SHORT).show();
                    sl=0;
                }
                else
                {
                    String a = String.valueOf(sl);
                    txtsl.setText(a);
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