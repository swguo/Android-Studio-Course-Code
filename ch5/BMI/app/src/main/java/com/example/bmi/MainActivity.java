package com.example.bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
        Button button =(Button)findViewById(R.id.button);
        button.setOnClickListener(calBMI);
    }
    private View.OnClickListener calBMI = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            EditText fheight = (EditText)findViewById(R.id.editTextNumber); //把輸入在editTextNumber裡的變數放入fhight
            EditText fweight = (EditText)findViewById(R.id.editTextNumber2);//把輸入在editTextNumber2裡的變數放入fweight

            double height = Double.parseDouble(fheight.getText().toString())/100; // 計算身高
            double weight = Double.parseDouble(fweight.getText().toString()); //計算體重
            double BMI = weight/(height*height); //計算BMI

            TextView result = (TextView)findViewById(R.id.textViewbmi); //把算出來的BMI數值輸出到textViewbmi裡
            result.setText(Double.toString(BMI)); //將BMI裡的數字應用nf裡的變數轉換

            TextView suggest = (TextView)findViewById(R.id.textViewans);
            //判斷BMI的值在哪個範圍並輸出
            if (BMI>24.00){
                suggest.setText("過胖!");
            }
            else if (BMI<18.50){
                suggest.setText("過瘦!");
            }
            else {
                suggest.setText("適中!");
            }

        }
    };
}