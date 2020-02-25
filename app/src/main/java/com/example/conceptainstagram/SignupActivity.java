package com.example.conceptainstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {
    public static final String TAG= "SignupActivity";
    private Button btnSignup;
    private EditText etUsername;
    private EditText etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUsername=findViewById(R.id.etsetUsername);
        etPassword=findViewById(R.id.etsetPassword);
        btnSignup=findViewById(R.id.btnSetinfo);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                String username=etUsername.getText().toString();
                String password=etPassword.getText().toString();
                if (username.isEmpty() || password.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Password or username not entered", Toast.LENGTH_SHORT).show();
                }
                SignupUser(username,password);

            }
        });
    }

    private void SignupUser(final String username, String password) {
        // Create the ParseUser
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e!=null){
                    Log.i(TAG, "failed to sign up"+ username);
                    return;
                }
                enableLogin();
                Toast.makeText(SignupActivity.this, "Account created", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void enableLogin() {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
