package iti.jets.mad.tripplanner.screens.splashscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import iti.jets.mad.tripplanner.R;
import iti.jets.mad.tripplanner.screens.registerscreen.RegisterActivity;

public class SplashActivity extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        btn = findViewById(R.id.registerbtn);
        btn.setOnClickListener((e)->{

            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
