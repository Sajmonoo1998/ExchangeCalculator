package com.example.exchangecalculator;

public interface ICurrencies {
    Rates getRates();

    void setRates(Rates rates);

     String getBase();

    void setBase(String base);

     String getDate();

     void setDate(String date);
}
