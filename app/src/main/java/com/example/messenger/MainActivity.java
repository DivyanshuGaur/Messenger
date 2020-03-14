package com.example.messenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(findViewById(R.id.button));
        b2=(findViewById(R.id.button2));

    }



    public void show(View view){
        Intent second=new Intent(getApplicationContext(),ShowNames.class);
        startActivity(second);
    }
    public void show1(View view){

        Intent record=new Intent(getApplicationContext(),Record.class);
        startActivity(record);




    }



}
