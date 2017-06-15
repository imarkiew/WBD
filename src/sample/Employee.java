package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

import static java.lang.System.exit;

/**
 * Created by KonradSob on 2017-01-11.
 */
public class Employee {
    public javafx.scene.control.Label infoLabel;
    public javafx.scene.control.Label LabelUnderFields;
    public javafx.scene.control.TextArea TextLeft;
    public javafx.scene.control.Button accept;

    private Logic sql = new Logic();
    private String bufforLeft;
    public javafx.scene.control.Label Label1;
    public javafx.scene.control.Label Label2;
    public javafx.scene.control.Label Label3;
    public javafx.scene.control.Label Label4;
    public javafx.scene.control.Label Label5;
    public javafx.scene.control.Label Label6;
    public javafx.scene.control.Label Label7;
    public javafx.scene.control.Label Label8;
    public javafx.scene.control.Label Label9;
    public javafx.scene.control.Label Label10;
    public javafx.scene.control.Label Label11;
    public javafx.scene.control.Label Label12;

    public TextField Field1;
    public TextField Field2;
    public TextField Field3;
    public TextField Field4;
    public TextField Field5;
    public TextField Field6;
    public TextField Field7;
    public TextField Field8;
    public TextField Field9;
    public TextField Field10;
    public TextField Field11;
    public TextField Field12;

    public String clientID;
    public String PESEL;
    public String forename;
    public String surname;
    public String streetAddress;
    public String homeNumber;
    public String postalCode;
    public String placeOfResidence;
    public String NIP;
    public String login;
    public String password;
    public String clientServiceID;
    public String descriptionOfAgreement;
    public String monthlyCostOfAnAgreement;
    public String dateOfEnterInToContract;
    public String numberOfTelephoneID;
    public String agreementID;
    public String complaintID;
    public String complaintNumber;
    public String descriptionOfComplaint;
    public String dateOfComplaint;
    public String statusOfComplaint;
    public String promotionID;

    ArrayList<ArrayList<String>> List = new ArrayList<>();
    private static String Login;
    private Stage stage = new Stage();
    private boolean addClientFlag = false;
    private boolean editComplaintsFlag = false;
    private boolean addPromotionFlag = false;
    private boolean editAgreementFlag = false;
    private boolean addAgreementFlag = false;
    private boolean editClientFlag = false;

    /**
     * czyścimy flagi wyborów
     */
    public void clearFlags() {
        addClientFlag = false;
        editComplaintsFlag = false;
        addPromotionFlag = false;
        editAgreementFlag = false;
        addAgreementFlag = false;
        editClientFlag = false;
    }

    /**
     * Przeprowadze walidacje danych oraz pobiera wpisywane texty z textfieldnów w promocjach
     *
     * @return
     */
    public boolean getAllTextsPromotions() {
        boolean result = true;
        clientID = Field1.getText();
        if (!clientID.isEmpty())
            if (sql.isInt(clientID)) {
            } else return false;
        else return false;

        promotionID = Field2.getText();
        if (!promotionID.isEmpty())
            if (sql.isInt(promotionID)) {
            } else return false;
        else return false;

        return result;
    }

    /**
     * Przeprowadze walidacje danych oraz pobiera wpisywane texty z textfieldnów w reklamacji
     *
     * @return
     */
    public boolean getAllTextsComplaint() {
        boolean result = true;

        complaintID = Field1.getText();
        if (!complaintID.isEmpty())
            if (sql.isInt(complaintID)) {
            } else return false;
        else return false;


        complaintNumber = Field2.getText();

        descriptionOfComplaint = Field3.getText();

        dateOfComplaint = Field4.getText();
        if (sql.isDate(dateOfComplaint, "dd-MM-yyyy") || dateOfComplaint.isEmpty()) {
        } else return false;

        statusOfComplaint = Field5.getText();
        if (sql.isName(statusOfComplaint) || statusOfComplaint.isEmpty()) {
        } else return false;

        clientID = Field6.getText();
        if (sql.isInt(clientID) || clientID.isEmpty()) {
        } else return false;

        agreementID = Field7.getText();
        if (sql.isInt(agreementID) || agreementID.isEmpty()) {
        } else return false;

        return result;
    }

