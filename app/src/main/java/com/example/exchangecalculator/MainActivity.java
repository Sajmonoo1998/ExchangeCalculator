package com.example.exchangecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Console;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText firstCurrencyInput,secondCurrencyInput,thirdCurrencyInput;
    Button butt1,butt2,butt3,butt4,butt5,butt6,butt7,butt8,butt9,butt0,butt00,buttDel,buttClear,buttRefresh;
    ArrayList<Button> buttList = new ArrayList<>();
    String currentNumber = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initParts();
        firstCurrencyInput.requestFocus();
        setButtonsFunctionality(buttList);


    }

    private void initParts() {
        firstCurrencyInput = findViewById(R.id.firstCurrencyInput);
        secondCurrencyInput = findViewById(R.id.secondCurrencyInput);
        thirdCurrencyInput = findViewById(R.id.thirdCurrencyInput);
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
                            currentNumber = "1";
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
                            currentNumber += "0";
                            break;
                        case (R.id.butt00):
                            currentNumber += "00";
                            break;
                        case (R.id.buttDel):
                            currentNumber = currentNumber.substring(0, currentNumber.length()-1);
                            break;
                        case (R.id.buttRefresh):
                            //Todo
                            break;
                        case (R.id.buttClear):
                            currentNumber = "";
                            break;
                    }
                firstCurrencyInput.setText(currentNumber);
                secondCurrencyInput.setText(currentNumber);
                thirdCurrencyInput.setText(currentNumber);
                }
            });
    }

    }


