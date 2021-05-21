package com.example.myprofession;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myprofession.adapters.ProfessionsListAdapter;
import com.example.myprofession.models.ProfessionVO;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProfessionsListActivity extends AppCompatActivity {

    DatabaseReference reference;

    String nameFromDB, usernameFromDB, phoneFromDB, descriptionFromDB, locationFromDB, emailFromDB, passwordFromDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professions_list);

        reference = FirebaseDatabase.getInstance("https://myproffession-bd4bd-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("users");

        Intent intent = getIntent();

        nameFromDB = intent.getStringExtra("name");
        usernameFromDB = intent.getStringExtra("username");
        phoneFromDB = intent.getStringExtra("phone");
        descriptionFromDB = intent.getStringExtra("description");
        locationFromDB = intent.getStringExtra("location");
        emailFromDB = intent.getStringExtra("email");
        passwordFromDB = intent.getStringExtra("password");

        initScreen();
    }

    private void initScreen() {
        ListView mainList = findViewById(R.id.main_list);

        //FROM DB
        ArrayList<ProfessionVO> professions = new ArrayList<>();
        ProfessionVO p1 = new ProfessionVO();
        p1.setName("userloc");
        p1.setDescription("najjar");
        ProfessionVO p2 = new ProfessionVO();
        p2.setName("test");
        p2.setDescription("desct");
        ProfessionVO p3 = new ProfessionVO();
        p3.setName("nabz");
        p3.setDescription("asdf");
        ProfessionVO p4 = new ProfessionVO();
        p4.setName("jad");
        p4.setDescription("Plumber");
        ProfessionVO p5 = new ProfessionVO();
        p5.setName("Fadi");
        p5.setDescription("Electrician");
        ProfessionVO p6 = new ProfessionVO();
        p6.setName("Ali");
        p6.setDescription("Plumber");
        ProfessionVO p7 = new ProfessionVO();
        p7.setName("Georges");
        p7.setDescription("najjar");
        ProfessionVO p8 = new ProfessionVO();
        p8.setName("Hadi");
        p8.setDescription("topography");
        ProfessionVO p9 = new ProfessionVO();
        p9.setName("Charbel");
        p9.setDescription("Electrician");
        professions.add(p1);
        professions.add(p2);
        professions.add(p3);
        professions.add(p4);
        professions.add(p5);
        professions.add(p6);
        professions.add(p7);
        professions.add(p8);
        professions.add(p9);

        //

        ProfessionsListAdapter adapter = new ProfessionsListAdapter(this, professions);
        mainList.setAdapter(adapter);

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProfessionVO profession = (ProfessionVO) parent.getItemAtPosition(position);
                Intent i = new Intent(ProfessionsListActivity.this, MainActivity.class);
                i.putExtra("ProfessionUserName", profession.getName());
                startActivity(i);
            }
        });


    }

    public void MyInfo(View view) {

        if (nameFromDB == null) {

            Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
            startActivity(intent);

        } else {

            Intent intent = new Intent(getApplicationContext(), UserProfile.class);
            intent.putExtra("name", nameFromDB);
            intent.putExtra("username", usernameFromDB);
            intent.putExtra("phone", phoneFromDB);
            intent.putExtra("description", descriptionFromDB);
            intent.putExtra("location", locationFromDB);
            intent.putExtra("email", emailFromDB);
            intent.putExtra("password", passwordFromDB);


            startActivity(intent);
        }
    }
}