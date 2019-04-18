package iti.jets.mad.tripplanner.screens.splashscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import iti.jets.mad.tripplanner.R;
import iti.jets.mad.tripplanner.screens.homescreen.HomeActivity;
import iti.jets.mad.tripplanner.screens.registerscreen.RegisterActivity;

public class SplashActivity extends AppCompatActivity {

    Button btn;
    private static final String SETTING_INFOS = "User_Info";
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "PASSWORD";    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        btn = findViewById(R.id.registerbtn);
        SharedPreferences settings = getSharedPreferences(SETTING_INFOS, 0);
        if(settings.contains(EMAIL))
        {
            String name = settings.getString(EMAIL, "");
            String Password = settings.getString(PASSWORD, "");
            startActivity(new Intent(this, HomeActivity.class));
        }
        else {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
    }
    }


