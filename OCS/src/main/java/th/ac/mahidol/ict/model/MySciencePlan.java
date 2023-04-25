package th.ac.mahidol.ict.model;

import edu.gemini.app.ocs.model.DataProcRequirement;
import edu.gemini.app.ocs.model.SciencePlan;
import edu.gemini.app.ocs.model.StarSystem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MySciencePlan extends SciencePlan{

    private String observerFeedback;

    private List<Astronomer> collaborators = new ArrayList<>();

    public MySciencePlan() {
    }

    public MySciencePlan(String creator, String submitter, double fundingInUSD, String objectives, StarSystem.CONSTELLATIONS starSystem, Date startDate, Date endDate, TELESCOPELOC telescopeLocation, DataProcRequirement dataProcRequirements, Astronomer collaborator) {
        super(creator, submitter,fundingInUSD, objectives, starSystem, startDate, endDate, telescopeLocation, dataProcRequirements);
        this.collaborators.add(collaborator);
    }

    public String getObserverFeedback() {
        return observerFeedback;
    }

    public void setObserverFeedback(String observerFeedback) {
        this.observerFeedback = observerFeedback;
    }

    public List<Astronomer> getCollaborator() {
        return collaborators;
    }

    public void setCollaborator(Astronomer collaborator) {
        this.collaborators.add(collaborator);
    }

    public void setStartDate(String startDate) {
        if(startDate.contains("T")){
            DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Date inputDate = null;
            try {
                inputDate = inputDateFormat.parse(startDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            DateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String outputDate = outputDateFormat.format(inputDate);
            try {
                super.setStartDate((new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).parse(outputDate));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }else{
            try {
                super.setStartDate((new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).parse(startDate));
            } catch (ParseException var3) {
                var3.printStackTrace();
            }
        }
    }

    public void setEndDate(String endDate) {
        if(endDate.contains("T")){
            DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Date inputDate = null;
            try {
                inputDate = inputDateFormat.parse(endDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            DateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String outputDate = outputDateFormat.format(inputDate);
            try {
                super.setEndDate((new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).parse(outputDate));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }else{
            try {
                super.setEndDate((new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).parse(endDate));
            } catch (ParseException var3) {
                var3.printStackTrace();
            }
        }
    }
}
