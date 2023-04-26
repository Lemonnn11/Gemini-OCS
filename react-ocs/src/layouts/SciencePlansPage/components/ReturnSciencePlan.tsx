import React from 'react';
import SciencePlanModel from '../../../models/SciencePlanModel';
import { Link, useHistory } from 'react-router-dom';

export const ReturnSciencePlan: React.FC<{ sciencePlan: SciencePlanModel }> = (props) => {

    const history = useHistory();

    const handleClick = (value: number) => {
        history.push(`/manageSciencePlan/${value}`);
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
            ): props.sciencePlan.status === "INVALIDATED" ? (
                <button className="btn btn-danger">
                    {props.sciencePlan.status}
                </button>
            ):
            (
                <button className="btn btn-success">
                    {props.sciencePlan.status}
                </button>
            )}
        </td>
        <td>{props.sciencePlan.creator}</td>
        </tr>


    );
}