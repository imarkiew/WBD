package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.stage.Stage;

import java.util.ArrayList;

import static java.lang.System.exit;

public class Client {


    public javafx.scene.control.Label infoLabel;
    public javafx.scene.control.TextArea TextLeft;
    public javafx.scene.control.TextArea TextRight;
    public javafx.scene.control.Button accept;
    public Logic sql = new Logic();
    public String bufforLeft;
    public String bufforRight;
    ArrayList<ArrayList<String>> List = new ArrayList<ArrayList<String>>();
    public static String Login;
    private Stage stage = new Stage();
    public boolean ZgloszenieFlag = false;
    public boolean ReklamacjaFlag = false;

    /**
     * Wyświetla okno klienta
     */
    public void createClient(String Name) throws Exception {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("client.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            stage.setScene(new Scene(root1, 700, 500));
            stage.setTitle("Client Screen");
            stage.setResizable(false);
            stage.show();
            Login = Name;
            sql.connect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Wyświetla informacje Klienta
     * @param actionEvent
     */
    public void showInformation(ActionEvent actionEvent) {
        ZgloszenieFlag = false;
        ReklamacjaFlag = false;
        infoLabel.setText("");
        TextLeft.clear();
        TextLeft.setEditable(false);
        TextLeft.setBlendMode(BlendMode.MULTIPLY);

        TextRight.clear();
        TextRight.setEditable(false);
        TextRight.setBlendMode(BlendMode.MULTIPLY);

        List = sql.showPrivInfo("Klienci", Login);

        for (int i = 0; i < List.size() - 1; i++) {
            if (i == 0) {
                bufforLeft = List.get(0).get(0) + "*\n";
            } else
                bufforLeft += List.get(i).get(0) + "\n";
            if (i == 0) {
                bufforRight = List.get(List.size() - 1).get(0) + "\n";
            } else
                bufforRight += List.get(List.size() - 1).get(i) + "\n";
        }
        TextLeft.setText(bufforLeft);
        TextRight.setText(bufforRight);
        accept.setVisible(false);
        accept.setDisable(true);
    }
    /**
     * Wyłącza program
     * @param actionEvent
     */
    public void close(ActionEvent actionEvent) {
        sql.close();
        exit(0);
    }

    /**
     * zapisuje do buffora zgłoszenia
     */
    public void showClientMalfunction() {
        TextLeft.setEditable(true);
        TextRight.setEditable(false);
        TextRight.clear();
        List = sql.showMalfunctionsToClient(Login);
        if (List.size() > 7) {
            for (int j = 7; j < List.size(); j++) {
                for (int i = 0; i < 7; i++) {
                    if (i == 0 && j == 7) {
                        bufforRight = List.get(i).get(0) + " :\n";
                        bufforRight += List.get(j).get(i) + "\n\n";
                    } else {
                        bufforRight += List.get(i).get(0) + " :\n";
                        bufforRight += List.get(j).get(i) + "\n\n";
                        if (i == 6)
                            bufforRight += "\n\n---------------------------------------------\n";
                    }
                }
            }
            TextRight.setText(bufforRight);
        } else
            infoLabel.setText("Brak zgłoszeń");
    }

    /**
     * zapisuje do buffora umowy
     */
    public void showClientAgreement() {
        TextLeft.setEditable(false);
        TextRight.setEditable(false);
        TextRight.clear();
        List = sql.showAgreementInfoToClient(Login);
        if (List.size() > 6) {
            for (int j = 6; j < List.size(); j++) {
                for (int i = 0; i < 6; i++) {
                    if (i == 0 && j == 6) {
                        bufforRight = List.get(i).get(0) + " :\n";
                        bufforRight += List.get(j).get(i) + "\n\n";
                    } else {
                        bufforRight += List.get(i).get(0) + " :\n";
                        bufforRight += List.get(j).get(i) + "\n\n";
                        if (i == 5)
                            bufforRight += "\n\n---------------------------------------------\n";
                    }
                }
            }
            TextRight.setText(bufforRight);
        } else
            infoLabel.setText("Brak umów");
    }

    /**
     * zapisuje do buffora zgłoszenia
     */
    public void showClientComplaints() {
        TextRight.clear();
        TextLeft.setEditable(true);
        TextRight.setEditable(false);
        List = sql.showComplaintsToClient(Login);
        if (List.size() > 7) {
            for (int j = 7; j < List.size(); j++) {
                for (int i = 0; i < 7; i++) {
                    if (i == 0 && j == 7) {
                        bufforRight = List.get(i).get(0) + " :\n";
                        bufforRight += List.get(j).get(i) + "\n\n";
                    } else {
                        bufforRight += List.get(i).get(0) + " :\n";
                        bufforRight += List.get(j).get(i) + "\n\n";
                        if (i == 6)
                            bufforRight += "\n\n---------------------------------------------\n";
                    }
                }
            }
            TextRight.setText(bufforRight);
        } else
            infoLabel.setText("Brak reklamacji");
    }

    /**
     * zapisuje do buffora promocje
     */
    public void showClientPromtions() {
        TextLeft.setEditable(false);
        TextRight.setEditable(false);
        TextRight.clear();
        List = sql.showPromotionsToClient(Login);
        if (List.size() > 4) {
            for (int j = 4; j < List.size(); j++) {
                for (int i = 0; i < 4; i++) {
                    if (i == 0 && j == 4) {
                        bufforRight = List.get(i).get(0) + " :\n";
                        bufforRight += List.get(j).get(i) + "\n\n";
                    } else {
                        bufforRight += List.get(i).get(0) + " :\n";
                        bufforRight += List.get(j).get(i) + "\n\n";
                        if (i == 3)
                            bufforRight += "\n\n---------------------------------------------\n";
                    }
                }
            }
            TextRight.setText(bufforRight);
        } else
            infoLabel.setText("Brak Promocji");
    }

    /**
     * wyświetla wlasne reklamacje
     * @param actionEvent
     */
    public void setTextMalfunction(ActionEvent actionEvent) {
        ZgloszenieFlag = false;
        infoLabel.setText("");
        ReklamacjaFlag = true;
        TextLeft.clear();
        TextRight.setBlendMode(BlendMode.MULTIPLY);
        TextLeft.setBlendMode(BlendMode.SRC_OVER);
        TextLeft.setPromptText("Wprowadz reklamacje");

        showClientComplaints();

        accept.setVisible(true);
        accept.setDisable(false);
    }

    /**
     * Wyświetla informacje Klienta
     * @param actionEvent
     */
    public void setTextComplaints(ActionEvent actionEvent) {
        ReklamacjaFlag = false;
        infoLabel.setText("");
        ZgloszenieFlag = true;
        TextLeft.clear();
        TextRight.clear();
        TextRight.setBlendMode(BlendMode.MULTIPLY);
        TextLeft.setBlendMode(BlendMode.SRC_OVER);
        TextLeft.setPromptText("Wprowadz zgłoszenie");

        TextLeft.setEditable(true);
        TextRight.setEditable(false);

        showClientMalfunction();

        TextRight.setText(bufforRight);


        accept.setVisible(true);
        accept.setDisable(false);
    }

    /**
     * Wyświetla promocje Klienta
     * @param actionEvent
     */
    public void setTextPromotion(ActionEvent actionEvent) {
        ZgloszenieFlag = false;
        ReklamacjaFlag = false;
        infoLabel.setText("");
        TextLeft.clear();
        TextRight.clear();
        TextRight.setBlendMode(BlendMode.MULTIPLY);
        TextLeft.setBlendMode(BlendMode.MULTIPLY);
        TextLeft.setText("\t\t\tPromocje\n\nW celu uzyskania nowych promocji prosze skontaktować sie z najbliższą Obsługą Klienta");
        showClientPromtions();


        accept.setVisible(false);
        accept.setDisable(true);
    }

    /**
     * Obsługa buttona
     * @param actionEvent
     */
    public void ButtonPressed(ActionEvent actionEvent) {
        if (ZgloszenieFlag) {
            if (TextLeft.getText().length() > 20) {
                bufforLeft = TextLeft.getText();
                sql.addAMalfunction(Login, bufforLeft);
                infoLabel.setText("Zgloszenie dodane");
                showClientMalfunction();

                TextLeft.clear();
            } else
                infoLabel.setText("Za krotkie zgloszenie");

        } else if (ReklamacjaFlag) {
            if (TextLeft.getText().length() > 20) {
                bufforLeft = TextLeft.getText();
                sql.addAComplaint(Login, bufforLeft);
                infoLabel.setText("Reklamacja dodana");
                showClientComplaints();

                TextLeft.clear();
            } else
                infoLabel.setText("Za krotka reklamacja");
        }

    }
    /**
     * Wyświetla  umowy
     * @param actionEvent
     */
    public void setTextAgreement(ActionEvent actionEvent) {
        ZgloszenieFlag = false;
        ReklamacjaFlag = false;
        infoLabel.setText("");
        TextLeft.clear();
        TextRight.clear();
        TextRight.setBlendMode(BlendMode.MULTIPLY);
        TextLeft.setBlendMode(BlendMode.MULTIPLY);
        TextLeft.setText("\t\t\tUmowy \n\n W razie pytań prosimy o kontakt telefoniczny lub o odwiedzenie najbliższej placówki");
        TextLeft.setEditable(false);
        showClientAgreement();


        accept.setVisible(false);
        accept.setDisable(true);
    }

}
