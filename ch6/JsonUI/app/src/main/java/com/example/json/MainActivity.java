package com.example.json;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RecyclerView 初始化
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 解析 JSON 並設置數據
        String strJson = "{ \"Employee\" :[{\"id\":\"101\",\"name\":\"Sonoo Jaiswal\",\"salary\":\"50000\"},{\"id\":\"102\",\"name\":\"Vimal Jaiswal\",\"salary\":\"60000\"}] }";
        List<Employee> employeeList = parseJson(strJson);

        // 設置適配器
        EmployeeAdapter adapter = new EmployeeAdapter(employeeList);
        recyclerView.setAdapter(adapter);
    }

    // 解析 JSON 為 Employee 列表
    private List<Employee> parseJson(String strJson) {
        List<Employee> employeeList = new ArrayList<>();
        try {
            JSONObject jsonRootObject = new JSONObject(strJson);
            JSONArray jsonArray = jsonRootObject.optJSONArray("Employee");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.optString("id");
                String name = jsonObject.optString("name");
                String salary = jsonObject.optString("salary");
                employeeList.add(new Employee(id, name, salary));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}