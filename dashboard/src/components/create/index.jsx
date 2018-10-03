import React, { Component } from 'react';
import PropTypes from 'prop-types';
import './create.scss';

const strings = {
  en: {
    text: 'Create questions'
  },
  ru: {
    text: 'Страница создания вопросов'
  },
  fr: {
    text: "Créer des questions"
  },
  gr: {
    text: 'Erstellen Sie Fragen'
  },
  it: {
    text: 'Crear preguntas'
  }
};

class Create extends Component {
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
      <div className='create'>
        <div className='create__content main'>
          <p className='create__text text'>{strings.text}</p>
        </div>
      </div>
    )
  }
};

export default Create;

Create.propTypes = {
  lang: PropTypes.string,
}
