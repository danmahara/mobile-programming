package com.example.practicemain;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends AppCompatActivity {
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_grid_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        gridView = findViewById(R.id.gridView);
        List<UserData> userDataList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            userDataList.add(new UserData("name " + i, "email " + i, i, i));
        }

//        CustomGridAdapter adapter=new CustomGridAdapter(userDataList, new GridViewActivity());

        CustomGridAdapter adapter = new CustomGridAdapter(userDataList,new UserDataCallback(){
            @Override
            public void onItemClicked(UserData userData) {
                Toast.makeText(GridViewActivity.this,
                        "Item clicked data "+userData.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        gridView.setAdapter(adapter);
    }
}