package com.n11.hw1;

import com.n11.hw1.enums.Currency;
import com.n11.hw1.interfaces.IPayment;
import com.n11.hw1.paymentmethods.ApplePay;
import com.n11.hw1.paymentmethods.CreditCard;
import com.n11.hw1.paymentmethods.PayPal;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Başarılı Ödeme Senaryoları ===\n");

        // Başarılı ödemeler
        IPayment payment = new CreditCard();
        payment.makePayment(100, Currency.EUR);

        payment = new ApplePay();
        payment.makePayment(200, Currency.USD);

        payment = new PayPal();
        payment.makePayment(300, Currency.TRY);

        // Exception Test Senaryoları
        System.out.println("\n=== Exception Test Senaryoları ===\n");

        // Test Case 1: Negatif amount
        System.out.println("Test 1: Negatif tutar ile ödeme");
        try {
            payment.makePayment(-400, Currency.TRY);
        } catch (IllegalArgumentException e) {
            System.err.println("Hata: " + e.getMessage());
        }

        // Test Case 2: Sıfır amount
        System.out.println("\nTest 2: Sıfır tutar ile ödeme");
        try {
            payment.makePayment(0, Currency.EUR);
        } catch (IllegalArgumentException e) {
            System.err.println("Hata: " + e.getMessage());
        }

        // Test Case 3: Null currency
        System.out.println("\nTest 3: Null currency ile ödeme");
        try {
            payment.makePayment(500, null);
        } catch (IllegalArgumentException e) {
            System.err.println("Hata: " + e.getMessage());
        }

        System.out.println("\n=== Tüm testler tamamlandı ===");
    }
}