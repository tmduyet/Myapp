package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminActivity extends AppCompatActivity {




    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    private AddFragment addFragment;
    private UpdateFragment updateFragment;
    private DeleteFragment deleteFragment;
    private AdminnotificationFragment adminnotificationFragment;


    void init()
    {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.admin_botnav);
        frameLayout = (FrameLayout) findViewById(R.id.admin_framelayout);
        addFragment = new AddFragment();
        updateFragment = new UpdateFragment();
        deleteFragment = new DeleteFragment();
        adminnotificationFragment = new AdminnotificationFragment();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        init();

        setFragment(addFragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
                switch (Item.getItemId() )
                {
                    case R.id.admin_navadd :
                        setFragment(addFragment);
                        return true;
                    case R.id.admin_update:
                        setFragment(updateFragment);
                        return true;
                    case R.id.admin_navdelete:
                        setFragment(deleteFragment);
                        return true;
                    case R.id.admin_noti:
                        setFragment(adminnotificationFragment);
                        return  true;

                    default: return false;

                }
            }
        });

    }
    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.admin_framelayout,fragment);
        fragmentTransaction.commit();

    }
}