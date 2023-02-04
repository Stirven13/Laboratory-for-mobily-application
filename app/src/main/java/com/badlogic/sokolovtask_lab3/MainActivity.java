package com.badlogic.sokolovtask_lab3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(android.view.View view) {
        android.content.Intent intent = new android.content.Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}