package com.badlogic.sokolovtask_lab2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    protected int number_click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(android.view.View view) {
        number_click++;
        android.widget.TextView textView = findViewById(R.id.textView);
        textView.setText("Количество нажатий: " + number_click);
    }
}