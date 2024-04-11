package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tn.esprit.models.User;
import tn.esprit.services.ServiceUser;

public class AjouterUser {
    ServiceUser sp  = new ServiceUser();
    @FXML
    private TextField tfFirstname;

    @FXML
    private TextField tfLastname;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfDateNaissance;
    @FXML
    private TextField tfNumTel;



    @FXML
    void ajouterPersonne(ActionEvent event) {

        User u = new User();

        u.setFirstname(tfFirstname.getText());
        u.setLastname(tfLastname.getText());
        u.setEmail(tfEmail.getText());
        u.setPassword(tfPassword.getText());
        u.getDate_naissance();
        u.setNum_tel(Integer.parseInt(tfNumTel.getText()));


        sp.add(u);

    }

}

