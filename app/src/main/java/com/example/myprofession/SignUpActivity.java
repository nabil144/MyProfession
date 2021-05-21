package com.example.myprofession;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myprofession.models.UserVO;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    TextInputLayout regFullName, regUserName, regPhoneNo, regDescription, regLocation, regEmail, regPassword;

    Button regBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        regFullName = findViewById(R.id.name);
        regUserName = findViewById(R.id.username);
        regPhoneNo = findViewById(R.id.phone);
        regDescription = findViewById(R.id.description);
        regLocation = findViewById(R.id.location);
        regEmail = findViewById(R.id.email);
        regPassword = findViewById(R.id.password);

        regBtn = findViewById(R.id.go);

        rootNode = FirebaseDatabase.getInstance("https://myproffession-bd4bd-default-rtdb.asia-southeast1.firebasedatabase.app/");
        reference = rootNode.getReference("users");

//        regBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//
//                //Get all values
//                String name = regFullName.getEditText().getText().toString();
//                String username = regUserName.getEditText().getText().toString();
//                String phone = regPhoneNo.getEditText().getText().toString();
//                String description = regDescription.getEditText().getText().toString();
//                String email = regEmail.getEditText().getText().toString();
//                String password = regPassword.getEditText().getText().toString();
//
//                UserVO userVO = new UserVO(name, username, phone, description, email, password);
//
//
//                reference.child(username).setValue(userVO);
//
//                Intent intent = new Intent(SignUpActivity.this, ProfessionsListActivity.class);
////                intent.putExtra("UserId", phone);
//                startActivity(intent);
//                finish();
//            }
//        });
    }

    private Boolean validateName() {
        String val = regFullName.getEditText().getText().toString();
        if (val.isEmpty()) {
            regFullName.setError("Field cannot be empty");
            return false;
        } else {
            regFullName.setError(null);
            regFullName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = regFullName.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            regFullName.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            regFullName.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regFullName.setError("White Spaces are not allowed");
            return false;
        } else {
            regFullName.setError(null);
            regFullName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = regPhoneNo.getEditText().getText().toString();

        if (val.isEmpty()) {
            regPhoneNo.setError("Field cannot be empty");
            return false;
        } else {
            regPhoneNo.setError(null);
            regPhoneNo.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateDescription() {
        String val = regDescription.getEditText().getText().toString();
        if (val.isEmpty()) {
            regDescription.setError("Field cannot be empty");
            return false;
        } else {
            regDescription.setError(null);
            regDescription.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void BackToLogin(View view) {
        Intent intent = new Intent(SignUpActivity.this, Login.class);
        startActivity(intent);
        finish();
    }

    public void RegisterUser(View view) {
        if (!validateName() | !validatePassword() | !validatePhoneNo() | !validateDescription() | !validateEmail() | !validateUsername()) {
            return;
        }

        //Get all values
        String name = regFullName.getEditText().getText().toString();
        String username = regUserName.getEditText().getText().toString();
        String phone = regPhoneNo.getEditText().getText().toString();
        String description = regDescription.getEditText().getText().toString();
        String location = regLocation.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();

        UserVO userVO = new UserVO(name, username, phone, description, location, email, password);


        reference.child(username).setValue(userVO);

        Intent intent = new Intent(SignUpActivity.this, ProfessionsListActivity.class);
//                intent.putExtra("UserId", phone);
        startActivity(intent);
        finish();
    }


}