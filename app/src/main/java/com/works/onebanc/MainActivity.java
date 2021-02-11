package com.works.onebanc;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        BottomNavigationView navView = findViewById(R.id.bottom_nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(nav_view);

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,new CuisineFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener nav_view =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

//                    Fragment selectedFragment = null;
                    Fragment selectedFragment = new CuisineFragment();

                    switch (item.getItemId()) {
                        case R.id.cuisine:
                            selectedFragment = new CuisineFragment();
                            break;

                        case R.id.dishes:
                            selectedFragment = new DishesFragment();
                            break;

                        case R.id.cart:
                            startActivity(new Intent(MainActivity.this,CartActivity.class));
                            break;

                        case R.id.languge:
                            selectedFragment = new LanguageFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,selectedFragment).commit();
                    return true;
                }
            };


    public interface DataTransferInterface{
        public void onSetValues(Integer price);
    }

}