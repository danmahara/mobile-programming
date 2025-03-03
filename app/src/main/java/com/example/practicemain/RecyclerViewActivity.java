package com.example.practicemain;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    List<UserData> userDataList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recycler_view);
        // we set layout managers

        // for list view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // for grid view
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        // for list
//        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setLayoutManager(gridLayoutManager);
//        userDataList = new ArrayList<>();
//        for (int i = 1; i <=10; i++) {
//            userDataList.add(new UserData("name " + i, "email " + i, i, i));
//        }

        DBConnect dbConnect = new DBConnect(this);
        for (int i = 1; i <= 5; i++) {
            dbConnect.insertUser(new UserData("name " + i, "email " + i, i, i));
        }

        dbConnect.deleteUser(5);
        dbConnect.updateUser(4, new UserData("Dan Mahara", "dan@gmail.com", 5, 5));

//        CustomRecyclerViewAdapter adapter = new CustomRecyclerViewAdapter(userDataList);
        CustomRecyclerViewAdapter adapter = new CustomRecyclerViewAdapter(dbConnect.retriveUsers());
        recyclerView.setAdapter(adapter);



    }
}