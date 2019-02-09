import Immutable from 'seamless-immutable';
import * as types from './actionTypes';

const initialState = Immutable({
    questionnaire: [],
    currentPageNum: -1,
    canGoPrevPage: false,
    canGoNextPage: false,
    isLastPage: false
});

export default function reduce(state = initialState, action = {}) {
    switch (action.type) {
        case types.HOME_PAGE_FETCHED:
            return state.merge({
                questionnaire: action.state.questionnaire,
                currentPageNum: action.state.currentPageNum,
                canGoPrevPage: action.state.canGoPrevPage,
                canGoNextPage: false,
                isLastPage: action.state.lastPage
            });
        case types.HOME_PAGE_DESTRUCTED:
            return state.merge({
                questionnaire: action.state.questionnaire,
                currentPageNum: action.state.currentPageNum,
                canGoPrevPage: action.state.canGoPrevPage,
                canGoNextPage: true,
                isLastPage: false
            });
        case types.REQUEST_SELECTED:
            return state.merge({
                questionnaire: action.state.questionnaire,
                canGoNextPage: action.state.canGoNextPage
            });
        default:
            return state;
    }
}

export function getQuestionnaire(state) {
    return state.homePage.questionnaire;
}

export function getCurrentQuestionnairePage(state) {
    return state.homePage.questionnaire[state.homePage.currentPageNum] || [];
}

export function canGoPrevPage(state) {
    return state.homePage.canGoPrevPage;
}

export function canGoNextPage(state) {
    return state.homePage.canGoNextPage;
}

export function isLastPage(state) {
    return state.homePage.lastPage;
}