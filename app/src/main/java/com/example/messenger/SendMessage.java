package com.example.messenger;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.Random;

public class SendMessage extends AppCompatActivity {


    TextView tv, tv1, tv2;
    EditText tv3;
    Button send;
    String pno;
    String name;
    String otps,msg;
    DataFeeder dataFeeder;
    DatabaseReference reff;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        tv = (findViewById(R.id.textView));
        tv1 = (findViewById(R.id.textView2));
        tv2 = (findViewById(R.id.textView3));
        send = (findViewById(R.id.button3));

        tv3 = (EditText)(findViewById(R.id.editTEXT));


        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        Intent i = getIntent();
        String s = i.getStringExtra("name");
        int k = s.indexOf(':');
        name = s.substring(0, k);
        pno = "91"+s.substring(k + 1);



        System.out.println(msg);
        tv.setText("Name : " + name + "\n");
        tv1.setText("Phone No : +" + pno);
        otp();

    }


    public void sendmsg(View v) {
       try {

            msg=tv3.getText().toString();
            String apiKey = "apikey=" + "qx/Re3bWpKg-xAuscEIfiFiG7YACBA7T2PfBG1atqX";
            String message = "&message="  + msg+"  "+otps;
            String sender = "&sender=" + "TXTLCL";
            String numbers = "&numbers=" + pno;

            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();
            ArrayList <String> al=new ArrayList<>();
            al.add(otps);
            Intent i=new Intent(getApplicationContext(),Record.class);
            i.putExtra("otp",al.get(al.size()-1));

            Toast.makeText(this, stringBuffer.toString(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            System.out.println("Error SMS " + e);
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

        dataFeeder = new DataFeeder();
        reff= FirebaseDatabase.getInstance().getReference();
        dataFeeder.setName(name);
        dataFeeder.setOtp(otps);
        System.out.println(reff.getRoot());
        reff.push().setValue(dataFeeder);


        Toast.makeText(this, "Message & OTP Sent Succesfully", Toast.LENGTH_SHORT).show();







    }



    public void otp(){
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        otps =String.format("%06d", number);
        tv2.setText("OTP : "+otps);

        otps="Your OTP is "+otps;

    }
}