    /**
     * Przeprowadze walidacje danych oraz pobiera wpisywane texty z textfieldnów w umowie
     *
     * @param add czy dodajemy czy edytujemy
     * @return
     */
    public boolean getAllTextsAgreement(boolean add) {
        boolean result = true;

        if (!add) {
            agreementID = Field1.getText();
            if (!agreementID.isEmpty())
                if (sql.isInt(agreementID)) {
                } else return false;
            else return false;
        }

        descriptionOfAgreement = Field2.getText();


        monthlyCostOfAnAgreement = Field3.getText();
        if (sql.isInt(monthlyCostOfAnAgreement) || monthlyCostOfAnAgreement.isEmpty()) {
        } else return false;

        dateOfEnterInToContract = Field4.getText();
        if (sql.isDate(dateOfEnterInToContract, "dd-MM-yyyy") || dateOfEnterInToContract.isEmpty()) {
        } else return false;

        clientID = Field5.getText();
        if (sql.isInt(clientID) || clientID.isEmpty()) {
        } else return false;

        numberOfTelephoneID = Field6.getText();
        if (sql.isInt(numberOfTelephoneID) || numberOfTelephoneID.isEmpty()) {
        } else return false;


        return result;
    }

    /**
     * Przeprowadze walidacje danych oraz pobiera wpisywane texty z textfieldnów w kliencie
     *
     * @param add czy dodajemy czy edytujemy
     * @return
     */
    public boolean getAllTextsClient(boolean add) {
        boolean result = true;

        if (!add) {
            clientID = Field1.getText();
            if (!clientID.isEmpty())
                if (sql.isInt(clientID)) {
                } else return false;
            else return false;
        }

        PESEL = Field2.getText();
        if (sql.isInt(PESEL) || PESEL.isEmpty()) {
        } else return false;

        forename = Field3.getText();
        if (sql.isName(forename) || forename.isEmpty()) {
        } else return false;

        surname = Field4.getText();
        if (sql.isName(surname) || surname.isEmpty()) {
        } else return false;

        streetAddress = Field5.getText();
        homeNumber = Field6.getText();
        postalCode = Field7.getText();
        placeOfResidence = Field8.getText();
        if (sql.isName(placeOfResidence) || placeOfResidence.isEmpty()) {
        } else return false;

        NIP = Field9.getText();
        if (sql.isInt(NIP) || NIP.isEmpty()) {
        } else return false;

        login = Field10.getText();
        password = Field11.getText();
        clientServiceID = Field12.getText();
        if (sql.isInt(clientServiceID) || clientServiceID.isEmpty()) {
        } else return false;

        return result;
    }

