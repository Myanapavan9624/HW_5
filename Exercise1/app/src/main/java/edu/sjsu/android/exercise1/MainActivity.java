package edu.sjsu.android.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle myInput=this.getIntent().getExtras();
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textView=findViewById(R.id.textview);
        textView.setText("Hello "+myInput.getString("UserName"));
    }
}