package com.example.sqlite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ListUsersFragment extends Fragment {

    private myDbAdapter dbAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 加載佈局
        View view = inflater.inflate(R.layout.fragment_list_users, container, false);

        // 初始化視圖
        TextView usersListTextView = view.findViewById(R.id.tv_users_list);

        // 初始化數據庫適配器
        dbAdapter = new myDbAdapter(requireContext());

        // 從數據庫獲取用戶數據
        String usersData = dbAdapter.getData();
        if (usersData.isEmpty()) {
            usersListTextView.setText("目前無用戶");
        } else {
            usersListTextView.setText(usersData);
        }

        return view;
    }
}
