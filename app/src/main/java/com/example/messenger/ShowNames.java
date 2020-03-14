package com.example.messenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class ShowNames extends AppCompatActivity {
    String first[]={"Divyanshu","Kisaan","B","Enosh"," A","C","D","E","f","G"};
    String last[]={"gaur","Network","arya","kmr","ww","ww","ww","ww","ww","ww"};
    String name[]=new String[first.length];
    String phoneno[]={"8267883385","9810153260","","","","","","","",""};
    String fname="";

    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_names);
        mainListView = (ListView) findViewById( R.id.mainListView);


        adddata();
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent=new Intent(getApplicationContext(),SendMessage.class);
                intent.putExtra("name",name[position]+":"+phoneno[position]);
                fname=name[position];
                startActivity(intent);

                Toast.makeText(getApplicationContext(), name[position], Toast.LENGTH_SHORT).show();



            }
        });








    }

    public void adddata() {


        for (int i = 0; i < first.length; i++) {

            name[i] = first[i] + " " + last[i];

        }


        ArrayList<String> planetList = new ArrayList<String>();
        planetList.addAll(Arrays.asList(name));


        listAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, planetList);

        mainListView.setAdapter(listAdapter);


    }
}
