package com.example.r1_navdrawe;

import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.main);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        // Cargar primer fragmento por defecto
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_contenedor, new CrudFragment())
                .commit();

        // Cambiar de fragmento al presionar un botón del menú
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.crud) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_contenedor, new CrudFragment()).commit();
            } else if (id == R.id.tabla1) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_contenedor, new tabla1Fragment()).commit();
            } else if (id == R.id.tabla2) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_contenedor, new tabla2Fragment()).commit();
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }
}