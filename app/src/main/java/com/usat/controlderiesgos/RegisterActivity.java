package com.usat.controlderiesgos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailTV,pwdTV,cnfTV;

    private ImageView btnRegistrar;

    private TextView login;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegistrar=findViewById(R.id.btnRegistrar);
        emailTV = findViewById(R.id.email);
        pwdTV = findViewById(R.id.password);
        cnfTV = findViewById(R.id.confirmPassword);
        login = findViewById(R.id.tvLogin);

        mAuth = FirebaseAuth.getInstance();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = String.valueOf(emailTV.getText());
                String pwd = String.valueOf(pwdTV.getText());
                String cnf = String.valueOf(cnfTV.getText());

                if(!pwd.equals(cnf)){
                    Toast.makeText(RegisterActivity.this, "Verificar que las contraseñas sean iguales...", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(email) && TextUtils.isEmpty(pwd) && TextUtils.isEmpty(cnf)) {

                    // checking if the text fields are empty or not.
                    Toast.makeText(RegisterActivity.this, "Ingrese sus credenciales...", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Usuario registrado, Inicie Sesion...", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();

                            }else{
                                Toast.makeText(RegisterActivity.this, "Fallo el registro...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }




}