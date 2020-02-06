package com.example.exchangecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView currencyRelation,firstCurrencyMark,secondCurrencyMark,thirdCurrencyMark;
    EditText firstCurrencyInput,secondCurrencyInput,thirdCurrencyInput;
    Button butt1,butt2,butt3,butt4,butt5,butt6,butt7,butt8,butt9,butt0,butt00,buttDot,buttDel,buttClear,buttRefresh;
    List<Button> buttList = new ArrayList<>();
    List<EditText> editTextList = new ArrayList<>();
    String currentNumber = "";
    Currencies currencies;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    double firstCurrencyValue = 0;
    double secondCurrencyValue = 0;
    double thirdCurrencyValue = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setFunctionality(buttList, editTextList);
        requestCurrencies("DKK");
        firstCurrencyInput.requestFocus();
    }
/*
    private void requestCurrencies(String mark) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.exchangeratesapi.io/latest?base="+mark;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Currencies currencies = gson.fromJson(response, Currencies.class);
                        updateCurrencies(currencies);
                        test(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Rest response", error.toString());
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

*/
private void requestCurrencies(String mark) {
    RequestQueue queue = Volley.newRequestQueue(this);
    String url ="https://api.exchangeratesapi.io/latest?base="+mark;
    // Request a string response from the provided URL.
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    Currencies currencies = gson.fromJson(response, Currencies.class);
                    updateCurrencies(currencies);
                    test(response);
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("Rest response", error.toString());
        }
    });
    // Add the request to the RequestQueue.
    queue.add(stringRequest);
}
    private void updateCurrencies(Currencies curr){
        this.currencies = curr;
    }

    private void init() {
        firstCurrencyMark = findViewById(R.id.firstCurrencyMark);
        secondCurrencyMark = findViewById(R.id.secondCurrencyMark);
        thirdCurrencyMark = findViewById(R.id.thirdCurrencyMark);
        currencyRelation = findViewById(R.id.currencyRelation);
        //
        firstCurrencyInput = findViewById(R.id.firstCurrencyInput);
        secondCurrencyInput = findViewById(R.id.secondCurrencyInput);
        thirdCurrencyInput = findViewById(R.id.thirdCurrencyInput);
        editTextList = Arrays.asList(firstCurrencyInput,secondCurrencyInput,thirdCurrencyInput);
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
        buttList = Arrays.asList(butt0,butt00,butt1,butt2,butt3,butt4,butt5,butt6,butt7,butt8,butt9,buttClear,buttDel,buttDot,buttRefresh);
    }

    private void test (final String s) {
        findViewById(R.id.buttRefresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson g = new Gson();
                Currencies c = g.fromJson(s, Currencies.class);
                String firstName = null;
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONObject r = jsonObject.getJSONObject("Rates");
                     firstName = (String) r.get("EUR");


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Log.d("DKKK HERE - - ",c.getBase());
                Log.v("KURA",c.getRates().getCHF().toString());
            }
        });
    }
    /*
    public void testYourJSON(String genreJson){

        JsonParser parser=new JsonParser();  //parser used to parse String to Correct Json format.

        JsonElement obj_ComplexData = (JsonElement) parser.parse(genreJson); // Now Your String Converted to a JSONObject Type.

        //person tag Array Data is fetched and Stored into a JSONArray Object.
        JSONArray obj_arrayPersonData = (JSONArray) parser.parse(obj_ComplexData.get("person").toString());
        J
        for (Object person : obj_arrayPersonData ) { //Iterate through all Person Array.
            System.out.println(person.get("name"));
            System.out.println(person.get("city"));
        }

        //Select "updated" Tag Json Data.
        JSONObject obj_Updated = (JSONObject) parser.parse(obj_ComplexData.get("updated").toString());

        System.out.println(obj_Updated.get("time")); //display time tag.
        System.out.println(obj_Updated.get("date")); //display date tag.


        System.out.println(obj_Updated.get("error")); //display Your Error.

    }

     */
    private void setFunctionality(List<Button> ab, List<EditText> atv) {
        for(Button b: ab){
            setOperation(b);
        }
        for(EditText tv: atv){
            setFocusListeners(tv);
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
    private void setFocusListeners(final EditText et){
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                switch (et.getId()){
                    case(R.id.firstCurrencyInput):
                        if(hasFocus && firstCurrencyInput.getText().length() != 0)
                            currentNumber = firstCurrencyInput.getText().toString();
                        requestCurrencies(firstCurrencyMark.getText().toString());
                        break;
                    case(R.id.secondCurrencyInput):
                        if(hasFocus && secondCurrencyInput.getText().length() != 0)
                            currentNumber = secondCurrencyInput.getText().toString();
                        requestCurrencies(secondCurrencyMark.getText().toString());

                        break;
                    case(R.id.thirdCurrencyInput):
                        if(hasFocus && thirdCurrencyInput.getText().length() != 0)
                            currentNumber = thirdCurrencyInput.getText().toString();
                        requestCurrencies(thirdCurrencyMark.getText().toString());
                        break;
                }
                et.setShowSoftInputOnFocus(false);

            }
        });
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
                        if (currentNumber.length() > 0 && !currentNumber.contains(".")) currentNumber += ".";
                        break;
                    case (R.id.buttDel):
                        if (currentNumber.length() > 0) currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
                        break;
                    case (R.id.buttRefresh):
                        
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
                //secondCurrencyValue = 0.1337;
                secondCurrencyValue = currencies.getRates().getEUR();
                output = calculateAndFormat(currentNumber,secondCurrencyValue);
                secondCurrencyInput.setText(output);
                //Calculating third currency while we insert numbers for the first one
                thirdCurrencyValue = currencies.getRates().getPLN();
                output2 = calculateAndFormat(currentNumber,thirdCurrencyValue);
                thirdCurrencyInput.setText(output2);
                break;

            case (R.id.secondCurrencyInput):
                secondCurrencyInput.setText(currentNumber);
                firstCurrencyValue = currencies.getRates().getDKK();;
                //Calculating first currency while we insert numbers for the second one
                output = calculateAndFormat(currentNumber,firstCurrencyValue);
                firstCurrencyInput.setText(output);
                thirdCurrencyValue = currencies.getRates().getPLN();
                //Calculating third currency while we insert numbers for the second one
                output2 = calculateAndFormat(currentNumber,thirdCurrencyValue);
                thirdCurrencyInput.setText(output2);
                break;
            case (R.id.thirdCurrencyInput):
                thirdCurrencyInput.setText(currentNumber);
                firstCurrencyValue = currencies.getRates().getDKK();;
                //Calculating third currency while we insert numbers for the first one
                output = calculateAndFormat(currentNumber,firstCurrencyValue);
                firstCurrencyInput.setText(output);
                secondCurrencyValue = currencies.getRates().getEUR();;
                //Calculating third currency while we insert numbers for the second one
                output2 = calculateAndFormat(currentNumber,secondCurrencyValue);
                secondCurrencyInput.setText(output2);
                break;
        }
    }
    private String calculateAndFormat(String currNumber, double currencyValue){
        double output = Double.parseDouble(currNumber) * currencyValue;
        return df2.format(output);
    }

}


