package com.example.sqlite;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // 初始顯示 "新增用戶" 頁面
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new AddUserFragment())
                .commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.nav_add_user) {
                selectedFragment = new AddUserFragment();
            } else if (item.getItemId() == R.id.nav_update_user) {
                selectedFragment = new UpdateUserFragment();
            } else if (item.getItemId() == R.id.nav_list_users) {
                selectedFragment = new ListUsersFragment(); // 新增處理列出用戶的頁面
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }

            return true;
        });
    }
}
