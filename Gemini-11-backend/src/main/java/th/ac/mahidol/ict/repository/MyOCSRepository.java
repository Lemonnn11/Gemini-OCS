package th.ac.mahidol.ict.repository;

import edu.gemini.app.ocs.OCS;
import edu.gemini.app.ocs.model.DataProcRequirement;
import edu.gemini.app.ocs.model.SciencePlan;
import edu.gemini.app.ocs.model.StarSystem;
import org.springframework.stereotype.Repository;
import th.ac.mahidol.ict.model.Astronomer;
import th.ac.mahidol.ict.model.MySciencePlan;
import th.ac.mahidol.ict.model.ScienceObserver;
import th.ac.mahidol.ict.model.User;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@Repository
public class MyOCSRepository extends OCS {

    private static List<User> users = new ArrayList<User>();

    private static List<Astronomer> astronomers = new ArrayList<>();

    private static Map<Date, Date> reservedDateAndTime = new HashMap<>(); //

    private static List<ScienceObserver> scienceObservers = new ArrayList<>();

    private static List<MySciencePlan> mySciencePlans = new ArrayList();

    public MyOCSRepository(){
        super();
    }

    public MyOCSRepository(boolean createMockData){
        super(createMockData);
        this.H2GenFirstTimeDB();
        this.createUserDB();
        this.getUsersFromDB();
        this.populateSciencePlans();
    }

    private void populateSciencePlans() {
        this.getSciencePlansFromDB();
    }

    public List<User> getAllUsers() {
        this.getUsersFromDB();
        return users;
    }

    private void getAstronomersfromAllUser(){
        astronomers.clear();
        getUsersFromDB();
        for (User user: users) {
            if(user.getAccessLevel().equals("Astronomer")){
                Astronomer astronomer = new Astronomer();
                astronomer.setId(user.getId());
                astronomer.setFname(user.getFname());
                astronomer.setLname(user.getLname());
                astronomer.setEmail(user.getEmail());
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
                scienceObserver.setEmail(user.getEmail());
                this.scienceObservers.add(scienceObserver);
            }
        }
    }

