package com.example.practicemain;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    Spinner spinner;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view);

        List<UserData> userDataList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            userDataList.add(new UserData("name " + i, "email " + i, i, i));
        }
//        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, userDataList);
//        spinner.setAdapter(adapter);


        listView = findViewById(R.id.list_view);

        CustomListAdapter customListAdapter = new CustomListAdapter(this, userDataList);
        listView.setAdapter(customListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UserData userData=userDataList.get(i);
                Toast.makeText(ListViewActivity.this,userData.getName() , Toast.LENGTH_SHORT).show();
            }
        });

    }


//    @Override
//    protected void onResume() {
//        super.onResume();
////        String name = getIntent().getStringExtra("username");
////        int age = getIntent().getIntExtra("age", 0);
////        float height = getIntent().getFloatExtra("height", 0.0f);
////        Toast.makeText(this, name + " " + age + " " + height, Toast.LENGTH_SHORT).show();
//    }


    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
//        String name = bundle.getString("username");
//        int age = bundle.getInt("age",0);
    }
}