package com.example.practicemain;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    List<UserData> userDataList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = findViewById(R.id.recycler_view);
        // we set layout managers

        // for list view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // for grid view
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        // for list
//        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setLayoutManager(gridLayoutManager);
        userDataList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            userDataList.add(new UserData("name " + i, "email " + i, i, i));
        }
        CustomRecyclerViewAdapter adapter = new CustomRecyclerViewAdapter(userDataList, new UserDataCallback() {
            @Override
            public void onItemClicked(UserData userData) {
                Toast.makeText(RecyclerViewActivity.this, userData.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);


    }
}