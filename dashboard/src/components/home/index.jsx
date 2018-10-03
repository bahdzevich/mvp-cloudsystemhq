import React, { Component } from 'react';
import PropTypes from 'prop-types';
import './home.scss';

const strings = {
  en: {
    text: 'Home page'
  },
  ru: {
    text: 'Главная страница'
  },
  fr: {
    text: "Page d'accueil"
  },
  gr: {
    text: 'Hauptseite'
  },
  it: {
    text: 'Página de inicio'
  }
};

class Home extends Component {
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
      <div className='home'>
        <div className='home__content main'>
          <p className='home__text text'>{strings.text}</p>
        </div>
      </div>
    )
  }
};

export default Home;

Home.propTypes = {
  lang: PropTypes.string,
}
