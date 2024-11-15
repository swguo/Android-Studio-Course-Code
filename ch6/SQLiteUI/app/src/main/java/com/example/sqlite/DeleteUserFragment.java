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

public class DeleteUserFragment extends Fragment {

    private myDbAdapter dbAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 加載刪除用戶界面佈局
        View view = inflater.inflate(R.layout.fragment_delete_user, container, false);

        // 初始化視圖
        EditText editNameToDelete = view.findViewById(R.id.edit_name_to_delete);
        Button btnDeleteUser = view.findViewById(R.id.btn_delete_user);

        // 初始化數據庫適配器
        dbAdapter = new myDbAdapter(requireContext());

        // 設置刪除按鈕點擊事件
        btnDeleteUser.setOnClickListener(v -> {
            String nameToDelete = editNameToDelete.getText().toString().trim();

            if (nameToDelete.isEmpty()) {
                Message.message(requireContext(), "請輸入要刪除的用戶名");
            } else {
                int result = dbAdapter.delete(nameToDelete);
                if (result > 0) {
                    Message.message(requireContext(), "用戶刪除成功");
                    editNameToDelete.setText("");
                } else {
                    Message.message(requireContext(), "刪除失敗，用戶名不存在");
                }
            }
        });

        return view;
    }
}
