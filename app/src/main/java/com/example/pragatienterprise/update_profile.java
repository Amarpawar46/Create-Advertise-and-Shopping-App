package com.example.pragatienterprise;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Objects;

public class update_profile extends AppCompatActivity {


   // private TextView profileName, profileMob, profileEmail, profileCity, profileUname;
    private Button profileUpdate, changePassword, Logout;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference myRef;
   private FirebaseUser user;
   private String userID;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Logout = (Button) findViewById(R.id.Logout);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        userID=user.getUid();
        //this line is retrive data from database
        myRef = FirebaseDatabase.getInstance().getReference("Users").child(Objects.requireNonNull(firebaseAuth.getUid()));

        final TextView  profileCity = (TextView) findViewById(R.id.etUsercity);
        final TextView  profileName = (TextView)findViewById(R.id.tvProfileName);
        final TextView profileMob = (TextView)findViewById(R.id.tvProfileMobile);
        final TextView profileEmail = (TextView)findViewById(R.id.tvProfileEmail);
      final TextView  profileUname = (TextView)findViewById(R.id.etUname);

        changePassword = findViewById(R.id.btnChangePassword);
        profileUpdate = findViewById(R.id.btnProfileUpdate);

//getSupportActionBar().setDisplayHomeAsUpEnabled(true); this line for back button

myRef.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        UserProfile userProfile=snapshot.getValue(UserProfile.class);
        if(userProfile !=null){
            String fullname=userProfile.b_fullname;
            String email=userProfile.email;
            String mobile=userProfile.a_mob;
            String city=userProfile.city;
            String uname=userProfile.uname;

            profileName.setText(fullname);
            profileMob.setText(mobile);
            profileEmail.setText(email);
            profileCity.setText(city);
            profileUname.setText(uname);
        }
    }
    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        Toast.makeText(update_profile.this,"Something went to wrong", Toast.LENGTH_SHORT).show();
    }
});
/*

        myRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserProfile userProfile = snapshot.getValue(UserProfile.class);
                assert userProfile != null;
                //this values was same
                profileName.setText("Name :" + userProfile. getB_fullname());
                profileMob.setText("Mobile: " + userProfile.getA_mob());
                profileEmail.setText("City:" + userProfile. getEmail());
                profileCity.setText("Email:" + userProfile.getCity());
               profileUname.setText("UserName:" + userProfile.getUname());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(update_profile.this, error.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
 */

        profileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(update_profile.this, Register.class));
            }
        });

        Logout.setOnClickListener(view -> signOut());
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(update_profile.this, RegistrationActivity.class));
            }
        });
    }
    private void signOut() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(update_profile.this, MainActivity.class));
    }


}
