import React, { Component } from 'react';
import { createStore, combineReducers } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { LocalizeProvider, localizeReducer } from 'react-localize-redux';
import './base/styles/common.scss';

import Header from './components/header';
import Home from './components/home';
import Invoice from './components/invoice';
import Create from './components/create';
import NotFound from './components/not-found';

const USING_REDUX_KEY = 'redux';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      store: this.getReduxStore(),
      lang: 'en',
    };

    this.changeLang = this.changeLang.bind(this);
  }

  getReduxStore() {
    return createStore(combineReducers({
      localize: localizeReducer
    }), composeWithDevTools());
  }

  changeLang(lang) {
    this.setState({lang: lang});
  }

  render() {
    const lang = this.state.lang;

    return (
      <LocalizeProvider store={this.state.store}>
        <Router >
          <div className='page'>
            <Header lang={lang} changeLang={this.changeLang} />
            <Switch >
              <Route exact path='/' render={() => <Home lang={lang} />} />
              <Route path='/invoice' render={() => <Invoice lang={lang} />} />
              <Route path='/create' render={() => <Create lang={lang} />} />
              <Route render={() => <NotFound lang={lang} />} />
            </Switch>
          </div>
        </Router>
      </LocalizeProvider>
    );
  }
}

export default App;