    /**
     * Wyświetla
     *
     * @param choice - decyduje jakie Labele i TextFieldy wyświetlić
     * @param status - czy widoczne czy nie
     */
    public void setTextLabelsAndFields(String choice, boolean status) {
        Field1.clear();
        Field2.clear();
        Field3.clear();
        Field4.clear();
        Field4.setPromptText("");
        Field5.clear();
        Field6.clear();
        Field7.clear();
        Field8.clear();
        Field9.clear();
        Field10.clear();
        Field11.clear();
        Field12.clear();


        if (choice.equals("Klient")) {
            LabelUnderFields.setVisible(status);

            Label1.setVisible(status);
            Label1.setText("ID Klienta* :");
            Label2.setVisible(status);
            Label2.setText("PESEL :");
            Label3.setVisible(status);
            Label3.setText("Imie*  :");
            Label4.setVisible(status);
            Label4.setText("Nazwisko*  :");
            Label5.setVisible(status);
            Label5.setText("Ulica* :");
            Label6.setVisible(status);
            Label6.setText("Nr. Mieszkania* :");
            Label7.setVisible(status);
            Label7.setText("Kod Pocztowy* :");
            Label8.setVisible(status);
            Label8.setText("Miejscowość* :");
            Label9.setVisible(status);
            Label9.setText("NIP :");
            Label10.setVisible(status);
            Label10.setText("Login* :");
            Label11.setVisible(status);
            Label11.setText("Haslo* :");
            Label12.setVisible(status);
            Label12.setText("ID Biura* :");

            Field1.setDisable(!status);
            Field1.setVisible(status);
            Field2.setVisible(status);
            Field3.setVisible(status);
            Field4.setVisible(status);
            Field5.setVisible(status);
            Field6.setVisible(status);
            Field7.setVisible(status);
            Field8.setVisible(status);
            Field9.setVisible(status);
            Field10.setVisible(status);
            Field11.setVisible(status);
            Field12.setVisible(status);
        } else if (choice.equals("Complaints")) {
            LabelUnderFields.setVisible(status);

            Label1.setVisible(status);
            Label1.setText("ID Reklamacji* :");
            Label2.setVisible(status);
            Label2.setText("Nr Reklamacji :");
            Label3.setVisible(status);
            Label3.setText("Opis Reklamacji :");
            Label4.setVisible(status);
            Label4.setText("Data Reklamacji :");
            Label5.setVisible(status);
            Label5.setText("Status :");
            Label6.setVisible(status);
            Label6.setText("ID Klienta :");
            Label7.setVisible(status);
            Label7.setText("ID Umowy :");
            Label8.setVisible(false);
            Label9.setVisible(false);
            Label10.setVisible(false);
            Label11.setVisible(false);
            Label12.setVisible(false);

            Field1.clear();
            Field1.setDisable(!status);
            Field1.setVisible(status);
            Field2.setVisible(status);
            Field3.setVisible(status);
            Field4.setVisible(status);
            Field4.setPromptText("dd-MM-yyyy");
            Field5.setVisible(status);
            Field6.setVisible(status);
            Field7.setVisible(status);
            Field8.setVisible(false);
            Field9.setVisible(false);
            Field10.setVisible(false);
            Field11.setVisible(false);
            Field12.setVisible(false);
        } else if (choice.equals("Umowy")) {
            LabelUnderFields.setVisible(status);

            Label1.setVisible(status);
            Label1.setText("ID Umowy* :");
            Label2.setVisible(status);
            Label2.setText("Opis Umowy* :");
            Label3.setVisible(status);
            Label3.setText("Koszt Umowy* :");
            Label4.setVisible(status);
            Label4.setText("Data Zawarcia* :");
            Label5.setVisible(status);
            Label5.setText("ID Klienta* :");
            Label6.setVisible(status);
            Label6.setText("ID Nr Telefonu* :");
            Label7.setVisible(false);
            Label8.setVisible(false);
            Label9.setVisible(false);
            Label10.setVisible(false);
            Label11.setVisible(false);
            Label12.setVisible(false);

            Field1.clear();
            Field1.setDisable(!status);
            Field1.setVisible(status);
            Field2.setVisible(status);
            Field3.setVisible(status);
            Field4.setVisible(status);
            Field4.setPromptText("dd-MM-yyyy");
            Field5.setVisible(status);
            Field6.setVisible(status);
            Field7.setVisible(false);
            Field8.setVisible(false);
            Field9.setVisible(false);
            Field10.setVisible(false);
            Field11.setVisible(false);
            Field12.setVisible(false);
        } else if (choice.equals("Promotion")) {
            Label1.setVisible(status);
            Label1.setText("ID Klienta* :");
            Label2.setVisible(status);
            Label2.setText("ID Promocji* :");
            Label3.setVisible(false);
            Label4.setVisible(false);
            Label5.setVisible(false);
            Label6.setVisible(false);
            Label7.setVisible(false);
            Label8.setVisible(false);
            Label9.setVisible(false);
            Label10.setVisible(false);
            Label11.setVisible(false);
            Label12.setVisible(false);

            Field1.setVisible(status);
            Field2.setVisible(status);
            Field3.setVisible(false);
            Field4.setVisible(false);
            Field5.setVisible(false);
            Field6.setVisible(false);
            Field7.setVisible(false);
            Field8.setVisible(false);
            Field9.setVisible(false);
            Field10.setVisible(false);
            Field11.setVisible(false);
            Field12.setVisible(false);
        }
    }


