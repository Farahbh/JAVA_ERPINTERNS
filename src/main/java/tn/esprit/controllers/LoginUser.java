package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tn.esprit.models.User;
import tn.esprit.services.ServiceUser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginUser {

    ServiceUser su = new ServiceUser();

    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private Button Afficher;

    @FXML
    private Button Sauthentifier;

    @FXML
    private ChoiceBox<String> roleChoiceBox;

    @FXML
    public void initialize() {
        // Ajouter des options au ChoiceBox pour le rôle
        roleChoiceBox.setItems(FXCollections.observableArrayList("Supervisor", "Intern", "Former", "Head Master"));

        // Ajouter des écouteurs d'événements pour les boutons
        Afficher.setOnAction(this::seConnecter);
        Sauthentifier.setOnAction(this::sAuthentifier);
    }




    @FXML
    void seConnecter(ActionEvent event) {

        // Méthode à exécuter lorsqu'on clique sur le bouton "Se Connecter"
        String Email = tfEmail.getText();
        String password = tfPassword.getText();
        String role = roleChoiceBox.getValue(); // Obtenir le rôle sélectionné dans le ChoiceBox

        // Écrire ici le code pour vérifier les informations de connexion et effectuer l'authentification
        // Par exemple, vous pouvez afficher les informations de connexion dans la console pour le moment :
        System.out.println("Email: " + Email);
        System.out.println("Password: " + password);
        System.out.println("Role: " + role);

        if (isEmailExist(tfEmail.getText())) {
            // L'email existe, implémentez ici la logique d'authentification
            System.out.println("Email exists");
            navigateToAfficherUser1();
        } else {
            // L'email n'existe pas, afficher un message d'erreur
            showErrorAlert("Adresse inexistante", "L'adresse email n'existe pas dans la base de données.");
        }


    }

    private boolean isEmailExist(String email) {
        try {
            List<User> existingUsers = su.getAll();
            for (User user : existingUsers) {
                if (email.equals(user.getEmail())) {
                    return true; // L'email existe
                }
            }
            return false; // L'email n'existe pas
        } catch (Exception e) {
            showErrorAlert("Erreur", e.getMessage());
            return false; // En cas d'erreur, considérer que l'email n'existe pas
        }
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void navigateToAfficherUser1() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherUser1.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Obtenir la fenêtre principale (stage)
            Stage stage = (Stage) Afficher.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            // Gérer l'exception en cas d'erreur de chargement du fichier FXML
        }
    }

    @FXML
    void sAuthentifier(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterUser.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Obtenir la scène actuelle à partir de l'événement
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Afficher la nouvelle scène
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            // Gérer l'exception en cas d'erreur de chargement du fichier FXML
        }
    }

    public void AfficherUser1(ActionEvent event) {
        // Méthode pour afficher l'utilisateur
    }

    public void AjouterUser(ActionEvent event) {
        // Méthode pour ajouter un utilisateur
    }
}
