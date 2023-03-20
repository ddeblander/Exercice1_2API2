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

public class SessionCoursModelDB implements DAO
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
        if(!lSC.isEmpty())
        {
            lSC= new ArrayList<>();
        }

        String query1="select * from APISESSIONCOURS order by ID";
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
    public Object getByID(int id) {
        return null;
    }
}
