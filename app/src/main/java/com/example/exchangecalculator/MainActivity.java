package com.example.exchangecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Console;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView currencyRelation,firstCurrencyMark,secondCurrencyMark,thirdCurrencyMark;
    EditText firstCurrencyInput,secondCurrencyInput,thirdCurrencyInput;
    Button butt1,butt2,butt3,butt4,butt5,butt6,butt7,butt8,butt9,butt0,butt00,buttDot,buttDel,buttClear,buttRefresh;
    ArrayList<Button> buttList = new ArrayList<>();
    String currentNumber = "";
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    double firstCurrencyValue = 0;
    double secondCurrencyValue = 0;
    double thirdCurrencyValue = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initParts();
        secondCurrencyInput.requestFocus();
        setButtonsFunctionality(buttList);


    }

    private void initParts() {
        firstCurrencyMark = findViewById(R.id.firstCurrencyMark);
        secondCurrencyMark = findViewById(R.id.secondCurrencyMark);
        thirdCurrencyMark = findViewById(R.id.thirdCurrencyMark);
        currencyRelation = findViewById(R.id.currencyRelation);
        //
        firstCurrencyInput = findViewById(R.id.firstCurrencyInput);
        secondCurrencyInput = findViewById(R.id.secondCurrencyInput);
        thirdCurrencyInput = findViewById(R.id.thirdCurrencyInput);
        firstCurrencyInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus && firstCurrencyInput.getText().length() != 0)
                    currentNumber = firstCurrencyInput.getText().toString();
            }
        });
        secondCurrencyInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus && firstCurrencyInput.getText().length() != 0)
                    currentNumber = secondCurrencyInput.getText().toString();
            }
        });
        thirdCurrencyInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus && firstCurrencyInput.getText().length() != 0)
                    currentNumber = thirdCurrencyInput.getText().toString();
            }
        });
        //
        butt1 = findViewById(R.id.butt1);
        butt2 = findViewById(R.id.butt2);
        butt3 = findViewById(R.id.butt3);
        butt4 = findViewById(R.id.butt4);
        butt5 = findViewById(R.id.butt5);
        butt6 = findViewById(R.id.butto6);
        butt7 = findViewById(R.id.butt7);
        butt8 = findViewById(R.id.butt8);
        butt9 = findViewById(R.id.butt9);
        butt0 = findViewById(R.id.butt0);
        butt00 = findViewById(R.id.butt00);
        buttDel = findViewById(R.id.buttDel);
        buttRefresh = findViewById(R.id.buttRefresh);
        buttClear = findViewById(R.id.buttClear);
        buttDot = findViewById(R.id.buttDot);
        buttList.add(butt1);
        buttList.add(butt2);
        buttList.add(butt3);
        buttList.add(butt4);
        buttList.add(butt5);
        buttList.add(butt6);
        buttList.add(butt7);
        buttList.add(butt8);
        buttList.add(butt9);
        buttList.add(butt0);
        buttList.add(butt00);
        buttList.add(buttDel);
        buttList.add(buttClear);
        buttList.add(buttRefresh);
        buttList.add(buttDot);
    }
    private void setButtonsFunctionality(ArrayList<Button> ab) {
        for(Button b: ab){
            setOperation(b);
        }
    }

    private EditText findFocus() {
        if(firstCurrencyInput.hasFocus())
        return firstCurrencyInput;
        else if(secondCurrencyInput.hasFocus())
            return secondCurrencyInput;
        else
            return thirdCurrencyInput;
    }
    private void setOperation(final Button b) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (b.getId()) {
                    case (R.id.butt1):
                        currentNumber += "1";
                        break;
                    case (R.id.butt2):
                        currentNumber += "2";
                        break;
                    case (R.id.butt3):
                        currentNumber += "3";
                        break;
                    case (R.id.butt4):
                        currentNumber += "4";
                        break;
                    case (R.id.butt5):
                        currentNumber += "5";
                        break;
                    case (R.id.butto6):
                        currentNumber += "6";
                        break;
                    case (R.id.butt7):
                        currentNumber += "7";
                        break;
                    case (R.id.butt8):
                        currentNumber += "8";
                        break;
                    case (R.id.butt9):
                        currentNumber += "9";
                        break;
                    case (R.id.butt0):
                        if (currentNumber.length() > 0) currentNumber += "0";
                        break;
                    case (R.id.butt00):
                        if (currentNumber.length() > 0) currentNumber += "00";
                        break;
                    case (R.id.buttDot):
                        if (currentNumber.length() > 0) currentNumber += ".";
                        break;
                    case (R.id.buttDel):
                        if (currentNumber.length() > 0) currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
                        break;
                    case (R.id.buttRefresh):
                        //Todo
                        break;
                    case (R.id.buttClear):
                        currentNumber = "";
                        break;
                }
                if(currentNumber.length()>0)
                    calculateCurrencies(currentNumber);
                else {
                    clearInputs();
                }
            }});}

    private void clearInputs() {
        firstCurrencyInput.getText().clear();
        secondCurrencyInput.getText().clear();
        thirdCurrencyInput.getText().clear();
    }


    private void calculateCurrencies(String currentNumber) {
        EditText focused = findFocus();
        String output, output2;
    switch (focused.getId()) {
        case (R.id.firstCurrencyInput):
        firstCurrencyInput.setText(currentNumber);
        //Calculating second currency while we insert numbers for the first one
        secondCurrencyValue = 0.1337;
        output = calculateAndFormat(currentNumber,secondCurrencyValue);
        secondCurrencyInput.setText(output);
        //Calculating third currency while we insert numbers for the first one
        thirdCurrencyValue = 0.12;
        output2 = calculateAndFormat(currentNumber,thirdCurrencyValue);
        thirdCurrencyInput.setText(output2);
        break;

        case (R.id.secondCurrencyInput):
            secondCurrencyInput.setText(currentNumber);
            firstCurrencyValue = 7.47;
            //Calculating first currency while we insert numbers for the second one
            output = calculateAndFormat(currentNumber,firstCurrencyValue);
            firstCurrencyInput.setText(output);
            thirdCurrencyValue = 1.11;
            ////Calculating third currency while we insert numbers for the second one
            output2 = calculateAndFormat(currentNumber,thirdCurrencyValue);
            thirdCurrencyInput.setText(output2);
        break;
        case (R.id.thirdCurrencyInput):
            thirdCurrencyInput.setText(currentNumber);
            firstCurrencyValue = 8.33;
            //Calculating first currency while we insert numbers for the third one
            output = calculateAndFormat(currentNumber,firstCurrencyValue);
            firstCurrencyInput.setText(output);
            secondCurrencyValue = 0.897;
            //Calculating second currency while we insert numbers for the third one
            output2 = calculateAndFormat(currentNumber,secondCurrencyValue);
            thirdCurrencyInput.setText(output2);
            break;
    }
    }
    private String calculateAndFormat(String currNumber, double currencyValue){
        double output = Double.parseDouble(currNumber) * currencyValue;
        return df2.format(output);
    }

}


