package com.n11.hw1.interfaces;

import com.n11.hw1.enums.Currency;

public interface IPayment {
    void  makePayment(double amount, Currency currency);
}
