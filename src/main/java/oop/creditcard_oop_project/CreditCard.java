package oop.creditcard_oop_project;

import javafx.scene.control.Alert;

import java.time.LocalDate;

public class CreditCard {
    private String cardNo;
    private String holderName;
    private LocalDate dateOfExpiry;
    private String gateWayName;
    private Integer creditLimit;
    private String cardType;

    public void showCardInfoToAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Card Infomation");
        alert.setHeaderText("Credit Card Details");
        alert.setContentText("cardNo='" + cardNo + '\'' +
                ", holderName='" + holderName + '\'' +
                ", dateOfExpiry=" + dateOfExpiry +
                ", gateWayName='" + gateWayName + '\'' +
                ", creditLimit=" + creditLimit +
                ", cardType='" + cardType + '\'' +
                '}');
    };


    public CreditCard(String cardNo, String holderName, LocalDate dateOfExpiry, String gateWayName, Integer creditLimit, String cardType) {
        this.cardNo = cardNo;
        this.holderName = holderName;
        this.dateOfExpiry = dateOfExpiry;
        this.gateWayName = gateWayName;
        this.creditLimit = creditLimit;
        this.cardType = cardType;
    }


    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public LocalDate getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(LocalDate dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public String getGateWayName() {
        return gateWayName;
    }

    public void setGateWayName(String gateWayName) {
        this.gateWayName = gateWayName;
    }

    public Integer getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Integer creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNo='" + cardNo + '\'' +
                ", holderName='" + holderName + '\'' +
                ", dateOfExpiry=" + dateOfExpiry +
                ", gateWayName='" + gateWayName + '\'' +
                ", creditLimit=" + creditLimit +
                ", cardType='" + cardType + '\'' +
                '}';
    }
}
