package RentChat.Client.login;

import RentChat.Client.MainApp;
import RentChat.db.DbHelper;
import RentChat.db.User;
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

public class LoginController implements Initializable {

    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Label labelAuth;

    public LoginController() {

    }

    public void regButtonAction() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/RegView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = MainApp.getStage();
        stage.setScene(scene);
        stage.show();
    }

    public void logButtonAction() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String login = loginTextField.getText();
        String password = passwordTextField.getText();
        byte[] passwordBytes = password.getBytes("UTF-8");;
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        byte[] passwordHash = messageDigest.digest(passwordBytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : passwordHash) {
            sb.append(String.format("%04x", b));
        }

        DbHelper dbHelper = new DbHelper();
        User user = dbHelper.getLogin(login, sb.toString());
        if(user == null) {
            labelAuth.setText("Пользователь не найден");
        } else {
            labelAuth.setText("Добро пожаловать, " + user.getLogin());
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
