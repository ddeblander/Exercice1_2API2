package model;

import metier.Cours;
import metier.Formateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class FormateurModelDB implements DAO<Formateur>
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
    public Formateur add(Formateur o)
    {
        String query1="insert into APIFORMATEUR(matricule,nom,prenom)" +
                "values(?,?,?)";
        String query2="select * from APIFORMATEUR where MATRICULE=? and NOM=? and PRENOM=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2))
        {
            pstm.setString(1,o.getMatricule());
            pstm.setString(2,o.getNom());
            pstm.setString(3,o.getPrenom());
            if(pstm.executeUpdate()!=1)
            {
                return null;
            }

                pstm2.setString(1,o.getMatricule());
                pstm2.setString(2,o.getNom());
                pstm2.setString(3,o.getPrenom());

                try (ResultSet rs = pstm2.executeQuery()) {

                    if (rs.next()) {
                        int nc = rs.getInt(1);
                        System.out.println("numero de formateur inséré =" + nc);
                        o.setId(nc);
                        return o;

                    } else {
                        System.out.println("erreur lors de l'insertion ,formateur introuvable");
                    }
                }
        }catch (Exception e)
        {
            System.out.println(e.toString()+" erreur BDD select");
        }
        return null;
    }

    @Override
    public boolean remove(Formateur o) {
        String query = "delete  from apiformateur   where id = ?";
        try( PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,o.getId());
            pstm.executeQuery();
            return true;
        }
        catch (SQLException e) {
            System.out.println("erreur SQL =" + e);
        }
        DBConnection.closeConnection();
        return false;
    }

    @Override
    public boolean update(Formateur o) {
        String query = "update apiformateur set matricule = ? ,nom= ?,prenom = ?  where id=?";
        try( PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setString(1,o.getMatricule());
            pstm.setString(2,o.getNom());
            pstm.setString(3,o.getPrenom());
            pstm.setInt(4,o.getId());
            pstm.executeQuery();
            return true;
        }
        catch (SQLException e) {
            System.out.println("erreur SQL =" + e);
        }
        DBConnection.closeConnection();
        return false;
    }

    @Override
    public List<Formateur> getAll() {
        lForm=new ArrayList<>();
        Formateur fInsert;
        String query1="select * from APIFORMATEUR order by ID";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1))
        {

            try (ResultSet rs = pstm.executeQuery())
            {
                while(rs.next())
                {

                    fInsert=new Formateur(rs.getInt("ID"),rs.getString("MATRICULE"),
                            rs.getString("NOM"),rs.getString("PRENOM"));
                    lForm.add(fInsert);
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
    public Formateur getByID(int id)
    {
        Formateur f;
        String query1="select * from APIFORMATEUR where  ID = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1))
        {
            pstm.setInt(1,id);
            try (ResultSet rs = pstm.executeQuery())
            {

                while(rs.next())
                {

                    f=new Formateur(rs.getInt("ID"),rs.getString("MATRICULE"),
                            rs.getString("NOM"),rs.getString("PRENOM"));
                    return f;
                }
            }


        } catch (SQLException e) {
            System.out.println("erreur " + e);

        }
        DBConnection.closeConnection();
        return null;
    }

    @Override
    public Formateur read(Formateur rech) {
        return getByID(rech.getId());
    }
}
