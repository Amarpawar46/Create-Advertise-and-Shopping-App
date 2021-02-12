package com.example.pragatienterprise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    Button regButton;
    private EditText etName;
    private EditText etMobile;
   private ImageView btn;
    private EditText etConent;

String name,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Spinner spinner = (Spinner) findViewById(R.id.ddlist);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(MainActivity3.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Select));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);
        etName = findViewById(R.id.etname);
        etMobile = findViewById(R.id.txtMob);
        etConent = findViewById(R.id.content);
       // btn=findViewById(R.id.back);
        regButton = findViewById(R.id.btnRegister);
spinner=findViewById(R.id.ddlist);


       // btn.setOnClickListener(view -> startActivity(new Intent(MainActivity3.this, ThirdActivity.class)));

        name=etName.getText().toString();
content=etConent.getText().toString();

        regButton.setOnClickListener(view -> {

                String name =etName.getText().toString();
                String content =etConent.getText().toString();

                if(name.isEmpty()|| content.isEmpty()){
                    Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                }else {

                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = firebaseDatabase.getReference("Advertise");

                    User user = new User(name, content);
                    myRef.child(content).setValue(user);
                    Toast.makeText(MainActivity3.this, " Upload complete!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(MainActivity3.this, ThirdActivity.class));
                }

      });


    }


    }
