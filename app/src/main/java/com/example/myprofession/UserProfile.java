package com.example.myprofession;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {

    TextInputLayout fullName, phoneNo, description, location, email, password;
    TextView fullNameLabel, usernameLabel;

    String user_name, user_username, user_phone, user_description, user_location, user_email, user_password;

    DatabaseReference reference;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        reference = FirebaseDatabase.getInstance("https://myproffession-bd4bd-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("users");

        fullName = findViewById(R.id.full_name);
        phoneNo = findViewById(R.id.phone_no);
        description = findViewById(R.id.description);
        location = findViewById(R.id.location);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        fullNameLabel = findViewById(R.id.fullname_field);
        usernameLabel = findViewById(R.id.username_field);

        //show all data
        ShowAllUserData();

    }

    private void ShowAllUserData() {

        Intent intent = getIntent();
        user_name = intent.getStringExtra("name");
        user_username = intent.getStringExtra("username");
        user_phone = intent.getStringExtra("phone");
        user_description = intent.getStringExtra("description");
        user_location = intent.getStringExtra("location");
        user_email = intent.getStringExtra("email");
        user_password = intent.getStringExtra("password");

        fullNameLabel.setText(user_name);
        fullName.getEditText().setText(user_name);
        usernameLabel.setText(user_username);
        phoneNo.getEditText().setText(user_phone);
        description.getEditText().setText(user_description);
        location.getEditText().setText(user_location);
        email.getEditText().setText(user_email);
        password.getEditText().setText(user_password);

    }

//                        intent.putExtra("name", nameFromDB);
//                        intent.putExtra("username", usernameFromDB);
//                        intent.putExtra("phone", phoneFromDB);
//                        intent.putExtra("description", descriptionFromDB);
//                        intent.putExtra("email", emailFromDB);
//                        intent.putExtra("password", passwordFromDB);

    public void Update(View view) {

        if (idNameChanged() || isPasswordChanged()) {
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this, "Data is same and can not be updated", Toast.LENGTH_LONG).show();

    }

    private boolean isPasswordChanged() {

        if (!user_password.equals(password.getEditText().getText().toString())) {
            reference.child(user_username).child("password").setValue(password.getEditText().getText().toString());
            user_password = password.getEditText().getText().toString();

            return true;
        } else {
            return false;
        }
    }

    private boolean idNameChanged() {

        if (!user_name.equals(fullName.getEditText().getText().toString())) {
            reference.child(user_username).child("name").setValue(fullName.getEditText().getText().toString());
            user_name = fullName.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }

}