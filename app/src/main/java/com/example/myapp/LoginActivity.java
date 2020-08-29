package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText mPass, mEmail;
    TextView forgotTextLink;
    long backPressTime;
    Toast backToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView textRegister;
        mAuth = FirebaseAuth.getInstance();
        mEmail = findViewById(R.id.editTextUserEmail);
        mPass = findViewById(R.id.editTextPassword);
        forgotTextLink = findViewById(R.id.textForgetpass);
        final FirebaseUser currentFirebaseUser = mAuth.getCurrentUser();
        CardView cardViewLogin = (CardView)findViewById(R.id.cardViewLogin);
        mEmail.setText("");
        mPass.setText("");
        cardViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mEmail.getText().toString().trim();
                String pass = mPass.getText().toString().trim();
                if(TextUtils.isEmpty(user)){
                    mEmail.setError("Email is required!");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    mPass.setError("Password is required!");
                    return;
                }
                if(pass.length() < 6){
                    mPass.setError("Password must be >= 6 characters!");
                    return;
                }
                mAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            if (currentFirebaseUser.getEmail().equals("admin@admin.com")) {
                                Toast.makeText(LoginActivity.this, "Logged in successfully!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                                mEmail.setText("");
                                mPass.setText("");
                            } else {
                                Toast.makeText(LoginActivity.this, "Logged in successfully!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                mEmail.setText("");
                                mPass.setText("");
                            }
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Can't Login!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            mEmail.setText("");
                            mPass.setText("");
                        }
                    }
                });
            }
        });

        textRegister = (TextView)findViewById(R.id.textRegister);
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgotTextLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // extract the email and send reset link
                        String mail = resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(LoginActivity.this, "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                    }
                });

                passwordResetDialog.create().show();

            }
        });
    }

    @Override
    public void onBackPressed() {
            if(backPressTime + 2000 > System.currentTimeMillis()){
                //Intent intent = new Intent(this, LoginActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //startActivity(intent);
                finishAffinity();
            }
            else{
                backToast = Toast.makeText(getBaseContext(), "Press Back Again To Exit", Toast.LENGTH_SHORT);
                backToast.show();
            }
            backPressTime = System.currentTimeMillis();
    }
}