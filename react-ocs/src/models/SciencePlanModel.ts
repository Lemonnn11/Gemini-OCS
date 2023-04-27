import AstronomerModel from "./AstronomerModel";
import { DataProcRequirementModel } from "./DataProcRequirementModel";

class SciencePlanModel{
    planNo: number;
    creator: string;
    submitter: string = "";
    fundingInUSD: number;
    objectives: string;
    starSystem: string;
    startDate: string;
    endDate: string;
    telescopeLocation: string;
    dataProcRequirement: DataProcRequirementModel;
    status: string;
    observerFeedback: string = "";
    collaborator: AstronomerModel;
    constructor(planNo:number, creator: string, submitter: string, fundingInUSD: number, objectives: string, starSystem: string, startDate: string, endDate: string, telescopeLocation: string, dataProcRequirement: DataProcRequirementModel, status: string, collaborator: AstronomerModel){
        this.planNo = planNo;
        this.creator = creator;
        this.submitter = submitter;
        this.fundingInUSD =  fundingInUSD;
        this.objectives = objectives;
        this.starSystem = starSystem;
        this.startDate = startDate;
        this.endDate = endDate;
        this.telescopeLocation = telescopeLocation;
        this.dataProcRequirement = dataProcRequirement;
        this.status = status;
        this.collaborator = collaborator;
    }
    setDates(start: Date, end: Date) {
        this.startDate = start.toISOString();
        this.endDate = end.toISOString();
      }

    setObserverFeedBack(feedback: string){
        this.observerFeedback = feedback;
    }
}
export default SciencePlanModel;