import { useEffect, useState } from "react";
import { Navbar } from "../layouts/NavbarAndFooter/Navbar1";
import { useOktaAuth } from '@okta/okta-react';
import { SpinnerLoading } from "../layouts/Utils/SpinnerLoading";
import { ReturnCollaborators } from "./conponents/returnCollaborators";
import { ReturnStarSystems } from "./conponents/returnStarStstems";
import AstronomerModel from "../models/AstronomerModel";
import DataProcRequirementModel from "../models/DataProcRequirementModel";
import SciencePlanModel from "../models/SciencePlanModel";
import SciencePlanModel2 from "../models/SciencePlanModel2";
import AstronomerModel2 from "../models/AstronomerModel2";


export const CreateSciencePlanPage = () => {

    const [showWarning, setShowWarning] = useState(false);
    const [showSuccess, setShowSuccess] = useState(false);
    const [isLoading, setLoading] = useState(true);
    const [httpError, setHttpError] = useState(null);
    const { oktaAuth, authState } = useOktaAuth();


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


    useEffect(() => {

    const fetchStarSystems = async () => {

        const url: string = "http://localhost:8080/resources/starSystem";

        const response = await fetch(url, {
            headers: {
              "Authorization": `Bearer ${authState?.accessToken?.accessToken}`
            }
          });

        if (!response.ok) {
            throw new Error('Error found');
        }
        const responseJson = await response.json();

        const loadedStarSystems: string[] = [];

        for (const key in responseJson) {

            loadedStarSystems.push(responseJson[key]);
        }

        console.log(loadedStarSystems);

        setStarSystems(loadedStarSystems);
        setLoading(false);
    };

    const fetchCollaborators = async () => {

        const url: string = "http://localhost:8080/resources/allAstronomer";

        const response = await fetch(url, {
            headers: {
              "Authorization": `Bearer ${authState?.accessToken?.accessToken}`
            }
          });

        if (!response.ok) {
            throw new Error('Error foundd');
        }
        const responseJson = await response.json();

        const loadedCollaborators: AstronomerModel2[] = [];

        for (const key in responseJson) {

            const person = new AstronomerModel2(
                responseJson[key].id,
                responseJson[key].fname,
                responseJson[key].lname,
            );

            loadedCollaborators.push(person);
        }

        setCollaborators(loadedCollaborators);
        setLoading(false);
    };
    fetchStarSystems().catch((error: any) => {
        setLoading(false);
        setHttpError(error.message);
    })
        fetchCollaborators().catch((error: any) => {
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

    const handleLocation = (value: string) => {
        setLocation(value);
    }

    const handleCollab = (value: string) => {
        setCollab(value);
    }

    const handleStarSystem = (value: string) => {
        if (value === '') {
            setStarSystem(value);
        } else {
            setStarSystem(value);
        }
    }

    async function addSciencePlan(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault();
        const url = `http://localhost:8080/sciencePlans/add`;
        if (authState?.isAuthenticated && collab !== 'collaborator' && fundingInUSD !== '' && starSystem !== 'Star System' && location !== 'location' && objectives !== ''
            && fileType !== '' && fileQuality !== '' && colorType !== '' && contrast !== '' && brightness !== '' && saturation !== '' && highlights !== '' && exposure !== '' && shadows !== ''
            && whites !== '' && blacks !== '' && luminance !== '' && hue !== '' && startDate !== '' && startTime !== '' && endDate !== '' && endTime !== '') {
            const [firstName, lastName] = collab.split(" ");
            const collabora: AstronomerModel = new AstronomerModel(firstName, lastName);
            const DPR: DataProcRequirementModel = new DataProcRequirementModel(fileType, fileQuality, colorType, parseInt(contrast), parseInt(brightness), parseInt(saturation), parseInt(highlights),
                parseInt(exposure), parseInt(shadows), parseInt(whites), parseInt(blacks), parseInt(luminance), parseInt(hue))
            const sciencePlann: SciencePlanModel2 = new SciencePlanModel2(parseInt(fundingInUSD), objectives, starSystem, new Date(startDate + 'T' + startTime).toISOString(), new Date(endDate + 'T' + endTime).toISOString(), location,
                DPR, collabora, "SAVED");
            console.log(JSON.stringify(sciencePlann));
            const request = {
                method: "POST",
                headers: {
                    Authorization: `Bearer ${authState?.accessToken?.accessToken}`,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(sciencePlann)
            };

            const addNewSciencePlan = await fetch(url, request);
            if (!addNewSciencePlan) {
                throw new Error('Error found');
            } else {
                setShowWarning(false);
                setShowSuccess(true);
            }

            setCollab('collaborator');
            setFunding('');
            setStarSystem('Star System');
            setLocation('location');
            setObjective('');
            setFileType('');
            setFileQuality('');
            setColorType('');
            setContrast('');
            setBrightness('');
            setSaturation('');
            setHighlights('');
            setExposure('');
            setShadows('');
            setWhites('');
            setBlacks('');
            setLuminance('');
            setHue('');
            setStartDate('');
            setStartTime('');
            setEndDate('');
            setEndTime('');

        } else {
            setShowWarning(true);
            setShowSuccess(false)
        }

    }

    return (
        <div>
            <Navbar />
            <div>
                <div>
                </div>
                <div className="card shadow" style={{ marginTop: '50px', marginLeft: '160px', marginRight: '160px', borderRadius: '15px' }}>
                    <div className="card-header pt-3 pb-3 row d-flex justify-content-between" style={{marginLeft: '0.5px', marginRight: '0.5px'}}>
                        <div className="col">
                            <a style={{ fontSize: '25px', fontWeight: 500 }}>Create a science plan</a>
                        </div>
                        <div className="col mt-2">
                            {showSuccess &&
                                    <a>Create science plan successfully</a>
                            }
                            {showWarning &&
                                <div className="alert alert-danger" role="alert">
                                    Something went wrong
                                </div>
                            }
                        </div>
                    </div>
                    <div className="card-body">
                        <form onSubmit={addSciencePlan}>
                            <div className="row ml-2 mt-3">
                                <div className="col mx-5">
                                    <div className="row">
                                        <div className="col-md-3 mb-3">
                                            <label className="form-label">Start Date:</label>
                                            <input type="date" className="form-control" name="startDate" required onChange={e => setStartDate(e.target.value)} value={startDate} />
                                        </div>
                                        <div className="col-md-3 mb-3">
                                            <label className="form-label">Start Time:</label>
                                            <input type="time" className="form-control" name="startTime" required onChange={e => setStartTime(e.target.value)} value={startTime} />
                                        </div>
                                        <div className="col-md-3 mb-3">
                                            <label className="form-label">End Date:</label>
                                            <input type="date" className="form-control" name="endDate" required onChange={e => setEndDate(e.target.value)} value={endDate} />
                                        </div>
                                        <div className="col-md-3 mb-3">
                                            <label className="form-label">End Time:</label>
                                            <input type="time" className="form-control" name="endTime" required onChange={e => setEndTime(e.target.value)} value={endTime} />
                                        </div>
                                        <div className="col-md-3 mb-3">
                                            <label className="form-label">Star System:</label>
                                            <div className="dropdown">
                                                <button className="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                    {starSystem}
                                                </button>
                                                <ul className="dropdown-menu" style={{ maxHeight: '300px', overflowY: 'auto' }}>
                                                    {starSystems.slice(0, starSystems.length).map(starSystem => (
                                                        <ReturnStarSystems starSystem={starSystem} handleStarSystem={handleStarSystem} />
                                                    ))}
                                                </ul>
                                            </div>
                                        </div>
                                        <div className="col-md-3 mb-3">
                                            <label className="form-label">Collaborators:</label>
                                            <div className="dropdown">
                                                <button className="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                    {collab}
                                                </button>
                                                <ul className="dropdown-menu" >
                                                    {collaborators.slice(0, collaborators.length).map(collaborator => (
                                                        <ReturnCollaborators collaborator={collaborator} handleCollab={handleCollab} key={collaborator.id} />
                                                    ))}
                                                </ul>
                                            </div>
                                        </div>
                                        <div className="col-md-4 mb-3">
                                            <label className="form-label">Telescope Location:</label>
                                            <div className="dropdown">
                                                <button className="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                    {location}
                                                </button>
                                                <ul className="dropdown-menu">
                                                    <li onClick={() => handleLocation("CHILE")}>
                                                        <a className="dropdown-item" href="#">CHILE</a>
                                                    </li>
                                                    <li onClick={() => handleLocation("HAWAII")}>
                                                        <a className="dropdown-item" href="#">HAWAII</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div className="col-md-4 mb-3">
                                            <label className="form-label">Funding(USD):</label>
                                            <input type="text" className="form-control" name="fundingInUSD" required onChange={e => setFunding(e.target.value)} value={fundingInUSD} />
                                        </div>
                                    </div>
                                </div>
                                <div className="col">
                                    <div className="row">
                                        <div className="col-md-11 mb-3">
                                            <div className="row">
                                                <label className="col form-label">Objective:</label>
                                            </div>
                                            <textarea className="form-control" name="objectives" required rows={7} onChange={e => setObjective(e.target.value)} value={objectives}></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <hr />
                            <div className="row">
                                <div className="row ml-2 mt-3">
                                    <div className="col mx-5">
                                        <label className="form-label" style={{ fontSize: '20px', fontWeight: 400 }}>Data Process Requirements</label>
                                    </div>
                                </div>
                                <div className="row ml-2 mt-2">
                                    <div className="col mx-5">
                                        <div className="row">
                                            <div className="col-md-2 mb-3">
                                                <label className="form-label">File Type:</label>
                                                <input type="text" className="form-control" name="fileType" required onChange={e => setFileType(e.target.value)} value={fileType} />
                                            </div>
                                            <div className="col-md-2 mb-3">
                                                <label className="form-label">File Quality:</label>
                                                <input type="text" className="form-control" name="fileQuality" required onChange={e => setFileQuality(e.target.value)} value={fileQuality} />
                                            </div>
                                            <div className="col-md-2 mb-3">
                                                <label className="form-label">Color Type:</label>
                                                <input type="text" className="form-control" name="colorType" required onChange={e => setColorType(e.target.value)} value={colorType} />
                                            </div>
                                            <div className="col-md-2 mb-3">
                                                <label className="form-label">Contrast:</label>
                                                <input type="text" className="form-control" name="contrast" required onChange={e => setContrast(e.target.value)} value={contrast} />
                                            </div>
                                            <div className="col-md-2 mb-3">
                                                <label className="form-label">Brightness:</label>
                                                <input type="text" className="form-control" name="brightness" required onChange={e => setBrightness(e.target.value)} value={brightness} />
                                            </div>
                                            <div className="col-md-2 mb-3">
                                                <label className="form-label">Saturation:</label>
                                                <input type="text" className="form-control" name="saturation" required onChange={e => setSaturation(e.target.value)} value={saturation} />
                                            </div>
                                            <div className="col-md-2 mb-3">
                                                <label className="form-label">Highlights:</label>
                                                <input type="text" className="form-control" name="highlights" required onChange={e => setHighlights(e.target.value)} value={highlights} />
                                            </div>
                                            <div className="col-md-2 mb-3">
                                                <label className="form-label">Exposure:</label>
                                                <input type="text" className="form-control" name="exposure" required onChange={e => setExposure(e.target.value)} value={exposure} />
                                            </div>
                                            <div className="col-md-2 mb-3">
                                                <label className="form-label">Shadows:</label>
                                                <input type="text" className="form-control" name="shadows" required onChange={e => setShadows(e.target.value)} value={shadows} />
                                            </div>
                                            <div className="col-md-2 mb-3">
                                                <label className="form-label">whites:</label>
                                                <input type="text" className="form-control" name="whites" required onChange={e => setWhites(e.target.value)} value={whites} />
                                            </div>
                                            <div className="col-md-2 mb-3">
                                                <label className="form-label">Blacks:</label>
                                                <input type="text" className="form-control" name="blacks" required onChange={e => setBlacks(e.target.value)} value={blacks} />
                                            </div>
                                            <div className="col-md-2 mb-3">
                                                <label className="form-label">Luminance:</label>
                                                <input type="text" className="form-control" name="luminance" required onChange={e => setLuminance(e.target.value)} value={luminance} />
                                            </div>
                                            <div className="col-md-2">
                                                <label className="form-label">Hue:</label>
                                                <input type="text" className="form-control" name="hue" required onChange={e => setHue(e.target.value)} value={hue} />
                                            </div>
                                            <div className="d-flex justify-content-end">
                                                <button className="btn btn-primary btn-md mb-3 px-3 pt-2"><i className="bi bi-save"></i> SAVED</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );

}