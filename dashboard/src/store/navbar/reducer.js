import Immutable from 'seamless-immutable';
import * as types from './actionTypes';

const initialState = Immutable({
    isLoggedIn: false,
    navItems: [
        {
            label: 'Home',
            to: '/',
            activeOnlyWhenExact: true
        },
        {
            label: 'Create',
            to: '/create'
        },
        {
            label: 'Invoice',
            to: '/invoice'
        }
    ]
});

export default function reduce(state = initialState, action = {}) {
    switch (action.type) {
        case types.NAVBAR_FETCHED:
            console.log(action);
            return state.merge({
                isLoggedIn: action.state.isLoggedIn
            });
        default:
            return state;
    }
}

export function getLoggedIn(state) {
    return state.navbar.isLoggedIn;
}

export function getNavItems(state) {
    return state.navbar.navItems;
}
