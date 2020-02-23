package com.example.theshoppy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    String ProvinceList[] = {"Ontario", "Quebec", "Alberta" ,"Saskatchewan", "Manitoba",
                                "New Brunswick", "Nova Scotia"};
    DatePickerDialog datePicker;
    EditText edtDate;
    Button btnShowDate;
    TextView txtDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        AutoCompleteTextView autoProvinces = (AutoCompleteTextView)findViewById(R.id.autoListProvinces);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ProvinceList);
        autoProvinces.setAdapter(adapter1);

        edtDate = (EditText)findViewById(R.id.editDate);
        edtDate.setInputType(InputType.TYPE_NULL);

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);
                // date picker dialog
                datePicker = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                edtDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePicker.show();
            }
        });
    }
}
