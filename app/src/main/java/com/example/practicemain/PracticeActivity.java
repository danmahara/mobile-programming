package com.example.practicemain;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class PracticeActivity extends AppCompatActivity {

    TextView textView;
    private RadioGroup radioGroup;
    private CheckBox checkBox1, checkBox2;
    private List<CheckBox> checkBoxes;
    private List<String> list;
    private Spinner spinner;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practice);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String name = getIntent().getStringExtra("name");
        String faculty = getIntent().getStringExtra("faculty");

        textView = findViewById(R.id.display_data);
        textView.setText("Name: " + name + "\nFaculty: " + faculty);

        radioGroup = findViewById(R.id.radio_group);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = radioGroup.findViewById(i);
                if (radioButton.getId() == R.id.male) {
                    Snackbar.make(radioGroup, "Male", Snackbar.LENGTH_SHORT).show();
                } else if (radioButton.getId() == R.id.female) {
                    Snackbar.make(radioGroup, "Female", Snackbar.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(radioGroup, "Other", Snackbar.LENGTH_SHORT).show();
                }

            }
        });

//        checkBox1 = findViewById(R.id.java_checkbox);
//        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b) {
//                    Toast.makeText(Practice.this, compoundButton.getText() + " is selected", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


        checkBoxes = new ArrayList<>();
        checkBoxes.add(findViewById(R.id.java_checkbox));
        checkBoxes.add(findViewById(R.id.hibernate_checkbox));
        checkBoxes.add(findViewById(R.id.php_checkbox));
        checkBoxes.add(findViewById(R.id.js_checkbox));
        checkBoxes.add(findViewById(R.id.spring_boot_checkbox));
        checkBoxes.add(findViewById(R.id.react_js_checkbox));
        hideNavigationBar();
        list = new ArrayList<>(); // to store selected language from checkbox

        // Set the same listener for all checkboxes
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    list.add(compoundButton.getText().toString());
                    Snackbar.make(compoundButton, compoundButton.getText() + " is selected", Snackbar.LENGTH_SHORT).show();
                } else {
                    list.remove(compoundButton.getText().toString());
                }
            }
        };
        // Apply the listener to all checkboxes
        for (CheckBox checkBox : checkBoxes) {
            checkBox.setOnCheckedChangeListener(listener);
        }

        // to display all selected checkbox items
        Button b = findViewById(R.id.show_language_btn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.isEmpty()) {
                    Snackbar.make(view, "No languages selected", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(PracticeActivity.this);
                builder.setTitle("Selected Languages: ");
                // Convert list to array
                String[] languagesArray = list.toArray(new String[0]);
                builder.setItems(languagesArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar.make(view, "Selected: " + languagesArray[which], Snackbar.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("OK", null);
                builder.show();
            }
        });


// displaying values in spinner

        spinner = findViewById(R.id.spinner_1);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.cities));
        spinner.setAdapter(arrayAdapter);



// Track first selection
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            private boolean isFirstSelection = true; // Flag to check first selection

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (isFirstSelection) {
                    isFirstSelection = false; // Ignore first selection
                    return;
                }
                String value = (String) adapterView.getItemAtPosition(i);
                // Show Snackbar only if it's not the "Select" item
                if (!value.equals("Select")) {
                    Snackbar.make(adapterView, value, Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    //    to hide navigation buttons
    private void hideNavigationBar() {
        getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // for hide below nav (back,home,recent buttons)
//                | View.SYSTEM_UI_FLAG_FULLSCREEN  // for hide above nav (notification)
                );
    }
}