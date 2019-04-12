package iti.jets.mad.tripplanner.screens.homescreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import iti.jets.mad.tripplanner.R;

public class HomeActivity extends AppCompatActivity {
    Button btn;

    public HomeActivity() {

    //hello world

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn.setOnClickListener((e)->{

        });

    }
}
