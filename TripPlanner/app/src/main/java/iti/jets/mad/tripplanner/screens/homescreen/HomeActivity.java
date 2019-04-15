package iti.jets.mad.tripplanner.screens.homescreen;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
