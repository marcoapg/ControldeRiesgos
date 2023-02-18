package com.usat.controlderiesgos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.usat.controlderiesgos.ui.amenaza.AmenazaFragment;
import com.usat.controlderiesgos.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    EditText correoLogin;
    EditText contrasenaLogin;
    ImageView btnLogin;
    FirebaseAuth mAuth;

    TextView mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correoLogin = findViewById(R.id.Email);
        contrasenaLogin = findViewById(R.id.Password);
        btnLogin = findViewById(R.id.imageView3);
        mAuth = FirebaseAuth.getInstance();
        mRegister = findViewById(R.id.tvRegister);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                login();
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

    }

    private void login() {
        String emailLogin = correoLogin.getText().toString();
        String passwordLogin = contrasenaLogin.getText().toString();

        mAuth.signInWithEmailAndPassword(emailLogin, passwordLogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(MainActivity.this, NavigationDrawer.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this, "Los datos son incorrectos, intente nuevamente!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




}