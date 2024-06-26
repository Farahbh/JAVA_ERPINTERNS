package tn.esprit.services;

import tn.esprit.interfaces.IService;
import tn.esprit.models.User;
import tn.esprit.utils.MyDataBase;


import java.sql.*;
import java.util.ArrayList;

public class ServiceUser implements IService<User> {

    private Connection cnx;

    public ServiceUser() {
        cnx = MyDataBase.getInstance().getCnx();

    }

    public boolean deleteUser(int id) {
        String query = "DELETE FROM `User` WHERE id = ?";

        try {
            PreparedStatement pstm = cnx.prepareStatement(query);

            // Paramètre pour la requête préparée
            pstm.setInt(1, id);

            // Exécution de la suppression
            int rowsDeleted = pstm.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Utilisateur supprimé avec succès !");
                return true; // Retourne true si l'utilisateur est supprimé avec succès
            } else {
                System.out.println("Aucun utilisateur trouvé avec l'ID spécifié !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
        }
        return false; // Retourne false si la suppression échoue
    }


    @Override
    public void add(User user) {

        //1-req sql INSERT
        //2-executer req
        String qry = "INSERT INTO `User`(`firstname`, `lastname`, `email`, `num_tel`, `password`, `is_active`, `matricule`,`date_naissance`,`verification_token`, `isemailverified`, `resetpasswordcode`, `role`, `isconnected`, `bio`, `profile`, `imageprofile`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);

            pstm.setString(1, user.getFirstname());
            pstm.setString(2, user.getLastname());
            pstm.setString(3, user.getEmail());
            pstm.setInt(4, user.getNum_tel());
            pstm.setString(5, user.getPassword());
            pstm.setInt(6, user.getIs_active());
            pstm.setString(7, user.getMatricule());


            pstm.setDate(8, java.sql.Date.valueOf(user.getDate_naissance()));
            pstm.setString(9, user.getVerification_token());
            pstm.setInt(10, user.getIsemailverified());
            pstm.setString(11, user.getResetpasswordcode());
            pstm.setString(12, user.getRole());
            pstm.setInt(13, user.getIsconnected());
            pstm.setString(14, user.getBio());
            pstm.setString(15, user.getProfile());
            pstm.setString(16, user.getImageprofile());


            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public ArrayList<User> getAll() {
        //1-req SELECT
        //2-recuperation de la base de donné remplissage dans Array
        //3-retour du tableau done
        ArrayList<User> users = new ArrayList<>();
        String qry = "SELECT * FROM `User`";
        try {
            Statement stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setFirstname(rs.getString("firstname"));
                u.setLastname(rs.getString("lastname"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setMatricule(rs.getString("matricule"));
                u.setVerification_token(rs.getString("verification_token"));
                u.setRole(rs.getString("role"));
                u.setBio(rs.getString("bio"));
                u.setProfile(rs.getString("profile"));
                u.setImageprofile(rs.getString("imageprofile"));
                u.setResetpasswordcode(rs.getString("resetpasswordcode"));
                u.setNum_tel(rs.getInt("num_tel"));
                u.setIs_active(rs.getInt("is_active"));
                u.setIsemailverified(rs.getInt("isemailverified"));
                u.setIsconnected(rs.getInt("isconnected"));


                users.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return users;
    }


    public boolean updateUser(User user) {
        // Récupération de la connexion à la base de données
        MyDataBase myDataBase = MyDataBase.getInstance();
        Connection cnx = myDataBase.getCnx();

        // Requête SQL pour la mise à jour d'un utilisateur
        String query = "UPDATE `User` SET firstname = ?, lastname = ?, email = ?, password = ?, bio = ?, role = ? WHERE id = ?";

        try {
            PreparedStatement pstm = cnx.prepareStatement(query);

            // Paramètres pour la requête préparée
            pstm.setString(1, user.getFirstname());
            pstm.setString(2, user.getLastname());
            pstm.setString(3, user.getEmail());
            pstm.setString(4, user.getPassword());
            pstm.setString(5, user.getBio());
            pstm.setString(6, user.getRole());
            pstm.setInt(7, user.getId());

            // Exécution de la mise à jour
            int rowsUpdated = pstm.executeUpdate();

            // Vérification si la mise à jour a été effectuée avec succès
            if (rowsUpdated > 0) {
                System.out.println("Utilisateur mis à jour avec succès !");
                return true; // Retourne true si la mise à jour a réussi
            } else {
                System.out.println("Échec de la mise à jour de l'utilisateur !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de l'utilisateur : " + e.getMessage());
        } finally {

        }
        return false; // Retourne false en cas d'erreur lors de la mise à jour
    }
}



