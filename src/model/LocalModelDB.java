package model;

import metier.Cours;
import metier.Local;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocalModelDB implements DAO<Local>
{
    private Connection dbConnect;
    public LocalModelDB()
    {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            System.exit(1);
        }
    }


    @Override
    public Local add(Local o) {
        return null;
    }

    @Override
    public boolean remove(Local o) {
        return false;
    }

    @Override
    public boolean update(Local o) {
        return false;
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Local getByID(int id) {
        String query = "select * from EXO1_LOCAL where id= ?";
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
