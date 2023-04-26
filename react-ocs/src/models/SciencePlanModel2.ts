import AstronomerModel from "./AstronomerModel";
import { DataProcRequirementModel } from "./DataProcRequirementModel";

class SciencePlanModel2{
    planNo: number = 0;
    creator: string = "";
    submitter: string = "";
    fundingInUSD: number;
    objectives: string;
    starSystem: string;
    startDate: string;
    endDate: string;
    telescopeLocation: string;
    dataProcRequirements: DataProcRequirementModel;
    status: string;
    observerFeedback: string = "";
    collaborator: AstronomerModel;
    constructor(fundingInUSD: number, objectives: string, starSystem: string, startDate: string, endDate: string, telescopeLocation: string, dataProcRequirement: DataProcRequirementModel, collaborator: AstronomerModel, status: string){
        this.fundingInUSD =  fundingInUSD;
        this.objectives = objectives;
        this.starSystem = starSystem;
        this.startDate = startDate;
        this.endDate = endDate;
        this.telescopeLocation = telescopeLocation;
        this.dataProcRequirements = dataProcRequirement;
        this.collaborator = collaborator;
        this.status = status;
    }
}
export default SciencePlanModel2;