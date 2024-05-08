package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.User;
import tn.esprit.services.ServiceUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class AjouterUser {
    ServiceUser su = new ServiceUser();
    @FXML
    private TextField captchaTextField;
    @FXML
    private Label captchaLabel;
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
    @FXML
    private Button seconnecter;

    boolean iscaptcha = true;

    @FXML
    public void AjouterUser(ActionEvent event) {

        if (tfFirstname == null || tfFirstname.getText().isEmpty()) {
            showErrorAlert("Erreur de saisie", "Veuillez saisir un prénom  valide.");
            return;
        }
        if (tfLastname == null || tfLastname.getText().isEmpty()) {
            showErrorAlert("Erreur de saisie", "Veuillez saisir un nom valide.");
            return;
        }

        if (!isValidEmail(tfEmail.getText())) {
            showErrorAlert("Erreur de saisie", "Veuillez saisir une adresse e-mail valide.");
            return;
        }

        if (tfPassword.getText().length() < 8) {
            showErrorAlert("Erreur de saisie", "Veuillez saisir un mot de passe valide.");
            return;
        }
        // Contrôle du numéro de téléphone
        if (!isValidPhoneNumber(tfNumtel.getText())) {
            showErrorAlert("Erreur de saisie", "Veuillez saisir un numéro de téléphone valide (8 chiffres).");
            return;
        }

        if (!isEmailUnique(tfEmail.getText())) {
            showErrorAlert("Erreur d'unicité", "Cet email existe déja .");
            return;
        }
        // Ajoutez l'utilisateur
        User u = new User();
        u.setFirstname(tfFirstname.getText());
        u.setLastname(tfLastname.getText());
        u.setEmail(tfEmail.getText());
        u.setNum_tel(parseInt(tfNumtel.getText()));
        u.setPassword(tfPassword.getText());
        u.setDate_naissance(date_naissance.getValue());
        u.setBio(tfbio.getText());
        u.setRole(tfrole.getText());

        String enteredCaptcha = captchaTextField.getText(); // Replace with the actual TextField for captcha input
        if (!enteredCaptcha.equals(captchaLabel.getText())) {
            showErrorAlert("Erreur de captcha", "Le code captcha saisi est incorrect.");
            iscaptcha = false;
            updateCaptcha(); // Refresh the captcha code if incorrect
        }

        boolean ajoutReussi = su.add(u);
        if (ajoutReussi && iscaptcha) {
            // Effacez les champs après l'ajout de l'utilisateur
            tfFirstname.clear();
            tfLastname.clear();
            tfNumtel.clear();
            tfbio.clear();
            tfPassword.clear();
            tfEmail.clear();
            tfrole.clear();

            // Charger la page de connexion
            chargerPageConnexion(event);
        }


    }

    private boolean isEmailUnique(String email) {
        try {
            List<User> existingUser = su.getAll();
            for (User users : existingUser) {
                if (email.equals(users.getEmail())) {
                    return false; // L'email n'est pas unique
                }
            }
            return true; // L'email est unique
        } catch (Exception e) {
            showErrorAlert("Error", e.getMessage());
            return false;
        }
    }


    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void chargerPageConnexion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Obtenir la fenêtre principale (stage)
            Stage stage = (Stage) seconnecter.getScene().getWindow();

            // Afficher la nouvelle scène
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            // Gérer l'exception en cas d'erreur de chargement du fichier FXML
        }
    }

    @FXML
    void AfficherUser1(ActionEvent event) {
        try {
            // Chargez la vue AfficherUser1.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherUser1.fxml"));
            Parent root = loader.load();

            // Obtenez une référence au contrôleur associé à la vue chargée
            AfficherUser1 controller = loader.getController();

            // Obtenez une référence au conteneur des cartes dans le contrôleur
            HBox cardContainer = controller.getCardContainer();

            // Obtenez la liste des utilisateurs depuis le service
            ServiceUser serviceUser = new ServiceUser();
            ArrayList<User> users = serviceUser.getAll();

            // Créez des cartes utilisateur pour chaque utilisateur et ajoutez-les à cardContainer
            for (User user : users) {
                VBox card = createCard(user); // Méthode pour créer une carte utilisateur
                cardContainer.getChildren().add(card);
            }

            // Affichez la fenêtre après avoir ajouté toutes les cartes
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private VBox createCard(User user) {

        VBox card = new VBox();

        card.getStyleClass().add("user-card");
        card.setUserData(user.getId());

        Label firstNameLabel = new Label("Firstname: " + user.getFirstname());
        Label lastNameLabel = new Label("Lastname: " + user.getLastname());
        Label emailLabel = new Label("Email: " + user.getEmail());
        Label NumTelLabel = new Label("Numtel: " + user.getNum_tel());
        Label PasswordLabel = new Label("Password: " + user.getPassword());
        Label IsActivaLabel = new Label("is_active: " + user.getIs_active());
        Label MatriculeLabel = new Label("Matricule: " + user.getMatricule());
        Label VérificationtokenLabel = new Label("VérificationToken: " + user.getVerification_token());
        Label DatedenaissanceLabel = new Label("Date de naissance: " + user.getDate_naissance());
        Label IsemailverifiedLabel = new Label("Isemailverified: " + user.getIsemailverified());
        Label ResetpasswordcodeLabel = new Label("Resetpasswordcode: " + user.getResetpasswordcode());
        Label RoleLabel = new Label("Role: " + user.getRole());
        Label IsConnectedLabel = new Label("IsConnected: " + user.getIsconnected());
        Label BioLabel = new Label("Bio: " + user.getBio());
        Label ProfilLabel = new Label("Profil: " + user.getProfile());
        Label ImageProfilLabel = new Label("ImageProfil: " + user.getImageprofile());


        card.getChildren().addAll(firstNameLabel, lastNameLabel, emailLabel, NumTelLabel, PasswordLabel, IsActivaLabel, MatriculeLabel, VérificationtokenLabel, DatedenaissanceLabel,
                IsemailverifiedLabel, ResetpasswordcodeLabel, RoleLabel, IsConnectedLabel, BioLabel, ProfilLabel, ImageProfilLabel);
        return card;
    }

    @FXML
    void initialize() {
        updateCaptcha();
    }

    private String generateCaptchaCode() {
        // Generate a random 4-digit captcha code
        int captchaCode = (int) (Math.random() * 9000) + 1000;
        return String.valueOf(captchaCode);
    }

    private void updateCaptcha() {
        // Update the captcha label with a new captcha code
        captchaLabel.setText(generateCaptchaCode());
    }


    private boolean isValidEmail(String email) {
        // Utiliser une expression régulière simple pour valider l'email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private boolean isValidPhoneNumber(String NumTel) {
        // Vérifier si le numéro de téléphone a exactement 8 chiffres
        return NumTel.matches("\\d{8}");
    }

}






