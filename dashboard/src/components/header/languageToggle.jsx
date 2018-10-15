import React from 'react';
import { withLocalize } from 'react-localize-redux';

const LanguageToggle = ({languages, activeLanguage, setActiveLanguage}) => {
  const getClass = languageCode => {
    return languageCode === activeLanguage.code ? ' header__lang-item--active' : '';
  };

  return (
    <div className='header__lang-wrap'>
      {languages.map(lang => (
        <a key={lang.code}
          className={'header__lang-item text-little' + getClass(lang.code)}
          onClick={() => setActiveLanguage(lang.code)}
        >
          {lang.name}
        </a>
      ))}
    </div>
  );
};
export default withLocalize(LanguageToggle);
