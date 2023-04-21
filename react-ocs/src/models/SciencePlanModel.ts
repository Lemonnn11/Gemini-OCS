import { DataProcRequirementModel } from "./DataProcRequirementModel";

class SciencePlanModel{
    planNo: number;
    creator: string;
    submitter: string;
    fundingInUSD: number;
    objectives: string;
    starSystem: string;
    startDate: string;
    endDate: string;
    telescopeLocation: string;
    dataProcRequirement: DataProcRequirementModel;
    status: string;
    constructor(planNo:number, creator: string, submitter: string, fundingInUSD: number, objectives: string, starSystem: string, startDate: string, endDate: string, telescopeLocation: string, dataProcRequirement: DataProcRequirementModel, status: string){
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
    }
}
export default SciencePlanModel;