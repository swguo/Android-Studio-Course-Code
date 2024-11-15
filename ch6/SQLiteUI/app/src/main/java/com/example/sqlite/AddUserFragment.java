package com.example.sqlite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddUserFragment extends Fragment {

    private myDbAdapter dbAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 加載布局
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);

        // 初始化視圖
        EditText editName = view.findViewById(R.id.edit_name);
        EditText editPassword = view.findViewById(R.id.edit_password);
        Button btnAddUser = view.findViewById(R.id.btn_add_user);

        // 初始化數據庫適配器
        dbAdapter = new myDbAdapter(requireContext());

        // 設置按鈕點擊事件
        btnAddUser.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            if (name.isEmpty() || password.isEmpty()) {
                Message.message(requireContext(), "請輸入用戶名和密碼");
            } else {
                long result = dbAdapter.insertData(name, password);
                if (result > 0) {
                    Message.message(requireContext(), "用戶新增成功");
                    editName.setText("");
                    editPassword.setText("");
                } else {
                    Message.message(requireContext(), "用戶新增失敗");
                }
            }
        });

        return view;
    }
}
