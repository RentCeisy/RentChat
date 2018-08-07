package RentChat.Client.auth;

import RentChat.Client.MainApp;
import RentChat.db.DbHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class RegController implements Initializable {

    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField rePasswordField;
    @FXML
    private Label labelReg;

    public void loginButtonAction() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = MainApp.getStage();
        stage.setScene(scene);
        stage.show();
    }

    public void registerButtonAction() throws NoSuchAlgorithmException, IOException {
        String login = loginTextField.getText();
        String pass = passwordField.getText();
        String rePass = rePasswordField.getText();
        if(pass.equals(rePass)) {
            DbHelper dbHelper = new DbHelper();
            if(dbHelper.checkLogin(login)) {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
                byte[] passBytes = pass.getBytes("UTF-8");
                byte[] passHash = messageDigest.digest(passBytes);
                StringBuilder sb = new StringBuilder();
                for (byte b : passHash) {
                    sb.append(String.format("%04x", b));
                }
                dbHelper.registarionUser(login, sb.toString());
                Parent parent = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = MainApp.getStage();
                stage.setScene(scene);
                stage.show();
            } else {
                labelReg.setText("Такой Логин существует");
            }
        } else {
            labelReg.setText("Пароли не совпадают");

        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
