package com.n11.hw1.paymentmethods;

import com.n11.hw1.enums.Currency;
import com.n11.hw1.interfaces.IPayment;

public class ApplePay implements IPayment {
    @Override
    public void makePayment(double amount, Currency currency) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (currency == null) {
            throw new IllegalArgumentException("Currency cannot be null");
        }

        System.out.println("Paid " + amount + currency + " with Apple Pay");
    }
}
