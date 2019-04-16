package iti.jets.mad.tripplanner.screens.homescreen.historyfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import iti.jets.mad.tripplanner.R;
import iti.jets.mad.tripplanner.screens.homescreen.historyfragment.pastfragment.PastFragment;
import iti.jets.mad.tripplanner.screens.homescreen.historyfragment.upcomingfragment.UpcomingFragment;
import iti.jets.mad.tripplanner.screens.homescreen.homefragment.HomeFragment;

public class HistoryFragment extends Fragment {
    Button upcominBtn,pastBtn;
    Fragment selectedFragment=null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_history,container,false);
        upcominBtn=view.findViewById(R.id.upcomingBtnID);
        pastBtn=view.findViewById(R.id.PastBtnID);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container2,
                    new UpcomingFragment()).commit();
        }

        upcominBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment=new UpcomingFragment();
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container2, selectedFragment);
                fragmentTransaction.commit();
            }
        });
        pastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment=new PastFragment();
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container2, selectedFragment);
                fragmentTransaction.commit();
            }
        });


        return view;
    }


}
