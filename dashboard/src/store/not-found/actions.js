import * as types from './actionTypes';

export function fetchNotFound() {
    return async (dispatch, getState) => {
        try {
            const state = {
                text: 'Not Found',
                link: {
                    labelText: 'Go back to the ',
                    linkText: 'main page',
                    linkValue: '/'
                },
                error: {
                    width: 54,
                    height: 54
                }
            };
            dispatch({type: types.NOT_FOUND_FETCHED, state});
        } catch (error) {
            console.error(error);
        }
    };
}