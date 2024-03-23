package com.example.moviesreviewapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviesreviewapp.R;

public class LoginActivity extends AppCompatActivity {
  private EditText user,pass;
  private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }


    private void initView() {
        user=findViewById(R.id.username);
        pass=findViewById(R.id.password);
        login = findViewById(R.id.loginbutton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getText().toString().isEmpty()||pass.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
                }
                else if(user.getText().toString().equals("test")||pass.getText().toString().equals("test")){
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }
                else{
                    Toast.makeText(LoginActivity.this, "user and password is incoreect", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}