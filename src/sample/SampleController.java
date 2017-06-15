package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static java.lang.System.exit;

public class SampleController {

    private Logic logika = new Logic();
    @FXML
    private Label komunikat = new Label();
    public TextField Login = new TextField();
    public TextField IPText = new TextField();
    public TextField PortText = new TextField();
    public PasswordField Password = new PasswordField();
    private String Name;
    private String haslo;
    private String IP;
    private String Port;
    private boolean inEmployee = false;
    private boolean inClient = false;
    public Button Ok = new Button();


    @FXML
    public void openWindows(ActionEvent event) throws Exception {

        if (logika.connect()) {
            IP = IPText.getText();
            Port = PortText.getText();
            logika.getConnectData(IP, Port);

            haslo = Password.getText();
            Name = Login.getText();
            komunikat.setText(" ");

            if (logika.logIn(Name, haslo) == 1) {
                inEmployee = true;

                System.out.println(logika.close());
                ((Node) (event.getSource())).getScene().getWindow().hide();
                Employee employee = new Employee();
                employee.createEmployee(Name);
            } else if (logika.logIn(Name, haslo) == 0) {
                inClient = true;

                System.out.println(logika.close());
                ((Node) (event.getSource())).getScene().getWindow().hide();
                Client client = new Client();
                client.createClient(Name);

            } else {
                komunikat.setText("Bledne login lub haslo");
                return;
            }
        } else
            komunikat.setText("Blad Połączenia");

    }


    public void close(ActionEvent actionEvent) {
        exit(0);
    }

}



