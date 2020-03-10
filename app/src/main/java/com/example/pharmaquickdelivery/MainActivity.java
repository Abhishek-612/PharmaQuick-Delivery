package com.example.pharmaquickdelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.OneShotPreDrawListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText username,password;
    private Button login;
    private DatabaseReference mRef;
    private static boolean loggedIn=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(loggedIn)
            startActivity(new Intent(MainActivity.this, NavigationActivity.class));
        else {
            username = findViewById(R.id.username);
            password = findViewById(R.id.password);
            login = findViewById(R.id.login);

            mRef = FirebaseDatabase.getInstance().getReference().child("carriers");

            signIn();
        }
    }

    private void signIn(){

        final String user = username.getText().toString();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final String pass = password.getText().toString();
                    mRef.child(user).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                Toast.makeText(MainActivity.this, pass+"+"+user, Toast.LENGTH_SHORT).show();
                                if (pass.equals(data.child("password").getValue().toString())) {
                                    Toast.makeText(MainActivity.this, "Logged in...", Toast.LENGTH_SHORT).show();
                                    loggedIn = true;
                                    startActivity(new Intent(MainActivity.this, NavigationActivity.class));
                                } else
                                    Toast.makeText(MainActivity.this, pass+"+"+data.child("password").getValue().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "Please check credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
