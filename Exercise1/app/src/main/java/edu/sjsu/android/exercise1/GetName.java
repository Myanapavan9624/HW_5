package edu.sjsu.android.exercise1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class GetName extends AppCompatActivity implements android.view.View.OnClickListener
{
    EditText editText;
    Button button;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_get_name);
        editText=this.findViewById(R.id.editText);
        button=this.findViewById(R.id.button);
        button.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra("UserName",editText.getText().toString());
        this.startActivity(intent);
    }
}
