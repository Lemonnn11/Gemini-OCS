package th.ac.mahidol.ict.repository;

import edu.gemini.app.ocs.OCS;
import edu.gemini.app.ocs.model.DataProcRequirement;
import edu.gemini.app.ocs.model.SciencePlan;
import edu.gemini.app.ocs.model.StarSystem;
import org.springframework.stereotype.Repository;
import th.ac.mahidol.ict.model.Astronomer;
import th.ac.mahidol.ict.model.ScienceObserver;
import th.ac.mahidol.ict.model.User;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

@Repository
public class MyOCSRepository extends OCS {

    private static List<User> users = new ArrayList<User>();

    private static List<Astronomer> astronomers = new ArrayList<>();

    private static Map<Date, Date> reservedDateAndTime = new HashMap<>(); //

    private static List<ScienceObserver> scienceObservers = new ArrayList<>();

    public MyOCSRepository(){
        super();
    }

    public MyOCSRepository(boolean createMockData){
        super(createMockData);
        createUserDB();
        getUsersFromDB();
    }

    public List<User> getAllUsers() {
        this.getUsersFromDB();
        return users;
    }

    private void getAstronomersfromAllUser(){
        getUsersFromDB();
        for (User user: users) {
            if(user.getAccessLevel().equals("Astronomer")){
                Astronomer astronomer = new Astronomer();
                astronomer.setId(user.getId());
                astronomer.setFname(user.getFname());
                astronomer.setLname(user.getLname());
                this.astronomers.add(astronomer);
            }
        }
    }

    private void getScienceObserversfromAllUser(){
        getUsersFromDB();
        for (User user: users) {
            if(user.getAccessLevel().equals("scienceObserver")){
                ScienceObserver scienceObserver = new ScienceObserver();
                scienceObserver.setId(user.getId());
                scienceObserver.setFname(user.getFname());
                scienceObserver.setLname(user.getLname());
                this.scienceObservers.add(scienceObserver);
            }
        }
    }

    public List<Astronomer> getAllAstronomers(){
        getAstronomersfromAllUser();
        return astronomers;
    }

    public List<ScienceObserver> getAllScienceObservers(){
        getScienceObserversfromAllUser();
        return scienceObservers;
    }

    public void reserveDateAndTime(Date startDate, Date endDate) {
        reservedDateAndTime.put(startDate, endDate);
    }



    private void getUsersFromDB() {
        users.clear();
        String JDBC_DRIVER = "org.h2.Driver";
        String DB_URL = "jdbc:h2:./ocs";
        String USER = "sa";
        String PASS = "";
        Connection conn = null;
        Statement stmt = null;
        Statement stmt1 = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./ocs", "sa", "");
            stmt = conn.createStatement();
            String sql = "";
            sql = "SELECT * FROM user";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                User us = new User();
                us.setId(rs.getInt("userId"));
                us.setFname(rs.getString("fname"));
                us.setLname(rs.getString("lname"));
                us.setEmail(rs.getString("email"));
                us.setUsername(rs.getString("username"));
                us.setPassword(rs.getString("password"));
                us.setAccessLevel(rs.getString("accessLevel"));
                users.add(us);
            }

            stmt.close();
            conn.close();
        } catch (SQLException var35) {
            var35.printStackTrace();
        } catch (Exception var36) {
            var36.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException var34) {
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException var33) {
                var33.printStackTrace();
            }

        }

    }

    private void createUserDB(){
        String JDBC_DRIVER = "org.h2.Driver";
        String DB_URL = "jdbc:h2:./ocs";
        String USER = "sa";
        String PASS = "";
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.h2.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:h2:./ocs", "sa", "");
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            String sql = "";
            sql = " DROP TABLE IF EXISTS user; ";
            stmt.executeUpdate(sql);
            sql = " CREATE TABLE IF NOT EXISTS user  (userId INT not NULL,  fname VARCHAR(50),  lname VARCHAR(50), email VARCHAR(100),  username VARCHAR(30),  password VARCHAR(30), accessLevel VARCHAR(50), PRIMARY KEY ( userId ))";
            stmt.executeUpdate(sql);
            sql = " INSERT INTO user VALUES ( 1,'John','Doe', 'john@gmail.com','john123', 'johnchaorai', 'Astronomer'), ( 2,'Jane','Dunn', 'jane@gmail.com','jane123', 'janechaorai', 'scienceObserver');";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException var23) {
            var23.printStackTrace();
        } catch (Exception var24) {
            var24.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException var22) {
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException var21) {
                var21.printStackTrace();
            }

        }

        System.out.println("Goodbye!");
    }

    public boolean editSciencePlan(int planNo, SciencePlan sciencePlan) {
        String JDBC_DRIVER = "org.h2.Driver";
        String DB_URL = "jdbc:h2:./ocs";
        String USER = "sa";
        String PASS = "";
        Connection conn = null;
        Statement stmt = null;

        boolean var11;
        try {
            boolean var10;
            try {

                Class.forName("org.h2.Driver");
                conn = DriverManager.getConnection("jdbc:h2:./ocs", "sa", "");
                stmt = conn.createStatement();
                String sql = "";
                sql = " UPDATE MASSCIENCEPLAN SET creator = '" + sciencePlan.getCreator() + "', submitter = '" + sciencePlan.getSubmitter() + "', fundingInUSD = '" + sciencePlan.getFundingInUSD() + "', objectives = '" + sciencePlan.getObjectives() + "', starSystem = '" + sciencePlan.getStarSystem() + "', startDate = '" + sciencePlan.getStartDate() + "', endDate = '" + sciencePlan.getEndDate() + "', telescopeLocation = '" + sciencePlan.getTelescopeLocation() + "', SPStatus = '" + sciencePlan.getStatus() + "' WHERE planNo = " + planNo;
                stmt.executeUpdate(sql);
                stmt.close();
                conn.close();
                SciencePlan sp = this.getSciencePlanByNo(planNo);
                if (sp == null) {
                    System.out.println("Update science plan is unsuccessful...");
                    System.out.println("Not found planNo: " + planNo + "\n");
                    var11 = false;
                    return var11;
                }
                var11 = true;
            } catch (SQLException var30) {
                var30.printStackTrace();
                System.out.println("Edit failed...\n");
                var10 = false;
                return var10;
            } catch (Exception var31) {
                var31.printStackTrace();
                System.out.println("Edit failed...\n");
                var10 = false;
                return var10;
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException var29) {
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException var28) {
                var28.printStackTrace();
            }

        }

        return var11;
    }

}
