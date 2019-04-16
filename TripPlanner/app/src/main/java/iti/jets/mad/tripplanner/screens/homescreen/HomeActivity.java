package iti.jets.mad.tripplanner.screens.homescreen;

import android.support.annotation.NonNull;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import iti.jets.mad.tripplanner.R;
import iti.jets.mad.tripplanner.screens.homescreen.historyfragment.HistoryFragment;
import iti.jets.mad.tripplanner.screens.homescreen.homefragment.HomeFragment;
import iti.jets.mad.tripplanner.screens.homescreen.profilefragment.ProfileFragment;

public class HomeActivity extends AppCompatActivity {

    public HomeActivity() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar=findViewById(R.id.toolbarID);
        setSupportActionBar(toolbar);


        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navBottomListener);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }


    }
    private BottomNavigationView.OnNavigationItemSelectedListener navBottomListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment=null;
                    switch (menuItem.getItemId()){

                        case R.id.nav_home:
                            selectedFragment=new HomeFragment();
                            break;
                        case R.id.nav_profile:
                            selectedFragment=new ProfileFragment();
                            break;
                        case R.id.nav_history:
                            selectedFragment=new HistoryFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

    }
