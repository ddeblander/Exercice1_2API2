package model;

import metier.Cours;
import metier.Local;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocalModelDB implements DAO<Local>
{
    private Connection dbConnect;
    private List<Local> ll;
    public LocalModelDB()
    {
        ll= new ArrayList<>();
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            System.exit(1);
        }
    }


    @Override
    public Local add(Local o) {
        String query1 = "insert into APILOCAL(sigle,places,description) values(?,?,?)";
        String query2 = "select * from APILOCAL where sigle= ? and places =? and description =?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2))
        {

            pstm1.setString(1,o.getSigle());
            pstm1.setInt(2,o.getPlaces());
            pstm1.setString(3,o.getDescription());
            int res=pstm1.executeUpdate();
            System.out.println("res"+res);
            pstm2.setString(1,o.getSigle());
            pstm2.setInt(2,o.getPlaces());
            pstm2.setString(3,o.getDescription());



            try (ResultSet rs = pstm2.executeQuery()) {
                if (rs.next()) {
                    int nc = rs.getInt(1);
                    System.out.println("numero de Local inséré =" + nc);
                    o.setId(nc);
                    return o;
                } else {
                    System.out.println("erreur lors de l'insertion ,numero de Local introuvable");
                }
            }

        }
            catch (Exception e)
        {
            System.out.println(e);
        }
        DBConnection.closeConnection();
        return null;
    }

    @Override
    public boolean remove(Local o) {
        String query = "delete  from apilocal   where id = ?";
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
    public boolean update(Local o) {
        String query = "update apilocal set sigle = ? ,places= ?, description = ? where id = ?";
        try( PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setString(1,o.getSigle());
            pstm.setInt(2,o.getPlaces());
            pstm.setString(3,o.getDescription());
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
    public List getAll()
    {
        ll=new ArrayList<>();
        String query = "select * from apilocal";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            try (ResultSet rs = pstm.executeQuery())
            {
                while(rs.next())
                {
                    int idi = rs.getInt("ID");
                    String sigle = rs.getString("SIGLE");
                    int places = rs.getInt("PLACES");
                    String desc = rs.getString("DESCRIPTION");

                    ll.add(new Local(idi,places,sigle,desc));
                }
                return ll;
            }


        } catch (SQLException e) {
            System.out.println("erreur " + e);

        }
        DBConnection.closeConnection();
        return null;
    }

    @Override
    public Local getByID(int id) {
        String query = "select * from apilocal where id= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery())
            {
                while(rs.next())
                {
                    int idi = rs.getInt("ID");
                    String sigle = rs.getString("SIGLE");
                    int places = rs.getInt("PLACES");
                    String desc = rs.getString("DESCRIPTION");

                    return new Local(idi,places,sigle,desc);
                }

            }


        } catch (SQLException e) {
            System.out.println("erreur " + e);

        }
        DBConnection.closeConnection();
        return null;
    }

    @Override
    public Local read(Local rech) {
        return getByID(rech.getId());
    }




}
