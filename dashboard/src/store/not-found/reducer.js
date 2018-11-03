import Immutable from 'seamless-immutable';
import * as types from './actionTypes';

const initialState = Immutable({
    text: '',
    link: {
        labelText: '',
        linkText: '',
        linkValue: '/'
    },
    error: {
        width: 54,
        height: 54
    }
});

export default function reduce(state = initialState, action = {}) {
    switch (action.type) {
        case types.NOT_FOUND_FETCHED:
            return state.merge({
                text: action.state.text,
                link: action.state.link,
                error: action.state.error
            });
        default:
            return state;
    }
}

export function getText(state) {
    return state.notfound.text;
}

export function getLink(state) {
    return state.notfound.link;
}

export function getError(state) {
    return state.notfound.error;
}
