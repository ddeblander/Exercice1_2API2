package demoDB;

import myconnections.DBConnection;

import java.sql.Connection;

public class GestionCours
{
    public GestionCours()
    {
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
    }


}
