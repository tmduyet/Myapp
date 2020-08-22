package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    private NavigationView navigationViewHeader;
    private BottomNavigationView main_nav;
    private FrameLayout mainFrame;
    private DrawerLayout nav_header;
    private FirebaseAuth mAuth;
    private  HomeFragment homeFragment;
    private  AccountFragment accountFragment;
    private  NotAccountFragment notAccountFragment;
    private  CartFragment cartFragment;
    private DatabaseReference fdata;

    Toolbar toolbar;

    void Init()
    {
        main_nav = (BottomNavigationView) findViewById(R.id.main_nav);
        mainFrame = (FrameLayout) findViewById(R.id.main_framelayout);
        homeFragment = new HomeFragment();
        accountFragment = new AccountFragment();
        notAccountFragment = new NotAccountFragment();
        cartFragment = new CartFragment();
        //toolbar = findViewById(R.id.toolBar);
        nav_header = findViewById(R.id.drawer_layout);
        navigationViewHeader = findViewById(R.id.navView);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();
        mAuth = FirebaseAuth.getInstance();
        setFragment(homeFragment);
        fdata = FirebaseDatabase.getInstance().getReference().child("Sach");
        main_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId())
                {
                    case R.id.nav_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.nav_acc:
                        if(mAuth.getCurrentUser() != null) {
                            setFragment(accountFragment);
                            return true;
                        }
                        else{
                            setFragment(notAccountFragment);
                            return true;
                        }
                    case R.id.nav_cart:
                        setFragment(cartFragment);
                        return true;
                    default:
                        return false;

                }


            }
        });

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, nav_header, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        nav_header.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_framelayout,fragment);
        fragmentTransaction.commit();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin_botmenu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(nav_header.isDrawerOpen(GravityCompat.START)){
            nav_header.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}