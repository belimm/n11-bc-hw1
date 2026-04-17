# SOLID Prensipleri ile Ödeme Yöntemi Entegrasyonu

**Author:** [Berk Limoncu](https://www.belim.dev/)
**Bootcamp:** n11 TalentHub Backend Bootcamp

---

## Genel Bakış

Bu proje, SOLID yazılım tasarım prensiplerini takip ederek mevcut sisteme yeni ödeme yöntemlerinin nasıl entegre edileceğini göstermektedir. Uygulama, genişletilebilirlik, sürdürülebilirlik ve temiz kod mimarisi örneklerini içerir.

## Proje Yapısı

```
hw1/src/com/n11/hw1/
├── interfaces/
│   └── IPayment.java          # Payment interface'i
├── paymentmethods/
│   ├── CreditCard.java        # Kredi kartı ödeme implementasyonu
│   ├── PayPal.java            # PayPal ödeme implementasyonu
│   └── ApplePay.java          # Apple Pay ödeme implementasyonu
├── enums/
│   └── Currency.java          # Para birimi enum
└── Main.java                  # Uygulama başlangıç noktası
```

## Uygulanan SOLID Prensipleri

### (S)ingle Responsibility Principle
Her sınıfın tek ve net bir sorumluluğu vardır:
- `IPayment`: Ödeme interface'ini tanımlar
- `CreditCard/PayPal/ApplePay`: Belirli ödeme yöntemlerini yönetir
- `Currency`: Para birimi tiplerini yönetir
- Package organizasyonu sorumlulukları ayırır (interfaces, implementations, enums)

### (O)pen/Closed Principle
Sistem genişlemeye açık, değişikliğe kapalıdır. Yeni ödeme yöntemleri eklemek için mevcut kodu değiştirmeye gerek yoktur:

```java
public class GooglePay implements IPayment {
    @Override
    public void makePayment(double amount, Currency currency) {
        // Implementasyon
    }
}
```

### (L)iskov Substitution Principle
Tüm ödeme implementasyonları `IPayment` arayüzü üzerinden birbirleriyle değiştirilebilir:

```java
IPayment payment = new CreditCard();
payment.makePayment(100, Currency.EUR);

payment = new PayPal();  // Sorunsuz değişim
payment.makePayment(200, Currency.USD);
```

### (I)nterface Segregation Principle
`IPayment` arayüzü sadece gerekli metotları içerir. Hiçbir sınıf kullanmadığı fonksiyonları implement etmek zorunda değildir.

### (D)ependency Inversion Principle
`Main` sınıfı somut implementasyonlara değil, `IPayment` soyutlamasına bağımlıdır.


## Uygulamayı Çalıştırma

```bash
# Derleme
javac -d out src/com/n11/hw1/**/*.java

# Çalıştırma
java -cp out com.n11.hw1.Main
```

## Beklenen Output

```
=== Başarılı Ödeme Senaryoları ===

Paid 100.0EUR with Credit Card
Paid 200.0USD with Apple Pay
Paid 300.0TRY with PayPal

=== Exception Test Senaryoları ===

Test 1: Negatif tutar ile ödeme
Hata: Amount must be positive

Test 2: Sıfır tutar ile ödeme
Hata: Amount must be positive

Test 3: Null currency ile ödeme
Hata: Currency cannot be null

=== Tüm testler tamamlandı ===
```

## Sistemi Genişletme

### Yeni Ödeme Yöntemi Ekleme

1. `paymentmethods/` package'ında yeni bir sınıf oluşturun
2. `IPayment` arayüzünü implement edin
3. Validasyon mantığını ekleyin

```java
package com.n11.hw1.paymentmethods;

import com.n11.hw1.enums.Currency;
import com.n11.hw1.interfaces.IPayment;

public class GooglePay implements IPayment {
    @Override
    public void makePayment(double amount, Currency currency) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (currency == null) {
            throw new IllegalArgumentException("Currency cannot be null");
        }

        System.out.println("Paid " + amount + currency + " with Google Pay");
    }
}
```

### Yeni Para Birimi Ekleme

```java
public enum Currency {
    EUR, USD, CAD, AUD, TRY, GBP
}
```

## Tasarım Kararları

- **Package yapısı**: Interface'ler, implementasyonlar ve enum'ların mantıksal ayrımı
- **Hata yönetimi**: Sınırda girdi validasyonu (**amount > 0, null olmayan currency**)
- **Enum kullanımı**: Tip güvenli ve geçersiz para birimi değerlerini engeller
- **Interface tabanlı tasarım**: Polymorphism ve kolay test edilebilirlik sağlar

## Avantajlar

- Modülerlik ve sorumlulukların ayrımı
- Yeni ödeme yöntemleri **kolayca** eklenebilir
- Bir ödeme yöntemindeki değişiklik diğerlerini **etkilemez**
- Test edilebilir ve sürdürülebilir kod
- **Run time**'da ödeme yöntemi seçiminde esneklik

---

*Bu proje n11 Bootcamp - Ödev 1 kapsamında geliştirilmiştir.*
