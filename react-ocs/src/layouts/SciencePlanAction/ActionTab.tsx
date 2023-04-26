import { useState } from "react";
import { Navbar } from "../NavbarAndFooter/Navbar1"
import { SciencePlanInfoPage } from "../SciencePlanInfoPage/SciencePlanInfoPage";
import { ValidateSciencePlan } from "../ValidateSciencePlan/ValidateSciencePlan";

export const ActionTab = () => {

    const [acctionClick, setActionClick] = useState(false);

    return (
        <div>
            <Navbar />
            <div className="container">
                <div className="mb-2" style={{ marginTop: '70px', marginLeft: '15px' }}><a style={{ fontWeight: 500, fontSize: '22px' }}>Manage Science Plan</a></div>
                <nav>
                    <div className="nav" role="tablist" style={{ marginLeft: '15px' }}>
                        <ul className="nav nav-underline">
                            <li className="nav-item">
                                <a className="nav-link active text-dark" data-bs-toggle="tab" data-bs-target="#submit-tab" aria-current="page">Submit</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link text-dark" data-bs-toggle="tab" data-bs-target="#validate-tab" aria-current="page">Validate</a>
                            </li>
                        </ul>
                    </div>
                </nav>
                <div className="tab-content">
                    <div className="tab-pane fade show active" id="submit-tab">
                        <SciencePlanInfoPage/>
                    </div>
                    <div className="tab-pane fade" id="validate-tab">
                        <ValidateSciencePlan/>
                    </div>
                </div>

            </div>
        </div>
    );

} 