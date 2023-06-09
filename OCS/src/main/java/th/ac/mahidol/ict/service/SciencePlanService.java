package th.ac.mahidol.ict.service;

import edu.gemini.app.ocs.model.SciencePlan;
import edu.gemini.app.ocs.model.StarSystem;
import th.ac.mahidol.ict.model.MySciencePlan;

import java.util.ArrayList;
import java.util.List;

public interface SciencePlanService {

    List<MySciencePlan> getSciencePlans();

    MySciencePlan getSciencePlanById(int id);
    boolean createSciencePlan(MySciencePlan mySciencePlan, String creatorEmail);

    boolean deleteSciencePlanByID(int id);

    boolean editSciencePlanByID(int id, MySciencePlan mySciencePlan);

    String submitSciencePlan(MySciencePlan sp, String creatorEmail);

    boolean validateSciencePlan(int planNo, SciencePlan.STATUS stssp);

    public boolean invalidateSciencePlan(String feedback, SciencePlan.STATUS stssp);

    List<MySciencePlan> searchSciencePlans(String query);

    List<MySciencePlan> findSciencePlansByStatus(SciencePlan.STATUS status);

    boolean reserveDateAndTime(String startDate1, String endDate1);

    List<SciencePlan.TELESCOPELOC> getTelescopeLocations();

    List<StarSystem.CONSTELLATIONS> getStarSystems();
}
