import * as types from './actionTypes';

export function fetchCreatePage() {
    return async (dispatch, getState) => {
        try {
            const state = {
                text: 'Create Page'
            };
            dispatch({type: types.CREATE_PAGE_FETCHED, state});
        } catch (error) {
            console.error(error);
        }
    };
}