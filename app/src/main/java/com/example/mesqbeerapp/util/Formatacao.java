package com.example.mesqbeerapp.util;

import java.text.NumberFormat;
import java.util.Locale;

public class Formatacao {
    //Devolve uma string no formato R$#.##9,99
    public static String formataMoeda(double dValor){
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(dValor);
    }

    //Devolve uma string no formato #.##9,99
    public static String formataDouble(double dValor){
        return NumberFormat.getInstance(new Locale("pt", "BR")).format(dValor);
    }

}
