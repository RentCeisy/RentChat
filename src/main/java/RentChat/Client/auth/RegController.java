package RentChat.Client.auth;

import RentChat.Client.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegController implements Initializable {

    public void loginButtonAction() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = MainApp.getStage();
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
