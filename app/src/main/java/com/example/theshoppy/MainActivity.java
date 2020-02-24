package com.example.theshoppy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    // primitive datatype variables
    String ProvinceList[] = {"Ontario", "Quebec", "Alberta" ,"Saskatchewan", "Manitoba",
                                "New Brunswick", "Nova Scotia"};
    String Brands[] = {"HP", "Dell", "Lenovo"};

    String province, date, typeOfComputer, brand, extraSSD, extraPrinter,
            perLaptop, perDesktop;

    int  priceSSD, pricePrinter, radioGroupLaptopPrice, radioGroupDesktopPrice, priceExtra,
            intermediateCost, pricePeri = 0;

    double  finalPrice, totalTax = 0.0;

    // Complex datatype variables has been declared
    DatePickerDialog datePicker;
    EditText edtDate;
    RadioGroup radioGroupTOC, radioGroupLaptop, radioGroupDesktop;
    RadioButton radioDesktop, radioLaptop, radioCoolingMat, radioUSB,
            radioLaptopStand, radioWebcam, radioExternalHardDrive, radioNone;
    ScrollView sViewDesktop, sViewLaptop;
    Spinner spinnerB;
    CheckBox chkBoxSSD, ChkBoxPrinter;
    Button btnSubmit;
    TextView userName, txtReport;
    AutoCompleteTextView autoProvinces;
    LinearLayout spaceDesktop, spaceLaptop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // to set the label on the main activity
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        // an autocomplete logic for the provinces
        autoProvinces = findViewById(R.id.autoListProvinces);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ProvinceList);
        autoProvinces.setAdapter(adapter1);

        province = autoProvinces.getText().toString();

        userName = findViewById(R.id.editUserName);

        edtDate = findViewById(R.id.editDate);
        edtDate.setInputType(InputType.TYPE_NULL);
        date = edtDate.getText().toString();


        // date logic has been implemented
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);
                // date picker dialog has been created
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

        // radio group table of computer variables
        radioGroupTOC = findViewById(R.id.radioGroup);
        radioDesktop = findViewById(R.id.radioDesktop);
        radioLaptop = findViewById(R.id.radioLaptop);

        // scroll view handler variables
        sViewDesktop = findViewById(R.id.scrollViewDesktop);
        sViewLaptop = findViewById(R.id.scrollViewLaptop);

        // linear view handler variables
        spaceDesktop = findViewById(R.id.spacePerDesktop);
        spaceLaptop = findViewById(R.id.spacePerLaptop);

        radioGroupTOC.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioDesktop){

                    // switching between desktop to desktop views
                    sViewDesktop.setVisibility(View.VISIBLE);
                    sViewLaptop.setVisibility(View.GONE);

                    spaceDesktop.setVisibility(View.VISIBLE);
                    spaceLaptop.setVisibility(View.GONE);

                    typeOfComputer = radioDesktop.getText().toString();
            } else if (checkedId == R.id.radioLaptop) {

                    // switching between laptop to desktop views
                    sViewLaptop.setVisibility(View.VISIBLE);
                    sViewDesktop.setVisibility(View.GONE);

                    spaceLaptop.setVisibility(View.VISIBLE);
                    spaceDesktop.setVisibility(View.GONE);

                    typeOfComputer = radioLaptop.getText().toString();
                }
            }
        });


        // a spinner has been initialized
        spinnerB = findViewById(R.id.spinnerBrands);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Brands);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerB.setAdapter(adapter2);

        spinnerB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                brand = spinnerB.getSelectedItem().toString();
            }

            // if there is nothing selected inside the spinner
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // complex data type has been initialized
        chkBoxSSD = findViewById(R.id.chkBoxSSD);
        ChkBoxPrinter = findViewById(R.id.chkBoxPrinter);

        radioGroupDesktop =  findViewById(R.id.radioPerDesktop);
        radioGroupLaptop =  findViewById(R.id.radioPerLaptop);

        radioCoolingMat = findViewById(R.id.radioCoolingMat);
        radioUSB = findViewById(R.id.radioUSB);
        radioLaptopStand =  findViewById(R.id.radioLaptopStand);

        radioWebcam = findViewById(R.id.radioWebcam);
        radioExternalHardDrive = findViewById(R.id.radioExternalHardDrive);
        radioNone = findViewById(R.id.radioNone);

        btnSubmit = findViewById(R.id.buttonSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtReport = findViewById(R.id.textReport);

                // logic for setting SSD and/or Printer
                if (chkBoxSSD.isChecked() && ChkBoxPrinter.isChecked()){
                    extraSSD = chkBoxSSD.getText().toString();
                    extraPrinter = ChkBoxPrinter.getText().toString();
                    priceExtra = 160;
                } else if (chkBoxSSD.isChecked()) {
                    extraSSD = chkBoxSSD.getText().toString();
                    priceExtra = 60;
                    extraPrinter = "";
                } else if (ChkBoxPrinter.isChecked()) {
                    extraPrinter = ChkBoxPrinter.getText().toString();
                    priceExtra = 100;
                    extraSSD = "";
                } else {
                    extraPrinter = "";
                    extraSSD = "";
                }

                // logic for setting laptop peripherals
                if (radioWebcam.isChecked()){
                    perDesktop = radioWebcam.getText().toString();
                    radioGroupDesktopPrice = 95;
                    perLaptop = "";
                } else if (radioExternalHardDrive.isChecked()) {
                    perDesktop = radioExternalHardDrive.getText().toString();
                    radioGroupDesktopPrice = 64;
                    perLaptop = "";
                } else  if (radioNone.isChecked()){
                    perDesktop = radioNone.getText().toString();
                    radioGroupDesktopPrice = 0;
                    perLaptop = "";
                }

                // logic for setting desktop peripherals
                if (radioCoolingMat.isChecked())
                {
                    perLaptop = radioCoolingMat.getText().toString();
                    radioGroupLaptopPrice = 33;
                    perDesktop = "";
                }
                else if (radioUSB.isChecked())
                {
                    perLaptop = radioUSB.getText().toString();
                    radioGroupLaptopPrice = 60;
                    perDesktop = "";
                }
                else if (radioLaptopStand.isChecked())
                {
                    perLaptop = radioLaptopStand.getText().toString();
                    radioGroupLaptopPrice = 139;
                    perDesktop = "";
                }

                // logic for setting prices for desktops/laptops
                if (typeOfComputer.equals("Desktop") && brand.equals("HP")) {
                    radioCoolingMat.setChecked(false);
                    radioUSB.setChecked(false);
                    radioLaptopStand.setChecked(false);
                    pricePeri = 400;
                } else if (typeOfComputer.equals("Desktop") && brand.equals("Dell")) {
                    radioCoolingMat.setChecked(false);
                    radioUSB.setChecked(false);
                    radioLaptopStand.setChecked(false);
                    pricePeri = 475;
                } else if (typeOfComputer.equals("Desktop") && brand.equals("Lenovo")) {
                    radioCoolingMat.setChecked(false);
                    radioUSB.setChecked(false);
                    radioLaptopStand.setChecked(false);
                    pricePeri = 450;
                } else if (typeOfComputer.equals("Laptop") && brand.equals("HP")) {
                    radioWebcam.setChecked(false);
                    radioExternalHardDrive.setChecked(false);
                    radioNone.setChecked(false);
                    pricePeri = 1150;
                } else if (typeOfComputer.equals("Laptop") && brand.equals("Dell")) {
                    radioWebcam.setChecked(false);
                    radioExternalHardDrive.setChecked(false);
                    radioNone.setChecked(false);
                    pricePeri = 1249;
                } else if (typeOfComputer.equals("Laptop") && brand.equals("Lenovo")) {
                    radioWebcam.setChecked(false);
                    radioExternalHardDrive.setChecked(false);
                    radioNone.setChecked(false);
                    pricePeri = 1549;
                }

                // logic for calculation cost of the selected items
                intermediateCost = pricePeri + priceExtra + priceSSD + pricePrinter + radioGroupDesktopPrice + radioGroupLaptopPrice;
                totalTax = intermediateCost * 0.13;
                finalPrice = intermediateCost + totalTax;

                txtReport.setText("Customer: " + userName.getText().toString() + "\n"
                         + "Province: " + autoProvinces.getText().toString() + "\n"
                         + "Date of Purchase: " + edtDate.getText().toString() + "\n"
                         + "Type: " + typeOfComputer + "\n"
                         + "Brand: " + brand + "\n"
                         + "Additional: " + extraPrinter + " " + extraSSD + "\n"
                         + "Added Peripherals: " + perDesktop + " " + perLaptop + "\n"
                         + "intermediate: " + intermediateCost + "\n"
                         + "Cost :" + "$" + finalPrice);
                radioGroupDesktopPrice = 0;
                radioGroupLaptopPrice = 0;
                pricePeri = 0;
                finalPrice = 0.0;
                totalTax = 0.0;
                intermediateCost = 0;
            }
        });
    }
}
