package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Switch;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView main_nav;
    private FrameLayout mainFrame;

    private  HomeFragment homeFragment;
    private  AccountFragment accountFragment;
    private  CartFragment cartFragment;

    void Init()
    {
        main_nav = (BottomNavigationView) findViewById(R.id.main_nav);
        mainFrame = (FrameLayout) findViewById(R.id.main_framelayout);
        homeFragment = new HomeFragment();
        accountFragment = new AccountFragment();
        cartFragment = new CartFragment();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Init();
        setFragment(homeFragment);
        main_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId())
                {
                    case R.id.nav_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.nav_acc:
                        setFragment(accountFragment);
                        return true;
                    case R.id.nav_cart:
                        setFragment(cartFragment);
                        return true;
                    default:
                        return false;

                }


            }
        });
    }

    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_framelayout,fragment);
        fragmentTransaction.commit();

    }
}