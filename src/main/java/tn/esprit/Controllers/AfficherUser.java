package tn.esprit.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AfficherUser implements Initializable {

    @FXML
    private Label lbUser;

    public void setLbUser(String lbUsers) {
        this.lbUser.setText(lbUsers);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