    public boolean addFeedback(int planno, String feedback){
        String JDBC_DRIVER = "org.h2.Driver";
        String DB_URL = "jdbc:h2:./ocs";
        String USER = "sa";
        String PASS = "";
        Connection conn = null;
        Statement stmt = null;

        boolean var10;
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./ocs", "sa", "");
            stmt = conn.createStatement();
            String sql = "";
            String var10000 = feedback;
            sql = " UPDATE MASSCIENCEPLAN SET SPFeedback = '" + var10000 + "' WHERE planNo = " + planno;
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
            MySciencePlan sp = this.getSciencePlanByNo(planno);
            boolean var11;
            if (sp != null) {
                sp.setObserverFeedback(feedback);
                var11 = true;
                return var11;
            }
            var11 = false;
            return var11;
        } catch (SQLException var30) {
            var30.printStackTrace();
            var10 = false;
        } catch (Exception var31) {
            var31.printStackTrace();
            var10 = false;
            return var10;
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

        return var10;
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

    private void H2GenFirstTimeDB() {
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
            sql = " DROP TABLE IF EXISTS masSciencePlan; ";
            stmt.executeUpdate(sql);
            sql = " DROP TABLE IF EXISTS collaborators; ";
            stmt.executeUpdate(sql);
            sql = " DROP TABLE IF EXISTS trDataProcRequirement; ";
            stmt.executeUpdate(sql);
            sql = " DROP TABLE IF EXISTS masObservingProgram; ";
            stmt.executeUpdate(sql);
            sql = " DROP TABLE IF EXISTS masTelePositionPair; ";
            stmt.executeUpdate(sql);
            sql = " DROP TABLE IF EXISTS opTable; ";
            stmt.executeUpdate(sql);
            sql = " CREATE TABLE IF NOT EXISTS masSciencePlan  (planNo INT not NULL,  creator VARCHAR(100),  submitter VARCHAR(100),  fundingInUSD Double,  objectives VARCHAR(255),  starSystem VARCHAR(50),  startDate DateTime ,  endDate DateTime ,  telescopeLocation VARCHAR(50), SPStatus VARCHAR(50), SPFeedback VARCHAR(500), PRIMARY KEY ( planNo ))";
            stmt.executeUpdate(sql);
            sql = " CREATE TABLE IF NOT EXISTS collaborators  (planNo INT not NULL, collabFname VARCHAR(50), collabLname VARCHAR(50) )";
            stmt.executeUpdate(sql);
            sql = " CREATE TABLE IF NOT EXISTS trDataProcRequirement  (planNo INT not NULL,  fileType VARCHAR(100),  fileQuality VARCHAR(100),  colorType VARCHAR(100),  contrast Double,  brightness Double,  saturation Double,  highlights Double,  exposure Double,  shadows Double,  whites Double,  blacks Double,  luminance Double,  hue Double  )";
            stmt.executeUpdate(sql);
            sql = " CREATE TABLE IF NOT EXISTS masObservingProgram  (planNo INT not NULL,  geminiLocation VARCHAR(10),  opticsPrimary VARCHAR(20),  fStop Double,  opticsSecondaryRMS Double,  scienceFoldMirrorDegree Double,  scienceFoldMirrorType VARCHAR(50),  moduleContent INT ,  calibrationUnit VARCHAR(10), lightType VARCHAR(50), PRIMARY KEY ( planNo ))";
            stmt.executeUpdate(sql);
            sql = " CREATE TABLE IF NOT EXISTS masTelePositionPair  (planNo INT not NULL,  direction Double,  degree Double )";
            stmt.executeUpdate(sql);
            sql = " CREATE TABLE IF NOT EXISTS opTable  (id INT not NULL auto_increment,  physicalDevice VARCHAR(255),  PRIMARY KEY ( id ) )";
            stmt.executeUpdate(sql);
            sql = " INSERT INTO masSciencePlan VALUES ( 1,'John Doe','John Doe',1000,'1. To explore Neptune.\n2. To collect astronomical data for future research.','Andromeda','2021-04-15 09:00:00','2021-04-15 10:00:00','HAWAII','TESTED','' ); INSERT INTO collaborators VALUES ( 1, 'Elon', 'Musk'); INSERT INTO trDataProcRequirement VALUES ( 1,'PNG','Fine','B&W mode',20,0,0,10,15,5,7,10,0,0 );  INSERT INTO masObservingProgram VALUES(1,'N','GNZ',2.4,16.0,37,'CASSEGRAIN_FOCUS' ,3,'Xenon','CerroPachonSkyEmission'); INSERT INTO masTelePositionPair VALUES(1,135,44); INSERT INTO masTelePositionPair VALUES(1,90,65); INSERT INTO masTelePositionPair VALUES(1,210,35); INSERT INTO masSciencePlan VALUES ( 2,'Jane Dunn','Andrew Griffin',2500,'1. To explore Mars.\n2. To collect astronomical data for future research.','Antlia','2021-05-15 13:00:00','2021-05-15 15:00:00','CHILE','SAVED', '' ); INSERT INTO collaborators VALUES ( 2, 'Elon', 'Musk'); INSERT INTO trDataProcRequirement VALUES ( 2,'JPEG','Low','Color mode',11,10,5,0,7,0,0,0,10,8 ); INSERT INTO masObservingProgram VALUES(2,'S','GSZ',5.5,12.0,40,'REFLECTIVE_CONVERGING_BEAM' ,1,'Argon','MaunaKeaSkyEmission'); INSERT INTO masTelePositionPair VALUES(2,90,34); INSERT INTO masTelePositionPair VALUES(2,210,70);";
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

    public List<MySciencePlan> getAllMySciencePlans() {
        this.getSciencePlansFromDB();
        this.checkAndUpdateStatus();
        return mySciencePlans;
    }

    public String createSciencePlan(MySciencePlan sp) {
        String[] oplists = this.insertSciencePlanToDB(sp);
        System.out.println(oplists[0]);
        System.out.println(oplists[1]);
        return oplists[0] + "\n" + oplists[1];
    }

    public String submitSciencePlan(MySciencePlan sp) {
        SciencePlan.STATUS spSts = sp.getStatus();
        String op;
        if (spSts == SciencePlan.STATUS.TESTED) {
            this.updateSciencePlanStatus(sp.getPlanNo(), SciencePlan.STATUS.SUBMITTED);
            op = "Your science plan has been submitted.";
        } else {
            op = "Please test your science plan.";
        }

        return op;
    }

    private String[] insertSciencePlanToDB(MySciencePlan sp) {
        String[] slists = new String[2];
        String spCreator = sp.getCreator();
        String spSubmitter = sp.getSubmitter();
        double spFunding = sp.getFundingInUSD();
        String spObjective = sp.getObjectives();
        StarSystem.CONSTELLATIONS spStarSystem = sp.getStarSystem();
        String spStartDate = null;
        String spEndDate = null;
        String errDateformat = null;
        Date chkSDate = null;
        Date chkEDate = null;
        spStartDate = sp.getStartDate();
        if (spStartDate != "-1") {
            try {
                chkSDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(spStartDate);
            } catch (ParseException var46) {
                var46.printStackTrace();
                errDateformat = "Date format must be 'dd/MM/yyyy HH:mm:ss'";
            }
        } else {
            errDateformat = "Date format must be 'dd/MM/yyyy HH:mm:ss";
        }

        spEndDate = sp.getEndDate();
        if (spEndDate != "-1") {
            try {
                chkEDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(spEndDate);
            } catch (ParseException var45) {
                var45.printStackTrace();
                errDateformat = "Date format must be 'dd/MM/yyyy HH:mm:ss'";
            }
        } else {
            errDateformat = "Date format must be 'dd/MM/yyyy HH:mm:ss";
        }

        SciencePlan.TELESCOPELOC spTelescopeLocation = sp.getTelescopeLocation();
        ArrayList<DataProcRequirement> ListDPR = sp.getDataProcRequirements();
        List<Astronomer> listCollaborators = sp.getCollaborator();
        if (errDateformat == null) {
            int result = chkSDate.compareTo(chkEDate);
            if (result < 0) {
                String JDBC_DRIVER = "org.h2.Driver";
                String DB_URL = "jdbc:h2:./ocs";
                String USER = "sa";
                String PASS = "";
                Connection conn = null;
                Statement stmt = null;

                try {
                    Class.forName("org.h2.Driver");
                    conn = DriverManager.getConnection("jdbc:h2:./ocs", "sa", "");
                    stmt = conn.createStatement();
                    String sql = "SELECT MAX(planNo) FROM masSciencePlan";
                    ResultSet rs = stmt.executeQuery(sql);
                    int curplanNo = 0;
                    if (rs.next()) {
                        curplanNo = rs.getInt(1);
                        ++curplanNo;
                    }

                    if (spFunding >= 0.0) {
                        sql = " INSERT INTO MASSCIENCEPLAN VALUES (" + curplanNo + ",'" + spCreator + "','" + spSubmitter + "'," + spFunding + ",'" + spObjective + "','" + String.valueOf(spStarSystem) + "','" + spStartDate + "','" + spEndDate + "','" + String.valueOf(spTelescopeLocation) + "','SAVED', '' );" ;
                        stmt.executeUpdate(sql);
                        Iterator var26 = ListDPR.iterator();

                        while(var26.hasNext()) {
                            DataProcRequirement drp = (DataProcRequirement)var26.next();
                            sql = " INSERT INTO trDataProcRequirement VALUES ( " + curplanNo + ",'" + drp.getFileType() + "','" + drp.getFileQuality() + "','" + drp.getColorType() + "'," + drp.getContrast() + "," + drp.getBrightness() + "," + drp.getSaturation() + "," + drp.getHighlights() + "," + drp.getExposure() + "," + drp.getShadows() + "," + drp.getWhites() + "," + drp.getBlacks() + "," + drp.getLuminance() + "," + drp.getHue() + " );";
                            stmt.executeUpdate(sql);
                        }

                        Iterator var27 = listCollaborators.iterator();

                        while(var27.hasNext()) {
                            Astronomer collab = (Astronomer)var27.next();
                            sql = " INSERT INTO collaborators VALUES ( " + curplanNo + ",'" + collab.getFname() + "','" + collab.getLname() + "');";
                            stmt.executeUpdate(sql);
                        }

                        sp.setPlanNo(curplanNo);
                        sp.setStatus(SciencePlan.STATUS.SAVED);
                        mySciencePlans.add(sp);
                        slists[0] = "planNo = " + String.valueOf(curplanNo);
                        slists[1] = "Your science plan has been saved.\n";
                    } else {
                        slists[0] = "-1";
                        slists[1] = "Funding cannot be negative.\n";
                    }
                } catch (SQLException var47) {
                    slists[0] = "-1";
                    slists[1] = var47.getMessage();
                    var47.printStackTrace();
                } catch (Exception var48) {
                    slists[0] = "-1";
                    slists[1] = var48.getMessage();
                    var48.printStackTrace();
                } finally {
                    try {
                        if (stmt != null) {
                            stmt.close();
                        }
                    } catch (SQLException var44) {
                    }

                    try {
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (SQLException var43) {
                        var43.printStackTrace();
                    }

                }
            } else {
                slists[0] = "-1";
                slists[1] = "The start date must be before the end date\n";
            }
        } else {
            slists[0] = "-1";
            slists[1] = errDateformat + "\n";
        }

        return slists;
    }

    public MySciencePlan getSciencePlanByNo(int planNo) {
        this.getSciencePlansFromDB();
        this.checkAndUpdateStatus();
        Iterator var2 = mySciencePlans.iterator();

        MySciencePlan sp;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            sp = (MySciencePlan)var2.next();
        } while(sp.getPlanNo() != planNo);

        return sp;
    }

    private void checkAndUpdateStatus() {
    }

    private void getSciencePlansFromDB() {
        mySciencePlans.clear();
        String JDBC_DRIVER = "org.h2.Driver";
        String DB_URL = "jdbc:h2:./ocs";
        String USER = "sa";
        String PASS = "";
        Connection conn = null;
        Statement stmt = null;
        Statement stmt1 = null;
        Statement stmt2 = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./ocs", "sa", "");
            stmt = conn.createStatement();
            String sql = "";
            sql = "SELECT * FROM masSciencePlan";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                MySciencePlan sp1 = new MySciencePlan();
                sp1.setPlanNo(rs.getInt("planNo"));
                sp1.setCreator(rs.getString("creator"));
                sp1.setSubmitter(rs.getString("submitter"));
                sp1.setFundingInUSD(rs.getDouble("fundingInUSD"));
                sp1.setObjectives(rs.getString("objectives"));
                sp1.setStarSystem(StarSystem.CONSTELLATIONS.valueOf(rs.getString("starSystem")));
                String sDate = rs.getString("startDate");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                sDate = sdf2.format(sdf.parse(sDate));
                sp1.setStartDate(sDate);
                sp1.setTelescopeLocation(SciencePlan.TELESCOPELOC.valueOf(rs.getString("telescopeLocation")));
                String eDate = rs.getString("endDate");
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat edf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                eDate = edf2.format(sdf.parse(eDate));
                sp1.setEndDate(eDate);
                sp1.setStatus(SciencePlan.STATUS.valueOf(rs.getString("SPStatus")));
                sp1.setObserverFeedback(rs.getString("SPFeedback"));
                String sql1 = "SELECT * FROM trDataProcRequirement WHERE planNo = " + rs.getInt("planNo");
                stmt1 = conn.createStatement();
                ResultSet rs1 = stmt1.executeQuery(sql1);
                String sql2 = "SELECT * FROM collaborators WHERE planNo = " + rs.getInt("planNo");
                stmt2 = conn.createStatement();
                ResultSet rs2 = stmt2.executeQuery(sql2);

//                System.out.println(rs2);


                while(rs1.next()) {
                    DataProcRequirement dpr1 = new DataProcRequirement(rs1.getString("fileType"), rs1.getString("fileQuality"), rs1.getString("colorType"), rs1.getDouble("contrast"), rs1.getDouble("brightness"), rs1.getDouble("saturation"), rs1.getDouble("highlights"), rs1.getDouble("exposure"), rs1.getDouble("shadows"), rs1.getDouble("whites"), rs1.getDouble("blacks"), rs1.getDouble("luminance"), rs1.getDouble("hue"));
                    sp1.setDataProcRequirements(dpr1);
                }

                while(rs2.next()) {
                    Astronomer coll = new Astronomer(rs2.getString("collabFname"), rs2.getString("collabLname"));
                    sp1.setCollaborator(coll);
                }

                stmt1.close();
                stmt2.close();
                mySciencePlans.add(sp1);
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
            sql = " CREATE TABLE IF NOT EXISTS user  (userId INT not NULL,  fname VARCHAR(50),  lname VARCHAR(50), email VARCHAR(100), accessLevel VARCHAR(50), PRIMARY KEY ( userId ))";
            stmt.executeUpdate(sql);
            sql = " INSERT INTO user VALUES ( 1,'John','Doe', 'john.doe@email.com', 'Astronomer'), ( 2,'Jane','Dunn', 'jane.dunn@email.com', 'scienceObserver'), ( 3,'Elon','Musk', 'elon.musk@email.com', 'Astronomer');";
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

    public boolean editSciencePlan(int planNo, MySciencePlan sp) {
        System.out.println(sp);
        System.out.println(sp.getDataProcRequirements());
        System.out.println(sp.getCollaborator());
        String[] slists = new String[2];
        String spCreator = sp.getCreator();
        String spSubmitter = sp.getSubmitter();
        double spFunding = sp.getFundingInUSD();
        String spObjective = sp.getObjectives();
        StarSystem.CONSTELLATIONS spStarSystem = sp.getStarSystem();
        String spStartDate = null;
        String spEndDate = null;
        String errDateformat = null;
        Date chkSDate = null;
        Date chkEDate = null;
        spStartDate = sp.getStartDate();

        if (spStartDate != "-1") {
            try {
                chkSDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(spStartDate);
            } catch (ParseException var46) {
                var46.printStackTrace();
                errDateformat = "Date format must be 'dd/MM/yyyy HH:mm:ss'";
            }
        } else {
            errDateformat = "Date format must be 'dd/MM/yyyy HH:mm:ss";
        }

        spEndDate = sp.getEndDate();
        if (spEndDate != "-1") {
            try {
                chkEDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(spEndDate);
            } catch (ParseException var45) {
                var45.printStackTrace();
                errDateformat = "Date format must be 'dd/MM/yyyy HH:mm:ss'";
            }
        } else {
            errDateformat = "Date format must be 'dd/MM/yyyy HH:mm:ss";
        }

        SciencePlan.TELESCOPELOC spTelescopeLocation = sp.getTelescopeLocation();
        ArrayList<DataProcRequirement> ListDPR = sp.getDataProcRequirements();
        List<Astronomer> listCollaborators = sp.getCollaborator();
        boolean var11 = false;

        if (errDateformat == null) {
            String JDBC_DRIVER = "org.h2.Driver";
            String DB_URL = "jdbc:h2:./ocs";
            String USER = "sa";
            String PASS = "";
            Connection conn = null;
            Statement stmt = null;
            try {
                boolean var10;
                try {
                    Class.forName("org.h2.Driver");
                    conn = DriverManager.getConnection("jdbc:h2:./ocs", "sa", "");
                    stmt = conn.createStatement();
                    String sql = "";

                    if(spFunding >= 0.0 && spObjective != null && spStarSystem != null && spTelescopeLocation != null){
                        sql = " UPDATE MASSCIENCEPLAN SET creator = '" + spCreator + "', submitter = '" + spSubmitter + "', fundingInUSD = '" + spFunding + "', objectives = '" + spObjective + "', starSystem = '" + spStarSystem + "', startDate = '" + spStartDate + "', endDate = '" + spEndDate + "', telescopeLocation = '" + spTelescopeLocation + "', SPStatus = '" + "SAVED" + "' WHERE planNo = " + planNo;
                        stmt.executeUpdate(sql);

                        Iterator listDPR = ListDPR.iterator();

                        while(listDPR.hasNext()) {
                            DataProcRequirement drp = (DataProcRequirement)listDPR.next();
                            sql = " UPDATE trDataProcRequirement SET fileType = '" + drp.getFileType() + "', fileQuality = '" + drp.getFileQuality() +  "', colorType = '" + drp.getColorType() +  "', contrast = '" + drp.getContrast() +  "', brightness = '" + drp.getBrightness() +  "', saturation = '" + drp.getSaturation() +  "', highlights = '" + drp.getHighlights() +  "', exposure = '" + drp.getExposure() +  "', shadows = '" + drp.getShadows() +  "', whites = '" + drp.getWhites() +  "', blacks = '" + drp.getBlacks() +  "', luminance = '" + drp.getLuminance() +  "', hue = '" + drp.getHue() + "' WHERE planNo = " + planNo;
                            stmt.executeUpdate(sql);
                        }

                        Iterator listCollab = listCollaborators.iterator();

                        while(listCollab.hasNext()) {
                            Astronomer collab = (Astronomer)listCollab.next();
                            sql = " UPDATE collaborators SET collabFname = '" + collab.getFname() +  "', collabLname = '" + collab.getLname() + "' WHERE planNo = " + planNo;
                            stmt.executeUpdate(sql);
                        }

                        stmt.close();
                        conn.close();
                    }
                    else{
                        var11 = false;
                        return var11;
                    }

                    MySciencePlan spp = this.getSciencePlanByNo(planNo);
                    if (spp == null) {
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
        }

        return var11;
    }

}
