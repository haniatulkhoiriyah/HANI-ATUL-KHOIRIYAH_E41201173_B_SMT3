package com.example.fragment_bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

// menjalankan program dengan bahasa java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // membuat simpanan instan konten pada layout activitymain.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // memanggil toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // memanggil gambar yang sudah dibuat dengan nama gambar_layout
        drawer = findViewById(R.id.gambar_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // menggunakan toggle untuk membuka navigation dan menutup navigation
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // mmengatur item item yang akan dipilih dan yang sudah dibuat pada xml
        switch (item.getItemId()) {
            case R.id.nav_writemessage:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contain,
                        new Fragmentmessage()).commit();
                break;
            case R.id.nav_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contain,
                        new Fragmentchat()).commit();
                break;
            case R.id.nav_game:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contain,
                        new Fragmentgame()).commit();
                break;
            case R.id.nav_send:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;

        }
        return false;
    }
        @Override
        public void onBackPressed () {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }
