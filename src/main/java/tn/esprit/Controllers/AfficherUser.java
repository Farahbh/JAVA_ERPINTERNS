package tn.esprit.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AfficherUser implements Initializable {


    @FXML
    private VBox cardContainer;

    public AfficherUser() {

        cardContainer = new VBox();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public VBox getCardContainer() {

        return cardContainer;
    }
}


