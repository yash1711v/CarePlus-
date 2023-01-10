package com.careplus.medtracker;

// #################################################################################################
// This is the fragment where cards of Guests are shown in RecyclerView
// #################################################################################################

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.careplus.medtracker.adapter.GuestCardAdapter;
import com.careplus.medtracker.model.Guest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GuestsFragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton fab;

    DatabaseReference databaseReference;
    GuestCardAdapter adapter;
    ArrayList<Guest> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_guests, container, false);
        recyclerView = myView.findViewById(R.id.recyclerView);
        fab = myView.findViewById(R.id.fab);

        databaseReference = FirebaseDatabase.getInstance().getReference("Guest/Guests");
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();

        adapter = new GuestCardAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);

        // Pulling value from Firebase in alphabetically order of guestName
        Query query = databaseReference.orderByChild("guestName");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Guest guest = dataSnapshot.getValue(Guest.class);
                    list.add(guest);    // Adding all the Guest objects (that are received from the Firebase) to the list
                }
                adapter.notifyDataSetChanged();     // Notifying adapter (guestCardAdpater) that the dataset has been updated
            }

            // Displaying the error msg in the Toast if fetching data from Firebase is unsuccessful
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // On clicking "+" button, going to AddGuestActivity
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddGuestActivity.class));
            }
        });
        return myView;
    }
}