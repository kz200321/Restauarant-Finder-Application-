package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.TextValueSanitizer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView register;
    private FirebaseAuth mAuth;
    private EditText edtUserNameLogin, edtPasswordlogin;
    private Button login;
    private String userName, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        edtUserNameLogin = findViewById(R.id.edtEmailLogin);
        edtPasswordlogin = findViewById(R.id.edtPasswordLogin);

        login = findViewById(R.id.btnLogin);
        login.setOnClickListener(this);

        register = findViewById(R.id.txtRegister);
        register.setOnClickListener(this);
    }



    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.txtRegister:
                startActivity(new Intent(this, RegisterUser.class));
                break;
            case R.id.btnLogin:
                loginUser();
                break;
        }
    }

    private void loginUser() {
        String username = edtUserNameLogin.getText().toString().trim();
        String password = edtPasswordlogin.getText().toString().trim();

        if(username.isEmpty()){
            edtUserNameLogin.setError("Email is required!");
            edtUserNameLogin.requestFocus();
            return;
        }
        if(password.isEmpty()){
            edtPasswordlogin.setError("Password is required!");
            edtPasswordlogin.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                startActivity(new Intent(MainActivity.this, HomePage.class));
            }else{
                Toast.makeText(MainActivity.this, "Check credentials", Toast.LENGTH_SHORT).show();
            }
        });

    }
}