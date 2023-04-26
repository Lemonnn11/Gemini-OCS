import { useEffect, useState } from "react";
import SciencePlanModel from "../../models/SciencePlanModel";
import { error } from "console";
import { ReturnSciencePlan } from "./components/ReturnSciencePlan";
import { SpinnerLoading } from "../Utils/SpinnerLoading";
import DataProcRequirementModel from "../../models/DataProcRequirementModel";
import { Navbar } from "../NavbarAndFooter/Navbar1";
import { useOktaAuth } from '@okta/okta-react';
import { Link } from "react-router-dom";
import AstronomerModel from "../../models/AstronomerModel";

export const SciencePlans = () =>{

    const [sciencePlans, setSciencePlan] = useState<SciencePlanModel[]>([]);
    const [isLoading, setLoading] = useState(true);
    const [httpError, setHttpError] = useState(null);
    const [query, setQuery] = useState('');
    const [queryUrl, setQueryUrl] = useState('');
    const [category, setCategory] = useState('Status');
    const{ oktaAuth, authState } = useOktaAuth();

    useEffect(() => {
        const fetchSciencePlans = async () => {

            const baseUrl: string = "http://localhost:8080/sciencePlans/";

            let url: string = ''

            if(queryUrl === ''){
                url = baseUrl;
            }else{
                url = baseUrl + queryUrl;
            }

            const response = await fetch(url, {
                headers: {
                  "Authorization": `Bearer ${authState?.accessToken?.accessToken}`
                }
              });

            if(!response.ok){
                throw new Error('Error found');
            }
            const responseJson = await response.json();

            const loadedSciencePlan: SciencePlanModel[] = [];

            for(const key in responseJson){

                const dataProcReq = new DataProcRequirementModel(
                    responseJson[key].dataProcRequirements[0].fileType,
                    responseJson[key].dataProcRequirements[0].fileQuality,
                    responseJson[key].dataProcRequirements[0].colorType,
                    responseJson[key].dataProcRequirements[0].contrast,
                    responseJson[key].dataProcRequirements[0].brightness,
                    responseJson[key].dataProcRequirements[0].saturation,
                    responseJson[key].dataProcRequirements[0].highlights,
                    responseJson[key].dataProcRequirements[0].exposure,
                    responseJson[key].dataProcRequirements[0].shadows,
                    responseJson[key].dataProcRequirements[0].whites,
                    responseJson[key].dataProcRequirements[0].blacks,
                    responseJson[key].dataProcRequirements[0].luminance,
                    responseJson[key].dataProcRequirements[0].hue
                );

                const collab = new AstronomerModel(responseJson[key].collaborator[0].fname, responseJson[key].collaborator[0].lname);
                
                const plan = new SciencePlanModel(
                    responseJson[key].planNo,
                    responseJson[key].creator,
                    responseJson[key].submitter,
                    responseJson[key].fundingInUSD,
                    responseJson[key].objectives,
                    responseJson[key].starSystem,
                    responseJson[key].startDate,
                    responseJson[key].endDate,
                    responseJson[key].telescopeLocation,
                    dataProcReq,
                    responseJson[key].status,
                    collab
                );
            
                loadedSciencePlan.push(plan);
            }

            setSciencePlan(loadedSciencePlan);
            setLoading(false);
        };
        fetchSciencePlans().catch((error: any) => {
            setLoading(false);
            setHttpError(error.message);
        })
    }, [queryUrl]);

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

    const handleQuery = () => {
        if (query === '') {
          setQueryUrl('');
        } else {
          setQueryUrl(`search?query=${query}`);
        }
      };

    const handleCategory = (value: string) => {
        if(value === 'Status'){
            setQueryUrl('');
        }else{
            setCategory(value);
            setQueryUrl(`category?status=${value}`);
        }
    }

    return(
        <div>
            <Navbar/>
            <div className="container-fluid bs-emphasis-color">
            <div className="d-flex mb-1" style={{marginTop: '10px', marginLeft: '15px', marginRight: '15px'}}>
                <div className="me-auto p-2"><p style={{ fontSize: '26px', fontWeight:'bold'}}>Science Plan</p></div>
                <div className="p-2" style={{marginTop: '8px'}}>
                    <Link type="button" className="btn btn-dark" to={"/createSciencePlan"}><i className="bi bi-plus-lg"></i> Create a science plan</Link>
                </div>
            </div>
            <div className="row mb-4 ms-3">
                <div className="col-6">
                    <div className="d-flex">
                        <input className="form-control me-2" type="search"
                        placeholder="Search..." aria-labelledby="Search"
                        onChange={e => setQuery(e.target.value)}/>
                        <button className="btn btn-outline-secondary" onClick={() => handleQuery()}>
                            Search
                        </button>
                    </div>
                </div>
                <div className="col-4">
                    <div className="dropdown">
                        <button className="btn btn-secondary dropdown-toggle" type="button"  data-bs-toggle="dropdown" aria-expanded="false">
                            {category}
                        </button>
                        <ul className="dropdown-menu">
                            <li onClick={() => handleCategory("SAVED")}>
                                <a className="dropdown-item" href="#">SAVED</a>
                            </li>
                            <li onClick={() => handleCategory("TESTED")}>
                                <a className="dropdown-item" href="#">TEST</a>
                            </li>
                            <li onClick={() => handleCategory("SUBMITTED")}>
                                <a className="dropdown-item" href="#">SUBMITTED</a>
                            </li>
                            <li onClick={() => handleCategory("Status")}>
                                <a className="dropdown-item" href="#">All</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            {/* <div className="mt-2 ms-5">
                <h5>Number of results: (20)</h5>
                <p>1 to 20 of {sciencePlans.length} plans</p>
            </div> */}
            <div>
                <table className='table table-hover '>
                    <thead className="table-active text-center">
                        <tr >
                            <th scope='col'>planNo</th>
                            <th scope='col'>starSystem</th>
                            <th scope='col'>startDate</th>
                            <th scope='col'>endDate</th>
                            <th scope='col'>status</th>
                            <th scope='col'>creator</th>
                        </tr>
                    </thead>
                    <tbody className="text-center">
                        {sciencePlans.slice(0,sciencePlans.length).map(sciencePlan => (
                        <ReturnSciencePlan sciencePlan={sciencePlan} key={sciencePlan.planNo}/>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
        </div>
    );
}