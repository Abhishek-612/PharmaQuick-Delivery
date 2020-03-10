package com.example.pharmaquickdelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.OneShotPreDrawListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText username,password;
    private Button login;
    private DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        mRef = FirebaseDatabase.getInstance().getReference().child("carriers");

        signIn();
    }

    private void signIn(){
        final String user = username.getText().toString();
        final String pass = password.getText().toString();
        final String[] auth = {""};

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    auth[0] = mRef.child(user).toString();
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "Please check login credentials", Toast.LENGTH_SHORT).show();
                }

                if(pass.equals(auth[0])){
                    Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,NavigationActivity.class));
                }
                else{
                    Toast.makeText(MainActivity.this, "Please check login credentials", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
