import React, { Component } from 'react'
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';
import Eror from './icons/404.svg';
import './not-found.scss';

const strings = {
  en: {
    text: 'Page not found.',
    text1: 'Go back to the',
    link: 'main page'
  },
  ru: {
    text: 'Страница не найдена.',
    text1: 'Вернуться на',
    link: 'главную страницу'
  },
  fr: {
    text: 'Page non trouvée.',
    text1: 'Retour à',
    link: "page d'accueil"
  },
  gr: {
    text: 'Seite nicht gefunden.',
    text1: 'Zurück zu',
    link: 'startseite'
  },
  it: {
    text: 'Página no encontrada.',
    text1: 'Volver a',
    link: 'página de inicio'
  }
};

export default class NotFound extends Component {
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
    return (
      <div className='not-found'>
        <Eror width={54} height={54}/>
        <p className='not-found__text text'>{strings.text}</p>
        <p className='not-found__text text'>{strings.text1} <Link to='/'>{strings.link}</Link>?</p>
      </div>
    )
  };
}

NotFound.propTypes = {
  lang: PropTypes.string,
}
