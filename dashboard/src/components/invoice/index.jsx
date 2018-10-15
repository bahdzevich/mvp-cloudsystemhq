import React, { Component } from 'react';
import { withLocalize, Translate } from 'react-localize-redux';

import translations from '../../translations/invoice.json';
import './invoice.scss';

class Invoice extends Component {
  constructor(props){
    super(props);
    this.props.addTranslation(translations);
  }
  render() {
    return(
      <Translate>
        {({translate}) =>
          <div className='invoice'>
            <div className='invoice__content main'>
              <p className='invoice__text text'>{translate('invoice.text')}</p>
            </div>
          </div>
        }
      </Translate>
    )
  }
};

export default withLocalize(Invoice);
