import React, { Component } from 'react';
import { withLocalize, Translate } from 'react-localize-redux';

import translations from '../../translations/create.json';
import './create.scss';

class Create extends Component {
  constructor(props){
    super(props);
    this.props.addTranslation(translations);
  }
  render() {
    return(
      <Translate>
        {({translate}) =>
          <div className='create'>
            <div className='create__content main'>
              <p className='create__text text'>{translate('create.text')}</p>
            </div>
          </div>
        }
      </Translate>
    )
  }
};

export default withLocalize(Create);
