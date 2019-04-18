package iti.jets.mad.tripplanner.screens.homescreen.historyfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import iti.jets.mad.tripplanner.R;
import iti.jets.mad.tripplanner.screens.homescreen.historyfragment.pastfragment.PastFragment;
import iti.jets.mad.tripplanner.screens.homescreen.historyfragment.upcomingfragment.RecyclerViewAdapter;
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

        View view=inflater.inflate(R.layout.fragment_home,container,false);

        RecyclerView recyclerView= view.findViewById(R.id.recycleView);
        RecyclerViewAdapter recyclerViewAdapter= new RecyclerViewAdapter(getContext());
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }


}
