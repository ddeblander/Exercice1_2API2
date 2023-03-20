package model;

import metier.Cours;
import metier.Formateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormateurModelDB implements DAO
{
    List<Formateur> lForm;
    private Connection dbConnect;

    public FormateurModelDB()
    {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            System.exit(1);
        }
        lForm=new ArrayList<>();
    }
    public List<Formateur> getFormateurByCours(Cours c)
    {
        if(!lForm.isEmpty())
        {
            lForm= new ArrayList<>();
        }

        String query1="select distinct APIFORMATEUR.id,APIFORMATEUR.matricule,APIFORMATEUR.nom,APIFORMATEUR.prenom from APIFORMATEUR, APISESSIONCOURS, APIINFOS " +
                "where APIINFOS.idsessioncours = APISESSIONCOURS.id and APISESSIONCOURS.idcours= ? and APIFORMATEUR.id=APIINFOS.idformateur";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1))
        {
            pstm.setInt(1,c.getId());

            try (ResultSet rs = pstm.executeQuery())
            {
                while(rs.next())
                {
                    int id = rs.getInt("ID");
                    String matricule = rs.getString("MATRICULE");
                    String nom = rs.getString("NOM");
                    String prenom = rs.getString("PRENOM");
                    lForm.add(new Formateur(id,matricule,nom,prenom));
                }
                return lForm;
            }


        } catch (SQLException e) {
            System.out.println("erreur " + e);

        }
        DBConnection.closeConnection();
        return null;
    }

    @Override
    public Object add(Object o) {
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
    public Object getByID(int id) {
        return null;
    }
}
