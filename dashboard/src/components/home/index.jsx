import React, { Component } from 'react';
import { withLocalize, Translate } from 'react-localize-redux';

import translations from '../../translations/index.json';
import './home.scss';

class Home extends Component {
  constructor(props){
    super(props);
    this.props.addTranslation(translations);
  }
  render() {
    return(
      <Translate>
        {({translate}) =>
          <div className='home'>
            <div className='home__content main'>
              <p className='home__text text'>{translate('home.text')}</p>
            </div>
          </div>
        }
      </Translate>
    )
  }
};

export default withLocalize(Home);
