package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.models.User;
import tn.esprit.services.ServiceUser;

import java.io.IOException;
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
    private DatePicker date_naissance;
    @FXML
    private TextField tfNumTel;
    @FXML
    private TextField tfisactive;
    @FXML
    private TextField tfMatricule;
    @FXML
    private TextField tfVérificationtoken;
    @FXML
    private TextField tfisemailverified;
    @FXML
    private TextField tfresetpasswordcode;
    @FXML
    private TextField tfrole;
    @FXML
    private TextField tfisconnected;
    @FXML
    private TextField tfbio;
    @FXML
    private TextField tfProfil;
    @FXML
    private TextField tfImageprofil;






    @FXML
    void ajouterUser(ActionEvent event) {

        User u = new User();

        u.setFirstname(tfFirstname.getText());
        u.setLastname(tfLastname.getText());
        u.setEmail(tfEmail.getText());
        u.setNum_tel(Integer.parseInt(tfNumTel.getText()));

        u.setPassword(tfPassword.getText());
        //Date dateNaissance = Date.valueOf(date_naissance.getText());
        u.setDate_naissance(date_naissance.getValue());
        u.setMatricule(tfMatricule.getText());
        u.setBio(tfbio.getText());
        u.setIs_active(Integer.parseInt(tfisactive.getText()));
        u.setIsconnected(Integer.parseInt(tfisconnected.getText()));
        u.setIsemailverified(Integer.parseInt(tfisemailverified.getText()));
        u.setResetpasswordcode(tfresetpasswordcode.getText());
        u.setRole(tfrole.getText());
        u.setImageprofile(tfImageprofil.getText());
        u.setVerification_token(tfVérificationtoken.getText());
        u.setProfile(tfProfil.getText());


        su.add(u);

        tfFirstname.clear();
        tfLastname.clear();
        tfNumTel.clear();
        tfbio.clear();
        tfPassword.clear();
        tfEmail.clear();
        tfisactive.clear();
        tfisconnected.clear();
        tfisemailverified.clear();
        tfMatricule.clear();
        tfVérificationtoken.clear();
        tfrole.clear();
        tfProfil.clear();
        tfresetpasswordcode.clear();
        tfisactive.clear();
        tfImageprofil.clear();


    }

    @FXML
    void afficherUser(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherUser.fxml"));

        try {
            Parent root = loader.load();
            AfficherUser ap = loader.getController();

            ap.setLbUser(su.getAll().toString());



        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }


}

