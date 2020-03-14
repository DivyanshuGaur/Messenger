package com.example.messenger;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Record extends AppCompatActivity {
    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;
    ArrayList <String> alist=new ArrayList<>();
    DatabaseReference reff;

    DataFeeder df=null;
    List <DataFeeder> ldf=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        mainListView=(findViewById(R.id.lisv));





    }

    public void showdb(View view){
        reff=FirebaseDatabase.getInstance().getReference();
        listAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, alist);


        reff.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                for(DataSnapshot ds : dataSnapshot.getChildren()) df=ds.getValue(DataFeeder.class);
                ldf.add(df);
                alist.add(df.getName()+"  "+df.getOtp());


                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren())
                    df=ds.getValue(DataFeeder.class);
                alist.add(df.getName()+"  "+df.getOtp());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ArrayList<String> planetList = new ArrayList<String>();
        planetList.addAll(alist);

        append(alist);



    }




    public void append(ArrayList <String> alist){


        System.out.print(alist);
        ArrayList<String> planetList = new ArrayList<String>();
        planetList.addAll(alist);

        listAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, planetList);


        mainListView.setAdapter(listAdapter);

    }




}
