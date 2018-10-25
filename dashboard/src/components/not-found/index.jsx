import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { withLocalize, Translate } from 'react-localize-redux';

import Eror from './icons/404.svg';
import './not-found.scss';

class NotFound extends Component {
  render() {
    return (
      <Translate>
        {({translate}) =>
          <div className='not-found'>
            <Eror width={54} height={54}/>
            <p className='not-found__text text'>
              {translate('not-found.text')}
            </p>
            <p className='not-found__text text'>
              {translate('not-found.text1')} <Link to='/'>{translate('not-found.link')}</Link>?
            </p>
          </div>
        }
      </Translate>
    )
  };
}

export default withLocalize(NotFound);
