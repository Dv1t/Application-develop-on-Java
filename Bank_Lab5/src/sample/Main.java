package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static User registration(String name, String surname) {
        return new User(name, surname);
    }

    private User user;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bank");
        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        /*
         * Registration block
         */
        GridPane gRegistration = new GridPane();
        gRegistration.setHgap(10);
        gRegistration.setVgap(10);
        gRegistration.setPadding(new Insets(25, 25, 25, 25));

        Label name = new Label("Name:");
        gRegistration.add(name, 0, 0);
        TextField tfName = new TextField();
        gRegistration.add(tfName, 1, 0);

        Label surname = new Label("Surname:");
        gRegistration.add(surname, 0, 1);
        TextField tfSurname = new TextField();
        gRegistration.add(tfSurname, 1, 1);

        Button bRegistration = new Button("Login/Registration");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(bRegistration);
        gRegistration.add(hbBtn, 1, 4);

        /*
         * Account block
         */
        GridPane gAccount = new GridPane();
        gAccount.setHgap(10);
        gAccount.setVgap(10);
        gAccount.setPadding(new Insets(25, 25, 25, 25));

        Label lBalance = new Label("Balance is unavailable");
        gAccount.add(lBalance, 0, 0);

        Button bAddMoney = new Button("Add money");
        bAddMoney.setDisable(true);
        Button bTakeMoney = new Button("Take money");
        bTakeMoney.setDisable(true);
        TextField tfAddMoney = new TextField();
        tfAddMoney.setDisable(true);
        tfAddMoney.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfAddMoney.setText(newValue.replaceAll("[^\\d]", ""));
            }
            bAddMoney.setDisable(newValue.equals(""));
        });
        TextField tfTakeMoney = new TextField();
        tfTakeMoney.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfTakeMoney.setText(newValue.replaceAll("[^\\d]", ""));
            }
            bTakeMoney.setDisable(newValue.equals(""));
        });
        tfTakeMoney.setDisable(true);
        gAccount.add(tfAddMoney, 0, 1);
        gAccount.add(bAddMoney, 1, 1);
        gAccount.add(tfTakeMoney, 0, 2);
        gAccount.add(bTakeMoney, 1, 2);


        bRegistration.setOnAction(event -> {
            user = registration(tfName.getText(), tfSurname.getText());
            if(Saver.INSTANCE.reestablishUser(user.getName(),user.getSurname())==null) {
                user.addAccount(new Account(1000, 0));
                    Saver.INSTANCE.saveUser(user);

            }
            else {
                user = Saver.INSTANCE.reestablishUser(user.getName(),user.getSurname());
            }
            lBalance.setText("Balance: " + user.accounts.get(0).getMoney());
            bRegistration.setDisable(true);
            tfName.setDisable(true);
            tfSurname.setDisable(true);
            tfAddMoney.setDisable(false);
            tfTakeMoney.setDisable(false);
        });

        bAddMoney.setOnAction(event -> {
            user.accounts.get(0).addMoney(Integer.parseInt(tfAddMoney.getText()));
            Saver.INSTANCE.saveUser(user);

            lBalance.setText("Balance: " + user.accounts.get(0).getMoney());
            tfAddMoney.setText("");
        });

        bTakeMoney.setOnAction(event -> {
            if (Integer.parseInt(tfTakeMoney.getText()) > user.accounts.get(0).getMoney()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Error:");
                alert.setContentText("You don't have money for it!");
                alert.showAndWait();
            } else {
                user.accounts.get(0).takeMoney(Integer.parseInt(tfTakeMoney.getText()));
                Saver.INSTANCE.saveUser(user);
                lBalance.setText("Balance: " + user.accounts.get(0).getMoney());
            }
            tfTakeMoney.setText("");
        });

        root.getChildren().addAll(
                gRegistration,
                gAccount
        );
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
