package com.example.testapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private TextView txtAppName;
    private Button registerUser;
    private EditText edtName, edtAge, edtEmail, edtPassword;
    private ProgressBar progressBar;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private String email, name, age, ID;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();
        txtAppName = findViewById(R.id.txtAppName);
        txtAppName.setOnClickListener(this);

        edtName = findViewById(R.id.edtEmailLogin);
        edtAge = findViewById(R.id.edtPasswordLogin);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        progressBar =findViewById(R.id.progressbar);

        registerUser = findViewById(R.id.btnRegister);
        registerUser.setOnClickListener(this);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users");

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.txtAppName:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btnRegister:
                registerUser();
                break;
        }
    }

    private void registerUser(){
        name = edtName.getText().toString().trim();
        age = edtAge.getText().toString().trim();
        email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();



        if(name.isEmpty()){
            edtName.setError("Full name is required!");
            edtName.requestFocus();
            return;
        }
        if(age.isEmpty()){
            edtAge.setError("Age is required!");
            edtAge.requestFocus();
            return;
        }
        if(email.isEmpty()){
            edtEmail.setError("Email is required!");
            edtEmail.requestFocus();
            return;
        }
        if(!(Patterns.EMAIL_ADDRESS.matcher(email).matches())){
            edtEmail.setError("Provide a valid email address!");
            edtEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            edtPassword.setError("Password is required!");
            edtPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            edtPassword.setError("Minpassword length is 6 characters!");
            edtPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            ID = mAuth.getUid();
                            User user = new User(name, age, email, ID);
                            reference.child("ID").setValue(ID);
                            reference.child("ID/Info").setValue(user);
                            user.setID(ID);
                        }

//                            FirebaseDatabase.getInstance().getReference("Users")
//                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                    .push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//
//                                            if(task.isSuccessful()){
//
//                                                Toast.makeText(RegisterUser.this, "User has been registered", Toast.LENGTH_LONG).show();
//                                                progressBar.setVisibility(View.GONE);
//                                            }else{
//                                                Toast.makeText(RegisterUser.this, "Failed to register!", Toast.LENGTH_LONG).show();
//                                                progressBar.setVisibility(View.GONE);
//                                            }
//
//
//
//                                        }


                                    }
                    });
                }

        }

