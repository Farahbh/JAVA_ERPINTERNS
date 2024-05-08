package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.User;
import tn.esprit.services.ServiceUser;

public class ProfilUser {

    private User user; // Utilisateur actuellement connecté
    private ServiceUser serviceUser; // Service pour la gestion des utilisateurs

    @FXML
    private TextField tfFirstname;

    @FXML
    private TextField tfLastname;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfNumtel;

    @FXML
    private Button saveChangesButton;

    public ProfilUser(User user) {
        this.user = user;
        serviceUser = new ServiceUser();
    }

    public void setEmail(String email) {
        tfEmail.setText(email);
    }
    @FXML
    public void initialize(String Email) {
        // Initialiser les champs avec les informations de l'utilisateur
        tfFirstname.setText(user.getFirstname());
        tfLastname.setText(user.getLastname());
        tfEmail.setText(user.getEmail());
        tfNumtel.setText(String.valueOf(user.getNum_tel()));
    }

    @FXML
    void saveChanges() {
        // Mettre à jour les informations de l'utilisateur avec les valeurs des champs
        user.setFirstname(tfFirstname.getText());
        user.setLastname(tfLastname.getText());
        user.setEmail(tfEmail.getText());
        user.setNum_tel(Integer.parseInt(tfNumtel.getText()));

        // Mettre à jour l'utilisateur dans la base de données
        boolean updateSuccessful = serviceUser.updateUser(user);
        if (updateSuccessful) {
            // Fermer la fenêtre de profil après la mise à jour
            Stage stage = (Stage) saveChangesButton.getScene().getWindow();
            stage.close();
        } else {
            // Afficher un message d'erreur en cas d'échec de la mise à jour
            System.out.println("Erreur lors de la mise à jour de l'utilisateur.");
        }
    }
}
