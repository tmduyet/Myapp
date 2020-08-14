package com.example.myapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ForgetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        CardView cardViewConfirm = (CardView)findViewById(R.id.cardViewConfirm);
        cardViewConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(ForgetPassword.this).create();
                alertDialog.setTitle("Announcement");
                alertDialog.setMessage("New Password Had Been Send To Your Email!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ForgetPassword.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        });
                alertDialog.show();
            }
        });
    }
}