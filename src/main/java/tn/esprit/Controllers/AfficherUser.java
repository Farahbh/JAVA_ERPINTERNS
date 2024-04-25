package tn.esprit.Controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.User;
import tn.esprit.services.ServiceUser;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AfficherUser implements Initializable {

    ArrayList<Pane> userPanesList = new ArrayList<>();

    @FXML
    private VBox cardContainer;
    @FXML
    private GridPane gridPane;


    ServiceUser su = new ServiceUser();
   
    @FXML
    private Button afficher;


    public AfficherUser() {

        cardContainer = new VBox();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceUser serviceUser = new ServiceUser();
        ArrayList<User> users = serviceUser.getAll();

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        // Ajouter les cartes utilisateur au GridPane
        int columnIndex = 0;
        int rowIndex = 0;
        // Créer et ajouter des cartes utilisateur pour chaque utilisateur
        for (User user : users) {
            Pane userCard = createCard(user);
            gridPane.add(userCard, columnIndex, rowIndex);

            Button modifyButton = new Button("Modifier");
            modifyButton.setOnAction(event -> modifierUtilisateur(user)); // Appel de la méthode pour modifier l'utilisateur

            gridPane.add(modifyButton, columnIndex, rowIndex + 1);

            columnIndex++;
            if (columnIndex == 3) {
                columnIndex = 0;
                rowIndex++;
            }
        }
    }

    // Méthode pour créer une carte utilisateur
    private VBox createCard(User user) {
        VBox userPane = new VBox();
        userPane.setPrefSize(500, 200);
        userPane.getStyleClass().add("user-card");

        userPane.setStyle("-fx-background-color: #EB91BE ; -fx-border-color: #cccccc; -fx-border-width: 1px;   -fx-border-radius: 5px;");
        Integer userId = user.getId();
        int id = userId != null ? userId.intValue() : 0;
        // Créer et configurer des labels pour afficher les détails de l'utilisateur
        Label IDLabel = new Label("ID: " + user.getId());
        Label firstNameLabel = new Label("Firstname: " + user.getFirstname());
        firstNameLabel.setLayoutX(10);
        firstNameLabel.setLayoutY(10);

        Label lastNameLabel = new Label("Lastname: " + user.getLastname());
        lastNameLabel.setLayoutX(10);
        lastNameLabel.setLayoutY(30);

        Label emailLabel = new Label("Email: " + user.getEmail());
        emailLabel.setLayoutX(10);
        emailLabel.setLayoutY(50);

        Label NumTelLabel = new Label("Numtel: " + user.getNum_tel());
        NumTelLabel.setLayoutX(10);
        NumTelLabel.setLayoutY(70);

        Label PasswordLabel = new Label("Password: " + user.getPassword());
        PasswordLabel.setLayoutX(10);
        PasswordLabel.setLayoutY(90);

        Label IsActivaLabel = new Label("is_active: " + user.getIs_active());
        IsActivaLabel.setLayoutX(10);
        IsActivaLabel.setLayoutY(110);

        Label MatriculeLabel = new Label("Matricule: " + user.getMatricule());
        MatriculeLabel.setLayoutX(10);
        MatriculeLabel.setLayoutY(130);

        Label VérificationtokenLabel = new Label("VérificationToken: " + user.getVerification_token());
        VérificationtokenLabel.setLayoutX(10);
        VérificationtokenLabel.setLayoutY(150);

        Label DatedenaissanceLabel = new Label("Date de naissance: " + user.getDate_naissance());
        DatedenaissanceLabel.setLayoutX(10);
        DatedenaissanceLabel.setLayoutY(170);

        Label IsemailverifiedLabel = new Label("Isemailverified: " + user.getIsemailverified());
        IsemailverifiedLabel.setLayoutX(10);
        IsemailverifiedLabel.setLayoutY(190);

        Label ResetpasswordcodeLabel = new Label("Resetpasswordcode: " + user.getResetpasswordcode());
        ResetpasswordcodeLabel.setLayoutX(10);
        ResetpasswordcodeLabel.setLayoutY(210);

        Label RoleLabel = new Label("Role: " + user.getRole());
        RoleLabel.setLayoutX(10);
        RoleLabel.setLayoutY(230);

        Label IsConnectedLabel = new Label("IsConnected: " + user.getIsconnected());
        IsConnectedLabel.setLayoutX(10);
        IsConnectedLabel.setLayoutY(250);

        Label BioLabel = new Label("Bio: " + user.getBio());
        BioLabel.setLayoutX(10);
        BioLabel.setLayoutY(270);

        Label ProfilLabel = new Label("Profil: " + user.getProfile());
        ProfilLabel.setLayoutX(10);
        ProfilLabel.setLayoutY(290);

        Label ImageProfilLabel = new Label("ImageProfil: " + user.getImageprofile());
        ImageProfilLabel.setLayoutX(10);
        ImageProfilLabel.setLayoutY(310);

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> {
            VBox parentVBox = (VBox) deleteButton.getParent();

            // Supprimer le panneau utilisateur de l'interface graphique
            gridPane.getChildren().remove(parentVBox);

            // Supprimer également le panneau utilisateur de la liste des panneaux utilisateur
            userPanesList.remove(parentVBox);

            ServiceUser serviceUser = new ServiceUser();

            boolean deletionSuccessful = serviceUser.deleteUser(userId);
            if (!deletionSuccessful) {
                // Gérer l'échec de la suppression, par exemple afficher un message d'erreur
                System.out.println("Erreur lors de la suppression de l'utilisateur de la base de données.");
            }
        });
        Button modifyButton = new Button("Modifier");
        modifyButton.setOnAction(event -> modifierUtilisateur(user));
        // Ajouter les labels à la carte utilisateur
        userPane.getChildren().addAll(IDLabel,firstNameLabel, lastNameLabel, emailLabel, NumTelLabel, PasswordLabel, IsActivaLabel, MatriculeLabel, VérificationtokenLabel, DatedenaissanceLabel,
                IsemailverifiedLabel, ResetpasswordcodeLabel, RoleLabel, IsConnectedLabel, BioLabel, ProfilLabel, ImageProfilLabel, deleteButton);

        return userPane;
    }

    private void modifierUtilisateur(User user) {

        // Créer une nouvelle fenêtre de modification de l'utilisateur
        Stage modificationStage = new Stage();
        VBox modificationLayout = new VBox();
        Scene modificationScene = new Scene(modificationLayout, 400, 300);

        // Ajouter des champs de saisie pour les nouvelles informations de l'utilisateur
        TextField newFirstNameField = new TextField();
        TextField newLastNameField = new TextField();
        TextField newEmailField = new TextField();
        TextField newPasswordField = new TextField();
        TextField newBioField = new TextField();
        TextField newRoleField = new TextField();

        // Créer un bouton de confirmation pour appliquer les modifications
        Button confirmButton = new Button("Confirmer");
        confirmButton.setOnAction(e -> {
            // Récupérer les nouvelles informations saisies par l'utilisateur
            String newFirstName = newFirstNameField.getText();
            String newLastName = newLastNameField.getText();
            String newEmail = newEmailField.getText();
            String newPassword = newPasswordField.getText();
            String newBio = newBioField.getText();
            String newRole = newRoleField.getText();

            // Mettre à jour les propriétés de l'utilisateur
            user.setFirstname(newFirstName);
            user.setLastname(newLastName);
            user.setEmail(newEmail);
            user.setPassword(newPassword);
            user.setRole(newRole);
            user.setBio(newBio);

            // Mettre à jour l'utilisateur dans la base de données
            ServiceUser serviceUser = new ServiceUser();
            boolean updateSuccessful = serviceUser.updateUser(user);
            if (updateSuccessful) {
                // Afficher un message de confirmation ou fermer la fenêtre de modification
                modificationStage.close();
            } else {
                // Afficher un message d'erreur si la mise à jour a échoué
                System.out.println("Erreur lors de la mise à jour de l'utilisateur dans la base de données.");
            }
        });

        // Ajouter les champs de saisie et le bouton de confirmation au layout
        modificationLayout.getChildren().addAll(
                new Label("Firstname:"), newFirstNameField,
                new Label("Lastname:"), newLastNameField,
                new Label("Email:"), newEmailField,
                new Label("Password:"), newPasswordField,
                new Label("Role :"), newRoleField,
                new Label("Bio :"), newBioField,
                confirmButton // Ajouter le bouton de confirmation une seule fois à la fin
        );

        // Définir le titre et afficher la fenêtre de modification
        modificationStage.setTitle("Modifier Utilisateur");
        modificationStage.setScene(modificationScene);
        modificationStage.show();
    }

    public VBox getCardContainer() {

        return cardContainer;
    }





    }






