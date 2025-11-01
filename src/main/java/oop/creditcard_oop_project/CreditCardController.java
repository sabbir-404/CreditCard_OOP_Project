package oop.creditcard_oop_project;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class CreditCardController
{
    @javafx.fxml.FXML
    private DatePicker dateOfExpiry;
    @javafx.fxml.FXML
    private TextField holderName;
    @javafx.fxml.FXML
    private TextField creditLimitFilter;
    @javafx.fxml.FXML
    private TableColumn<CreditCard, String> CardNoTable;
    @javafx.fxml.FXML
    private ComboBox<String> cardType;
    @javafx.fxml.FXML
    private TextField cardNo;
    @javafx.fxml.FXML
    private Label Gatewayname;
    @javafx.fxml.FXML
    private TableColumn<CreditCard, String> CreditLimitTable;
    @javafx.fxml.FXML
    private TableView<CreditCard> cardDisplayTable;
    @javafx.fxml.FXML
    private TableColumn<CreditCard, String> holderNameTable;
    @javafx.fxml.FXML
    private ComboBox<String> GatewaynameFilter;
    @javafx.fxml.FXML
    private ComboBox<String> gatwewayName;
    @javafx.fxml.FXML
    private TableColumn<CreditCard, String> Gatewaynametable;
    @javafx.fxml.FXML
    private TextArea result;
    @javafx.fxml.FXML
    private TextArea notification;

    ArrayList<CreditCard> creditCardList;
    @javafx.fxml.FXML
    private TextField creditLimit;

    @javafx.fxml.FXML
    public void initialize() {
        creditCardList = new ArrayList<>();

        gatwewayName.getItems().setAll("Visa", "MasterCard");
        GatewaynameFilter.getItems().setAll("Visa", "MasterCard");
        cardType.getItems().setAll("silver", "gold", "titanium", "platinum");

        notification.setEditable(false);

        CardNoTable.setCellValueFactory(new PropertyValueFactory<>("cardNo"));
        holderNameTable.setCellValueFactory(new PropertyValueFactory<>("holderName"));
        Gatewaynametable.setCellValueFactory(new PropertyValueFactory<>("gateWayName"));
        CreditLimitTable.setCellValueFactory(new PropertyValueFactory<>("creditLimit"));


        CreditCard c1 = new CreditCard(
                "4123456789012345",
                "John Doe",
                LocalDate.of(2026, 5, 31),
                "Visa",
                50000,
                "gold"
        );

        CreditCard c2 = new CreditCard(
                "5123987654321098",      // starts with 5 → MasterCard
                "Jane Smith",
                LocalDate.of(2027, 8, 15),
                "MasterCard",
                80000,
                "platinum"
        );

        CreditCard c3 = new CreditCard(
                "4123987654322222",
                "Alex Johnson",
                LocalDate.of(2025, 12, 1),
                "Visa",
                30000,
                "silver"
        );

        CreditCard c4 = new CreditCard(
                "5234567890128888",
                "Emily Brown",
                LocalDate.of(2028, 4, 20),
                "MasterCard",
                100000,
                "titanium"
        );

        creditCardList.add(c1);
        creditCardList.add(c2);
        creditCardList.add(c3);
        creditCardList.add(c4);
//        String tmp = c1.getCardNo().substring(0,1);
//        System.out.println("first number"+tmp);
        cardDisplayTable.getItems().addAll(creditCardList);
    }

    @javafx.fxml.FXML
    public void searchAndLoadTable(ActionEvent actionEvent) {
        String gatewayFilter = this.GatewaynameFilter.getValue();
        String creditFilter = this.creditLimitFilter.getText();
        cardDisplayTable.getItems().clear();

        for (CreditCard creditCard : creditCardList) {
            if (creditCard.getGateWayName().equals(gatewayFilter)
                    && creditCard.getCreditLimit() >= Integer.parseInt(creditFilter)) {
                cardDisplayTable.getItems().add(creditCard);
            }
        }
    }

    @javafx.fxml.FXML
    public void validateAddNewCard(ActionEvent actionEvent) {
        String card = this.cardNo.getText();
        String holder = this.holderName.getText();
        LocalDate expiry = this.dateOfExpiry.getValue();
        String gateway = this.gatwewayName.getValue();
        String type = this.cardType.getValue();
        String limitText = this.creditLimit.getText();

        if (card == null || card.isEmpty()
                || holder == null || holder.isEmpty()
                || expiry == null
                || gateway == null
                || type == null
                || limitText == null || limitText.isEmpty()) {
            notification.setText("Fill out all the Fields");
            return;
        }
        if(!expiry.isAfter(LocalDate.now())){
            notification.setText("Expiry date cannot be present!");
            return;
        }

        Integer cardLimit = Integer.parseInt(limitText);

        if (card.length() <16){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Card number is less then 16 digits, you have entered: " + card.length());
            alert.showAndWait();
            return;
        }
        String tmp = card.substring(0,1);
        System.out.println(tmp);
        boolean cardValidity = false;

        if (tmp.equals("4") && gateway.equals("Visa")) {
            cardValidity = true;
        } else if (tmp.equals("5") && gateway.equals("MasterCard")) {
            cardValidity = true;
        } else if (!tmp.equals("4") && !tmp.equals("5")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setContentText("Start card number with 4 for Visa or 5 for MasterCard.");
            alert.showAndWait();
            return;
        }

        if (!cardValidity){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setContentText("Gateway did not match.");
            alert.showAndWait();
        }
        else{
            CreditCard newCard = new CreditCard(card, holder, expiry, gateway, cardLimit, type);
            creditCardList.add(newCard);
            cardDisplayTable.getItems().add(newCard);
            reset();
            notification.setText("Card has been added!");
        }



    }

    private void reset(){
        cardNo.clear();
        dateOfExpiry.setValue(null);
        holderName.clear();
        gatwewayName.setValue(null);
        creditLimit.clear();
        cardType.setValue(null);
    };

    @javafx.fxml.FXML
    public void showAverageCreditLimitfromLoadedTableView(ActionEvent actionEvent) {
        Integer totalCredit = 0;
        int count = 0;
        for(CreditCard c: creditCardList){
            totalCredit += c.getCreditLimit();
            count += 1;
        }
        float avg = (float) totalCredit / count;
        result.setText("Average Credit limit is: " + avg+"\n"+
                "Total credit: " + totalCredit +"\n"+
                "Total count is: " + count);
    }

    @javafx.fxml.FXML
    public void randomCardNo(ActionEvent actionEvent) {
        Random random = new Random();
        String gateWay = this.gatwewayName.getValue();

        if(gateWay == null){
            notification.setText("Select a gateway!");
            return;
        }
        StringBuilder card = new StringBuilder();
        if (gateWay.equals("MasterCard")){
             card = new StringBuilder("5");
        } else if (gateWay.equals("Visa")) {
            card = new StringBuilder("4");
        }

        for(int i = 0; i < 15; i++){
            card.append(random.nextInt(10));
        }
        cardNo.setText(String.valueOf(card));

    }
}