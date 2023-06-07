package com.koumare.livraisonci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordMainActivity extends AppCompatActivity {

    EditText email;
    FirebaseAuth mAuth;
    ProgressBar forgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_main);

        email = findViewById(R.id.email);
        forgotPass = findViewById(R.id.forgotPass);

        mAuth = FirebaseAuth.getInstance();
    }

    public void forgotPasswordResetBtnPressed(View v) {
        resetPassword();
    }

    private void resetPassword() {
        String txtEmail = email.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches()) {
            email.setError("Veuillez entrer une adresse e-mail valide");
            email.requestFocus();
            return;
        }

        forgotPass.setVisibility(View.VISIBLE);

        mAuth.sendPasswordResetEmail(txtEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                forgotPass.setVisibility(View.GONE);

                if (task.isSuccessful()) {
                    Toast.makeText(ForgotPasswordMainActivity.this, "Veuillez consulter votre e-mail pour réinitialiser votre mot de passe", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ForgotPasswordMainActivity.this, ConnexionActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ForgotPasswordMainActivity.this, "Échec de la réinitialisation de votre mot de passe", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
