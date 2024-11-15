package com.example.imageview;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
    private GestureDetector gestureDetector;
    private float currentScale = 1.0f;

    private ImageView imageView;
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
        imageView = (ImageView) findViewById(R.id.simpleImageView);
        imageView.setImageResource(R.mipmap.lion);

        // 建立 GestureDetector 檢測雙擊
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // 雙擊事件處理
                currentScale *= 1.5f; // 放大 1.5 倍
                imageView.setScaleX(currentScale);
                imageView.setScaleY(currentScale);
                return true;
            }
        });

        // 為 ImageView 設定觸控事件
        imageView.setOnTouchListener(this::onTouch);

    }

    private boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true; // 確保事件不被父視圖攔截
    }
}