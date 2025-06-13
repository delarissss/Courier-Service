package org.example;

import java.util.Scanner;

public class Payment {
    // Rонстанты для способов оплаты
    public static final String CARD = "Картой";
    public static final String CASH = "Наличными";
    public static final String SBP = "По СБП";

    // Константы для коэффициентов
    public static final double ECONOMY = 0.9; // 10% скидка за эконом доставку
    public static final double EXPRESS = 1.1; // 10% наценка за экспресс-доставку
    public static final double DOCUMENTS = 1.2; // 20% наценка за доставку документов
    public static final double STANDARD = 1; // стандартный тип доставки
    public static final double COMMISSION_CARD = 0.02;   // 2% комиссия за оплату картой
    public static final double COMMISSION_SBP = 0.01; // 1% комиссия за оплату по CБП
    public static final double VAT = 0.2; // 20% НДС
    // Расчет стоимости доставки
    public static double calculateCost(String deliveryType, double price, String paymentMethod) {
        double commission = 0;
        double discount = switch (deliveryType) {
            case "ECONOMY" -> price * ECONOMY;
            case "EXPRESS" -> price * EXPRESS;
            case "DOCUMENTS" -> price * DOCUMENTS;
            default -> price * STANDARD;
        };
        if (paymentMethod.equals(CARD)) {
            commission = COMMISSION_CARD;
        } else if (paymentMethod.equals(SBP)) {
            commission = COMMISSION_SBP;
        }
        return discount * (1 + VAT) * (1+ commission);
    }
    // Вывода чека
    public static String getCheck(String deliveryType, double price, String paymentMethod) {
        StringBuilder total = new StringBuilder();
        total.append("Итоговый чек:\n");
        total.append("Базовая стоимость: ").append(price).append(" руб.\n");
        total.append("Тип доставки: ").append(deliveryType).append("\n");
        if (deliveryType.equals("ECONOMY")) {
            total.append("Скидка (").append(100 - ECONOMY * 100).append("%): ")
                    .append(price - price * ECONOMY).append(" руб.\n");
        }
        total.append("НДС (").append(VAT * 100).append("%): ")
                .append(price * VAT).append(" руб.\n");
        if (paymentMethod.equals(CARD)) {
            total.append("Комиссия за оплату картой (").append(COMMISSION_CARD * 100).append("%): ")
                    .append(price * COMMISSION_CARD).append(" руб.\n");
        } else if (paymentMethod.equals(SBP)) {
            total.append("Комиссия за оплату по СБП (").append(COMMISSION_SBP * 100).append("%): ")
                    .append(price * COMMISSION_SBP).append(" руб.\n");
        }
        total.append("Итоговая сумма к оплате: ").append(calculateCost(deliveryType, price, paymentMethod)).append(" руб.\n");
        total.append("Метод оплаты: ").append(paymentMethod).append("\n");
        return total.toString();
    }
}