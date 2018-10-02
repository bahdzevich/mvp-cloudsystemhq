import React, { Component } from 'react';
import './invoice.scss';

class Invoice extends Component {
  render() {
    return(
      <div className='invoice'>
        <div className='invoice__content main'>
          <p className='invoice__text text'>Invoice Dependencies</p>
        </div>
      </div>
    )
  }
};

export default Invoice;
