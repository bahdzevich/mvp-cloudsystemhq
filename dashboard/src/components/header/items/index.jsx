import React, { Component } from 'react';
import { Route, Link } from 'react-router-dom';
import { withLocalize, Translate } from 'react-localize-redux';

class HeaderItems extends Component {
  render() {
    return(
      <Translate>
        {({translate}) =>
          <div className='header__items'>
            <HeaderItem activeOnlyWhenExact={true} to='/' label={translate('header.home')} />
            <HeaderItem to='/create' label={translate('header.create')} />
            <HeaderItem to='/invoice' label={translate('header.invoice')} />
          </div>
        }
      </Translate>
    )
  }
};

const HeaderItem = ({ label, to, activeOnlyWhenExact }) => (
  <Route
    path={to}
    exact={activeOnlyWhenExact}
    children={({ match }) => (
      <Link to={to} className={'header__item text' + (match ? ' header__item--active' : '')}>{label}</Link>
    )}
  />
);

export default withLocalize(HeaderItems);
