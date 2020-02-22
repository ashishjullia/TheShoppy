package com.example.theshoppy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;


public class MainActivity extends AppCompatActivity {

    String ProvinceList[] = {"Ontario", "Quebec", "Alberta" ,"Saskatchewan", "Manitoba",
                                "New Brunswick", "Nova Scotia"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        AutoCompleteTextView autoProvinces = (AutoCompleteTextView)findViewById(R.id.autoListProvinces);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ProvinceList);
        autoProvinces.setAdapter(adapter1);


    }
}
