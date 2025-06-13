package org.example;
public class PaymentCard {
    protected String cardNumber;
    protected String expiryDate;
    protected String cvc;

    public PaymentCard(String cardNumber, String expiryDate, String cvc) {
        setCardNumber(cardNumber);
        setExpiryDate(expiryDate);
        setCvc(cvc);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.isBlank()) {
            throw new IllegalArgumentException("Not valid argument!");
        }
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        if (expiryDate == null || expiryDate.isBlank()) {
            throw new IllegalArgumentException("Not valid argument!");
        }
        this.expiryDate = expiryDate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        if (cvc == null || cvc.isBlank()) {
            throw new IllegalArgumentException("Not valid argument!");
        }
        this.cvc = cvc;
    }
    public boolean isValid() {
        return cardNumber.length() == 16 && expiryDate.length() == 5 && cvc.length() == 3;
    }

    @Override
    public String toString() {
        return "PaymentCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", cvp='" + cvc + '\'' +
                '}';
    }
}