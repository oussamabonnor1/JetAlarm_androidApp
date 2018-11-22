package com.jetlightstudio.jetalarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        String temp2 = getIntent().getStringExtra("key");
        TextView textView = findViewById(R.id.textView);
        textView.setText(temp2);
    }

}
