package com.example.pragatienterprise;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {


    private EditText userName, userPassword, userEmail, UserMobile,userFname,userCity;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser ;
    private String userID;
   String a_mob, b_fullname, city, email, uname ,password;

Button back;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();


        regButton.setOnClickListener(view -> {
            if(validate()){
                //Upload data to the database
                String user_email = userEmail.getText().toString().trim();
                String user_password = userPassword.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(task -> {

                    if(task.isSuccessful()){
                       // sendEmailVerification();
                        sendUserData();
                        firebaseAuth.signOut();
                        Toast.makeText(MainActivity2.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(MainActivity2.this, MainActivity.class));
                    }else{
                        Toast.makeText(MainActivity2.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }

                });
            }

           /*
            Toast.makeText(MainActivity2.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
finish();
            startActivity(new Intent(MainActivity2.this, MainActivity.class));
            */
        });

        userLogin.setOnClickListener(view -> startActivity(new Intent(MainActivity2.this, MainActivity.class)));
        back.setOnClickListener(view -> startActivity(new Intent(MainActivity2.this, MainActivity.class)));

    }

    private void setupUIViews(){
        userName = (EditText)findViewById(R.id.etusername);
        userPassword = (EditText)findViewById(R.id.etpassword);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        regButton = (Button)findViewById(R.id.btnRegister);
        userLogin = (TextView)findViewById(R.id.tvUserLogin);
        UserMobile = (EditText)findViewById(R.id.etmobile);
        userFname= (EditText)findViewById(R.id.etfullname);
        userCity= (EditText)findViewById(R.id.etcity);
        back=findViewById(R.id.back);

    }

    private Boolean validate(){
        boolean result = false;

        city  = userName.getText().toString();
        email    = userFname.getText().toString();
        uname   = userEmail.getText().toString();
        b_fullname  = UserMobile.getText().toString();
        a_mob=userCity.getText().toString();
password=userPassword.getText().toString();

        if(uname.isEmpty() || password.isEmpty() || email.isEmpty() || city.isEmpty() ||a_mob.isEmpty()|| b_fullname.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    sendUserData();
                    Toast.makeText(MainActivity2.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(MainActivity2.this, MainActivity.class));
                }else{
                    Toast.makeText(MainActivity2.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void sendUserData(){

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference myRef = firebaseDatabase.getReference("Users");
            //get all the values from the text field

            UserProfile userProfile = new UserProfile(b_fullname, email, a_mob, uname, city);
            myRef.child(firebaseAuth.getUid()).setValue(userProfile);
            //uploadTask.addOnFailureListener(e -> Toast.makeText(RegistrationActivity.this, "Upload failed!", Toast.LENGTH_SHORT).show()).addOnCompleteListener((OnCompleteListener<UploadTask.TaskSnapshot>) task -> Toast.makeText(RegistrationActivity.this, "Upload successful!", Toast.LENGTH_SHORT).show());

    }
}
