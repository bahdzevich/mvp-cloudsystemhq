import * as types from './actionTypes';
import authService from '../../services/auth.service';

export function fetchNavbar() {
    return async (dispatch, getState) => {
        try {
            const isLoggedIn = await authService.isLoggedIn();
            const state = {
                isLoggedIn: isLoggedIn
            };
            dispatch({type: types.NAVBAR_FETCHED, state});
        } catch (error) {
            console.error(error);
        }
    };
}