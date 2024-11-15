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

public class UpdateUserFragment extends Fragment {

    private myDbAdapter dbAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 加載更新用戶界面佈局
        View view = inflater.inflate(R.layout.fragment_update_user, container, false);

        // 初始化視圖
        EditText editOldName = view.findViewById(R.id.edit_old_name);
        EditText editNewName = view.findViewById(R.id.edit_new_name);
        Button btnUpdateUser = view.findViewById(R.id.btn_update_user);

        // 初始化數據庫適配器
        dbAdapter = new myDbAdapter(requireContext());

        // 設置更新按鈕點擊事件
        btnUpdateUser.setOnClickListener(v -> {
            String oldName = editOldName.getText().toString().trim();
            String newName = editNewName.getText().toString().trim();

            if (oldName.isEmpty() || newName.isEmpty()) {
                Message.message(requireContext(), "請輸入舊名稱和新名稱");
            } else {
                int result = dbAdapter.updateName(oldName, newName);
                if (result > 0) {
                    Message.message(requireContext(), "更新成功");
                    editOldName.setText("");
                    editNewName.setText("");
                } else {
                    Message.message(requireContext(), "更新失敗，請檢查舊名稱是否存在");
                }
            }
        });

        return view;
    }
}
