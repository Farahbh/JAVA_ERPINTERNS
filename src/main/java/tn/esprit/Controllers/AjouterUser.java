package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import tn.esprit.models.User;
import tn.esprit.services.ServiceUser;

import java.io.IOException;
import java.util.ArrayList;

public class AjouterUser {
    ServiceUser su = new ServiceUser();
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
    private Button afficher;




    @FXML
    public void AjouterUser(ActionEvent event) {
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
    void AfficherUser(ActionEvent event) throws IOException {
        try {
            // Chargez la vue AfficherUser.fxml
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherUser.fxml"));

            AfficherUser controller = new AfficherUser();

            // Obtenez la référence au conteneur des cartes dans le contrôleur
            VBox cardContainer = controller.getCardContainer();

            // Obtenez la liste des utilisateurs depuis le service
            ServiceUser serviceUser = new ServiceUser();
            ArrayList<User> users = serviceUser.getAll();

            // Créez des cartes utilisateur pour chaque utilisateur et ajoutez-les à cardContainer
            for (User user : users) {
                VBox card = createCard(user); // Méthode pour créer une carte utilisateur
                cardContainer.getChildren().add(card);
            }




             // Affichez la fenêtre après avoir ajouté toutes les cartes
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

            } catch(IOException e){
                throw new RuntimeException(e);
            }
        }



    private VBox createCard (User user){

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



}


