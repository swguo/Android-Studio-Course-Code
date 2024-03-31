package com.example.internelstorage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {
    TextView getname, getpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        getname = (TextView)findViewById(R.id.getname);
        getpass = (TextView)findViewById(R.id.getpass);

    }
    public void  load(View view)
    {
        try {
            FileInputStream fileInputStream =  openFileInput("Code.txt");
            int read = -1;
            StringBuffer buffer = new StringBuffer();
            while((read =fileInputStream.read())!= -1){
                buffer.append((char)read);
            }
            Log.d("Code", buffer.toString());
            String name = buffer.substring(0,buffer.indexOf(" "));
            String pass = buffer.substring(buffer.indexOf(" ")+1);
            getname.setText(name);
            getpass.setText(pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this,"Loaded", Toast.LENGTH_SHORT).show();
    }

    public void  back( View view)
    {
        Toast.makeText(this,"Back", Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}