    /**
     * tworzy okno pracownika
     */
    public void createEmployee(String Name) throws Exception {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employee.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            stage.setScene(new Scene(root1, 900, 600));
            stage.setTitle("Employee Screen");
            stage.setResizable(false);
            stage.show();
            Login = Name;
            sql.connect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * zamyka program
     *
     * @param actionEvent
     */
    public void close(ActionEvent actionEvent) {
        sql.close();
        exit(0);
    }

    /**
     * zapisuje info o pracowniku
     */
    public void showPrivInfo() {
        TextLeft.clear();
        TextLeft.setEditable(false);
        List = sql.showPrivInfo("Pracownicy", Login);
        if (List.size() > 20) {
            for (int j = 20; j < List.size(); j++) {
                for (int i = 0; i < 20; i++) {
                    if (i == 0 && j == 20) {
                        bufforLeft = List.get(i).get(0) + " :\n";
                        bufforLeft += List.get(j).get(i) + "\n\n";
                    } else {
                        bufforLeft += List.get(i).get(0) + " :\n";
                        bufforLeft += List.get(j).get(i) + "\n\n";
                        if (i == 19)
                            bufforLeft += "\n\n---------------------------------------------\n";
                    }
                }
            }
            TextLeft.setText(bufforLeft);
        } else
            infoLabel.setText("Nie jesteś pracownikiem");
    }

    /**
     * wyświetla info o pracowniku
     *
     * @param actionEvent
     */
    public void showInformation(ActionEvent actionEvent) {
        infoLabel.setText("Twoje Informacje");
        setTextLabelsAndFields("Klient", false);
        TextLeft.clear();

        TextLeft.setText(bufforLeft);
        showPrivInfo();
        accept.setVisible(false);
    }

    /**
     * zapisuje info o wynagrodzeniu do buffora
     */
    public void getPayment() {
        TextLeft.clear();
        TextLeft.setEditable(false);
        List = sql.showPaymentToEmployee(Login);
        if (List.size() > 6) {
            for (int j = 6; j < List.size(); j++) {
                for (int i = 0; i < 6; i++) {
                    if (i == 0 && j == 6) {
                        bufforLeft = List.get(i).get(0) + " :\n";
                        bufforLeft += List.get(j).get(i) + "\n\n";
                    } else {
                        bufforLeft += List.get(i).get(0) + " :\n";
                        bufforLeft += List.get(j).get(i) + "\n\n";
                        if (i == 5)
                            bufforLeft += "\n\n---------------------------------------------\n";
                    }
                }
            }
            TextLeft.setText(bufforLeft);
        } else
            infoLabel.setText("Nie jesteś pracownikiem");
    }

    /**
     * wyświetla info o wynagrodzeniu pracownika
     *
     * @param actionEvent
     */
    public void showPayment(ActionEvent actionEvent) {
        getPayment();
        setTextLabelsAndFields("Klient", false);
        accept.setVisible(false);
        infoLabel.setText("Wynagrodzenie");
    }

    /**
     * wyświetla info o kliencie
     *
     * @param actionEvent
     */
    public void setTextClient(ActionEvent actionEvent) {
        infoLabel.setText("Lista Klientów");
        setTextLabelsAndFields("Klient", false);
        TextLeft.clear();
        showClientInfo();

        accept.setVisible(false);
    }

    /**
     * dodaje Klienta
     *
     * @param actionEvent
     */
    public void addClient(ActionEvent actionEvent) {
        clearFlags();
        infoLabel.setText("Dodaj Klienta");
        TextLeft.clear();
        TextLeft.setEditable(false);

        accept.setVisible(true);
        setTextLabelsAndFields("Klient", true);
        Field1.setDisable(true);
        Field1.setText("Auto-Inkrementacja");
        showClientInfo();
        addClientFlag = true;
    }

    /**
     * Zapisuje do buffora info o kliencie
     */
    public void showClientInfo() {
        TextLeft.setEditable(false);
        List = sql.showClientsInfoToEmployee();
        if (List.size() > 12) {
            for (int j = 12; j < List.size(); j++) {
                for (int i = 0; i < 12; i++) {
                    if (i == 0 && j == 12) {
                        bufforLeft = List.get(i).get(0) + " :\n";
                        bufforLeft += List.get(j).get(i) + "\n\n";
                    } else {
                        bufforLeft += List.get(i).get(0) + " :\n";
                        bufforLeft += List.get(j).get(i) + "\n\n";
                        if (i == 11)
                            bufforLeft += "\n\n---------------------------------------------\n";
                    }
                }
            }
            TextLeft.setText(bufforLeft);
        } else
            infoLabel.setText("Brak klientów");
    }

    /**
     * Edytuje Klienta
     *
     * @param actionEvent
     */
    public void editClientInfo(ActionEvent actionEvent) throws Exception {
        clearFlags();
        infoLabel.setText("Edytuj Klienta");
        editClientFlag = true;

        accept.setVisible(true);
        setTextLabelsAndFields("Klient", true);

        TextLeft.clear();
        showClientInfo();
    }

    /**
     * Usuwa Klienta
     *
     * @param actionEvent
     */
    public void removeClient(ActionEvent actionEvent) throws Exception {
        infoLabel.setText("");

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Usuń Klienta");
        dialog.setHeaderText("Wybierz Klienta");
        dialog.setContentText("ID Klienta:");

        try {
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Dialog Potwiedzający");
                alert.setHeaderText("Usuń Klienta");
                alert.setContentText("Czy jesteś pewien?");

                Optional<ButtonType> result2 = alert.showAndWait();
                if (result2.get() == ButtonType.OK) {
                    System.out.println("ID: " + result.get());
                    if (sql.deleteClient(result.get())) {
                        TextLeft.clear();
                        showClientInfo();
                        TextLeft.setEditable(false);
                        infoLabel.setText("Sukces");
                    } else infoLabel.setText("Błąd");

                    // ... user chose OK
                } else {
                    // ... user chose CANCEL or closed the dialog
                }

            }

        } catch (Exception e) {
        }
    }

