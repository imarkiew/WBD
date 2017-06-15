package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;


public class Logic {

    private String port;
    private String ip;
    private String source;
    private String loginDatabase;
    private String passwordDatabase;
    private static Connection connection;
    private static Statement statement;


    Logic() {
        port = "1521";
        ip = "localhost";
        passwordDatabase = "wbd";
        connection = null;
        statement = null;
        loginDatabase = "wbd";
        source = "jdbc:oracle:thin:@" + ip + ":" + port + ":orcl";
    }


    /**
     * Pobranie i przypisanie wartosci ip oraz portu z GUI
     *
     * @param ipGUI
     * @param portGUI
     */
    public void getConnectData(String ipGUI, String portGUI) {

        if (!ipGUI.equals("") && portGUI.equals("")) {
            source = "jdbc:oracle:thin:@" + ipGUI + ":" + this.port + ":orcl";
        } else if (ipGUI.equals("") && !portGUI.equals("")) {
            source = "jdbc:oracle:thin:@" + this.ip + ":" + portGUI + ":orcl";
        } else if (!(portGUI.equals("") && portGUI.equals(""))) {
            source = "jdbc:oracle:thin:@" + ipGUI + ":" + portGUI + ":orcl";
        }

    }


    /**
     * Laczenie z baza danych, zwraca true gdy pomyslnie po�aczono sie z baza danych
     *
     * @return
     */
    public boolean connect() {

        boolean info = true;

        try {
            connection = DriverManager.getConnection(source, loginDatabase, passwordDatabase);
            statement = connection.createStatement();
        } catch (Exception exc) {
            exc.printStackTrace();
            info = false;

        }

        return info;
    }


    /**
     * Logowanie, zwraca 0 - gdy klient, 1 - gdy pracownik i 2 - gdy jest bledne haslo, bledny login, nie ma w bazie danych lub gdy wystapil blad
     *
     * @param login
     * @param password
     * @return
     */
    public int logIn(String login, String password) {
        ResultSet resultSet = null;
        int info = 2;

        try {
            resultSet = statement.executeQuery("SELECT" + " " + "\"Login\",\"Password\"" + " " + "FROM" + " " + "\"Klienci\"");

            while (resultSet.next()) {
                if (login.equals(resultSet.getString("Login")) && password.equals(resultSet.getString("Password"))) {
                    info = 0;
                    resultSet.close();

                    break;
                }

            }

            if (info != 0) {

                resultSet = statement.executeQuery("SELECT" + " " + "\"Login\",\"Password\"" + " " + "FROM" + " " + "\"Pracownicy\"");

                while (resultSet.next()) {
                    if (login.equals(resultSet.getString("Login")) && password.equals(resultSet.getString("Password"))) {
                        info = 1;
                        resultSet.close();

                        break;
                    }
                }
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            info = 2;
        }

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                info = 2;
            }
        }

