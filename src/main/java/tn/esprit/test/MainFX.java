package tn.esprit.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.User;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterUser.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);

            primaryStage.setTitle("Ajouter un utilisateur ");
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
        public void handleModifyButtonAction() {
            // Créer une nouvelle fenêtre de modification de l'utilisateur
            Stage modificationStage = new Stage();
            VBox modificationLayout = new VBox();
            Scene modificationScene = new Scene(modificationLayout, 400, 300);

            // Ajouter des champs de saisie pour les nouvelles informations de l'utilisateur
            TextField newFirstNameField = new TextField();
            // Ajouter d'autres champs de saisie pour les autres informations de l'utilisateur

            // Créer un bouton de confirmation pour appliquer les modifications
            Button confirmButton = new Button("Confirmer");
            confirmButton.setOnAction(e -> {
                // Récupérer les nouvelles informations saisies par l'utilisateur
                String newFirstName = newFirstNameField.getText();
                // Récupérer d'autres nouvelles informations
                // Mettre à jour les informations de l'utilisateur dans la base de données ou dans votre système backend
                // Fermer la fenêtre de modification
                modificationStage.close();
            });

            // Ajouter les champs de saisie et le bouton de confirmation au layout
            modificationLayout.getChildren().addAll(new Label("Nouveau prénom:"), newFirstNameField, confirmButton);

            // Définir le titre et afficher la fenêtre de modification
            modificationStage.setTitle("Modifier Utilisateur");
            modificationStage.setScene(modificationScene);
            modificationStage.show();
        }

    }







