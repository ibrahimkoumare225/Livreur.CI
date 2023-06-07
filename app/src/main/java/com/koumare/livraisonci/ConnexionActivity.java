package com.koumare.livraisonci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ConnexionActivity extends AppCompatActivity {
    EditText username, password;
    TextView forgotpass, existcompte;
    ProgressBar progressBar2;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        username = findViewById(R.id.email); // Correct ID for username EditText
        password = findViewById(R.id.password); // Correct ID for password EditText
        forgotpass = findViewById(R.id.forgotpass);
        existcompte = findViewById(R.id.existcompte);
        progressBar2 = findViewById(R.id.progressBar2);
        mAuth = FirebaseAuth.getInstance();
    }
    public void txtSingInRegisterClicked(View v) {
        // Implement the functionality for the register action
        Intent intent = new Intent(this,InscriptionActivity.class);
        startActivity(intent);
    }
    public void txtSingInForgotPasswordClicked(View v) {
        Intent intent = new Intent(ConnexionActivity.this, ForgotPasswordMainActivity.class);
        startActivity(intent);
    }

    public void onButtonConnexionClicked(View v) {
        String usernames = username.getText().toString().trim();
        String passwords = password.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(usernames).matches()) {
            username.setError("Veuillez entrer votre adresse mail");
            username.requestFocus();
            return; // Return early to prevent further execution
        }

        if (passwords.length() < 6) {
            password.setError("Mot de passe trop court");
            password.requestFocus();
            return; // Return early to prevent further execution
        }

        progressBar2.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(usernames, passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar2.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(ConnexionActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ConnexionActivity.this, "Connexion échouée", Toast.LENGTH_SHORT).show();
                }

            }

        });

        Intent aff = new Intent(ConnexionActivity.this,PrincipalActivity.class);
        startActivity(aff);


    }

}