        return info;
    }


    /**
     * Zamkniecie polaczenia z baza danych, zwraca true gdy pomyslnie zakonczono polaczenie
     *
     * @return
     */
    public boolean close() {
        boolean info = true;
        try {

            if (statement != null)
                statement.close();

            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            info = false;
        }
        return info;
    }


    /**
     * Zwraca true gdy string jest liczba calkowita
     *
     * @param value
     * @return
     */
    public boolean isInt(String value) {
        boolean info = true;

        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            info = false;
        }

        return info;
    }


    /**
     * Zwraca true gdy string jest liczba dziesietna (w szczegolnosci calkowita)
     *
     * @param value
     * @return
     */
    public boolean isDecimal(String value) {
        boolean info = true;
        String temp = value;

        int count = temp.length() - temp.replace(".", "").length();

        if (count == 0) {
            try {
                Integer.parseInt(value);
            } catch (NumberFormatException e) {
                info = false;
            }
        } else if (count == 1) {

            String[] result = value.split("\\."); //. ma specjalne znaczenie wiec daje dodatkowo \\

            System.out.println(result[0]);
            System.out.println(result[1]);
            try {
                Integer.parseInt(result[0]);
            } catch (NumberFormatException e) {
                info = false;
            }
        } else {
            info = false;

        }

        return info;
    }


    /**
     * Zwraca true gdy string zawiera tylko duze i male litery
     *
     * @param value
     * @return
     */
    public boolean isName(String value) {
        boolean info = true;

        if (!Pattern.matches("[a-zA-Z]+", value))
            info = false;

        return info;
    }


    /**
     * Zwraca true gdy string jest data w podanym formacie
     *
     * @param value
     * @param format
     * @return
     */
    public boolean isDate(String value, String format) {
        boolean info = true;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false); //Wylaczenie heurystyki

        try {

            Date date = sdf.parse(value);
            System.out.println(date);

        } catch (ParseException e) {

            info = false;
        }

        return info;
    }


    /**
     * Zwraca tablice z prywatnymi informacjiami pojedynczego klienta lub pracownika
     *
     * @param table
     * @param login
     * @return
     */
    public ArrayList<ArrayList<String>> showPrivInfo(String table, String login) {
        ResultSet resultSet = null;
        ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();

        try {
            resultSet = statement.executeQuery("SELECT" + " " + "*" + " " + "FROM" + " " + "\"" + table + "\"" + "  " + "WHERE" + " " + "\"Login\"=" + "'" + login + "'");
            ResultSetMetaData metadata = resultSet.getMetaData();
            int count = metadata.getColumnCount();

            for (int i = 1; i <= count; i++) {
                ArrayList<String> labels = new ArrayList<String>();
                labels.add(metadata.getColumnLabel(i));
                info.add(labels);
            }

            while (resultSet.next()) {
                ArrayList<String> row = new ArrayList<String>();

                for (int i = 1; i <= count; i++) {
                    row.add(resultSet.getString(i));
                }

                info.add(row);

            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return info;

    }


    /**
     * Zwraca tablice z informacjami o umowie dla pojedynczego klienta
     *
     * @param login
     * @return
     */
    public ArrayList<ArrayList<String>> showAgreementInfoToClient(String login) {
        ResultSet resultSet = null;
        ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
        String id = null;

        try {
            resultSet = statement.executeQuery("SELECT" + " " + "\"ID_Klienta\"" + " " + "FROM" + " " + "\"Klienci\"" + "  " + "WHERE" + " " + "\"Login\"=" + "'" + login + "'");

            while (resultSet.next()) {
                id = resultSet.getString("ID_Klienta");
            }


            resultSet.close();

            resultSet = statement.executeQuery("SELECT" + " " + "*" + " " + "FROM" + " " + "\"Umowy\"" + "  " + "WHERE" + " " + "\"ID_Klienta\"=" + "'" + id + "'");
            ResultSetMetaData metadata = resultSet.getMetaData();
            int count = metadata.getColumnCount();

            for (int i = 1; i <= count; i++) {
                ArrayList<String> labels = new ArrayList<String>();
                labels.add(metadata.getColumnLabel(i));
                info.add(labels);
            }

            while (resultSet.next()) {
                ArrayList<String> row = new ArrayList<String>();

                for (int i = 1; i <= count; i++) {
                    row.add(resultSet.getString(i));
                }

                info.add(row);

            }

            resultSet.close();

        } catch (SQLException e) {

        }

        return info;
    }


    /**
     * Dodanie reklamacji, zwraca true gdy nastapilo pomyslne dodanie reklamacji
     *
     * @param login
     * @param info
     * @return
     */
    public boolean addAComplaint(String login, String info) {
        boolean message = true;
        ResultSet resultSet = null;
        String idUmowy = null;
        String idKlienta = null;
        LocalDate localDate = LocalDate.now();
        String date = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(localDate);

        try {

            resultSet = statement.executeQuery("SELECT" + " " + "\"ID_Klienta\"" + " " + "FROM" + " " + "\"Klienci\"" + "  " + "WHERE" + " " + "\"Login\"=" + "'" + login + "'");

            while (resultSet.next()) {
                idKlienta = resultSet.getString("ID_Klienta");
            }

            resultSet.close();

            resultSet = statement.executeQuery("SELECT" + " " + "\"ID_Umowy\"" + " " + "FROM" + " " + "\"Umowy\"" + "  " + "WHERE" + " " + "\"ID_Klienta\"=" + "'" + idKlienta + "'");


            while (resultSet.next()) {
                idUmowy = resultSet.getString("ID_Umowy");
            }

            resultSet.close();

            statement.executeUpdate("INSERT INTO" + " " + "\"Reklamacje\"" + " " + "VALUES(idReklamacji.nextval, idReklamacji.nextval," + "'" + info + "'" + ", TO_DATE(" + "'" + date + "'" + ", 'DD-MM-YYYY'), " + "'Nierozpatrzona', " + idKlienta + ", " + idUmowy + ")");

        } catch (SQLException e) {
            message = false;
            e.printStackTrace();
        }

        return message;
    }


    /**
     * Zwraca tablice dla kilenta z jego reklamacjami
     *
     * @param login
     * @return
     */
    public ArrayList<ArrayList<String>> showComplaintsToClient(String login) {
        ResultSet resultSet = null;
        ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
        String idKlienta = null;

        try {
            resultSet = statement.executeQuery("SELECT" + " " + "\"ID_Klienta\"" + " " + "FROM" + " " + "\"Klienci\"" + "  " + "WHERE" + " " + "\"Login\"=" + "'" + login + "'");

            while (resultSet.next()) {
                idKlienta = resultSet.getString("ID_Klienta");
            }


            resultSet.close();


            resultSet = statement.executeQuery("SELECT" + " " + "*" + " " + "FROM" + " " + "\"Reklamacje\"" + "  " + "WHERE" + " " + "\"ID_Klienta\"=" + idKlienta);
            ResultSetMetaData metadata = resultSet.getMetaData();
            int count = metadata.getColumnCount();

            for (int i = 1; i <= count; i++) {
                ArrayList<String> labels = new ArrayList<String>();
                labels.add(metadata.getColumnLabel(i));
                info.add(labels);
            }

            while (resultSet.next()) {

                ArrayList<String> row = new ArrayList<String>();

                for (int i = 1; i <= count; i++) {
                    row.add(resultSet.getString(i));
                }

                info.add(row);

            }


            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return info;
    }


    /**
     * Dodanie zgloszenia, zwraca true w przypadku pozytywnego dodania zgloszenia
     *
     * @param login
     * @param info
     * @return
     */
    public boolean addAMalfunction(String login, String info) {
        boolean message = true;
        ResultSet resultSet = null;
        String idKlienta = null;
        LocalDate localDate = LocalDate.now();
        String date = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(localDate);


        try {

            resultSet = statement.executeQuery("SELECT" + " " + "\"ID_Klienta\"" + " " + "FROM" + " " + "\"Klienci\"" + "  " + "WHERE" + " " + "\"Login\"=" + "'" + login + "'");

            while (resultSet.next()) {
                idKlienta = resultSet.getString("ID_Klienta");
            }

            resultSet.close();

            statement.executeUpdate("INSERT INTO" + " " + "\"Zgloszenia\"" + " " + "VALUES(idZgloszenia.nextval, idZgloszenia.nextval," + "'" + info + "'" + ", 'Nierozpatrzone', " + "TO_DATE(" + "'" + date + "'" + ", 'DD-MM-YYYY'), " + "1" + ", " + idKlienta + ")");

        } catch (SQLException e) {
            message = false;
            e.printStackTrace();
        }

        return message;
    }


    /**
     * Zwaraca tablice dla klienta z jego zgloszeniami
     *
     * @param login
     * @return
     */
    public ArrayList<ArrayList<String>> showMalfunctionsToClient(String login) {
        ResultSet resultSet = null;
        ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
        String idKlienta = null;

        try {
            resultSet = statement.executeQuery("SELECT" + " " + "\"ID_Klienta\"" + " " + "FROM" + " " + "\"Klienci\"" + "  " + "WHERE" + " " + "\"Login\"=" + "'" + login + "'");

            while (resultSet.next()) {
                idKlienta = resultSet.getString("ID_Klienta");
            }

            resultSet.close();


            resultSet = statement.executeQuery("SELECT" + " " + "*" + " " + "FROM" + " " + "\"Zgloszenia\"" + "  " + "WHERE" + " " + "\"ID_Klienta\"=" + idKlienta);
            ResultSetMetaData metadata = resultSet.getMetaData();
            int count = metadata.getColumnCount();

            for (int i = 1; i <= count; i++) {
                ArrayList<String> labels = new ArrayList<String>();
                labels.add(metadata.getColumnLabel(i));
                info.add(labels);
            }

            while (resultSet.next()) {

                ArrayList<String> row = new ArrayList<String>();

                for (int i = 1; i <= count; i++) {
                    row.add(resultSet.getString(i));
                }

                info.add(row);

            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return info;
    }


    /**
     * Zwraca tablice dla klienta z jego promocjami
     *
     * @param login
     * @return
     */
    public ArrayList<ArrayList<String>> showPromotionsToClient(String login) {
        ResultSet resultSet = null;
        ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
        String idKlienta = null;

        try {
            resultSet = statement.executeQuery("SELECT" + " " + "\"ID_Klienta\"" + " " + "FROM" + " " + "\"Klienci\"" + "  " + "WHERE" + " " + "\"Login\"=" + "'" + login + "'");

            while (resultSet.next()) {
                idKlienta = resultSet.getString("ID_Klienta");
            }

            resultSet.close();

            resultSet = statement.executeQuery("SELECT" + " " + "\"ID_promocji\"" + " " + "FROM" + " " + "\"Promocja\"" + "  " + "WHERE" + " " + "\"ID_Klienta\"=" + idKlienta);

            ArrayList<String> tmp = new ArrayList<String>();

            while (resultSet.next()) {
                tmp.add(resultSet.getString("ID_promocji"));
            }

            resultSet.close();

            if (!tmp.isEmpty()) {
                resultSet = statement.executeQuery("SELECT" + " " + "*" + " " + "FROM" + " " + "\"Promocje\"" + "  " + "WHERE" + " " + "\"ID_Promocji\" IN " + tmp.toString().replace("[", "(").replace("]", ")"));

                ResultSetMetaData metadata = resultSet.getMetaData();
                int count = metadata.getColumnCount();

                for (int i = 1; i <= count; i++) {
                    ArrayList<String> labels = new ArrayList<String>();
                    labels.add(metadata.getColumnLabel(i));
                    info.add(labels);
                }

                while (resultSet.next()) {

                    ArrayList<String> row = new ArrayList<String>();

                    for (int i = 1; i <= count; i++) {
                        row.add(resultSet.getString(i));
                    }

                    info.add(row);

                }

            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return info;
    }


    /**
     * Zwraca tablice dla pracownika ze szczegolami jego wyplaty
     *
     * @param login
     * @return
     */
    public ArrayList<ArrayList<String>> showPaymentToEmployee(String login) {
        ResultSet resultSet = null;
        ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
        String idPracownika = null;

        try {
            resultSet = statement.executeQuery("SELECT" + " " + "\"ID_Pracownika\"" + " " + "FROM" + " " + "\"Pracownicy\"" + "  " + "WHERE" + " " + "\"Login\"=" + "'" + login + "'");

            while (resultSet.next()) {
                idPracownika = resultSet.getString("ID_Pracownika");
            }


            resultSet.close();


            resultSet = statement.executeQuery("SELECT" + " " + "*" + " " + "FROM" + " " + "\"Wynagrodzenia\"" + "  " + "WHERE" + " " + "\"ID_Pracownika\"=" + idPracownika);
            ResultSetMetaData metadata = resultSet.getMetaData();
            int count = metadata.getColumnCount();

            for (int i = 1; i <= count; i++) {
                ArrayList<String> labels = new ArrayList<String>();
                labels.add(metadata.getColumnLabel(i));
                info.add(labels);
            }

            while (resultSet.next()) {

                ArrayList<String> row = new ArrayList<String>();

                for (int i = 1; i <= count; i++) {
                    row.add(resultSet.getString(i));
                }

                info.add(row);

            }


            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return info;
    }


    /**
     * Zwraca tablice wszystki umow klientow
     *
     * @return
     */
    public ArrayList<ArrayList<String>> showAgreementsInfoToEmployee() {
        ResultSet resultSet = null;
        ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();

        try {
            resultSet = statement.executeQuery("SELECT" + " " + "*" + " " + "FROM" + " " + "\"Umowy\"");
            ResultSetMetaData metadata = resultSet.getMetaData();
            int count = metadata.getColumnCount();

            for (int i = 1; i <= count; i++) {
                ArrayList<String> labels = new ArrayList<String>();
                //System.out.print(metadata.getColumnLabel(i) + "\n");
                labels.add(metadata.getColumnLabel(i));
                info.add(labels);
            }

            while (resultSet.next()) {

                ArrayList<String> row = new ArrayList<String>();

                for (int i = 1; i <= count; i++) {
                    row.add(resultSet.getString(i));
                }

                info.add(row);

            }


            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return info;
    }


    /**
     * Zwraca tablice z informacjami o wszystkich klientach
     *
     * @return
     */
    public ArrayList<ArrayList<String>> showClientsInfoToEmployee() {
        ResultSet resultSet = null;
        ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();

        try {
            resultSet = statement.executeQuery("SELECT" + " " + "*" + " " + "FROM" + " " + "\"Klienci\"");
            ResultSetMetaData metadata = resultSet.getMetaData();
            int count = metadata.getColumnCount();

            for (int i = 1; i <= count; i++) {
                ArrayList<String> labels = new ArrayList<String>();
                labels.add(metadata.getColumnLabel(i));
                info.add(labels);
            }

            while (resultSet.next()) {

                ArrayList<String> row = new ArrayList<String>();

                for (int i = 1; i <= count; i++) {
                    row.add(resultSet.getString(i));
                }

                info.add(row);

            }


            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return info;
    }


    /**
     * Zwraca wszystkie krotki z encji brydzujacej Promocja
     *
     * @return
     */
    public ArrayList<ArrayList<String>> showPromotionToEmployee() {
        ResultSet resultSet = null;
        ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();

        try {
            resultSet = statement.executeQuery("SELECT" + " " + "*" + " " + "FROM" + " " + "\"Promocja\"");
            ResultSetMetaData metadata = resultSet.getMetaData();
            int count = metadata.getColumnCount();

            for (int i = 1; i <= count; i++) {
                ArrayList<String> labels = new ArrayList<String>();
                //System.out.print(metadata.getColumnLabel(i) + "\n");
                labels.add(metadata.getColumnLabel(i));
                info.add(labels);
            }

            while (resultSet.next()) {

                ArrayList<String> row = new ArrayList<String>();

                for (int i = 1; i <= count; i++) {
                    row.add(resultSet.getString(i));
                }

                info.add(row);

            }


            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return info;
    }


    /**
     * Zwraca tablice ze wszystkimi informacjami z tablicy Promocje
     *
     * @return
     */
    public ArrayList<ArrayList<String>> showPromotionsToEmployee() {
        ResultSet resultSet = null;
        ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();

        try {
            resultSet = statement.executeQuery("SELECT" + " " + "*" + " " + "FROM" + " " + "\"Promocje\"");
            ResultSetMetaData metadata = resultSet.getMetaData();
            int count = metadata.getColumnCount();

            for (int i = 1; i <= count; i++) {
                ArrayList<String> labels = new ArrayList<String>();
                labels.add(metadata.getColumnLabel(i));
                info.add(labels);
            }

            while (resultSet.next()) {

                ArrayList<String> row = new ArrayList<String>();

                for (int i = 1; i <= count; i++) {
                    row.add(resultSet.getString(i));
                }

                info.add(row);

            }


            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return info;
    }


    /**
     * Zwraca tablice ze wszystkimi reklamacjami
     *
     * @return
     */
    public ArrayList<ArrayList<String>> showComplaintsToEmployee() {
        ResultSet resultSet = null;
        ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();

        try {
            resultSet = statement.executeQuery("SELECT" + " " + "*" + " " + "FROM" + " " + "\"Reklamacje\"");
            ResultSetMetaData metadata = resultSet.getMetaData();
            int count = metadata.getColumnCount();

            for (int i = 1; i <= count; i++) {
                ArrayList<String> labels = new ArrayList<String>();
                labels.add(metadata.getColumnLabel(i));
                info.add(labels);
            }

            while (resultSet.next()) {

                ArrayList<String> row = new ArrayList<String>();

                for (int i = 1; i <= count; i++) {
                    row.add(resultSet.getString(i));
                }

                info.add(row);

            }


            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return info;
    }


    /**
     * Usuwanie umowy, zwraca true gdy pomyslnie udalo sie usunac umowe
     *
     * @param agreementID
     */
    public boolean deleteAgreement(String agreementID) {
        boolean info = true;
        ResultSet resultSet = null;

        try {

            resultSet = statement.executeQuery("SELECT \"ID_Umowy\" FROM" + " " + "\"Umowy\"" + " " + "WHERE \"ID_Umowy\" = " + agreementID);

            if (!resultSet.next()) {
                info = false;
            }

            resultSet.close();

            if (info == true) {
                statement.executeUpdate("DELETE FROM" + " " + "\"Umowy\"" + " " + "WHERE \"ID_Umowy\" = " + agreementID);
                resultSet.close();
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            info = false;
        }

        return info;
    }


    /**
     * Usuwanie krotki z tablicy Promocja, zwraca true gdy pomyslnie udalo sie usunac
     *
     * @param promotionID
     */
    public boolean deletePromotion(String promotionID) {
        boolean info = true;
        ResultSet resultSet = null;

        try {

            resultSet = statement.executeQuery("SELECT \"ID_Promocja\" FROM" + " " + "\"Promocja\"" + " " + "WHERE \"ID_Promocja\" = " + promotionID);

            if (!resultSet.next()) {
                info = false;
            }

            resultSet.close();

            if (info == true) {

                statement.executeUpdate("DELETE FROM" + " " + "\"Promocja\"" + " " + "WHERE \"ID_Promocja\" = " + promotionID);
                resultSet.close();

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            info = false;
            e.printStackTrace();
        }

        return info;
    }


    /**
     * Usuniecie reklamacji, zwraca true gdy pomyslnie udalo sie usunac reklamacje
     *
     * @param complaintID
     */
    public boolean deleteComplaint(String complaintID) {
        boolean info = true;
        ResultSet resultSet = null;

        try {

            resultSet = statement.executeQuery("SELECT \"ID_Reklamacji\" FROM" + " " + "\"Reklamacje\"" + " " + "WHERE \"ID_Reklamacji\" = " + complaintID);

            if (!resultSet.next()) {
                info = false;
            }

            resultSet.close();

            if (info == true) {

                statement.executeUpdate("DELETE FROM" + " " + "\"Reklamacje\"" + " " + "WHERE \"ID_Reklamacji\" = " + complaintID);
                resultSet.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            info = false;
        }

        return info;
    }


    /**
     * Usuniecie klienta, zwraca true gdy pomyslnie udalo sie usunac klienta
     *
     * @param clientID
     */
    public boolean deleteClient(String clientID) {
        boolean info = true;
        ResultSet resultSet = null;

        try {

            resultSet = statement.executeQuery("SELECT \"ID_Klienta\" FROM" + " " + "\"Klienci\"" + " " + "WHERE \"ID_Klienta\" = " + clientID);

            if (!resultSet.next()) {
                info = false;
            }

            resultSet.close();

            if (info == true) {

                statement.executeUpdate("DELETE FROM" + " " + "\"Klienci\"" + " " + "WHERE \"ID_Klienta\" = " + clientID);
                resultSet.close();
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            info = false;
            e.printStackTrace();
        }

        return info;
    }


    /**
     * Dodawanie umowy, zwraca true w przypadku pomyslnego dodania
     *
     * @param descriptionOfAgreement
     * @param monthlyCostOfAnAgreement
     * @param dateOfEnterInToContract
     * @param clientID
     * @param numberOfTelephoneID
     * @return
     */
    public boolean addAgreement(String descriptionOfAgreement, String monthlyCostOfAnAgreement, String dateOfEnterInToContract, String clientID, String numberOfTelephoneID) {

        boolean message = true;
        try {

            statement.executeUpdate("INSERT INTO" + " " + "\"Umowy\"" + " " + "VALUES(idUmowy.nextval, " + "'" + descriptionOfAgreement + "', " + monthlyCostOfAnAgreement + ", TO_DATE(" + "'" + dateOfEnterInToContract + "'" + ", 'DD-MM-YYYY'), " + clientID + ", " + numberOfTelephoneID + ")");

        } catch (SQLException e) {
            //e.printStackTrace();
            message = false;

        }
        return message;
    }


    /**
     * Dodanie klienta, zwraca true gdy udalo pomyslnie dodac sie klienta
     *
     * @param PESEL
     * @param forename
     * @param surname
     * @param streetAddress
     * @param homeNumber
     * @param postalCode
     * @param placeOfResidence
     * @param NIP
     * @param login
     * @param password
     * @param clientServiceID
     * @return
     */
    public boolean addClient(String PESEL, String forename, String surname, String streetAddress, String homeNumber, String postalCode, String placeOfResidence, String NIP, String login, String password, String clientServiceID) {
        boolean message = true;

        if (PESEL.equals("")) {
            PESEL = "null";
        }

        if (NIP.equals("")) {
            NIP = "null";
        }


        try {
            statement.executeUpdate("INSERT INTO \"Klienci\" VALUES(idKlienta.nextval, " + "'" + PESEL + "', " + "'" + forename + "'," + "'" + surname + "'," + "'" + streetAddress + "'," + "'" + homeNumber + "'," + "'" + postalCode + "'," + "'" + placeOfResidence + "'," + "'" + NIP + "'," + "'" + login + "'," + "'" + password + "'," + clientServiceID + ")");

        } catch (SQLException e) {
            //e.printStackTrace();
            message = false;
        }

        return message;
    }


    /**
     * Laczenie promocji z klientem, zwraca prawde gdy udalo sie dodac krotke w encji brydzujacej Promocja
     *
     * @param promotionID
     * @param clientID
     * @return
     */
    public boolean addPromotion(String promotionID, String clientID) {
        boolean message = true;

        try {
            statement.executeUpdate("INSERT INTO \"Promocja\" VALUES(idPromocja.nextval, " + promotionID + "," + clientID + ")");

        } catch (SQLException e) {
            e.printStackTrace();
            message = false;
        }

        return message;
    }


    /**
     * aktualizacja umowy, funkcja zwraca wartosc true gdy nastapila pozytywna aktualizacja
     *
     * @param agreementID
     * @param descriptionOfAgreement
     * @param monthlyCostOfAnAgreement
     * @param dateOfEnterInToContract
     * @param clientID
     * @param numberOfTelephoneID
     * @return
     */
    public boolean updateAgreement(String agreementID, String descriptionOfAgreement, String monthlyCostOfAnAgreement, String dateOfEnterInToContract, String clientID, String numberOfTelephoneID) {
        boolean message = true;
        ResultSet resultSet = null;
        ArrayList<String> tmp = new ArrayList<String>();

        tmp.add(descriptionOfAgreement);
        tmp.add(monthlyCostOfAnAgreement);
        tmp.add(dateOfEnterInToContract);
        tmp.add(clientID);
        tmp.add(numberOfTelephoneID);

        try {
            resultSet = statement.executeQuery("SELECT" + " " + "*" + " " + "FROM" + " " + "\"Umowy\"" + "WHERE \"ID_Umowy\" = " + agreementID);
            ResultSetMetaData metadata = resultSet.getMetaData();
            int count = metadata.getColumnCount();

            ArrayList<String> info = new ArrayList<String>();

            while (resultSet.next()) {

                for (int i = 2; i <= count; i++) {
                    info.add(resultSet.getString(i));
                }
            }

            if (info.size() != 0) {
                for (int i = 0; i < tmp.size(); i++) {
                    if (tmp.get(i).equals("")) {
                        tmp.set(i, info.get(i));
                    }

                }

                //Wycinanie czasu
                if (tmp.get(2).length() > 10) {

                    tmp.set(2, tmp.get(2).split(" ")[0]);
                    System.out.println("Po wycieciu: " + tmp.get(2));
                    statement.executeUpdate("UPDATE" + " " + "\"Umowy\"" + " " + "SET " + "\"Opis_umowy\" = " + "'" + tmp.get(0) + "', " + "\"Miesieczny_koszt_umowy\" = " + tmp.get(1) + ", " + "\"Data_Zawarcia_Umowy\" = " + "TO_DATE(" + "'" + tmp.get(2) + "'" + ", 'YYYY-MM-DD'), " + "\"ID_Klienta\" = " + tmp.get(3) + ", " + "\"ID_Nr_Telefonu\" = " + tmp.get(4) + "WHERE \"ID_Umowy\" = " + agreementID);
                } else {
                    statement.executeUpdate("UPDATE" + " " + "\"Umowy\"" + " " + "SET " + "\"Opis_umowy\" = " + "'" + tmp.get(0) + "', " + "\"Miesieczny_koszt_umowy\" = " + tmp.get(1) + ", " + "\"Data_Zawarcia_Umowy\" = " + "TO_DATE(" + "'" + tmp.get(2) + "'" + ", 'DD-MM-YYYY'), " + "\"ID_Klienta\" = " + tmp.get(3) + ", " + "\"ID_Nr_Telefonu\" = " + tmp.get(4) + "WHERE \"ID_Umowy\" = " + agreementID);

                }
            } else {
                message = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message = false;
        }

        return message;
    }


    /**
     * aktualizacja klienta, funkcja zwraca true gdy nastapila pozytywna aktualizacja
     *
     * @param clientID
     * @param PESEL
     * @param forename
     * @param surname
     * @param streetAddress
     * @param homeNumber
     * @param postalCode
     * @param placeOfResidence
     * @param NIP
     * @param login
     * @param password
     * @param clientServiceID
     */
    public boolean updateClient(String clientID, String PESEL, String forename, String surname, String streetAddress, String homeNumber, String postalCode, String placeOfResidence, String NIP, String login, String password, String clientServiceID) {
        boolean message = true;
        ResultSet resultSet = null;
        ArrayList<String> tmp = new ArrayList<String>();

        tmp.add(PESEL);
        tmp.add(forename);
        tmp.add(surname);
        tmp.add(streetAddress);
        tmp.add(homeNumber);
        tmp.add(postalCode);
        tmp.add(placeOfResidence);
        tmp.add(NIP);
        tmp.add(login);
        tmp.add(password);
        tmp.add(clientServiceID);


        try {
            resultSet = statement.executeQuery("SELECT" + " " + "*" + " " + "FROM" + " " + "\"Klienci\"" + "WHERE \"ID_Klienta\" = " + clientID);
            ResultSetMetaData metadata = resultSet.getMetaData();
            int count = metadata.getColumnCount();

            ArrayList<String> info = new ArrayList<String>();

            while (resultSet.next()) {

                for (int i = 2; i <= count; i++) {
                    info.add(resultSet.getString(i));
                }
            }

            if (info.size() != 0) {
                for (int i = 0; i < tmp.size(); i++) {
                    if (tmp.get(i).equals("")) {
                        tmp.set(i, info.get(i));
                    }

                }

                statement.executeUpdate("UPDATE" + " " + "\"Klienci\"" + " " + "SET " + "\"PESEL\" = " + "'" + tmp.get(0) + "', " + "\"Imie\" = " + "'" + tmp.get(1) + "', " + "\"Nazwisko\" = " + "'" + tmp.get(2) + "', " + "\"Adres_Ulica\" = " + "'" + tmp.get(3) + "', " + "\"Adres_Nr_Mieszkania\" = " + "'" + tmp.get(4) + "', " + "\"Kod_Pocztowy\" = " + "'" + tmp.get(5) + "', " + "\"Adres_Miejscowosc\" = " + "'" + tmp.get(6) + "', " + "\"NIP\" = " + "'" + tmp.get(7) + "', " + "\"Login\" = " + "'" + tmp.get(8) + "', " + "\"Password\" = " + "'" + tmp.get(9) + "', " + "\"ID_Obslugi_Klienta\" = " + "'" + tmp.get(10) + "'" + " WHERE \"ID_Klienta\" = " + clientID);

            } else {
                message = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message = false;
        }

        return message;
    }


    /**
     * aktualizacja reklamacji, funkcja zwraca true gdy nastapila pozytywna aktualizacja
     *
     * @param complaintID
     * @param complaintNumber
     * @param descriptionOfComplaint
     * @param dateOfComplaint
     * @param statusOfComplaint
     * @param clientID
     * @param agreementID
     */
    public boolean updateComplaint(String complaintID, String complaintNumber, String descriptionOfComplaint, String dateOfComplaint, String statusOfComplaint, String clientID, String agreementID) {
        boolean message = true;
        ResultSet resultSet = null;
        ArrayList<String> tmp = new ArrayList<String>();

        tmp.add(complaintNumber);
        tmp.add(descriptionOfComplaint);
        tmp.add(dateOfComplaint);
        tmp.add(statusOfComplaint);
        tmp.add(clientID);
        tmp.add(agreementID);

        try {
            resultSet = statement.executeQuery("SELECT" + " " + "*" + " " + "FROM" + " " + "\"Reklamacje\"" + "WHERE \"ID_Reklamacji\" = " + complaintID);
            ResultSetMetaData metadata = resultSet.getMetaData();
            int count = metadata.getColumnCount();

            ArrayList<String> info = new ArrayList<String>();

            while (resultSet.next()) {

                for (int i = 2; i <= count; i++) {
                    info.add(resultSet.getString(i));
                }
            }

            if (info.size() != 0) {
                for (int i = 0; i < tmp.size(); i++) {
                    if (tmp.get(i).equals("")) {
                        tmp.set(i, info.get(i));
                    }

                }

                //Wycinanie czasu
                if (tmp.get(2).length() > 10) {

                    tmp.set(2, tmp.get(2).split(" ")[0]);
                    System.out.println("Po wycieciu: " + tmp.get(2));
                    statement.executeUpdate("UPDATE" + " " + "\"Reklamacje\"" + " " + "SET " + "\"Nr_reklamacji\" = " + "'" + tmp.get(0) + "', " + "\"Opis_reklamacji\" = " + "'" + tmp.get(1) + "', " + "\"Data_Reklamacji\" = " + "TO_DATE(" + "'" + tmp.get(2) + "'" + ", 'YYYY-MM-DD')" + ", " + "\"Status\" = " + "'" + tmp.get(3) + "', " + "\"ID_Klienta\" = " + tmp.get(4) + ", " + "\"ID_Umowy\" = " + tmp.get(5) + " WHERE \"ID_Reklamacji\" = " + complaintID);
                } else {
                    statement.executeUpdate("UPDATE" + " " + "\"Reklamacje\"" + " " + "SET " + "\"Nr_reklamacji\" = " + "'" + tmp.get(0) + "', " + "\"Opis_reklamacji\" = " + "'" + tmp.get(1) + "', " + "\"Data_Reklamacji\" = " + "TO_DATE(" + "'" + tmp.get(2) + "'" + ", 'DD-MM-YYYY')" + ", " + "\"Status\" = " + "'" + tmp.get(3) + "', " + "\"ID_Klienta\" = " + tmp.get(4) + ", " + "\"ID_Umowy\" = " + tmp.get(5) + " WHERE \"ID_Reklamacji\" = " + complaintID);

                }


            } else {
                message = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message = false;
        }

        return message;
    }


    /**
     * Aktualizacja encji brydzujacej Promocja, funkcja zwraca true gdy aktualizacja przebiegla pomyslnie
     *
     * @param ID
     * @param promotionID
     * @param clientID
     */
    public boolean updatePromotion(String ID, String promotionID, String clientID) {
        boolean message = true;
        ResultSet resultSet = null;
        ArrayList<String> tmp = new ArrayList<String>();

        tmp.add(promotionID);
        tmp.add(clientID);

        try {
            resultSet = statement.executeQuery("SELECT" + " " + "*" + " " + "FROM" + " " + "\"Promocja\"" + "WHERE \"ID_Promocja\" = " + ID);
            ResultSetMetaData metadata = resultSet.getMetaData();
            int count = metadata.getColumnCount();

            ArrayList<String> info = new ArrayList<String>();

            while (resultSet.next()) {

                for (int i = 2; i <= count; i++) {
                    info.add(resultSet.getString(i));
                }
            }

            if (info.size() != 0) {

                for (int i = 0; i < tmp.size(); i++) {
                    if (tmp.get(i).equals("")) {
                        tmp.set(i, info.get(i));
                    }

                }

                statement.executeUpdate("UPDATE" + " " + "\"Promocja\"" + " " + "SET " + "\"ID_promocji\" = " + tmp.get(0) + ", " + "\"ID_Klienta\" = " + tmp.get(1) + " WHERE \"ID_Promocja\" = " + ID);

            } else {
                message = false;
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            message = false;
        }

        return message;
    }


    public static void main(String[] args) {

    }


}
