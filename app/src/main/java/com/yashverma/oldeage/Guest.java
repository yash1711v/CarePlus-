package com.yashverma.oldeage;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class Guest extends Fragment {
    EditText eg1,eg2,eg3,eg4,eg5,eg6,eg7,eg8;
    Button btn,medication,hospitalization;
    FirebaseDatabase rootNote2;
    DatabaseReference reference2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView=inflater.inflate(R.layout.fragment_guest, container, false);

        eg1=myView.findViewById(R.id.guest_id);
        eg2=myView.findViewById(R.id.Guest_name);
        eg3=myView.findViewById(R.id.Guest_nage);
        eg4=myView.findViewById(R.id.Guestdateofadmit);
        eg5=myView.findViewById(R.id.Guest_address);
        eg6=myView.findViewById(R.id.Guest_Known_name);
        eg7=myView.findViewById(R.id.Guest_Known_number);
        eg8=myView.findViewById(R.id.cakertaker_id);
        btn=myView.findViewById(R.id.Button3);
//        medication=myView.findViewById(R.id.Medication);
//        hospitalization=myView.findViewById(R.id.HospitalizationButton);
        rootNote2=FirebaseDatabase.getInstance();
        reference2=rootNote2.getReference("Guest Info");
       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String Guest_id=eg1.getText().toString();
               String Guest_name=eg2.getText().toString();
               String Guest_age=eg3.getText().toString();
               String Guest_date_of_Joining=eg4.getText().toString();
               String Guest_Address=eg5.getText().toString();
               String Guest_Known_Name=eg6.getText().toString();
               String Guest_Known_Number=eg7.getText().toString();
               String CareTaker_id=eg8.getText().toString();
               String GuId=eg1.getText().toString();
               hospitalisation Hospitalization= new hospitalisation();
               Bundle b= new Bundle();
               b.putString("Guest_Id",GuId);
               Hospitalization.setArguments(b);
               Medication MedicationID=new Medication();
               b.putString("Guest_Id",GuId);
               MedicationID.setArguments(b);
               FragmentManager fm=getFragmentManager();
               FragmentTransaction ft=fm.beginTransaction();
               ft.replace(R.id.Blank,MedicationID);
               ft.addToBackStack(" ");
               ft.commit();
//                  reference2.push().getKey();
               GuestHelper Ghelper=new GuestHelper(Guest_id,Guest_name.toUpperCase(),Guest_age,Guest_date_of_Joining,Guest_Address,Guest_Known_Name,Guest_Known_Number,CareTaker_id);
               reference2.child(Guest_id).setValue(Ghelper).addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void unused) {
                       Toast.makeText(getActivity(), "Guest Data Saved", Toast.LENGTH_SHORT).show();

                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(getActivity(), "Guest is Not Saved"+e.getMessage(), Toast.LENGTH_SHORT).show();
                   }
               });
//               String GuId=eg1.getText().toString();
//               hospitalisation Hospitalization= new hospitalisation();
//               Bundle b= new Bundle();
//               b.putString("Guest_Id",GuId);
//               Hospitalization.setArguments(b);
//               Medication MedicationID=new Medication();
//               b.putString("Guest_Id",GuId);
//               MedicationID.setArguments(b);
//               FragmentManager fm=getFragmentManager();
//               FragmentTransaction ft=fm.beginTransaction();
//               ft.replace(R.id.Blank,MedicationID);
//               ft.addToBackStack(" ");
//               ft.commit();

           }
       });
//       medication.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               String GuestId=eg1.getText().toString();
//               Medication MedicationID=new Medication();
//               Bundle b=new Bundle();
//               b.putString("Guest_Id",GuestId);
//               MedicationID.setArguments(b);
//               FragmentManager fm=getFragmentManager();
//               FragmentTransaction ft=fm.beginTransaction();
//               ft.replace(R.id.Blank,MedicationID);
//               ft.addToBackStack(" ");
//               ft.commit();
//           }
//       });
//       hospitalization.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               String GuId=eg1.getText().toString();
//               hospitalisation Hospitalization= new hospitalisation();
//               Bundle b= new Bundle();
//               b.putString("Guest_Id",GuId);
//               Hospitalization.setArguments(b);
//               FragmentManager fm=getFragmentManager();
//               FragmentTransaction ft=fm.beginTransaction();
//               ft.replace(R.id.Blank,Hospitalization);
//               ft.addToBackStack(" ");
//               ft.commit();
//
//           }
//       });
       return myView;
    }
}