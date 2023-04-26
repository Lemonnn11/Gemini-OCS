import { useEffect, useState } from "react";
import SciencePlanModel from "../../models/SciencePlanModel";
import { SpinnerLoading } from "../Utils/SpinnerLoading";
import DataProcRequirementModel from "../../models/DataProcRequirementModel";
import { useOktaAuth } from '@okta/okta-react';
import AstronomerModel from "../../models/AstronomerModel";
import { ScriptTarget } from "typescript";
import { Navbar } from "../NavbarAndFooter/Navbar1";
import { Link, useHistory } from "react-router-dom";
import SciencePlanModel2 from "../../models/SciencePlanModel2";
import AstronomerModel2 from "../../models/AstronomerModel2";

export const SciencePlanInfoPage = () => {

    const [sciencePlan, setSciencePlan] = useState<SciencePlanModel>();
    const [isLoading, setLoading] = useState(true);
    const [httpError, setHttpError] = useState(null);
    const { oktaAuth, authState } = useOktaAuth();
    const [showWarning, setShowWarning] = useState(false);
    const [showSuccess, setShowSuccess] = useState(false);

    const [planNo, setPlanNo] = useState("");
    const [creator, setCreator] = useState("");
    const [status, setStatus] = useState("");
    const [collab, setCollab] = useState('collaborator');
    const [objectives, setObjective] = useState('');
    const [starSystem, setStarSystem] = useState('Star System');
    const [location, setLocation] = useState('location');
    const [fundingInUSD, setFunding] = useState("");
    const [fileType, setFileType] = useState("");
    const [fileQuality, setFileQuality] = useState("");
    const [colorType, setColorType] = useState("");
    const [contrast, setContrast] = useState("");
    const [brightness, setBrightness] = useState("");
    const [saturation, setSaturation] = useState("");
    const [highlights, setHighlights] = useState("");
    const [exposure, setExposure] = useState("");
    const [shadows, setShadows] = useState("");
    const [whites, setWhites] = useState("");
    const [blacks, setBlacks] = useState("");
    const [luminance, setLuminance] = useState("");
    const [hue, setHue] = useState("");
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [startTime, setStartTime] = useState("");
    const [endTime, setEndTime] = useState("");
    const [starSystems, setStarSystems] = useState<string[]>([]);
    const [collaborators, setCollaborators] = useState<AstronomerModel2[]>([]);

    const history = useHistory();

    const handleClick = () => {
        const id = (window.location.pathname).split('/')[2];
        history.push(`/editSciencePlan/${id}`);
    }

    useEffect(() => {
        const fetchSciencePlans = async () => {
            console.log('It is called');
            const id = (window.location.pathname).split('/')[2];

            const baseUrl: string = `http://localhost:8080/sciencePlans/${id}`;

            const response = await fetch(baseUrl, {
                headers: {
                    "Authorization": `Bearer ${authState?.accessToken?.accessToken}`
                }
            });

            if (!response.ok) {
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

            const collab = new AstronomerModel(responseJson.collaborator[0].fname, responseJson.collaborator[0].lname);

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
                responseJson.status,
                collab
            );

            setSciencePlan(plan);

            setPlanNo(plan.planNo.toString());
            setCreator(plan.creator);
            setStatus(plan.status);
            setFunding(plan.fundingInUSD?.toString());
            setObjective(plan.objectives);
            setStarSystem(plan.starSystem);
            setCollab(plan.collaborator.fname + " "+ plan.collaborator.lname);
            setLocation(plan.telescopeLocation);
            setFileType(plan.dataProcRequirement.fileType);
            setFileQuality(plan.dataProcRequirement.fileQuality);
            setColorType(plan.dataProcRequirement.colorType);
            setContrast(plan.dataProcRequirement.contrast.toString());
            setBrightness(plan.dataProcRequirement.brightness.toString());
            setSaturation(plan.dataProcRequirement.saturation.toString());
            setHighlights(plan.dataProcRequirement.highlights.toString());
            setExposure(plan.dataProcRequirement.exposure.toString());
            setShadows(plan.dataProcRequirement.shadows.toString());
            setWhites(plan.dataProcRequirement.whites.toString());
            setBlacks(plan.dataProcRequirement.blacks.toString());
            setLuminance(plan.dataProcRequirement.luminance.toString());
            setHue(plan.dataProcRequirement.hue.toString());

            setLoading(false);
            console.log(sciencePlan)
        };
        fetchSciencePlans().catch((error: any) => {
            setLoading(false);
            setHttpError(error.message);
        })
    }, []);

    if (isLoading) {
        return (
            <SpinnerLoading />
        )
    }

    if (httpError) {
        return (
            <div className="container m-5">
                <p>{httpError}</p>
            </div>
        )
    }

    async function submitSciencePlan(event: React.MouseEvent<HTMLButtonElement>) {
        event.preventDefault();
        const url = `http://localhost:8080/sciencePlans/submit`;
            const request = {
                method: "PUT",
                headers: {
                    Authorization: `Bearer ${authState?.accessToken?.accessToken}`,
                    "Content-Type": "application/json"
                },
                body: planNo
            };

            const submitSciencePlan = await fetch(url, request);
            if (!submitSciencePlan) {
                setShowWarning(true);
                setShowSuccess(false);
                throw new Error('Error found');
            } else {
                setShowWarning(false);
                setShowSuccess(true);
            }

    }

    return (
        <div>
            <div className="container" style={{ marginTop: '30px' }}>

                <div className="card shadow" style={{ width: 'rem', borderRadius: '1rem' }}>

                    <div className="card-header">
                        <div className="card-tile d-flex bd-highlight pt-3">
                            <div className="card-title mx-2" style={{fontWeight: 500}}>PlanNo</div>
                            <div className="card-title mb-3 text-muted">#000{planNo}</div>
                            <div className="ms-auto bd-highlight">
                                <button className="btn btn-dark" style={{marginTop: '-6px'}} onClick={handleClick}><i className="bi bi-pen"></i> Edit</button>
                            </div>
                        </div>
                    </div>

                    <div className="card-body">
                        <div className="row">
                            <div className="col mt-3" style={{ marginLeft: '40px' }}>
                                <div className="row">
                                    <div className="card-title" style={{ fontWeight: 500 }}>Science Plan</div>
                                </div>
                                <div className="row mb-4 mt-3">
                                    <div className="col-sm-6">
                                        <div className="row">
                                            <label className="col-sm-5" ><i className="bi bi-person"></i> Creator:</label>
                                            <div className="col-sm-5">
                                                <a>{sciencePlan?.creator}</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-sm-6">
                                        <div className="row">
                                            <label className="col-sm-6" ><i className="bi bi-people"></i> Collaborator:</label>
                                            <div className="col">
                                                <a>{sciencePlan?.collaborator.fname} {sciencePlan?.collaborator.lname}</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="row mb-4">
                                    <div className="col-sm-6">
                                        <div className="row">
                                            <label className="col-sm-6" ><i className="bi bi-star"></i> Star System:</label>
                                            <div className="col-sm-5">
                                                <a>{sciencePlan?.starSystem}</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-sm-6">
                                        <div className="row">
                                            <label className="col-sm-5" ><i className="bi bi-geo-alt"></i> Location:</label>
                                            <div className="col">
                                                <a>{sciencePlan?.telescopeLocation}</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="row mb-4">
                                    <div className="col">
                                        <div className="row">
                                            <label className="col-sm-3 mb-2" ><i className="bi bi-x-circle"></i> Objective:</label>
                                            <div className="col-sm-12">
                                                <a>{sciencePlan?.objectives}</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div className="row mb-4">
                                    <div className="col">
                                        <div className="row">
                                            <label className="col-sm-2" >$Funding:</label>
                                            <div className="col">
                                                <a>{sciencePlan?.fundingInUSD}</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="row mb-4">
                                    <div className="col-sm-6">
                                        <div className="row">
                                            <label className="col-sm-5"><i className="bi bi-calendar4"></i> Start date:</label>
                                            <div className="col-sm-5">
                                                <a>{sciencePlan?.startDate}</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-sm-6">
                                        <div className="row">
                                            <label className="col-sm-5" ><i className="bi bi-calendar4"></i> End date:</label>
                                            <div className="col-sm-5">
                                                <a>{sciencePlan?.endDate}</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div className="col mt-3 mb-2" style={{marginRight: '-60px'}}>
                                <div className="card-title mb-4" style={{ fontWeight: 500 }}>Data Process Requirements</div>
                                <div className="row mb-4 mt-3">
                                    <div className="col-sm-6">
                                        <div className="row">
                                            <label className="col-sm-5" ><i className="bi bi-file-earmark"></i> File type:</label>
                                            <div className="col-sm-4">
                                                <a>{sciencePlan?.dataProcRequirement.fileType}</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-sm-6">
                                        <div className="row">
                                            <label className="col-sm-6" ><i className="bi bi-shield-check"></i> File quality:</label>
                                            <div className="col">
                                                <a>{sciencePlan?.dataProcRequirement.fileQuality}</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="row mb-4 mt-3">
                                    <div className="col-sm-6">
                                        <div className="row">
                                            <label className="col-sm-5" ><i className="bi bi-palette"></i> Color type:</label>
                                            <div className="col-sm-5">
                                                <a>{sciencePlan?.dataProcRequirement.colorType}</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-sm-6">
                                        <div className="row">
                                            <label className="col-sm-6" ><i className="bi bi-circle-half"></i> Contrast:</label>
                                            <div className="col">
                                                <a>{sciencePlan?.dataProcRequirement.contrast}</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="row mb-4 mt-3">
                                    <div className="col-sm-6">
                                        <div className="row">
                                            <label className="col-sm-5" ><i className="bi bi-brightness-high"></i> Brightness:</label>
                                            <div className="col-sm-5">
                                                <a>{sciencePlan?.dataProcRequirement.brightness}</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-sm-6">
                                        <div className="row">
                                            <label className="col-sm-6" ><i className="bi bi-thermometer"></i> Saturation:</label>
                                            <div className="col">
                                                <a>{sciencePlan?.dataProcRequirement.saturation}</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="row mb-4 mt-3">
                                    <div className="col-sm-6">
                                        <div className="row">
                                            <label className="col-sm-5" ><i className="bi bi-droplet-fill"></i> Highlights:</label>
                                            <div className="col-sm-5">
                                                <a>{sciencePlan?.dataProcRequirement.highlights}</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-sm-6">
                                        <div className="row">
                                            <label className="col-sm-6" ><i className="bi bi-plus-circle"></i> Exposure:</label>
                                            <div className="col">
                                                <a>{sciencePlan?.dataProcRequirement.exposure}</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="row mb-4 mt-3">
                                    <div className="col-sm-6">
                                        <div className="row">
                                            <label className="col-sm-5" ><i className="bi bi-mask"></i> Shadow:</label>
                                            <div className="col-sm-5">
                                                <a>{sciencePlan?.dataProcRequirement.shadows}</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-sm-6">
                                        <div className="row">
                                            <label className="col-sm-6" ><i className="bi bi-circle"></i> Whites:</label>
                                            <div className="col">
                                                <a>{sciencePlan?.dataProcRequirement.whites}</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="row mb-4 mt-3">
                                    <div className="col-sm-6">
                                        <div className="row">
                                            <label className="col-sm-5" ><i className="bi bi-record-circle-fill"></i> Blacks:</label>
                                            <div className="col-sm-5">
                                                <a>{sciencePlan?.dataProcRequirement.blacks}</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-sm-6">
                                        <div className="row">
                                            <label className="col-sm-6" ><i className="bi bi-brightness-alt-high"></i> Luminance:</label>
                                            <div className="col">
                                                <a>{sciencePlan?.dataProcRequirement.luminance}</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="row mt-3">
                                    <div className="col-sm-6">
                                        <div className="row">
                                            <label className="col-sm-5" ><i className="bi bi-rainbow"></i> Hue:</label>
                                            <div className="col-sm-5">
                                                <a>{sciencePlan?.dataProcRequirement.hue}</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>

                    </div>
                    <div className="d-flex justify-content-end">
                        <button className="btn btn-warning mb-4 mx-5" type="button" onClick={submitSciencePlan}>Submit<i className="bi bi-caret-right-fill"></i></button>
                    </div>


                </div>
            </div>
        </div>
    );
}