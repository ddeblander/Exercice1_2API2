package model;

import metier.Cours;
import metier.Local;
import metier.SessionCours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SessionCoursModelDB implements DAO<SessionCours>
{
    CoursModelDB cDB;
    LocalModelDB lDB;
    List<SessionCours> lSC;
    private Connection dbConnect;
    public SessionCoursModelDB()
    {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            System.exit(1);
        }
        cDB = new CoursModelDB();
        lDB= new LocalModelDB();

        lSC=new ArrayList<>();


    }

    @Override
    public SessionCours add(SessionCours o)
    {

        String query1 = "insert into apisessioncours(datedebut,nbrejours,idcours,id_local) values(?,?,?,?)";
        String query2 = "select * from apisessioncours where datedebut= ? and nbrejours =? and idcours =? and id_local= ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2))
        {

            pstm1.setDate(1,new java.sql.Date( o.getDateDebut().getTime()));
            pstm1.setInt(2,o.getNbreJours());
            pstm1.setInt(3,o.getCour().getId());
            pstm1.setInt(4,o.getLocal().getId());
            int res=pstm1.executeUpdate();
            if(res == -20000)
            {
                System.out.println("Local non disponible durant la date choisis");
                return null;
            }
            System.out.println("res"+res);
            pstm2.setDate(1,new java.sql.Date( o.getDateDebut().getTime()));
            pstm2.setInt(2,o.getNbreJours());
            pstm2.setInt(3,o.getCour().getId());
            pstm2.setInt(4,o.getLocal().getId());
            try (ResultSet rs = pstm2.executeQuery()) {
                if (rs.next()) {
                    int nc = rs.getInt(1);
                    System.out.println("numero de la session du cours inséré =" + nc);
                    o.setId(nc);
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
    public boolean remove(SessionCours o)
    {
        String query = "delete  from apisessioncours   where id = ?";
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
    public boolean update(SessionCours o)
    {
        String query = "update apisessioncours set datedebut = ? ,nbrejours= ?";
        try( PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setDate(1,new java.sql.Date( o.getDateDebut().getTime()));
            pstm.setInt(2,o.getNbreJours());
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
    public List getAll() {
        if(!lSC.isEmpty())
        {
            lSC= new ArrayList<>();
        }

        String query1="select * from apisessioncours order by ID";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1))
        {

            try (ResultSet rs = pstm.executeQuery())
            {
                while(rs.next())
                {
                    int id = rs.getInt("ID");
                    Date datedebut = rs.getDate("DATEDEBUT");
                    int nbrejours = rs.getInt("NBREJOURS");
                    int idCours = rs.getInt("IDCOURS");
                    int idlocal = rs.getInt("ID_LOCAL");
                    Local local=lDB.getByID(idlocal);
                    Cours cours=cDB.getByID(idCours);

                    lSC.add(new SessionCours(id,nbrejours,datedebut,cours,local));
                }
                return lSC;
            }


        } catch (SQLException e) {
            System.out.println("erreur " + e);

        }
        DBConnection.closeConnection();
        return null;
    }

    @Override
    public SessionCours getByID(int id)
    {

        String query1="select * from apisessioncours where id=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1))
        {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery())
            {
                while(rs.next())
                {
                    int idi = rs.getInt("ID");
                    Date datedebut = rs.getDate("DATEDEBUT");
                    int nbrejours = rs.getInt("NBREJOURS");
                    int idCours = rs.getInt("IDCOURS");
                    int idlocal = rs.getInt("ID_LOCAL");
                    Local local=lDB.getByID(idlocal);
                    Cours cours=cDB.getByID(idCours);

                    return new SessionCours(idi,nbrejours,datedebut,cours,local);
                }
            }


        } catch (SQLException e) {
            System.out.println("erreur " + e);

        }
        DBConnection.closeConnection();
        return null;
    }

    @Override
    public SessionCours read(SessionCours rech) {
        return getByID(rech.getId());
    }
}
