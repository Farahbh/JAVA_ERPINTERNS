package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tn.esprit.models.User;
import tn.esprit.services.ServiceUser;

import java.sql.Date;

public class AjouterUser {
    ServiceUser su  = new ServiceUser();
    @FXML
    private TextField tfFirstname;

    @FXML
    private TextField tfLastname;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField date_naissance;
    @FXML
    private TextField tfNumTel;




    @FXML
    void ajouterUser(ActionEvent event) {

        User u = new User();

        u.setFirstname(tfFirstname.getText());
        u.setLastname(tfLastname.getText());
        u.setEmail(tfEmail.getText());
        u.setPassword(tfPassword.getText());




        Date dateNaissance = Date.valueOf(date_naissance.getText());
        u.setDate_naissance(dateNaissance);



        u.setNum_tel(Integer.parseInt(tfNumTel.getText()));


        su.add(u);

    }


    public void afficheruser(ActionEvent actionEvent) {
    }
}

