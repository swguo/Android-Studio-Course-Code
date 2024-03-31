package com.example.userlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText ETaccount,ETpasswd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ETaccount = (EditText) findViewById(R.id.account);
        ETpasswd  = (EditText) findViewById(R.id.passwd);
    }
    public void logincheck(View view){
        String account  = ETaccount.getText().toString();
        String passwd  = ETpasswd.getText().toString();
        if (account.equals("swguo") && passwd.equals("123456")){
            Intent intent = new Intent(getApplicationContext(),UserInfo.class);
            String name = "swguo";
            String phone = "0921-000-111";
            String age = "18";
            intent.putExtra("name",name);
            intent.putExtra("phone",phone);
            intent.putExtra("age",age);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"帳號密碼錯誤",Toast.LENGTH_LONG).show();
        }
    }

    public void ckFinsh(View view){
        finish();
    }
}