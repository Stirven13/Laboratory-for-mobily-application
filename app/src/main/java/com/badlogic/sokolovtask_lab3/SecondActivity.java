package com.badlogic.sokolovtask_lab3;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setTextSize(20);
        textView.setPadding(16, 16, 16, 16);
        textView.setText("Лабораторная работа № 3. Работу выполнил .....");

        // setContentView draw only 1 View(
        // setContentView(R.layout.activity_second);
        // setContentView(textView);

        View activity_second = getLayoutInflater().inflate(R.layout.activity_second, null);
        ViewGroup viewGroup = (ViewGroup) activity_second;
        viewGroup.addView(textView);

        setContentView(activity_second);
    }

    public void onClickToBack(android.view.View view) {
        finish();
    }
}