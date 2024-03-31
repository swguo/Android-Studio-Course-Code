package com.example.externalstorage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    private int STORAGE_PERMISSION_CODE = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText2);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void next(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }

    public void savePublic(View view) {
        //Permission to access external storage
        //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION}, STORAGE_PERMISSION_CODE);
        String info = editText.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS);// Folder Name
        File myFile = new File(folder, "myData1.txt");// Filename
        writeData(myFile, info);
        editText.setText("");
    }

    public void savePrivate(View view) {
        String info = editText.getText().toString();
        File folder = getExternalFilesDir("AbhiAndroid");// Folder Name
        File myFile = new File(folder, "myData2.txt");// Filename
        writeData(myFile, info);
        editText.setText("");
    }

    private void writeData(File myFile, String data) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(myFile);
            fileOutputStream.write(data.getBytes());
            Toast.makeText(this, "Done" + myFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}