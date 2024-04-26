package tn.esprit.models;


import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private  int id;

    private String firstname , lastname,email,password,matricule, verification_token,resetpasswordcode, role,bio,profile,imageprofile;

     private int num_tel,is_active,isconnected,isemailverified;
     private LocalDate date_naissance;




    public User (String firstname, String lastname, String email, String password,
                 String matricule, String verification_token, String role, String bio, String profile,
                 String imageprofile, String resetpasswordcode, int num_tel, int is_active, int isemailverified , int isconnected, DatePicker date_naissance)
            throws IllegalArgumentException {
        {
            if (firstname == null || firstname.isEmpty()) {
                throw new IllegalArgumentException("Le prénom ne peut pas être vide.");
            }
            if (lastname == null || lastname.isEmpty()) {
                throw new IllegalArgumentException("Le nom ne peut pas être vide.");
            }
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("L'email ne peut pas être vide.");
            }
            if (password == null || password.isEmpty()) {
                throw new IllegalArgumentException("Le mot de passe ne peut pas être vide.");
            }




            if (password.length() < 8) {
                throw new IllegalArgumentException("Le mot de passe doit avoir au moins 8 caractères.");
            }

            if (firstname.length() >10) {
                throw new IllegalArgumentException("Le nom  doit avoir au plus 10 caractères.");
            }
            if (lastname.length() > 10) {
                throw new IllegalArgumentException("Le prenom doit avoir au plus 10 caractères.");
            }
            if (!isValidEmail(email)) {
                throw new IllegalArgumentException("L'email n'a pas un format valide.");
            }
            this.firstname = firstname;
            this.lastname = lastname;
            this.email = email;
            this.password = password;
            this.matricule = matricule;
            this.verification_token = verification_token;
            this.role = role;
            this.bio = bio;
            this.profile = profile;
            this.imageprofile = imageprofile;
            this.resetpasswordcode = resetpasswordcode;
            this.num_tel = num_tel;
            this.is_active = is_active;
            this.isemailverified = isemailverified;
            this.isconnected = isconnected;
            this.date_naissance = date_naissance.getValue();
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public User() {

    }

    public LocalDate getDate_naissance() {
        return this.date_naissance;
    }





    public int getId() {
        return id;
    }


    public int getIsconnected() {
        return isconnected;
    }

    public void setIsconnected(int isconnected) {
        this.isconnected = isconnected;
    }


    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getVerification_token() {
        return verification_token;
    }

    public String getRole() {
        return role;
    }

    public String getBio() {
        return bio;
    }

    public String getProfile() {
        return profile;
    }

    public String getImageprofile() {
        return imageprofile;
    }

    public String getResetpasswordcode() {
        return resetpasswordcode;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public int getIs_active() {
        return is_active;
    }

    public int getIsemailverified() {
        return isemailverified;
    }


    public void setId(int id) {
        this.id = id;
    }



    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setVerification_token(String verification_token) {
        this.verification_token = verification_token;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setImageprofile(String imageprofile) {
        this.imageprofile = imageprofile;
    }

    public void setResetpasswordcode(String resetpasswordcode) {
        this.resetpasswordcode = resetpasswordcode;
    }
    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public void setIsemailverified(int isemailverified) {
        this.isemailverified = isemailverified;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", matricule='" + matricule + '\'' +
                ", verification_token='" + verification_token + '\'' +
                ", resetpasswordcode='" + resetpasswordcode + '\'' +
                ", role='" + role + '\'' +
                ", bio='" + bio + '\'' +
                ", profile='" + profile + '\'' +
                ", imageprofile='" + imageprofile + '\'' +
                ", num_tel=" + num_tel +
                ", is_active=" + is_active +
                ", isconnected=" + isconnected +
                ", isemailverified=" + isemailverified +
                ", date_naissance=" + date_naissance +
                '}';
    }


    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }
}




