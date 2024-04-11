package tn.esprit;
import tn.esprit.models.User;
import tn.esprit.services.ServiceUser;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        Date date_naissance = new Date(2024 - 1900, 10 - 1, 15);
        User u = new User("benhenda","farah","farah.benhenda@esprit.tn","12345FARAH","FUT1234534","1234","intern","developper","fullstuck","","12345",21543876,1,1,1, date_naissance);
        //User u1 = new User (10,"test","test user","braiekselim43@gmail.com","$2y$13$KGLbNrN3CyZeBXQWuJXWQ.lze5n06nvVjp7MNvGEfu3bcYJ8eVjgy","FU12345","1234","intern","developper","fullstack","image","12345",123456, 1,1,1);

        ServiceUser su = new ServiceUser();
        su.add(u);
        //su.update(u1);
        //su.delete(13);

        //System.out.println(su.getAll());
    }
}


