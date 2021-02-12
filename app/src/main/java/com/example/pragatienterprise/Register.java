package com.example.pragatienterprise;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.Objects;

public class Register extends AppCompatActivity {

    private EditText newUserName, newUserEmail, newUserAge,newCity ,newUser;
    private Button save;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
newUser=findViewById(R.id.etUname);
        newUserName = findViewById(R.id.etNameUpdate);
        newUserEmail = findViewById(R.id.etEmailUpdate);
        newUserAge = findViewById(R.id.mobile);
        newCity=findViewById(R.id.etUsercity);
        save = findViewById(R.id.btnSave);


      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        final DatabaseReference myref = FirebaseDatabase.getInstance().getReference("Users").child(Objects.requireNonNull(firebaseAuth.getUid()));

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);

                assert userProfile != null;
                newUserName.setText(userProfile. getB_fullname());
                newUserAge.setText(userProfile.  getA_mob());
                newUserEmail.setText(userProfile.getEmail());
                newCity.setText(userProfile.  getCity());
                newUser.setText(userProfile. getUname());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Register.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });


        save.setOnClickListener(view -> {
            //city=Uname,email=userFname,Ahe=mobile=ucity,fname=umobile,uname=email
            String b_fullname =   newUserAge.getText().toString();
            String a_mob =   newCity.getText().toString();
            String email =  newUserName.getText().toString();
            String city =   newUser.getText().toString();
            String uname=newUserEmail.getText().toString();

            UserProfile userProfile = new UserProfile(b_fullname,email,a_mob,city,uname);

            myref.setValue(userProfile);
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference myRef = firebaseDatabase.getReference("Users");

            myRef.child(firebaseAuth.getUid()).setValue(userProfile);

            Toast.makeText(Register.this, "Upload Succesfully", Toast.LENGTH_SHORT).show();

        });
    }
}

