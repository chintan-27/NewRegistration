package com.example.newregistration;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
 */
public class File extends AppCompatActivity {

    Button bt;
    TextView tv;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        tv = findViewById(R.id.fileView);
        bt = findViewById(R.id.back);
        myDb = new DatabaseHelper(this);

       /* try{
                FileInputStream fileInputStream = openFileInput("myText.txt");
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer stringBuffer = new StringBuffer();
                String lines;
                while ((lines = bufferedReader.readLine()) != null) {
                    stringBuffer.append(lines + "\n");
                }
                tv.setText(stringBuffer.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        */
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {

            Toast.makeText(getApplicationContext(),"No Entries found", Toast.LENGTH_SHORT).show();
            return;
        }

        while (res.moveToNext()) {
            tv.append("Name :"+ res.getString(0)+"\n");
            tv.append("ID :"+ res.getString(1)+"\n");
            tv.append("Age :"+ res.getString(2)+"\n");
            tv.append("Branch :"+ res.getString(3)+"\n");
            tv.append("Gender : " + res.getString(4) + "\n\n");
        }


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(File.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
