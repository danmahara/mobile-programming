package com.example.practicemain;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_dialog);
        Button showdialog = findViewById(R.id.show_dialog);
        showdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialogBox();
            }
        });
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
                Toast.makeText(DisplayDialogActivity.this, name + "\n" + pwd, Toast.LENGTH_SHORT).show();
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


