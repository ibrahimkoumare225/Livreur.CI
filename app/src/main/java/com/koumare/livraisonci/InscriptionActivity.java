package com.koumare.livraisonci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class InscriptionActivity extends AppCompatActivity {

    EditText username, email, telephone, password;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        username = findViewById(R.id.email);
        password = findViewById(R.id.password);
        telephone = findViewById(R.id.telephone);
        email = findViewById(R.id.email);
        progressBar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onButtonSInscrireClicked(View v) {
        String txtUserName = username.getText().toString().trim();
        String txtPassWord = password.getText().toString().trim();
        String txtTelephone = telephone.getText().toString().trim();
        String txtEmail = email.getText().toString().trim();

        //On verifie si l'utilisateur a bien sasir ses coodonnées

        if (txtUserName.isEmpty()) {
            username.setError("Veuillez saisir votre nom");
            username.requestFocus();
        }
        if (txtPassWord.isEmpty() || txtPassWord.length() < 6) {
            password.setError("Veuillez saisir un mots de passe contenant plus de six caractères");
            password.requestFocus();
        }
        if (txtEmail.isEmpty()) {
            email.setError("Veuillez saisir votre adresse mail");
            email.requestFocus();
        }
        if (txtTelephone.isEmpty()) {
            telephone.setError("Veuuillez saisir votre numero");
            telephone.requestFocus();
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(txtEmail, txtPassWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User(txtUserName, txtPassWord, txtEmail, txtTelephone);

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(InscriptionActivity.this, "Utilisateur bien enregistrer", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                        Intent intent = new Intent(InscriptionActivity.this, ConnexionActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(InscriptionActivity.this, "Utilisateur non enregistrer", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                } else {
                    Toast.makeText(InscriptionActivity.this, "Utilisateur non enregistrer", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });



    }
    public void onButtonConnexionClicked(View v){
        Intent intent = new Intent(InscriptionActivity.this, ConnexionActivity.class);
        startActivity(intent);
    }


}