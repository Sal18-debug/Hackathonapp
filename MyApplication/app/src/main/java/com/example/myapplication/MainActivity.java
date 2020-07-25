package com.example.myapplication;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.hash.Hashing;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Button button = (Button) findViewById(R.id.LoginButton);

        final TextView EmailText = (TextView) findViewById(R.id.EditEmailText);
        final TextView PasswordText = (TextView) findViewById(R.id.EditPasswordText);

        button.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onClick(View v){
                if( (!EmailText.getText().toString().isEmpty()) && (!PasswordText.getText().toString().isEmpty())){
                    String email = EmailText.getText().toString();
                    String password = PasswordText.getText().toString();
                    //TODO: Please add correct URL/ REST call
                    //TODO: Add Hashing encryption
                    TextView ErrorMsg = (TextView) findViewById(R.id.ErrorLoginMessage);
                    String response = "";
                    if(email.equals("test@gmail.com")&& password.equals("password")) {
                        startActivity(new Intent(MainActivity.this, FrontPageActivity.class));
                        ErrorMsg.setVisibility(View.INVISIBLE);
                    }else{
                        ErrorMsg.setVisibility(View.VISIBLE);
                    }

                }
            }
        });

        TextView register = (TextView) findViewById(R.id.RegisterText);
        register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                    }
                }
        );



    }

    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }

    private String HTTPRequestGet(URL url){
        String response = "";
        HttpURLConnection urlConnection = null;

        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            System.out.println(1);
            InputStream input = urlConnection.getInputStream();
            System.out.println(2);
            InputStreamReader reader = new InputStreamReader(input);
            System.out.println(3);
            int data = reader.read();
            System.out.println(4);
            while(data != -1){
                char currentChar = (char) data;
                data = reader.read();
                response += currentChar;
            }
            Log.d("[message]", "HTTPRequestGet: "+response);
        }catch(Exception e){
            Log.e("[ERROR]", "HTTPRequestGet: DOESNT CONNECT");
            Toast errorMessageToast = Toast.makeText(getApplicationContext(),"Can not connect to server",Toast.LENGTH_LONG);
            errorMessageToast.show();
        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
        }
        return response;
    }
}

