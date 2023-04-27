package th.ac.mahidol.ict.service;

import edu.gemini.app.ocs.model.SciencePlan;
import edu.gemini.app.ocs.model.StarSystem;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.mahidol.ict.model.Astronomer;
import th.ac.mahidol.ict.model.MySciencePlan;
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
    private MyOCSRepository ocs = new MyOCSRepository(true);

    @Override
    public List<MySciencePlan> getSciencePlans() {
        return ocs.getAllMySciencePlans();
    }

    @Override
    public MySciencePlan getSciencePlanById(int id) {
        return ocs.getSciencePlanByNo(id);
    }

    @Override
    public String submitSciencePlan(MySciencePlan sp, String creatorEmail) {
        String submitter = null;
        for (Astronomer astronomer:
                ocs.getAllAstronomers()) {
            System.out.println(astronomer.getEmail());
            System.out.println(creatorEmail.equals(astronomer.getEmail()));
            if(creatorEmail.equals(astronomer.getEmail())){
                submitter = astronomer.getFname() + ' ' + astronomer.getLname();
            }
        }
        sp.setSubmitter(submitter);
        this.editSciencePlanByID(sp.getPlanNo(), sp);
        return ocs.submitSciencePlan(sp);
    }

    @Override
    public boolean invalidateSciencePlan(String feedback, SciencePlan.STATUS stssp) {
        String[] str = feedback.split(" ", 2);
        int planNo = Integer.parseInt(str[0]);
        feedback = str[1];
        boolean res1 = ocs.addFeedback(planNo, feedback);
        boolean res2 = ocs.updateSciencePlanStatus(planNo, stssp);
        if(res1 && res2){
            return true;
        }
        return false;
    }

    @Override
    public boolean validateSciencePlan(int planNo, SciencePlan.STATUS stssp) {
        return ocs.updateSciencePlanStatus(planNo, stssp);
    }

    @Override
    public boolean editSciencePlanByID(int id, MySciencePlan mySciencePlan) {
        return ocs.editSciencePlan(id, mySciencePlan);
    }

    @Override
    public boolean createSciencePlan(MySciencePlan mySciencePlan, String creatorEmail) {
        String creator = null;
        for (Astronomer astronomer:
             ocs.getAllAstronomers()) {
            System.out.println(astronomer.getEmail());
            System.out.println(creatorEmail.equals(astronomer.getEmail()));
            if(creatorEmail.equals(astronomer.getEmail())){
                creator = astronomer.getFname() + ' ' + astronomer.getLname();
            }
        }
        mySciencePlan.setCreator(creator);
        if(this.reserveDateAndTime(mySciencePlan.getStartDate(), mySciencePlan.getEndDate())){
            ocs.createSciencePlan(mySciencePlan);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteSciencePlanByID(int id) {
        return ocs.deleteSciencePlanByNo(id);
    }

    @Override
    public List<MySciencePlan> searchSciencePlans(String query){
        query = query.toLowerCase();
        List<MySciencePlan> querySciencePlans = new ArrayList<>();
        for (MySciencePlan mySciencePlan:
                ocs.getAllMySciencePlans()) {
            if(String.valueOf(mySciencePlan.getPlanNo()).contains(query) || mySciencePlan.getCreator().toLowerCase().contains(query)
                    || String.valueOf(mySciencePlan.getStarSystem()).toLowerCase().contains(query) || mySciencePlan.getStartDate().contains(query)
                    || mySciencePlan.getEndDate().contains(query)){
                querySciencePlans.add(mySciencePlan);
            }

        }
        return querySciencePlans;
    }

    @Override
    public List<MySciencePlan> findSciencePlansByStatus(SciencePlan.STATUS status){
        List<MySciencePlan> querySciencePlans = new ArrayList<>();
        for (MySciencePlan mySciencePlan:
                ocs.getAllMySciencePlans()) {
            if(mySciencePlan.getStatus().equals(status)){
                querySciencePlans.add(mySciencePlan);
            }
        }
        return querySciencePlans;
    }

    @Override
    public boolean reserveDateAndTime(String startDate, String endDate){
        Date startDate1;
        Date endDate1;
        try {
            startDate1 = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(startDate);
            endDate1 = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(endDate);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return false;
        }
        if(startDate1.after(endDate1)){
            return false;
        }
        for (MySciencePlan mySciencePlan:
                ocs.getAllMySciencePlans()) {
            Date startDate2;
            Date endDate2;
            try {
                startDate2 = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(mySciencePlan.getStartDate());
                endDate2 = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(mySciencePlan.getEndDate());
            } catch (ParseException var3) {
                var3.printStackTrace();
                return false;
            }
            if ((startDate1.after(startDate2) && startDate1.before(endDate2) ||
                    endDate1.after(startDate2) && endDate1.before(endDate2)) ||
                    startDate1.equals(startDate2) || endDate1.equals(endDate2)) {
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
