import React, { Component } from 'react';
import { createStore, combineReducers } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import { BrowserRouter as Router} from 'react-router-dom';
import { LocalizeProvider, localizeReducer } from 'react-localize-redux';
import './base/styles/common.scss';

import ItemsSwitch from './switch';
import Header from './components/header';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      store: this.getReduxStore()
    }
  }

  getReduxStore() {
    return createStore(combineReducers({
      localize: localizeReducer
    }), composeWithDevTools());
  }

  render() {
    return (
      <LocalizeProvider store={this.state.store}>
        <Router >
          <div className='page'>
            <Header />
            <ItemsSwitch />
          </div>
        </Router>
      </LocalizeProvider>
    );
  }
}

export default App;
