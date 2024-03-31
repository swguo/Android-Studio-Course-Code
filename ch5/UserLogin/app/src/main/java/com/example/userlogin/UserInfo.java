package com.example.userlogin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class UserInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        String name = intent.getStringExtra("name").toString();
        String phone = intent.getStringExtra("phone").toString();
        String age = intent.getStringExtra("age").toString();

        TextView tvname = (TextView) findViewById(R.id.username);
        TextView tvphone = (TextView) findViewById(R.id.phone);
        TextView tvage = (TextView) findViewById(R.id.age);

        tvname.setText(name);
        tvphone.setText(phone);
        tvage.setText(age);

    }
}