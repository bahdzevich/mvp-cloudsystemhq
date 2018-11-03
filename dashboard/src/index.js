import React from 'react';
import ReactDOM from 'react-dom';
import App from './App.jsx';
import registerServiceWorker from './registerServiceWorker';
import {applyMiddleware, combineReducers, createStore} from "redux";
import * as reducers from "./store/reducers";
import thunk from "redux-thunk";
import {Provider} from 'react-redux';

const store = createStore(combineReducers(reducers), applyMiddleware(thunk));

ReactDOM.render(
    <Provider store={store}>
        <App/>
    </Provider>,
    document.getElementById('root')
);
registerServiceWorker();
