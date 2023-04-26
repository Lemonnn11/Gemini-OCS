import { useEffect, useState } from "react";
import { Navbar } from "../NavbarAndFooter/Navbar1"
import { SciencePlanInfoPage } from "../SciencePlanInfoPage/SciencePlanInfoPage";
import { ValidateSciencePlan } from "../ValidateSciencePlan/ValidateSciencePlan";
import { TestSciencePlan } from "../TestSciencePlan";
import { useOktaAuth } from "@okta/okta-react";
import { SpinnerLoading } from "../Utils/SpinnerLoading";

export const ActionTab = () => {

    const [acctionClick, setActionClick] = useState(false);
    const { oktaAuth, authState } = useOktaAuth();
    const [isLoading, setLoading] = useState(true);
    const [httpError, setHttpError] = useState(null);
    const [status, setStatus] = useState("");

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

            setStatus(responseJson.status,);

            setLoading(false);
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

    return (
        <div>
            <Navbar />
            <div className="container">
                <div className="mb-2" style={{ marginTop: '70px', marginLeft: '15px' }}><a style={{ fontWeight: 500, fontSize: '22px' }}>Manage Science Plan</a></div>
                <nav>
                    <div className="nav" role="tablist" style={{ marginLeft: '15px' }}>
                        <ul className="nav nav-underline">
                            <li className="nav-item">
                                {status === "SAVED" ? (<a className="nav-link active text-dark" data-bs-toggle="tab" data-bs-target="#test-tab" aria-current="page">Test</a>) : (<a className="nav-link text-dark" data-bs-toggle="tab" data-bs-target="#test-tab" aria-current="page">Test</a>)}
                            </li>
                            <li className="nav-item">
                                {status === "TESTED" ? (<a className="nav-link active text-dark" data-bs-toggle="tab" data-bs-target="#submit-tab" aria-current="page">Submit</a>) : (<a className="nav-link text-dark" data-bs-toggle="tab" data-bs-target="#submit-tab" aria-current="page">Submit</a>)}
                            </li>
                            <li className="nav-item">
                                {status === "SUBMITTED" ? (<a className="nav-link active text-dark" data-bs-toggle="tab" data-bs-target="#validate-tab" aria-current="page">Validate</a>) : (<a className="nav-link text-dark" data-bs-toggle="tab" data-bs-target="#validate-tab" aria-current="page">Validate</a>)}
                            </li>
                        </ul>
                    </div>
                </nav>
                <div className="tab-content">
                    {status === "SAVED" ? (<div className="tab-pane fade show active" id="test-tab">
                        <TestSciencePlan />
                    </div>) : (<div className="tab-pane fade" id="test-tab">
                        <TestSciencePlan />
                    </div>)}
                    {status === "TESTED" ? (<div className="tab-pane fade show active" id="submit-tab">
                        <SciencePlanInfoPage />
                    </div>) : (<div className="tab-pane fade" id="submit-tab">
                        <SciencePlanInfoPage />
                    </div>)}
                    {status === "SUBMITTED" ? (<div className="tab-pane fade show active" id="validate-tab">
                        <ValidateSciencePlan />
                    </div>):(<div className="tab-pane fade" id="validate-tab">
                        <ValidateSciencePlan />
                    </div>)}
                </div>

            </div>
        </div>
    );

} 