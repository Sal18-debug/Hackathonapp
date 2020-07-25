package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.myapplication.DataModels.Quiz;
import com.example.myapplication.DataModels.ResponseModel;

public class FrontPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ArrayList<String> nameOfQuizzes = QuizService.getInstance().getNames();
        setContentView(R.layout.activity_front_page_actitivity);



        Button quizButton = (Button) findViewById(R.id.quiz_button);
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FrontPageActivity.this, listOfQuizActivity.class));
            }
        });
        ScrollView scrollView = new ScrollView(this);
        LinearLayout layout = new LinearLayout(this);
        setContentView(scrollView);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(200,80,80,200);

        for(int i = 0;i<nameOfQuizzes.size();i++){
            final Button tempBtn = new Button(this);
            layout.addView(tempBtn);
            tempBtn.setText(nameOfQuizzes.get(i));
            tempBtn.setWidth(320);
            tempBtn.setHeight(40);
            tempBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Quiz tempQuiz = QuizService.getInstance().getQuiz(tempBtn.getText().toString());
                    OptionPicked.getInstance().setQuiz(tempQuiz);
                    startActivity(new Intent(FrontPageActivity.this, listOfQuizActivity.class));
                }
            });
        }

        Button AnxietyButton = new Button(this);
        layout.addView(AnxietyButton);

        AnxietyButton.setText("Anxiety");
        AnxietyButton.setWidth(320);
        AnxietyButton.setHeight(80);
        AnxietyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FrontPageActivity.this, anxietytab.class));
            }
        });

        Button AsperButton = new Button(this);
        layout.addView(AsperButton);

        AsperButton.setText("Asper");
        AsperButton.setWidth(320);
        AsperButton.setHeight(80);
        AsperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FrontPageActivity.this, activityAsper.class));
            }
        });

        Button LogoutButton = new Button(this);
        layout.addView(LogoutButton);

        LogoutButton.setText("Logout");
        LogoutButton.setWidth(320);
        LogoutButton.setHeight(120);
        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FrontPageActivity.this, MainActivity.class));
            }
        });
        scrollView.addView(layout);



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
            InputStream input = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);
            int data = reader.read();
            while(data != -1){
                char currentChar = (char) data;
                data = reader.read();
                response += currentChar;
            }
        }catch(Exception e){
            Log.e("[ERROR]", "HTTPRequestGet: DOESNT CONNECT TO: " + url.getPath());
            Toast errorMessageToast = Toast.makeText(getApplicationContext(),"Can not connect to server",Toast.LENGTH_LONG);
            errorMessageToast.show();
            Log.e("[ERROR]",e.getMessage());
        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
        }
        return response;
    }
}
