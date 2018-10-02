import React, { Component } from 'react';
import { Route, Link } from "react-router-dom";
import Logout from './icons/logout.svg';
import './header.scss';

class Header extends Component {
  render() {
    return(
      <div className='header'>
        <div className='header__content main'>
          <div className='header__items'>
            <HeaderItem activeOnlyWhenExact={true} to='/' label='Home' />
            <HeaderItem to='/create' label='Create questions' />
            <HeaderItem to='/invoice' label='Invoice Dependencies' />
          </div>

          <a className='header__logout'>
            <Logout />
          </a>
        </div>
      </div>
    )
  }
};

const HeaderItem = ({ label, to, activeOnlyWhenExact }) => (
  <Route
    path={to}
    exact={activeOnlyWhenExact}
    children={({ match }) => (
      <Link to={to} className={'header__item' + (match ? ' header__item--active' : '')}>{label}</Link>
    )}
  />
);

export default Header;
