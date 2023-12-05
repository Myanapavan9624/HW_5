package edu.sjsu.android.hw_5;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    Context context;
    ImageView img;
    private EditText urlText;
    Bitmap bitmap;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView)findViewById(R.id.imgView);
        urlText = (EditText)findViewById(R.id.urlText);
        final Handler handler = new Handler();
        pd = new ProgressDialog(MainActivity.this);
        context = this;
    }
    Bitmap downloadBitmap (String ur) {
        // ... you fill in here
        try {
            return BitmapFactory.decodeStream((InputStream)new URL(ur).getContent());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void runRunnable(View view) {
        pd.show();
        pd.setContentView(R.layout.progress_bar);
        pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Bitmap bm = downloadBitmap(urlText.getText().toString());
                pd.dismiss();
                try{
                    img.setImageBitmap(bm);
                }
                catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Download failed....",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                Looper.loop();
            }
        });
        thread.start();
    }

    public void runMessages(View view) {
        // ... you fill in here
        pd.show();
        pd.setContentView(R.layout.progress_bar);
        pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Handler myHandler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                Bitmap bm = downloadBitmap(urlText.getText().toString());
                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                        try{
                            img.setImageBitmap(bm);
                        }
                        catch(Exception e){
                            Toast.makeText(getApplicationContext(), "Download failed....",Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
        thread.start();
    }

    private class GetImage extends AsyncTask<String, Void, Bitmap> {
        protected Bitmap doInBackground(String... params) {
            String URL = params[0];
            return downloadBitmap(URL);
        }
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            pd.dismiss();
            try{
                img.setImageBitmap(result);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void runAsyncTask(View view) {
        pd.show();
        pd.setContentView(R.layout.progress_bar);
        pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        try{
            new GetImage().execute(urlText.getText().toString());
        }
        catch(Exception e){

            e.printStackTrace();
        }

    }

    public void resetImage(View view) {
        img.setImageResource(R.drawable.apple);
        urlText.setText("");
    }
}