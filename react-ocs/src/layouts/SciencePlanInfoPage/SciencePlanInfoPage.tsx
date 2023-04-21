import { useEffect, useState } from "react";
import SciencePlanModel from "../../models/SciencePlanModel";
import { SpinnerLoading } from "../Utils/SpinnerLoading";
import DataProcRequirementModel from "../../models/DataProcRequirementModel";

export const SciencePlanInfoPage = () => {

    const [sciencePlan, setSciencePlan] = useState<SciencePlanModel>();
    const [isLoading, setLoading] = useState(true);
    const [httpError, setHttpError] = useState(null);
    
    useEffect(() => {
        const fetchSciencePlans = async () => {
            console.log('It is called');
            const id = (window.location.pathname).split('/')[2];

            const baseUrl: string = `http://localhost:8080/sciencePlans/${id}`;

            const response = await fetch(baseUrl);

            if(!response.ok){
                throw new Error('Error found');
            }
            const responseJson = await response.json();

            const dataProcReq = new DataProcRequirementModel(
                responseJson.dataProcRequirements[0].fileType,
                responseJson.dataProcRequirements[0].fileQuality,
                responseJson.dataProcRequirements[0].colorType,
                responseJson.dataProcRequirements[0].contrast,
                responseJson.dataProcRequirements[0].brightness,
                responseJson.dataProcRequirements[0].saturation,
                responseJson.dataProcRequirements[0].highlights,
                responseJson.dataProcRequirements[0].exposure,
                responseJson.dataProcRequirements[0].shadows,
                responseJson.dataProcRequirements[0].whites,
                responseJson.dataProcRequirements[0].blacks,
                responseJson.dataProcRequirements[0].luminance,
                responseJson.dataProcRequirements[0].hue
            );
            
            const plan = new SciencePlanModel(
                responseJson.planNo,
                responseJson.creator,
                responseJson.submitter,
                responseJson.fundingInUSD,
                responseJson.objectives,
                responseJson.starSystem,
                responseJson.startDate,
                responseJson.endDate,
                responseJson.telescopeLocation,
                dataProcReq,
                responseJson.status
            );

            setSciencePlan(plan);
            setLoading(false);
            console.log(sciencePlan)
        };
        fetchSciencePlans().catch((error: any) => {
            setLoading(false);
            setHttpError(error.message);
        })
    }, []);

    if(isLoading){
        return(
            <SpinnerLoading/>
        )
    }

    if(httpError){
        return(
            <div className="container m-5">
                <p>{httpError}</p>
            </div>
        )
    }

    return(
        <div>
            <h3>{sciencePlan?.starSystem}</h3>
            <h3>{sciencePlan?.creator}</h3>
        </div>
    );
}