
package com.example.pragatienterprise;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView userRegistration;
    private TextView forgot;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = findViewById(R.id.edtUsername);
        Password = findViewById(R.id.edtPassword);
        Login = findViewById(R.id.btnLogin);
        userRegistration = findViewById(R.id.txtSignup);
        forgot = findViewById(R.id.txtForgot);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(MainActivity.this, ThirdActivity.class));
        }


//this line validate data of userMail and Password

        Login.setOnClickListener(view ->

                validate(Name.getText().toString(), Password.getText().toString()));

       // Login.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ThirdActivity.class)));

        userRegistration.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, MainActivity2.class)));

    forgot.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, RegistrationActivity.class)));
   //activity has to be add
       // forgot.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ActivitySearch.class)));

    }

    private void validate(String userName, String userPassword) {

        progressDialog.setMessage("Please wait");
        progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        checkEmailVerification();
                        // Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        // startActivity(new Intent(MainActivity.this, ActivitySearch.class));
                    } else {
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            });
        }


    private void checkEmailVerification(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        boolean emailflag = firebaseUser.isEmailVerified();
        startActivity(new Intent(MainActivity.this, ActivitySearch.class));
/*
        if(emailflag){
            finish();
            startActivity(new Intent(MainActivity.this, ActivitySearch.class));
        }else{
            Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show();
          //  firebaseAuth.signOut();
        }

 */
    }
}