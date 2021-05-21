package com.example.myprofession;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextInputLayout fullName, phoneNo, description, location, email;
    TextView fullNameLabel, usernameLabel;

    String username;
//    user_name, user_phone, user_description, user_location, user_email;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullName = findViewById(R.id.full_name);
        phoneNo = findViewById(R.id.phone_no);
        description = findViewById(R.id.description);
        location = findViewById(R.id.location);
        email = findViewById(R.id.email);

        fullNameLabel = findViewById(R.id.fullname_field);
        usernameLabel = findViewById(R.id.username_field);

        reference = FirebaseDatabase.getInstance("https://myproffession-bd4bd-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("users");
        Intent intent = getIntent();
        username = intent.getStringExtra("ProfessionUserName");

        initScreen();
    }

    private void initScreen() {
//        TextView tv = findViewById(R.id.profession_name);
//        tv.setText(getIntent().getStringExtra("ProfessionName"));

//        int professionId = getIntent().getIntExtra("ProfessionId", 0);





        Query checkUser = reference.orderByChild("username").equalTo(username);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    String nameFromDB = dataSnapshot.child(username).child("name").getValue(String.class);
                    String usernameFromDB = dataSnapshot.child(username).child("username").getValue(String.class);
                    String phoneFromDB = dataSnapshot.child(username).child("phone").getValue(String.class);
                    String descriptionFromDB = dataSnapshot.child(username).child("description").getValue(String.class);
                    String locationFromDB = dataSnapshot.child(username).child("location").getValue(String.class);
                    String emailFromDB = dataSnapshot.child(username).child("email").getValue(String.class);

                    //set values of the selected user
                    fullNameLabel.setText(nameFromDB);
                    fullName.getEditText().setText(nameFromDB);
                    usernameLabel.setText(usernameFromDB);
                    phoneNo.getEditText().setText(phoneFromDB);
                    description.getEditText().setText(descriptionFromDB);
                    location.getEditText().setText(locationFromDB);
                    email.getEditText().setText(emailFromDB);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void OpenEmail(View view) {

        Query checkUser = reference.orderByChild("username").equalTo(username);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 String emailFromDB = dataSnapshot.child(username).child("email").getValue(String.class);

                 Intent intent = new Intent(MainActivity.this, EmailActivity.class);
                 intent.putExtra("ToEmail", emailFromDB);
                 startActivity(intent);
//        finish();
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
        });
    }

    public void OpenCall(View view) {

        Query checkUser = reference.orderByChild("username").equalTo(username);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String phoneFromDB = dataSnapshot.child(username).child("phone").getValue(String.class);

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phoneFromDB));

//                intent.putExtra("ToEmail", phoneFromDB);
                startActivity(intent);
//        finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}