    /**
     * Zapisuje text reklamacji do buffora
     */
    public void showComplaintsInfo() {
        TextLeft.setEditable(false);
        List = sql.showComplaintsToEmployee();
        if (List.size() > 7) {
            for (int j = 7; j < List.size(); j++) {
                for (int i = 0; i < 7; i++) {
                    if (i == 0 && j == 7) {
                        bufforLeft = List.get(i).get(0) + " :\n";
                        bufforLeft += List.get(j).get(i) + "\n\n";
                    } else {
                        bufforLeft += List.get(i).get(0) + " :\n";
                        bufforLeft += List.get(j).get(i) + "\n\n";
                        if (i == 6)
                            bufforLeft += "\n\n---------------------------------------------\n";
                    }
                }
            }
            TextLeft.setText(bufforLeft);
        } else
            infoLabel.setText("Brak reklamacji");
    }

    /**
     * Wyświetla Reklamacje
     *
     * @param actionEvent
     */
    public void setTextComplaints(ActionEvent actionEvent) {
        infoLabel.setText("Lista Reklamacji");
        TextLeft.clear();

        setTextLabelsAndFields("Complaints", false);
        showComplaintsInfo();
        accept.setVisible(false);

    }

    /**
     * Edytuje Reklamacje
     *
     * @param actionEvent
     */
    public void editComplaints(ActionEvent actionEvent) {
        clearFlags();
        infoLabel.setText("Edytuj Reklamacje");
        editComplaintsFlag = true;
        accept.setVisible(true);
        setTextLabelsAndFields("Complaints", true);

        TextLeft.clear();
        showComplaintsInfo();

    }

