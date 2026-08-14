package com.example.demowallet;

import java.io.Serializable;

/**
 * Модель "актива" в демо-кошельке.
 * ВНИМАНИЕ: это учебный проект. Баланс — обычное число в памяти приложения,
 * никак не связанное с реальными криптовалютами или блокчейном.
 */
public class Asset implements Serializable {

    private final String symbol;
    private final String name;
    private final String iconLetter;
    private double amount;
    private final double usdRate; // условный демо-курс для отображения "≈ $"

    public Asset(String symbol, String name, String iconLetter, double amount, double usdRate) {
        this.symbol = symbol;
        this.name = name;
        this.iconLetter = iconLetter;
        this.amount = amount;
        this.usdRate = usdRate;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getIconLetter() {
        return iconLetter;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getUsdValue() {
        return amount * usdRate;
    }
}
