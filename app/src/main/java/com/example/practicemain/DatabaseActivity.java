package com.example.practicemain;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DatabaseActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<UserData> userDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        recyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        userDataList = new ArrayList<>();

        DBConnect db=new DBConnect(this);
        for (int i = 1; i <= 5; i++) {
            db.insertUser(new UserData("name " + i, "email " + i, i, i));
        }

        db.deleteUser(5);
        db.updateUser(4, new UserData("Dan", "dan@gmail.com", 22, 5.7F));

        CustomRecyclerViewAdapter adapter = new CustomRecyclerViewAdapter(db.retriveUsers(), new UserDataCallback() {
            @Override
            public void onItemClicked(UserData userData) {
                Toast.makeText(DatabaseActivity.this, "Selected: " + userData.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);

    }
}