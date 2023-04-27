import React, { useState } from 'react';
import SciencePlanModel from '../../../models/SciencePlanModel';
import { Link, Redirect, useHistory } from 'react-router-dom';
import { useOktaAuth } from '@okta/okta-react';

export const ReturnSciencePlan: React.FC<{ sciencePlan: SciencePlanModel }> = (props) => {

    const { oktaAuth, authState } = useOktaAuth();
    const history = useHistory();

    const handleClick = (value: number) => {
        history.push(`/manageSciencePlan/${value}`);
    }

    async function deleteSciencePlan(event: React.MouseEvent<HTMLButtonElement>) {
        event.preventDefault();
        // const data = new FormData();
        // data.append('id', props.sciencePlan.planNo.toString());
        // console.log(data);
        console.log(JSON.stringify(props.sciencePlan.planNo));
        const url = `http://localhost:8080/sciencePlans/delete`;
        const request = {
            method: "DELETE",
            headers: {
                Authorization: `Bearer ${authState?.accessToken?.accessToken}`,
                "Content-Type": "application/json"
            },
            body: JSON.stringify(props.sciencePlan.planNo)
        };

        const validateSciencePlan = await fetch(url, request);
        if (!validateSciencePlan) {
            throw new Error('Error found');
        }
        history.push(`/sciencePlans`);
    }

    return (
        // <Link to={`/sciencePlan/${props.sciencePlan.planNo}`} className='text-decoration-none text-body-secondary'>
        // {props.sciencePlan.planNo}
        // </Link>
        <tr className='align-middle' onClick={() => handleClick(props.sciencePlan.planNo)}>
            <td className='py-3'>
                {props.sciencePlan.planNo}
            </td>
            <td>{props.sciencePlan.starSystem}</td>
            <td>{props.sciencePlan.startDate}</td>
            <td>{props.sciencePlan.endDate}</td>
            <td>
                {props.sciencePlan.status === "SAVED" ? (
                    <button className="btn btn-secondary">
                        {props.sciencePlan.status}
                    </button>
                ) : props.sciencePlan.status === "TESTED" ? (
                    <button className="btn btn-primary">
                        {props.sciencePlan.status}
                    </button>
                ) : props.sciencePlan.status === "SUBMITTED" ? (
                    <button className="btn btn-warning">
                        {props.sciencePlan.status}
                    </button>
                ) : props.sciencePlan.status === "INVALIDATED" ? (
                    <button className="btn btn-danger">
                        {props.sciencePlan.status}
                    </button>
                ) :
                    (
                        <button className="btn btn-success">
                            {props.sciencePlan.status}
                        </button>
                    )}
            </td>
            <td>{props.sciencePlan.creator}</td>
            <td>
                <button className="btn btn-danger" onClick={(event) => { event.stopPropagation(); }} data-bs-toggle="modal" data-bs-target="#popup">
                    <i className="bi bi-trash3"></i>
                </button>
            </td>
            <div className="modal fade" id="popup" tabIndex={-1} aria-labelledby="popup" aria-hidden="true">
                <div className="modal-dialog modal-dialog-centered">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h2 className="modal-title fs-5" id="popup">Delete a science plan</h2>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                            Do you want to delete a science plan?
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" className="btn btn-danger" data-bs-dismiss="modal" onClick={deleteSciencePlan}>delete</button>
                        </div>
                    </div>
                </div>
            </div>
        </tr>


    );
}