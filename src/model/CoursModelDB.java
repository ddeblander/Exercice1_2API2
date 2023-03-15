package model;

import metier.Cours;

import java.sql.Connection;
import java.util.List;

public class CoursModelDB implements DAO
{


    public CoursModelDB()
    {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            System.exit(1);
        }
    }

    private Connection dbConnect;
    @Override
    public Cours add(Object o) {
        Cours c=(Cours) o;
        String query1 = "insert into APICOURS(codecours,description,heures) values(?,?,?)";
        String query2 = "select idclient from APICOURS where nom= ? and prenom =? and tel =?";

        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean update(Object o) {
        return false;
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Cours getByCodeCours(int id) {
        return null;
    }
}
