package th.ac.mahidol.ict.service;

import edu.gemini.app.ocs.model.SciencePlan;
import edu.gemini.app.ocs.model.StarSystem;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.mahidol.ict.repository.MyOCSRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class SciencePlanServiceimpl implements SciencePlanService {

    @Autowired
    private MyOCSRepository ocs;

    @Override
    public List<SciencePlan> getSciencePlans() {
        return ocs.getAllSciencePlans();
    }

    @Override
    public void createSciencePlan(SciencePlan sciencePlan) {
        if(this.reserveDateAndTime(sciencePlan.getStartDate(), sciencePlan.getEndDate())){

        }
        ocs.createSciencePlan(sciencePlan);
    }

    @Override
    public boolean deleteSciencePlanByID(int id) {
        return ocs.deleteSciencePlanByNo(id);
    }

    @Override
    public boolean editSciencePlanByID(int id, SciencePlan sciencePlan) {
        return ocs.editSciencePlan(id, sciencePlan);
    }

    @Override
    public List<SciencePlan> searchSciencePlans(String query){
        List<SciencePlan> querySciencePlans = new ArrayList<>();
        for (SciencePlan sciencePlan:
                ocs.getAllSciencePlans()) {
            if(query.equals(String.valueOf(sciencePlan.getPlanNo())) || query.equals(sciencePlan.getCreator())
                    || query.equals(String.valueOf(sciencePlan.getStarSystem())) || query.equals(sciencePlan.getStartDate()) || query.equals(sciencePlan.getEndDate())){
                querySciencePlans.add(sciencePlan);
            }
        }
        return querySciencePlans;
    }

    @Override
    public List<SciencePlan> findSciencePlansByStatus(SciencePlan.STATUS status){
        List<SciencePlan> querySciencePlans = new ArrayList<>();
        for (SciencePlan sciencePlan:
                ocs.getAllSciencePlans()) {
            if(sciencePlan.getStatus().equals(status)){
                querySciencePlans.add(sciencePlan);
            }
        }
        return querySciencePlans;
    }

    @Override
    public boolean reserveDateAndTime(String startDate, String endDate){
        Date startDate1;
        Date endDate1;
        try {
            startDate1 = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).parse(startDate);
            endDate1 = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).parse(endDate);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return false;
        }
        for (SciencePlan sciencePlan:
                ocs.getAllSciencePlans()) {
            Date startDate2;
            Date endDate2;
            try {
                startDate2 = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).parse(sciencePlan.getStartDate());
                endDate2 = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).parse(sciencePlan.getStartDate());
            } catch (ParseException var3) {
                var3.printStackTrace();
                return false;
            }
            if(startDate1.after(startDate2) && startDate1.before(startDate2) || endDate1.after(endDate2) && endDate1.before(endDate2)){
                return false;
            }
        }
        ocs.reserveDateAndTime(startDate1, endDate1);
        return true;
    }

    @Override
    public List<SciencePlan.TELESCOPELOC> getTelescopeLocations() {
        List<SciencePlan.TELESCOPELOC> st = new ArrayList<>();
        for (SciencePlan.TELESCOPELOC tmp:
             SciencePlan.TELESCOPELOC.values()) {
            st.add(tmp);
        }
        return st;
    }

    @Override
    public List<StarSystem.CONSTELLATIONS> getStarSystems(){
        List<StarSystem.CONSTELLATIONS> sc = new ArrayList<>();
        for (StarSystem.CONSTELLATIONS tmp:
                StarSystem.CONSTELLATIONS.values()) {
            sc.add(tmp);
        }
        return sc;
    }
}
