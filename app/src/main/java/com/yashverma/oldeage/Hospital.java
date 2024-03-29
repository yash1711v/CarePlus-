package com.yashverma.oldeage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yashverma.oldeage.FullDetails.Blank2;
import com.yashverma.oldeage.FullDetails.GuestDetails;

public class Hospital extends Fragment {
EditText Hospital_Id,Hospital_Name,Hospital_mobile,Hospital_Email,Hospital_address;
Button add;
FirebaseDatabase rootnote6;
DatabaseReference reference6;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView=inflater.inflate(R.layout.fragment_hospital, container, false);
        Bundle b=getArguments();
        String HospitId=b.getString("Hp_Id");
        Hospital_Id=myView.findViewById(R.id.Hospital_Id);
        Hospital_Id.setText(HospitId);
        Hospital_Name=myView.findViewById(R.id.Hospital_Name);
        Hospital_mobile=myView.findViewById(R.id.Hospital_number);
        Hospital_Email=myView.findViewById(R.id.Hospital_Email);
        Hospital_address=myView.findViewById(R.id.Hospital_Address);
        add=myView.findViewById(R.id.buttonhOSPITAL);
        rootnote6=FirebaseDatabase.getInstance();
        reference6=rootnote6.getReference("Hospital_Details");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Hospital_id=Hospital_Id.getText().toString();
                String Hospital_name=Hospital_Name.getText().toString();
                String Hospital_Mbile=Hospital_mobile.getText().toString();
                String Hospital_email=Hospital_Email.getText().toString();
                String Hospital_Address=Hospital_address.getText().toString();
                HospitalHelper hospitalhelper=new HospitalHelper(Hospital_id,Hospital_name,Hospital_Mbile,Hospital_email,Hospital_Address);
                reference6.child(Hospital_id).setValue(hospitalhelper);
                GuestDetails GuestDetails= new GuestDetails();
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.Blank2, GuestDetails);
                ft.commit();
            }
        });
        return myView;
    }
}