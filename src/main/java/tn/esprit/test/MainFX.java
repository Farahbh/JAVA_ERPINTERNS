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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/S'authentifier.fxml"));
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


    }







