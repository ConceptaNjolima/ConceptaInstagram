package com.example.conceptainstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG="LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (ParseUser.getCurrentUser()!=null){
            getMainActivity();

        }

        etPassword=findViewById(R.id.etsetPassword);
        etUsername=findViewById(R.id.etsetUsername);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup=findViewById(R.id.btnSignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                String username=etUsername.getText().toString();
                String password=etPassword.getText().toString();
                loginUser(username,password);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSignUp();
            }
        });


    }


    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login user " +username);

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.i(TAG, "issue login ", e);
                    return;
                }
                //Navigate to the main activity to ensure that user has signed in
                getMainActivity();
                Log.d(TAG, "getting login ");
                Toast.makeText(LoginActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void getMainActivity() {
        Intent i =new Intent(this,MainActivity.class);
        startActivity(i);
        finish();

    }
    private void getSignUp() {
        Intent i =new Intent(this,SignupActivity.class);
        startActivity(i);
    }

}
