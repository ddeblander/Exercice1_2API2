package model;

import metier.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InfosModelDB implements DAO<Infos>
{
    private Connection dbConnect;

    private SessionCoursModelDB sscmDB;
    private FormateurModelDB fmDB;

    private List<Infos> li;

    public InfosModelDB()
    {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            System.exit(1);
        }
        sscmDB= new SessionCoursModelDB();
        fmDB=new FormateurModelDB();
        li=new ArrayList<>();
    }

    @Override
    public Infos add(Infos o) {
        String query1 = "insert into apiinfos(nh,idformateur,idsessioncours) values( ? , ? , ? )";
        String query2 = "select * from apiinfos where idformateur = ? and idsessioncours = ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2))
        {


            pstm1.setInt(1,-1);
            pstm1.setInt(2,o.getFormateur().getId());
            pstm1.setInt(3,o.getSessionCours().getId());
            int res=pstm1.executeUpdate();
            update(o);

            System.out.println("res"+res);
            pstm2.setInt(1,o.getFormateur().getId());
            pstm2.setInt(2,o.getSessionCours().getId());

            try (ResultSet rs = pstm2.executeQuery()) {
                if (rs.next()) {
                    int nc = rs.getInt("idformateur");
                    int nc2 = rs.getInt("idsessioncours");
                    System.out.println(" les ID des  infos inséré =" + nc +" "+nc2);

                    return o;
                } else {
                    System.out.println("erreur lors de l'insertion de la session du cours");
                }
            }
        }catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return null;

    }

    @Override
    public boolean remove(Infos o) {
        String query = "delete  from apiinfos   where idformateur = ? and idsessioncours = ?";

        try( PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,o.getFormateur().getId());
            pstm.setInt(2,o.getSessionCours().getId());
            pstm.executeQuery();
            return true;
        }
        catch (SQLException e) {
            System.out.println("erreur SQL =" + e);
        }
        return false;
    }

    @Override
    public boolean update(Infos o)
    {
        String query = "update apiinfos set NH = ? where IDFORMATEUR = ? and IDSESSIONCOURS = ?";
        int nhCour = sscmDB.getByID(o.getSessionCours().getId()).getCour().getHeures();
        int nbj = sscmDB.getByID(o.getSessionCours().getId()).getNbreJours();
        o.setNh(nhCour*nbj);
        try( PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,o.getNh());
            pstm.setInt(2,o.getFormateur().getId());
            pstm.setInt(3,o.getSessionCours().getId());
            pstm.executeQuery();
            return true;
        }
        catch (SQLException e) {
            System.out.println("erreur SQL =" + e);
        }
        return false;
    }

    @Override
    public List<Infos> getAll() {
        if(!li.isEmpty())
        {
            li= new ArrayList<>();
        }

        String query1="select * from apiinfos order by nh";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1))
        {

            try (ResultSet rs = pstm.executeQuery())
            {
                while(rs.next())
                {
                    int nh = rs.getInt("NH");
                    int idf = rs.getInt("IDFORMATEUR");
                    int idssc = rs.getInt("IDSESSIONCOURS");
                    Formateur f=fmDB.getByID(idf);
                    SessionCours ssc=sscmDB.getByID(idssc);

                    li.add(new Infos(nh,f,ssc));
                }
                return li;
            }


        } catch (SQLException e) {
            System.out.println("erreur " + e);

        }
        return null;
    }

    @Override
    public Infos getByID(int id) {
        return null;
    }

    @Override
    public Infos read(Infos rech) {
        String query1="select * from apiinfos where IDFORMATEUR = ? AND IDSESSIONCOURS = ? ";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1))
        {
            pstm.setInt(1,rech.getFormateur().getId());
            pstm.setInt(2,rech.getSessionCours().getId());
            try (ResultSet rs = pstm.executeQuery())
            {
                while(rs.next())
                {
                    int nh = rs.getInt("NH");
                    int idf = rs.getInt("IDFORMATEUR");
                    int idssc = rs.getInt("IDSESSIONCOURS");
                    Formateur f=fmDB.getByID(idf);
                    SessionCours ssc=sscmDB.getByID(idssc);

                    return new Infos(nh,f,ssc);
                }
            }


        } catch (SQLException e) {
            System.out.println("erreur " + e);

        }
        return null;
    }
}
