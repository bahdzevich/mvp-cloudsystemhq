import * as types from './actionTypes';

export function fetchHome() {
    return async (dispatch, getState) => {
        try {
            const state = {
                text: 'Home Page'
            };
            dispatch({type: types.HOME_FETCHED, state});
        } catch (error) {
            console.error(error);
        }
    };
}