package com.example.pharmaquickdelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.OneShotPreDrawListener;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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



        if(Build.VERSION.SDK_INT>=23){
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
            else{
                startService();
            }
        }
        else{
            startService();
        }


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









    void startService() {
        Intent intent = new Intent(MainActivity.this,LocationService.class);
        startService(intent);

        Log.i("lu",isMyServiceRunning(LocationService.class)+"");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case 1:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    startService();
                else
                    Toast.makeText(this, "Please provide permissions", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
