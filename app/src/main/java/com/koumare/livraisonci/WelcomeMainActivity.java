package com.koumare.livraisonci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WelcomeMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_main);




        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Ma première avec FIREBASE");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("WATER_DELIVERY_APP", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("WATER_DELIVERY_APP", "Failed to read value.", error.toException());
            }
        });
        //liaison  boutons coonnexion pour démarer nouvelle activity

    }
    public void onButtonSInscrireClicked(View view ){
        Intent inscription = new Intent(WelcomeMainActivity.this,InscriptionActivity.class);
        startActivity(inscription);
    }
    public void onButtonConnexionClicked(View view ){
        Intent connexion = new Intent(WelcomeMainActivity.this,ConnexionActivity.class);
        startActivity(connexion);
    }
}