import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import Header from './components/header';
import Home from './components/home';
import Invoice from './components/invoice';
import Create from './components/create';
import NotFound from './components/not-found';
import './base/styles/common.scss';

class App extends Component {
  render() {
    return (
      <Router >
        <div className='page'>
          <Header />
          <Switch >
            <Route exact path='/' component={Home} />
            <Route path='/invoice' component={Invoice} />
            <Route path='/create' component={Create} />
            <Route component={NotFound} />
          </Switch>
        </div>
      </Router>
    );
  }
}

export default App;
