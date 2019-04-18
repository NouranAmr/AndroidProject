package iti.jets.mad.tripplanner.screens.homescreen;

import android.content.Intent;
import android.location.LocationManager;
import android.support.annotation.NonNull;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import iti.jets.mad.tripplanner.R;
import iti.jets.mad.tripplanner.screens.addtripscreen.AddTripActivity;
import iti.jets.mad.tripplanner.screens.homescreen.historyfragment.HistoryFragment;
import iti.jets.mad.tripplanner.screens.homescreen.homefragment.HomeFragment;
import iti.jets.mad.tripplanner.screens.homescreen.profilefragment.ProfileFragment;
import iti.jets.mad.tripplanner.screens.registerscreen.RegisterActivity;

public class HomeActivity extends AppCompatActivity {
    private MenuItem logoutitem;
    private FloatingActionButton floatingActionButton;
    private GoogleSignInClient mGoogleSignInClient;

    public HomeActivity() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        logoutitem=menu.findItem(R.id.LogoutToolBarID);

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

        floatingActionButton=findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, AddTripActivity.class);
                startActivity(intent);
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.LogoutToolBarID: {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();
                mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
                FirebaseAuth.getInstance().signOut();
                // LoginManager.getInstance().logOut();
                mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
                finish();
                startActivity(new Intent(this, RegisterActivity.class).putExtra("flag",true));
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navBottomListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment=null;
                    FragmentManager fragmentManager=null;
                    FragmentTransaction fragmentTransaction=null;
                    switch (menuItem.getItemId()){

                        case R.id.nav_home:
                            selectedFragment=new HomeFragment();
                            fragmentManager=getSupportFragmentManager();
                            fragmentTransaction=fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container,selectedFragment);
                            fragmentTransaction.commit();
                            break;
                        case R.id.nav_profile:
                            selectedFragment=new ProfileFragment();
                            fragmentManager=getSupportFragmentManager();
                            fragmentTransaction=fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container,selectedFragment);
                            fragmentTransaction.commit();
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
