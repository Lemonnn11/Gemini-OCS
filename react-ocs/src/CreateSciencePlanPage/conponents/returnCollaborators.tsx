import React from 'react';
import { Link, useHistory } from 'react-router-dom';
import AstronomerModel from '../../models/AstronomerModel';

export interface ReturnCollaboratorsProps {
    collaborator: AstronomerModel;
    handleCollab: (value: string) => void;
}

export const ReturnCollaborators: React.FC<ReturnCollaboratorsProps> = (props) => {
    const history = useHistory();

    return (
        <li key={props.collaborator.id} onClick={() => props.handleCollab(`${props.collaborator.fname} ${props.collaborator.lname}`)}>
            <a  className="dropdown-item col" href="#">{props.collaborator.fname} {props.collaborator.lname}</a>
        </li>
    );
};