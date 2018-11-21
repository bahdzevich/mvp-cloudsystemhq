import Immutable from 'seamless-immutable';
import * as types from './actionTypes';

const initialState = Immutable({
    questionnaire: {}
});

export default function reduce(state = initialState, action = {}) {
    switch (action.type) {
        case types.HOME_PAGE_FETCHED:
            return state.merge({
                questionnaire: action.state.questionnaire
            });
        default:
            return state;
    }
}

export function getQuestionnaire(state) {
    return state.homePage.questionnaire;
}
