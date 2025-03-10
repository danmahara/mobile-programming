package com.example.practicemain;

import android.content.Intent;
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
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private CheckBox mp, java, php, js;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        radioGroup = findViewById(R.id.radioGroup);
        spinner = findViewById(R.id.spinner);

        /** Allow us to adapt the lists of data into a single view item **/
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.college_list));
        spinner.setAdapter(arrayAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = String.valueOf(adapterView.getItemAtPosition(i));
                Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton button = radioGroup.findViewById(i);

                if (button.getId() == R.id.male) {
                    Toast.makeText(MainActivity.this, "Male selected", Toast.LENGTH_SHORT).show();
                } else if (button.getId() == R.id.female) {
                    Toast.makeText(MainActivity.this, "Female selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mp = findViewById(R.id.mobile_prg);
        java = findViewById(R.id.java);
        php = findViewById(R.id.php);
        js = findViewById(R.id.js);

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean isChecked) {
                if (isChecked) {
                    Snackbar.make(cb, cb.getText() + " is selected", Snackbar.LENGTH_SHORT).show();
                }
            }
        };
//
        mp.setOnCheckedChangeListener(listener);
        java.setOnCheckedChangeListener(listener);
        php.setOnCheckedChangeListener(listener);
        js.setOnCheckedChangeListener(listener);


        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // new Intent(CurrentActivity.this,NextActivity.class)
                // new Intent(getApplicationContext(),NextActivity.class)
//                Intent intent= new Intent(MainActivity.this,GridViewActivity.class);
                Intent intent = new Intent(MainActivity.this, DisplayDialogActivity.class);
//                intent.putExtra("username", "Universal");
//                intent.putExtra("age", 20);
//                intent.putExtra("height", 4.5f);

//                Bundle bundle = new Bundle();
//                bundle.putString("username","Universal");
//                bundle.putInt("age",20);

                startActivity(intent);
            }
        });

    }
}