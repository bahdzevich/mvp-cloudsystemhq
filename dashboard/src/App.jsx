import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './base/styles/common.scss';

import Header from './components/header';
import Home from './components/home';
import Invoice from './components/invoice';
import Create from './components/create';
import NotFound from './components/not-found';

class App extends Component {
  constructor(){
    super();
    this.state = {
      lang: 'en',
    }
    this.changeLang = this.changeLang.bind(this);
  }
  changeLang(lang) {
    this.setState({lang: lang});
  }
  render() {
    const lang = this.state.lang;
    return (
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
    );
  }
}

export default App;
