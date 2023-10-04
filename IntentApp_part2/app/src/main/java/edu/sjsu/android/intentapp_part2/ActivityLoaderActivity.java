package edu.sjsu.android.intentapp_part2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityLoaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button webButton = findViewById(R.id.webApp) ;
        Button callButton = findViewById(R.id.makingCalls) ;

        webButton.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v)
            {

                String title = "Open Amazon.com with";
                Uri link = Uri.parse("http://www.amazon.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, link );
                Intent chooser = Intent.createChooser(webIntent, title);
                startActivity(chooser);
            }
        });

        //define click behavior for call button
        callButton.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v)
            {

                Uri number = Uri.parse("tel:"+"+194912344444");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
            }
        });
    }
}