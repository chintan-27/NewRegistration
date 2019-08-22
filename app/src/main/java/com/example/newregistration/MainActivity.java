package com.example.newregistration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
/*import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.nio.Buffer;*/


public class MainActivity extends AppCompatActivity {
    EditText et1,et2,et3,et4;
    String a,b,c,d,e;
    Button btn1,btn2,btn3;
    RadioGroup rg;
    SharedPreferences myPrefs;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDb = new DatabaseHelper(this);

        btn1 = findViewById(R.id.submit_area);
        btn2 = findViewById(R.id.last_Entry);
        btn3 = findViewById(R.id.file_saver);
        et1 = findViewById(R.id.input_name);
        et2 = findViewById(R.id.input_id);
        et3 = findViewById(R.id.input_age);
        et4 = findViewById(R.id.input_branch);
        rg = findViewById(R.id.radioSex);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(MainActivity.this,SubmitActivity.class);

                startActivity(i);
                a = et1.getText().toString();
                b = et2.getText().toString();
                c = et3.getText().toString();
                d = et4.getText().toString();

                i.putExtra("Name",a);
                i.putExtra("Id",b);
                i.putExtra("Age",c);
                i.putExtra("Branch",d);

                int selectedId = rg.getCheckedRadioButtonId();
                RadioButton rb = findViewById(selectedId);
                try {
                    e = rb.getText().toString();
                    i.putExtra("Gender", e);
                }
                catch(NullPointerException n){
                    Toast.makeText(getApplicationContext(),"Select Gender", Toast.LENGTH_SHORT).show();
                }

                myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.putString("nameKey", a);
                editor.putString("idKey", b);
                editor.putString("ageKey", c);
                editor.putString("branchKey", d);
                editor.putString("genderKey", e);
                editor.apply();

               //  I tried using file handling for all entries.
               /* try {
                    a = "Name: " + a + "\n";
                    b = "Id: " + b + "\n";
                    c = "Age: " + c + "\n";
                    d = "Branch: " + d + "\n";
                    e = "Gender: " + e + "\n";
                    FileOutputStream fileOutputStream = openFileOutput("myText.txt",MODE_PRIVATE);
                    fileOutputStream.write(a.getBytes());
                    fileOutputStream.write(b.getBytes());
                    fileOutputStream.write(c.getBytes());
                    fileOutputStream.write(d.getBytes());
                    fileOutputStream.write(e.getBytes());
                    OutputStreamWriter file_writer = new OutputStreamWriter(fileOutputStream);
                    BufferedWriter buffered_writer = new BufferedWriter(file_writer);
                    buffered_writer.write(a+b+c+d+e);
                    buffered_writer.close();
                    fileOutputStream.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                */

                boolean isInserted = myDb.insertData(a,b,c,d,e);
                if(isInserted)
                Toast.makeText(getApplicationContext(),"Entry Saved", Toast.LENGTH_SHORT).show();
                startActivity(i);
                finish();

            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);

                a = myPrefs.getString("nameKey","Not Specified");
                b = myPrefs.getString("idKey","Not Specified");
                c = myPrefs.getString("ageKey","Not Specified");
                d = myPrefs.getString("branchKey","Not Specified");
                e = myPrefs.getString("genderKey","Not Specified");


                Intent i = new Intent(MainActivity.this,SubmitActivity.class);

                i.putExtra("Name",a);
                i.putExtra("Id",b);
                i.putExtra("Age",c);
                i.putExtra("Branch",d);
                i.putExtra("Gender",e);
                startActivity(i);
                finish();

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent i = new Intent(MainActivity.this, File.class);
            startActivity(i);
            }
        });

    }

}


