package model;

import metier.Cours;
import metier.Formateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoursModelDB implements DAO
{

    List<Cours> lCours;
    int id;
    String codecours;
    String description;
    int heures;
    private Connection dbConnect;
    public CoursModelDB()
    {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            System.exit(1);
        }
        lCours= new ArrayList<>();
    }


    @Override
    public Cours add(Object o) {
        Cours c=(Cours) o;

        String query1 = "insert into EXO1_COURS(CODE,description,heures) values(?,?,?)";
        String query2 = "select * from EXO1_COURS where CODE= ? and description =? and heures =?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2))
        {


            pstm1.setString(1,c.getCodeCours());
            pstm1.setString(2,c.getDescription());
            pstm1.setInt(3,c.getHeures());
            int res=pstm1.executeUpdate();
            System.out.println("res"+res);

            pstm2.setString(1, c.getCodeCours());
            pstm2.setString(2, c.getDescription());
            pstm2.setInt(3, c.getHeures());
            try (ResultSet rs = pstm2.executeQuery()) {

                if (rs.next()) {
                    int nc = rs.getInt(1);
                    System.out.println("numero de cours inséré =" + nc);

                } else {
                    System.out.println("erreur lors de l'insertion ,numero de cours introuvable");
                }
                DBConnection.closeConnection();

            }


        }catch (Exception e)
        {
            DBConnection.closeConnection();
            System.out.println(e.toString());
        }

        return null;
    }

    @Override
    public boolean remove(Object o) {
        Cours c=(Cours) o;
        String query = "delete  from EXO1_COURS   where idcours = ?";
        try( PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,c.getId());
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
    public boolean update(Object o)
    {
        Cours c=(Cours) o;
        String query = "update EXO1_COURS set heures = ? ,description= ?  where code = ?";
        try( PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,c.getHeures());
            pstm.setString(2,c.getDescription());
            pstm.setString(3,c.getCodeCours());
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
    public List getAll()
    {
        if(!lCours.isEmpty())
        {
            lCours= new ArrayList<>();
        }

        String query1="select * from EXO1_COURS order by CODE";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1))
        {

            try (ResultSet rs = pstm.executeQuery())
            {
                while(rs.next())
                {
                    id = rs.getInt("IDCOURS");
                    codecours = rs.getString("CODE");
                    description = rs.getString("DESCRIPTION");
                    heures = rs.getInt("HEURES");
                    lCours.add(new Cours(id,heures,codecours,description));
                }
                return lCours;
            }


        } catch (SQLException e) {
            System.out.println("erreur " + e);

        }
        DBConnection.closeConnection();
        return null;
    }

    @Override
    public Cours getByID(int id)
    {
        String query1="select * from EXO1_COURS where IDCOURS = ?";

        try (PreparedStatement pstm = dbConnect.prepareStatement(query1))
        {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery())
            {
                while(rs.next())
                {
                    id = rs.getInt("IDCOURS");
                    codecours = rs.getString("CODE");
                    description = rs.getString("DESCRIPTION");
                    heures = rs.getInt("HEURES");
                    return new Cours(id,heures,codecours,description);
                }

            }


        } catch (SQLException e) {
            System.out.println("erreur " + e);

        }
        DBConnection.closeConnection();
        return null;
    }

    @Override
    public Cours read(Object rech) {
        Cours c=(Cours) rech;
        return getByID(c.getId());
    }


    public Cours getByCodeCours(String cc) {
        String query1="select * from APICOURS where CODE like ?";

        try (PreparedStatement pstm = dbConnect.prepareStatement(query1))
        {
            pstm.setString(1, cc);
            try (ResultSet rs = pstm.executeQuery())
            {
                while(rs.next())
                {
                    id = rs.getInt("IDCOURS");
                    codecours = rs.getString("CODE");
                    description = rs.getString("DESCRIPTION");
                    heures = rs.getInt("HEURES");
                    return new Cours(id,heures,codecours,description);
                }

            }


        } catch (SQLException e) {
            System.out.println("erreur " + e);

        }
        DBConnection.closeConnection();
        return null;
    }
}