    /**
     * Usuwa Reklamacje
     *
     * @param actionEvent
     */
    public void removeComplaints(ActionEvent actionEvent) {
        infoLabel.setText("");

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Usuń Reklamacje");
        dialog.setHeaderText("Wybierz Reklamacje");
        dialog.setContentText("ID Reklamacji:");

        try {
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Dialog Potwiedzający");
                alert.setHeaderText("Usuń Reklamcje");
                alert.setContentText("Czy jesteś pewien?");

                Optional<ButtonType> result2 = alert.showAndWait();
                if (result2.get() == ButtonType.OK) {
                    System.out.println("ID: " + result.get());
                    if (sql.deleteComplaint(result.get())) {
                        TextLeft.setEditable(false);
                        infoLabel.setText("Sukces");
                        TextLeft.clear();
                        showComplaintsInfo();
                    } else infoLabel.setText("Błąd");

                    // ... user chose OK
                } else {
                    // ... user chose CANCEL or closed the dialog
                }

            }

        } catch (Exception e) {
        }

    }

    /**
     * zapisuje aktualne promocje do buffora
     */
    public void showPromotionsInfo() {
        LabelUnderFields.setVisible(false);
        TextLeft.setEditable(true);
        List = sql.showPromotionsToEmployee();
        if (List.size() > 4) {
            for (int j = 4; j < List.size(); j++) {
                for (int i = 0; i < 4; i++) {
                    if (i == 0 && j == 4) {
                        bufforLeft = List.get(i).get(0) + " :\n";
                        bufforLeft += List.get(j).get(i) + "\n\n";
                    } else {
                        bufforLeft += List.get(i).get(0) + " :\n";
                        bufforLeft += List.get(j).get(i) + "\n\n";
                        if (i == 3)
                            bufforLeft += "\n\n---------------------------------------------\n";
                    }
                }
            }
            TextLeft.setText(bufforLeft);
        } else
            infoLabel.setText("Brak Promocji");

    }

    /**
     * wyświetla aktualne promocje
     *
     * @param actionEvent
     */
    public void setTextPromotion(ActionEvent actionEvent) {
        infoLabel.setText("Lista Aktualnych Promocji");
        setTextLabelsAndFields("Promotion", false);
        TextLeft.clear();
        showPromotionsInfo();
        Field1.setDisable(false);
        TextLeft.setEditable(false);

        accept.setVisible(false);
    }

    /**
     * Zapisuje Promocje klientów do buffora
     */
    public void showPromotionsInfoOfClient() {
        LabelUnderFields.setVisible(false);
        TextLeft.setEditable(true);
        List = sql.showPromotionToEmployee();
        if (List.size() > 3) {
            for (int j = 3; j < List.size(); j++) {
                for (int i = 0; i < 3; i++) {
                    if (i == 0 && j == 3) {
                        bufforLeft = List.get(i).get(0) + " :\n";
                        bufforLeft += List.get(j).get(i) + "\n\n";
                    } else {
                        bufforLeft += List.get(i).get(0) + " :\n";
                        bufforLeft += List.get(j).get(i) + "\n\n";
                        if (i == 2)
                            bufforLeft += "\n\n---------------------------------------------\n";
                    }
                }
            }
            TextLeft.setText(bufforLeft);
        } else
            infoLabel.setText("Brak Promocji");
    }

    /**
     * Wyświetla Promocje
     *
     * @param actionEvent
     */
    public void setTextPromotionsOfClient(ActionEvent actionEvent) {

        infoLabel.setText("Lista Promocji Klientów");
        setTextLabelsAndFields("Promotion", false);
        TextLeft.clear();
        showPromotionsInfoOfClient();
        Field1.setDisable(false);
        TextLeft.setEditable(false);

        accept.setVisible(false);
    }

    /**
     * Dodaje Promocje
     *
     * @param actionEvent
     */
    public void addPromotion(ActionEvent actionEvent) {
        clearFlags();
        infoLabel.setText("Przydziel Promocje");
        showPromotionsInfo();
        setTextLabelsAndFields("Promotion", true);
        accept.setVisible(true);
        Field1.setDisable(false);
        TextLeft.setEditable(false);
        addPromotionFlag = true;
    }


    /**
     * Usuwa Promocje
     *
     * @param actionEvent
     */
    public void removePromotion(ActionEvent actionEvent) {
        infoLabel.setText("");

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Usuń Promocje");
        dialog.setHeaderText("Wybierz Promocje");
        dialog.setContentText("ID Promocji:");

        try {
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Dialog Potwiedzający");
                alert.setHeaderText("Usuń Promocje");
                alert.setContentText("Czy jesteś pewien?");

                Optional<ButtonType> result2 = alert.showAndWait();
                if (result2.get() == ButtonType.OK) {
                    System.out.println("ID: " + result.get());
                    if (sql.deletePromotion(result.get())) {
                        TextLeft.setEditable(false);
                        infoLabel.setText("Sukces");
                        TextLeft.clear();
                        showPromotionsInfoOfClient();
                    } else infoLabel.setText("Błąd");

                    // ... user chose OK
                } else {
                    // ... user chose CANCEL or closed the dialog
                }

            }

        } catch (Exception e) {
        }
    }

    /**
     * Zapisuje text Umów do buffora
     */
    public void showAgreementInfo() {
        TextLeft.setEditable(false);
        List = sql.showAgreementsInfoToEmployee();
        if (List.size() > 6) {
            for (int j = 6; j < List.size(); j++) {
                System.out.println(j);
                for (int i = 0; i < 6; i++) {
                    if (i == 0 && j == 6) {
                        bufforLeft = List.get(i).get(0) + " :\n";
                        bufforLeft += List.get(j).get(i) + "\n\n";
                    } else {
                        bufforLeft += List.get(i).get(0) + " :\n";
                        bufforLeft += List.get(j).get(i) + "\n\n";
                        if (i == 5)
                            bufforLeft += "\n\n---------------------------------------------\n";
                    }
                }
            }
            TextLeft.setText(bufforLeft);
        } else
            infoLabel.setText("Brak Umów");
    }

    /**
     * Wyświetla Umowy
     *
     * @param actionEvent
     */
    public void setTextAgreement(ActionEvent actionEvent) {
        infoLabel.setText("Lista umów");
        TextLeft.clear();

        setTextLabelsAndFields("Umowy", false);
        showAgreementInfo();
        accept.setVisible(false);
    }

    /**
     * Dodaje Umowę
     *
     * @param actionEvent
     */
    public void addAgreement(ActionEvent actionEvent) {
        clearFlags();
        infoLabel.setText("Dodaj Umowę");
        TextLeft.clear();
        TextLeft.setEditable(true);
        showAgreementInfo();

        accept.setVisible(true);
        setTextLabelsAndFields("Umowy", true);
        Field1.setDisable(true);
        Field1.setText("Auto-Inkrementacja");

        addAgreementFlag = true;
    }


    /**
     * Edytuje Umowę
     *
     * @param actionEvent
     */
    public void editAgreement(ActionEvent actionEvent) {
        clearFlags();
        infoLabel.setText("Edytuj Umowy");

        accept.setVisible(true);
        setTextLabelsAndFields("Umowy", true);

        TextLeft.clear();
        showAgreementInfo();


        editAgreementFlag = true;

    }

    /**
     * Usuwa Umowę
     *
     * @param actionEvent
     */
    public void removeAgreement(ActionEvent actionEvent) {
        infoLabel.setText("");

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Usuń Umowę");
        dialog.setHeaderText("Wybierz Umowę");
        dialog.setContentText("ID Umowy:");

        try {
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Dialog Potwiedzający");
                alert.setHeaderText("Usuń Umowę");
                alert.setContentText("Czy jesteś pewien?");

                Optional<ButtonType> result2 = alert.showAndWait();
                if (result2.get() == ButtonType.OK) {
                    System.out.println("ID: " + result.get());
                    if (sql.deleteAgreement(result.get())) {
                        TextLeft.setEditable(false);
                        infoLabel.setText("Sukces");
                        TextLeft.clear();
                        showAgreementInfo();
                    } else infoLabel.setText("Błąd");

                    // ... user chose OK
                } else {
                    // ... user chose CANCEL or closed the dialog
                }

            }

        } catch (Exception e) {
        }

    }

    /**
     * Reaguje nie wciśnięcie Buttona
     *
     * @param actionEvent
     */
    public void ButtonPressed(ActionEvent actionEvent) {
        infoLabel.setText("");
        if (addClientFlag) {
            if (getAllTextsClient(true)) {
                if (sql.addClient(PESEL, forename, surname, streetAddress, homeNumber, postalCode, placeOfResidence, NIP, login, password, clientServiceID)) {
                    infoLabel.setText("Klient dodany");
                    showClientInfo();
                } else {
                    infoLabel.setText("Błąd");
                }
            } else
                infoLabel.setText("Błąd przy wprowadaniu danych");


        } else if (editClientFlag) {
            if (getAllTextsClient(false)) {
                if (sql.updateClient(clientID, PESEL, forename, surname, streetAddress, homeNumber, postalCode, placeOfResidence, NIP, login, password, clientServiceID)) {
                    infoLabel.setText("Klient zedytowany ");
                    showClientInfo();
                } else {
                    infoLabel.setText("Błąd");
                }
            } else
                infoLabel.setText("Błąd przy wprowadaniu danych");

        } else if (editComplaintsFlag) {
            if (getAllTextsComplaint()) {
                if (sql.updateComplaint(complaintID, complaintNumber, descriptionOfComplaint, dateOfComplaint, statusOfComplaint, clientID, agreementID)) {
                    infoLabel.setText("Reklamacja zedytowana");
                    showComplaintsInfo();
                } else {
                    infoLabel.setText("Błąd");
                }
            } else
                infoLabel.setText("Błąd przy wprowadaniu danych");

        } else if (addPromotionFlag) {
            if (getAllTextsPromotions()) {
                if (sql.addPromotion(promotionID, clientID)) {
                    infoLabel.setText("Promocja Przydzielona");
                    showPromotionsInfo();
                } else {
                    infoLabel.setText("Błąd");
                }
            } else
                infoLabel.setText("Błąd przy wprowadaniu danych");

        } else if (addAgreementFlag) {
            if (getAllTextsAgreement(true)) {
                if (sql.addAgreement(descriptionOfAgreement, monthlyCostOfAnAgreement, dateOfEnterInToContract, clientID, numberOfTelephoneID)) {
                    infoLabel.setText("Umowa dodana");
                    showAgreementInfo();
                } else {
                    infoLabel.setText("Błąd");
                }
            } else
                infoLabel.setText("Błąd przy wprowadaniu danych");

        } else if (editAgreementFlag) {
            if (getAllTextsAgreement(false)) {
                if (sql.updateAgreement(agreementID, descriptionOfAgreement, monthlyCostOfAnAgreement, dateOfEnterInToContract, clientID, numberOfTelephoneID)) {
                    infoLabel.setText("Umowa zedytowana");
                    showAgreementInfo();
                } else {
                    infoLabel.setText("Błąd");
                }
            } else
                infoLabel.setText("Błąd przy wprowadaniu danych");
        }

    }

}
