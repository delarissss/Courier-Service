import org.example.Payment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourierServiceTest {
    // discount * (1 + VAT) * (1+ commission)
    @Test
    public void testCalculateCostEconomyCard() {
        double basePrice = 500.0;
        String deliveryType = "ECONOMY";
        String paymentMethod = Payment.CARD;
        double expectedCost = basePrice * Payment.ECONOMY * (1 + Payment.VAT) * (1 + Payment.COMMISSION_CARD);
        assertEquals(expectedCost, Payment.calculateCost(deliveryType, basePrice, paymentMethod));
    }

    @Test
    public void testCalculateCostExpressCash() {
        double basePrice = 500.0;
        String deliveryType = "EXPRESS";
        String paymentMethod = Payment.CASH;
        double expectedCost = basePrice * Payment.EXPRESS * (1 + Payment.VAT);
        assertEquals(expectedCost, Payment.calculateCost(deliveryType, basePrice, paymentMethod));
    }

    @Test
    public void testCalculateCostDocumentsSBP() {
        double basePrice = 500.0;
        String deliveryType = "DOCUMENTS";
        String paymentMethod = Payment.SBP;
        double expectedCost = basePrice * Payment.DOCUMENTS * (1 + Payment.VAT) * (1 + Payment.COMMISSION_SBP);
        assertEquals(expectedCost, Payment.calculateCost(deliveryType, basePrice, paymentMethod));
    }

    @Test
    public void testGetCheckEconomyCard() {
        double basePrice = 500.0;
        String deliveryType = "ECONOMY";
        String paymentMethod = Payment.CARD;
        String expectedCheck = "Итоговый чек:\n" +
                "Базовая стоимость: 500.0 руб.\n" +
                "Тип доставки: ECONOMY\n" +
                "Скидка (10.0%): 50.0 руб.\n" +
                "НДС (20.0%): 100.0 руб.\n" +
                "Комиссия за оплату картой (2.0%): 10.0 руб.\n" +
                "Итоговая сумма к оплате: 550.8 руб.\n" +
                "Метод оплаты: Картой\n";
        assertEquals(expectedCheck, Payment.getCheck(deliveryType, basePrice, paymentMethod));
    }

    @Test
    public void testGetCheckExpressCash() {
        double basePrice = 500.0;
        String deliveryType = "EXPRESS";
        String paymentMethod = Payment.CASH;
        String expectedCheck = "Итоговый чек:\n" +
                "Базовая стоимость: 500.0 руб.\n" +
                "Тип доставки: EXPRESS\n" +
                "НДС (20.0%): 100.0 руб.\n" +
                "Итоговая сумма к оплате: 660.0 руб.\n" +
                "Метод оплаты: Наличными\n";
        assertEquals(expectedCheck, Payment.getCheck(deliveryType, basePrice, paymentMethod));
    }

    @Test
    public void testGetCheckDocumentsSBP() {
        double basePrice = 500.0;
        String deliveryType = "DOCUMENTS";
        String paymentMethod = Payment.SBP;
        String expectedCheck = "Итоговый чек:\n" +
                "Базовая стоимость: 500.0 руб.\n" +
                "Тип доставки: DOCUMENTS\n" +
                "НДС (20.0%): 100.0 руб.\n" +
                "Комиссия за оплату по СБП (1.0%): 5.0 руб.\n" +
                "Итоговая сумма к оплате: 727.2 руб.\n" +
                "Метод оплаты: По СБП\n";
        assertEquals(expectedCheck, Payment.getCheck(deliveryType, basePrice, paymentMethod));
    }

    @Test
    public void testAddCardValid() {
        String cardNumber = "2202202634714327";
        String expiryDate = "12/25";
        String cvc = "123";
        assertEquals("Карта добавлена.", Payment.addCard(cardNumber, expiryDate, cvc));
    }

    @Test
    public void testAddCardInvalid() {
        String cardNumber = "1234567890123"; // Некорректный номер карты
        String expiryDate = "12/25";
        String cvc = "123";
        assertEquals("Неверный ввод данных карты.", Payment.addCard(cardNumber, expiryDate, cvc));
    }

}