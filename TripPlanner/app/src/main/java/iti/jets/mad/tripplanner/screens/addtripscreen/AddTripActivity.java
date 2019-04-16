package iti.jets.mad.tripplanner.screens.addtripscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import iti.jets.mad.tripplanner.R;
import iti.jets.mad.tripplanner.model.Note;
import iti.jets.mad.tripplanner.model.Trip;

public class AddTripActivity extends AppCompatActivity implements AddTripContract.IView {

    // The Entry point of the database
    private FirebaseDatabase mFirebaseDatabase;
    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]

    String currentUserUID;
    String currentUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        // Creating a database object
        mFirebaseDatabase= FirebaseDatabase.getInstance( );
        // [START initialize_database_ref]
        Log.e("currentUserUID",currentUserUID);
        mDatabase = mFirebaseDatabase.getReference().child("Trips").child(currentUserUID);
        // [END initialize_database_ref]


    }

    private void addNewTrip(String tripName, String startPoint, String endPoint, Date tripDate , Note tripNote)
    {
        Trip trip=new Trip(tripName,startPoint,endPoint,tripDate,tripNote);
        mDatabase.child(mDatabase.push().getKey()).setValue(trip);
    }

}
