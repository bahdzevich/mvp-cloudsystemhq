import React, { Component } from 'react';
import PropTypes from 'prop-types';
import './invoice.scss';

const strings = {
  en: {
    text: 'Invoice Dependencies'
  },
  ru: {
    text: 'Зависимости счетов'
  },
  fr: {
    text: "Dépendances du compte"
  },
  gr: {
    text: 'Kontoabhängigkeiten'
  },
  it: {
    text: 'Dependencias de cuentas'
  }
};

class Invoice extends Component {
  constructor(){
    super();
    this.state = {
      strings: strings[Object.keys(strings)[0]],
    }
    this.checkLang = this.checkLang.bind(this);
  }
  checkLang(lang) {
    const language = strings[lang];
    this.setState({strings: language});
  }
  componentWillMount() {
    this.checkLang(this.props.lang);
  }
  componentWillReceiveProps() {
    setTimeout(() => { this.checkLang(this.props.lang); }, 0);
  }
  render() {
    const strings = this.state.strings;
    return(
      <div className='invoice'>
        <div className='invoice__content main'>
          <p className='invoice__text text'>{strings.text}</p>
        </div>
      </div>
    )
  }
};

export default Invoice;

Invoice.propTypes = {
  lang: PropTypes.string,
}
