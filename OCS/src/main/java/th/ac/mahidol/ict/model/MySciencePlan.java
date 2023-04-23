package th.ac.mahidol.ict.model;

import edu.gemini.app.ocs.model.DataProcRequirement;
import edu.gemini.app.ocs.model.SciencePlan;
import edu.gemini.app.ocs.model.StarSystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}
