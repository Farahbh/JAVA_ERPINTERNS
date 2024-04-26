package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
    @FXML
    private TextField tfFirstname;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private Button SeConnecter;

    @FXML
    private Button Sauthentifier;

    public Login(TextField tfFirstname, PasswordField tfPassword, Button seConnecter) {
        this.tfFirstname = tfFirstname;
        this.tfPassword = tfPassword;
        SeConnecter = seConnecter;
    }

    @FXML
    void seConnecter(ActionEvent event) {
        // Méthode à exécuter lorsqu'on clique sur le bouton "Se Connecter"
        String username = tfFirstname.getText();
        String password = tfPassword.getText();

        // Écrire ici le code pour vérifier les informations de connexion et effectuer l'authentification
        // Par exemple, vous pouvez afficher les informations de connexion dans la console pour le moment :
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }

    @FXML
    void sAuthentifier(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/S'authentifier.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Obtenir la scène actuelle à partir de l'événement
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Afficher la nouvelle scène
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer l'exception en cas d'erreur de chargement du fichier FXML
        }

    }

    public void AfficherUser(ActionEvent event) {
    }

    public void AjouterUser(ActionEvent event) {
    }
}