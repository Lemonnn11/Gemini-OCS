package th.ac.mahidol.ict.service;

import edu.gemini.app.ocs.model.SciencePlan;
import edu.gemini.app.ocs.model.StarSystem;

import java.util.ArrayList;
import java.util.List;

public interface SciencePlanService {

    List<SciencePlan> getSciencePlans();
    void createSciencePlan(SciencePlan sciencePlan);

    boolean deleteSciencePlanByID(int id);

    boolean editSciencePlanByID(int id, SciencePlan sciencePlan);

    List<SciencePlan> searchSciencePlans(String query);

    List<SciencePlan> findSciencePlansByStatus(SciencePlan.STATUS status);

    boolean reserveDateAndTime(String startDate1, String endDate1);

    List<SciencePlan.TELESCOPELOC> getTelescopeLocations();

    List<StarSystem.CONSTELLATIONS> getStarSystems();
}
