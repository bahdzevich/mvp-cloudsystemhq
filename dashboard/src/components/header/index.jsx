import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Route, Link } from "react-router-dom";
import Logout from './icons/logout.svg';
import './header.scss';

const strings = {
  en: {
    home: 'Home',
    creacte: 'Create questions',
    invoice: 'Invoice Dependencies'
  },
  ru: {
    home: 'Главная страница',
    creacte: 'Создать вопросы',
    invoice: 'Зависимости счетов'
  },
  fr: {
    home: "Page d'accueil",
    creacte: 'Créer des questions',
    invoice: 'Dépendances du compte'
  },
  gr: {
    home: 'Hauptseite',
    creacte: 'Erstellen Sie Fragen',
    invoice: 'Kontoabhängigkeiten'
  },
  it: {
    home: 'Página de inicio',
    creacte: 'Crear preguntas',
    invoice: 'Dependencias de cuentas'
  }
};

class Header extends Component {
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
    const lang = this.props.lang,
          strings = this.state.strings;

    return(
      <div className='header'>
        <div className='header__content main'>
          <div className='header__items'>
            <HeaderItem activeOnlyWhenExact={true} to='/' label={strings.home} />
            <HeaderItem to='/create' label={strings.creacte} />
            <HeaderItem to='/invoice' label={strings.invoice} />
          </div>

          <div className='header__btns'>
            <div className='header__lang-wrap'>
              <LangItem lang={lang} language='en' change={this.props.changeLang}/>
              <LangItem lang={lang} language='ru' change={this.props.changeLang}/>
              <LangItem lang={lang} language='fr' change={this.props.changeLang}/>
              <LangItem lang={lang} language='gr' change={this.props.changeLang}/>
              <LangItem lang={lang} language='it' change={this.props.changeLang}/>
            </div>


            <a className='header__logout'>
              <Logout />
            </a>
          </div>
        </div>
      </div>
    )
  }
};

const LangItem = ({ lang, language, change }) => (
  <a
    className={'header__lang-item text-little' + (lang === language ? ' header__lang-item--active' : '') }
    onClick={() => change(language)}>
    {language}
  </a>
);

const HeaderItem = ({ label, to, activeOnlyWhenExact }) => (
  <Route
    path={to}
    exact={activeOnlyWhenExact}
    children={({ match }) => (
      <Link to={to} className={'header__item text' + (match ? ' header__item--active' : '')}>{label}</Link>
    )}
  />
);

export default Header;

Header.propTypes = {
  changeLang: PropTypes.func,
  lang: PropTypes.string,
}
