import React from 'react';
import { Link, useHistory } from 'react-router-dom';

export interface ReturnStarSystemsProps {
    starSystem: string;
    handleStarSystem: (value: string) => void;
}

export const ReturnStarSystems: React.FC<ReturnStarSystemsProps> = (props) => {
    const history = useHistory();

    return (
        <li key={props.starSystem} onClick={() => props.handleStarSystem(`${props.starSystem}`)}>
            <a className="dropdown-item col" href="#">{props.starSystem}</a>
        </li>
    );
};