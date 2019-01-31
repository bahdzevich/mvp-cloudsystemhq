import React from 'react';
import {Link, Route} from "react-router-dom";

export const NavbarItemComponent = ({ label, to, activeOnlyWhenExact }) => (
    <Route
        path={to}
        exact={activeOnlyWhenExact}
        children={({ match }) => (
            <Link to={to} className={'header__item text' + (match ? ' header__item--active' : '')}>{label}</Link>
        )}
    />
);