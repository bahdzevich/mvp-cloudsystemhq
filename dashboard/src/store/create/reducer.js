import Immutable from 'seamless-immutable';
import * as types from './actionTypes';

const initialState = Immutable({
    text: ''
});

export default function reduce(state = initialState, action = {}) {
    switch (action.type) {
        case types.CREATE_PAGE_FETCHED:
            return state.merge({
                text: action.state.text
            });
        default:
            return state;
    }
}

export function getText(state) {
    return state.create.text;
}
