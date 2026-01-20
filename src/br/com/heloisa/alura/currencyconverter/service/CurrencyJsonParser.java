package br.com.heloisa.alura.currencyconverter.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CurrencyJsonParser {

    // Converte a String JSON em JsonObject
    public JsonObject parseJson(String json) {
        JsonElement element = JsonParser.parseString(json);
        return element.getAsJsonObject();
    }

    // Extrai a taxa de conversão da moeda desejada
    public double getRate(JsonObject jsonObject, String currencyCode) {
        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
        return conversionRates.get(currencyCode).getAsDouble();
    }
}