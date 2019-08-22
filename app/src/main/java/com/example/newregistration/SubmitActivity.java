package com.example.newregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubmitActivity extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4,tv5;
    String a,b,c,d,e;
    Button bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        tv1 = findViewById(R.id.name);
        tv2 = findViewById(R.id.id);
        tv3 = findViewById(R.id.age);
        tv4 = findViewById(R.id.branch);
        tv5 = findViewById(R.id.gender);
        bt = findViewById(R.id.back);



        a = "Name: " +  getIntent().getExtras().getString("Name");
        tv1.setText(a);


        b = "Id: " + getIntent().getExtras().getString("Id");
        tv2.setText(b);


        c = "Age: " + getIntent().getExtras().getString("Age");
        tv3.setText(c);


        d = "Branch: " + getIntent().getExtras().getString("Branch");
        tv4.setText(d);

        e = "Gender: " + getIntent().getExtras().getString("Gender");
        tv5.setText(e);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SubmitActivity.this,MainActivity.class);
                startActivity(i);
            }
        });


    }
}
