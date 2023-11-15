package com.example.sessionmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public static String SHARED_PREFS = "shared-prefs";
    public static String EMAIL_KEY = "email_key";
    public static String PASSWORD_KEY = "password_key";
    SharedPreferences sharedPreferences;
    String email,password;
    private EditText emailedt,passwordedt;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailedt=(EditText) findViewById(R.id.email_login_edt);
        passwordedt=(EditText) findViewById(R.id.password_login_edt);
        login=(Button) findViewById(R.id.login_btn);

        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        email =sharedPreferences.getString("EMAIL_KEY",null);
        password =sharedPreferences.getString("PASSWORD_KEY",null);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(emailedt.getText().toString()) && TextUtils.isEmpty(passwordedt.getText().toString())) {
                    // this method will call when email and password fields are empty.
                    Toast.makeText(LoginActivity.this, "Please Enter Email and Password", Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(EMAIL_KEY,emailedt.getText().toString());
                    editor.putString(PASSWORD_KEY,passwordedt.getText().toString());

                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (email != null && password !=null){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
}