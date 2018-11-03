import React, {Component} from 'react';
import {BrowserRouter as Router} from 'react-router-dom';
import './base/styles/common.scss';
import ItemsSwitch from './switch';
import NavbarContainer from './containers/navbar/navbar.container';

class App extends Component {

    render() {
        return (
            <Router>
                <div className='page'>
                    <NavbarContainer/>
                    <ItemsSwitch/>
                </div>
            </Router>
        );
    }
}

export default App;
