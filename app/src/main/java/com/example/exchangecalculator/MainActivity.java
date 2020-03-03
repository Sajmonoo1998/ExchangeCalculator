package com.example.exchangecalculator;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView currencyRelation,firstCurrencyMark,secondCurrencyMark,thirdCurrencyMark;
    EditText firstCurrencyInput,secondCurrencyInput,thirdCurrencyInput;
    Button butt1,butt2,butt3,butt4,butt5,butt6,butt7,butt8,butt9,butt0,butt00,buttDot,buttDel,buttClear,buttRefresh;
    List<Button> buttList = new ArrayList<>();
    List<EditText> editTextList = new ArrayList<>();
    String base = "DKK";
    String currentNumber = "";
    ICurrencies currencies = new Currencies();
    IRates rates = new Rates();
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        requestCurrencies(base);
        setFunctionality(buttList, editTextList);
        firstCurrencyInput.requestFocus();
    }

    private void requestCurrencies(String mark) {
    RequestQueue queue = Volley.newRequestQueue(this);
    String url ="https://api.exchangeratesapi.io/latest?base="+mark;
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    ICurrencies currencies = gson.fromJson(response, Currencies.class);
                    updateCurrencies(currencies);
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
                    Log.v("Error message: ", error.toString());
        }
    });
    queue.add(stringRequest);
}
    private void updateCurrencies(ICurrencies curr){
        this.currencies = curr;
        this.rates = curr.getRates();
        base = curr.getBase();
        updateCurrencyRelation();
    }

    private void init() {
        firstCurrencyMark = findViewById(R.id.firstCurrencyMark);
        secondCurrencyMark = findViewById(R.id.secondCurrencyMark);
        thirdCurrencyMark = findViewById(R.id.thirdCurrencyMark);
        currencyRelation = findViewById(R.id.currencyRelation);
        firstCurrencyInput = findViewById(R.id.firstCurrencyInput);
        secondCurrencyInput = findViewById(R.id.secondCurrencyInput);
        thirdCurrencyInput = findViewById(R.id.thirdCurrencyInput);
        editTextList = Arrays.asList(firstCurrencyInput,secondCurrencyInput,thirdCurrencyInput);
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
        buttList = Arrays.asList(butt0,butt00,butt1,butt2,butt3,butt4,butt5,butt6,butt7,
                                    butt8,butt9,buttClear,buttDel,buttDot,buttRefresh);
    }

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
                        if(hasFocus && et.getText().length() != 0)
                            currentNumber = firstCurrencyInput.getText().toString();
                        requestCurrencies(firstCurrencyMark.getText().toString());
                        break;
                    case(R.id.secondCurrencyInput):
                        if(hasFocus && et.getText().length() != 0)
                            currentNumber = secondCurrencyInput.getText().toString();
                        requestCurrencies(secondCurrencyMark.getText().toString());
                        break;
                    case(R.id.thirdCurrencyInput):
                        if(hasFocus && et.getText().length() != 0)
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
                        requestCurrencies(base);
                        showToast("Data refreshed: "+currencies.getDate());
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

    private void showToast(String msg) {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void updateCurrencyRelation() {
        switch (base) {
            case "DKK": {
                String value = calculateAndFormat(rates.getPLN().toString(), 1.0);
                currencyRelation.setText(String.format("1DKK = %s", value + "PLN"));
                break;
            }
            case ("EUR"): {
                String value = calculateAndFormat(rates.getPLN().toString(), 1.0);
                currencyRelation.setText(String.format("1EUR = %s", value + "PLN"));
                break;
            }
            case ("PLN"): {
                String value = calculateAndFormat(rates.getDKK().toString(), 1.0);
                currencyRelation.setText(String.format("1PLN = %s", value + "DKK"));
                break;
            }
        }
    }

    private void clearInputs() {
        firstCurrencyInput.getText().clear();
        secondCurrencyInput.getText().clear();
        thirdCurrencyInput.getText().clear();
    }


    private void calculateCurrencies(String currentNumber) {
        EditText focused = findFocus();
        double firstCurrencyValue,secondCurrencyValue,thirdCurrencyValue;
        switch (focused.getId()) {

            case (R.id.firstCurrencyInput):
                secondCurrencyValue = rates.getEUR();
                thirdCurrencyValue = rates.getPLN();
                calculateCurrency(firstCurrencyInput,secondCurrencyInput,thirdCurrencyInput,
                        secondCurrencyValue,thirdCurrencyValue,currentNumber);
                break;
            case (R.id.secondCurrencyInput):
                firstCurrencyValue = rates.getDKK();
                thirdCurrencyValue = rates.getPLN();
                calculateCurrency(secondCurrencyInput,firstCurrencyInput,thirdCurrencyInput,
                                    firstCurrencyValue,thirdCurrencyValue,currentNumber);
                break;
            case (R.id.thirdCurrencyInput):
                firstCurrencyValue = rates.getDKK();
                secondCurrencyValue = rates.getEUR();
                calculateCurrency(thirdCurrencyInput,firstCurrencyInput,secondCurrencyInput,
                                    firstCurrencyValue,secondCurrencyValue,currentNumber);
                break;
        }
    }

    private void calculateCurrency(EditText baseInput,
                                   EditText secondCurrencyInput,
                                   EditText thirdCurrencyInput,
                                   double secondCurrencyValue,
                                   double thirdCurrencyValue,
                                   String currentNumber){
        String output, output2;
        baseInput.setText(currentNumber);
        output = calculateAndFormat(currentNumber,secondCurrencyValue);
        secondCurrencyInput.setText(output);
        output2 = calculateAndFormat(currentNumber,thirdCurrencyValue);
        thirdCurrencyInput.setText(output2);


    }

    private String calculateAndFormat(String currNumber, double currencyValue){
        double output = Double.parseDouble(currNumber) * currencyValue;
        return df2.format(output);
    }

}


