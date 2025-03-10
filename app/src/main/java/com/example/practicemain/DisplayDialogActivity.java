package com.example.practicemain;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DisplayDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display_dialog);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button showdialog = findViewById(R.id.show_dialog);
        showdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialogBox();
            }
        });


        ProgressBar progressBar = findViewById(R.id.progressBar);
        Button retriveData = findViewById(R.id.retrive_data);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        progressBar.setVisibility(View.GONE);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        retriveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostDataRetrive postDataRetrive = new PostDataRetrive(new PostDataCallback() {
                    @Override
                    public void onLoading() {
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onSuccess(List<Post> postList) {
                        progressBar.setVisibility(View.GONE);
                        Log.e("posts", postList.toString());

                        // set recycler adapter for postlist
                        if (postList != null && !postList.isEmpty()) {
                            CustomPostRecyclerAdapter adapter = new CustomPostRecyclerAdapter(postList);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(String error) {
                        Log.e("PostDataRetrive", error);  // Log the error message
                        //progressBar.setVisibility(View.INVISIBLE);

                    }
                });
                postDataRetrive.execute();
            }
        });
//        PostDataRetrive postDataRetrive = new PostDataRetrive(new PostDataCallback() {
//            @Override
//            public void onLoading() {
//                progressBar.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onSuccess(List<Post> postList) {
//                progressBar.setVisibility(View.GONE);
//                Log.e("posts",postList.toString());
//
////                set recycler adapter for psotlist
//            }
//
//            @Override
//            public void onFailure(String error) {
//                Log.e("PostDataRetrive", error);  // Log the error message
////                progressBar.setVisibility(View.INVISIBLE);
//
//            }
//        });

//        retriveData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                postDataRetrive.execute();
//            }
//        });

    }


    private void showAlertDialogBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this); // activity context
//       to add custom view to alert dialog

        View view = LayoutInflater.from(this).inflate(R.layout.login_file, null);
        AlertDialog dialog = builder.create();
        dialog.setView(view);

        EditText edtName, edtpas;
        edtName = view.findViewById(R.id.edtName);
        edtpas = view.findViewById(R.id.edtPass);
        Button submit = view.findViewById(R.id.btn_login);

        dialog.show();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String pwd = edtpas.getText().toString();
                Toast.makeText(DisplayDialogActivity.this, name + " " + pwd, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


//        builder.setTitle("Default Alert Title ");
//        builder.setMessage("This is  a dummy for alert dialog message");
//
//        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int which) {
//
//            }
//        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });

//        builder.create();
//        builder.setCancelable(false); // this will not let you cancel , while tapping outside dialog, it does not cancel  dialog
//        builder.show();

    }
}


