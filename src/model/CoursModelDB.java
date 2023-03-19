package model;

import metier.Cours;

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
    public CoursModelDB()
    {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            System.exit(1);
        }
        lCours= new ArrayList<>();
    }

    private Connection dbConnect;
    @Override
    public Cours add(Object o) {
        Cours c=(Cours) o;
        Scanner sc = new Scanner(System.in);
        String query1 = "insert into APICOURS(codecours,description,heures) values(?,?,?)";
        String query2 = "select * from APICOURS where codecours= ? and description =? and heures =?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2))
        {
            System.out.println("Nouveau Cours :");
            System.out.println("introduire Code du cours :");
            String codeCour=sc.nextLine();
            System.out.println("introduire Description du cours :");
            String desc=sc.nextLine();
            System.out.println("introduire heures cours :");
            int heures=sc.nextInt();

            pstm1.setString(1,codeCour);
            pstm1.setString(2,desc);
            pstm1.setInt(3,heures);
            int res=pstm1.executeUpdate();
            System.out.println("res"+res);

            pstm2.setString(1, codeCour);
            pstm2.setString(2, desc);
            pstm2.setInt(3, heures);
            try (ResultSet rs = pstm2.executeQuery()) {

                if (rs.next()) {
                    int nc = rs.getInt(1);
                    System.out.println("numero de cours inséré =" + nc);

                } else {
                    System.out.println("erreur lors de l'insertion ,numero de client introuvable");
                }

            }


        }catch (Exception e)
        {
            System.out.println(e.toString());
        }

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
    public List getAll()
    {
        if(!lCours.isEmpty())
        {
            lCours= new ArrayList<>();
        }

        String query1="select * from APICOURS order by ID";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1))
        {

            try (ResultSet rs = pstm.executeQuery())
            {
                while(rs.next())
                {
                    id = rs.getInt("ID");
                    codecours = rs.getString("CODECOURS");
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
    public Object getByID(int id) {
        return null;
    }


    public Cours getByCodeCours(String cc) {
        String query1="select * from APICOURS where CODECOURS like ?";

        try (PreparedStatement pstm = dbConnect.prepareStatement(query1))
        {
            pstm.setString(1, cc);
            try (ResultSet rs = pstm.executeQuery())
            {
                while(rs.next())
                {
                    id = rs.getInt("ID");
                    codecours = rs.getString("CODECOURS");
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
