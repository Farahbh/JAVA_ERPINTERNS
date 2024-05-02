package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.User;
import tn.esprit.services.ServiceUser;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AfficherUser1 {

    public Button Ajout;
    @FXML
    private HBox cardContainer;
    ServiceUser su = new ServiceUser();
    @FXML
    private TextField tfFirstname;
    @FXML
    public TextField tfbio;
    @FXML
    private TextField tfLastname;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;
    @FXML
    private DatePicker date_naissance;
    @FXML
    public TextField tfNumtel;
    @FXML
    public TextField tfrole;


    private ServiceUser SERVuser = new ServiceUser();

    private Pane createCard(User user) {
        // Create a Pane or any suitable container for your card
        Pane card = new Pane();
        card.getStyleClass().add("card");
        card.setPrefSize(200, 500);
        card.setOpacity(10);

        Label FirstnameLabel = new Label("FirstName: " + user.getFirstname());
        FirstnameLabel.setLayoutX(10);
        FirstnameLabel.setLayoutY(10);

        Label lastnamelabel = new Label("LastName: " + user.getLastname());
        lastnamelabel.setLayoutX(10);
        lastnamelabel.setLayoutY(30);

        Label emailLabel = new Label("Email: " + user.getEmail());
        emailLabel.setLayoutX(10);
        emailLabel.setLayoutY(50);

        Label NumTelLabel = new Label("Numtel: " + user.getNum_tel());
        NumTelLabel.setLayoutX(10);
        NumTelLabel.setLayoutY(70);


        Label IsActivaLabel = new Label("is_active: " + user.getIs_active());
        IsActivaLabel.setLayoutX(10);
        IsActivaLabel.setLayoutY(90);

        Label MatriculeLabel = new Label("Matricule: " + user.getMatricule());
        MatriculeLabel.setLayoutX(10);
        MatriculeLabel.setLayoutY(110);

        Label VérificationtokenLabel = new Label("VérificationToken: " + user.getVerification_token());
        VérificationtokenLabel.setLayoutX(10);
        VérificationtokenLabel.setLayoutY(130);

        Label DatedenaissanceLabel = new Label("Date de naissance: " + user.getDate_naissance());
        DatedenaissanceLabel.setLayoutX(10);
        DatedenaissanceLabel.setLayoutY(150);

        Label IsemailverifiedLabel = new Label("Isemailverified: " + user.getIsemailverified());
        IsemailverifiedLabel.setLayoutX(10);
        IsemailverifiedLabel.setLayoutY(170);


        Label RoleLabel = new Label("Role: " + user.getRole());
        RoleLabel.setLayoutX(10);
        RoleLabel.setLayoutY(190);

        Label IsConnectedLabel = new Label("IsConnected: " + user.getIsconnected());
        IsConnectedLabel.setLayoutX(10);
        IsConnectedLabel.setLayoutY(210);

        Label BioLabel = new Label("Bio: " + user.getBio());
        BioLabel.setLayoutX(10);
        BioLabel.setLayoutY(230);

        Label ProfilLabel = new Label("Profil: " + user.getProfile());
        ProfilLabel.setLayoutX(10);
        ProfilLabel.setLayoutY(250);
        ProfilLabel.setMaxWidth(650); // Définir une largeur maximale pour le label
        ProfilLabel.setWrapText(true); // Permettre le passage à la ligne si nécessaire

        Label ImageProfilLabel = new Label("ImageProfil: " + user.getImageprofile());
        ImageProfilLabel.setLayoutX(10);
        ImageProfilLabel.setLayoutY(300);
        ImageProfilLabel.setMaxWidth(300); // Définir une largeur maximale pour le label
        ImageProfilLabel.setWrapText(true); // Permettre le passage à la ligne si nécessaire

        Button modifyButton = new Button("Modifier");
        modifyButton.setLayoutX(10);
        modifyButton.setLayoutY(330);
        modifyButton.setOnAction(event -> modifierUtilisateur(user));

        Button deleteB = new Button("Supprimer");
        deleteB.setLayoutX(10);
        deleteB.setLayoutY(350);
        deleteB.setOnAction(event -> deletFunc(user));



        card.getChildren().addAll(FirstnameLabel, lastnamelabel, emailLabel, NumTelLabel, IsActivaLabel, MatriculeLabel, VérificationtokenLabel, DatedenaissanceLabel,
                IsemailverifiedLabel, RoleLabel, IsConnectedLabel, BioLabel, ProfilLabel, ImageProfilLabel, deleteB, modifyButton);
        return card;
    }

    private void modifierUtilisateur(User user) {
        // Créer une nouvelle fenêtre de modification de l'utilisateur
        Stage modificationStage = new Stage();
        modificationStage.setTitle("Modifier Utilisateur");

        // Créer les champs de saisie pour les nouvelles informations de l'utilisateur
        TextField newFirstNameField = new TextField(user.getFirstname());
        TextField newLastNameField = new TextField(user.getLastname());
        TextField newEmailField = new TextField(user.getEmail());
        TextField newNumTelField = new TextField(String.valueOf(user.getNum_tel()));

        Button confirmButton = new Button("Confirmer");
        confirmButton.setOnAction(e -> {
            // Mettre à jour les propriétés de l'utilisateur avec les nouvelles valeurs
            user.setFirstname(newFirstNameField.getText());
            user.setLastname(newLastNameField.getText());
            user.setEmail(newEmailField.getText());
            user.setNum_tel(Integer.parseInt(newNumTelField.getText()));

            // Mettre à jour l'utilisateur dans la base de données
            boolean updateSuccessful = SERVuser.updateUser(user);
            if (updateSuccessful) {
                // Afficher un message de confirmation ou fermer la fenêtre de modification
                modificationStage.close();
            } else {
                // Afficher un message d'erreur si la mise à jour a échoué
                System.out.println("Erreur lors de la mise à jour de l'utilisateur dans la base de données.");
            }
        });
        // Créer un layout pour la fenêtre de modification
        VBox modificationLayout = new VBox(10, newFirstNameField, newLastNameField, newEmailField, newNumTelField, confirmButton);
        modificationStage.setScene(new Scene(modificationLayout, 400, 300));
        modificationStage.show();
    }


    private void display(List<User> users) {
        cardContainer.getChildren().clear();
        for (User user : users) {
            Pane card = createCard(user);
            cardContainer.getChildren().add(card);
        }
    }

    private void deletFunc(User u) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete Item");
        alert.setContentText("Are you sure you want to delete this item?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                SERVuser.deleteUser(u.getId());
                display(SERVuser.getAll());
            }
        });
    }


    @FXML
    public void initialize() {
        display(SERVuser.getAll());
    }


    public HBox getCardContainer() {
        return cardContainer;
    }


    public void AjouterUser(ActionEvent event) {
        User u = new User();
        u.setFirstname(tfFirstname.getText());
        u.setLastname(tfLastname.getText());
        u.setEmail(tfEmail.getText());
        u.setNum_tel(Integer.parseInt(tfNumtel.getText()));
        u.setPassword(tfPassword.getText());
        u.setDate_naissance(date_naissance.getValue());
        u.setBio(tfbio.getText());
        u.setRole(tfrole.getText());

        su.add(u);


        // Effacez les champs après l'ajout de l'utilisateur
        tfFirstname.clear();
        tfLastname.clear();
        tfNumtel.clear();
        tfbio.clear();
        tfPassword.clear();
        tfEmail.clear();
        tfrole.clear();


    }

}

