import React, { Component } from 'react';
import { renderToStaticMarkup } from 'react-dom/server';
import { withLocalize } from 'react-localize-redux';
import LanguageToggle from './languageToggle';
import HeaderItems from './items';

import globalTranslations from '../../translations/global.json';
import Logout from './icons/logout.svg';
import './header.scss';


class Header extends Component {
  constructor(props){
    super(props);
    this.lang = (window.localStorage.getItem('lang')) || 'en';
    this.props.initialize({
      languages: [
        { name: 'En', code: 'en' },
        { name: 'Ru', code: 'ru' },
        { name: 'Fr', code: 'fr' },
        { name: 'Gr', code: 'gr' },
        { name: 'It', code: 'it' }
      ],
      translation: globalTranslations,
      options: {
        renderToStaticMarkup,
        defaultLanguage: this.lang
      }
    });
  }
  render() {
    return(
      <div className='header'>
        <div className='header__content main'>
          <HeaderItems />

          <div className='header__btns'>
            <LanguageToggle />

            <a className='header__logout'>
              <Logout />
            </a>
          </div>
        </div>
      </div>
    )
  }
};

export default withLocalize(Header);
