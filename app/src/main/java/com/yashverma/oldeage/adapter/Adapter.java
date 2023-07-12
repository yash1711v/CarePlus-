package com.yashverma.oldeage.adapter;

import android.app.AlertDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.base.MoreObjects;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.yashverma.oldeage.FullDetails.Blank2;

import com.yashverma.oldeage.R;
import com.yashverma.oldeage.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context context;
    ArrayList<User> List;
    private CardClick cardClick;
    public Adapter(Context c, ArrayList<User> l,CardClick cardClick) {
        this.context = c;
        this.List = l;
        this.cardClick=cardClick;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.guestcard,parent,false);
        return new MyViewHolder(v,cardClick);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.guestId.setText(List.get(position).getGuestid());
        holder.guestName.setText(List.get(position).getGuestName());
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dgp=DialogPlus.newDialog(holder.guestId.getContext())
                        .setContentHolder(new ViewHolder(R.layout.updatepopup))
                        .setExpanded(true,1500)
                        .create();
                View view2=dgp.getHolderView();
                TextView Gid;
                EditText Gname,Gage,KnownName,KNownNumber,Address,DateofJOining,CakerTakerId;
                Button UpdatebtnPop;
               Gid=view2.findViewById(R.id.Gid);
                Gage=view2.findViewById(R.id.age);
                KnownName=view2.findViewById(R.id.GuestKNowName);
                KNownNumber=view2.findViewById(R.id.GuestKNownNumber);
                Address=view2.findViewById(R.id.Guestaddress);
                DateofJOining=view2.findViewById(R.id.DateofJoining);
                CakerTakerId=view2.findViewById(R.id.Cakertakerid);
                Gname=view2.findViewById(R.id.Name);
                UpdatebtnPop=view2.findViewById(R.id.Updte);
                Gid.setText("Guest Id:-"+holder.guestId.getText());
                Gname.setText(" "+holder.guestName.getText());
                UpdatebtnPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map= new HashMap<>();
                        map.put("guestid",Gid.getText().toString());
                        map.put("guestName",Gname.getText().toString());
                        map.put("guestage",Gage.getText().toString());
                        map.put("guestKnownName",KnownName.getText().toString());
                        map.put("guestKnownNumber",KNownNumber.getText().toString());
                        map.put("guestAddress",Address.getText().toString());
                        map.put("guestDateofJoining",DateofJOining.getText().toString());
                        map.put("cakertakerId",CakerTakerId.getText().toString());
                        FirebaseDatabase.getInstance().getReference("Guest Info").updateChildren(map);

                    }
                });
                dgp.show();

            }

        });
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.guestId.getContext());
                builder.setTitle("Are you sure You want to delete the data ");
                builder.setMessage("Deleted data can't be Undo");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "cancle", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView guestId,guestName;
        Button update, Delete;
        CardClick cardClick;
       // EditText name, age,KnownName,KnownNumber,Address,DatesOfjoining,CaretakerId;
        public MyViewHolder(@NonNull View itemView,CardClick cardClick) {
            super(itemView);
            guestId=(TextView) itemView.findViewById(R.id.fetchedId);
            guestName=(TextView) itemView.findViewById(R.id.fetchedName);
            update=itemView.findViewById(R.id.Update);
            Delete=itemView.findViewById(R.id.Delete);
            this.cardClick=cardClick;
        itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            cardClick.CardClick(getAdapterPosition());
        }
    }
    public interface CardClick{
        void CardClick(int position);
    }
}

