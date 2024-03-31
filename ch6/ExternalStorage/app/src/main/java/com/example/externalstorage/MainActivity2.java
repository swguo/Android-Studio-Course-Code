package com.example.externalstorage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {
    TextView showText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        showText = (TextView) findViewById(R.id.getText);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void back(View view) {
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent);
    }

    public void getPublic(View view) {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS); // Folder Name
        File myFile = new File(folder, "myData1.txt"); // Filename
        String text = getdata(myFile);
        if (text != null) {
            showText.setText(text);
        } else {
            showText.setText("No Data");
        }
    }

    public void getPrivate(View view) {
        File folder = getExternalFilesDir("AbhiAndroid"); // Folder Name
        File myFile = new File(folder, "myData2.txt"); // Filename
        String text = getdata(myFile);
        if (text != null) {
            showText.setText(text);
        } else {
            showText.setText("No Data");
        }
    }

    private String getdata(File myfile) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myfile);
            int i = -1;
            StringBuffer buffer = new StringBuffer();
            while ((i = fileInputStream.read()) != -1) {
                buffer.append((char) i);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}