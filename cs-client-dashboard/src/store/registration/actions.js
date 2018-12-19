import * as types from "./actionTypes";
import authService from "../../services/auth.service";

export function fetchRegistration() {
    return (dispatch, getState) => {
        let state = getState().registration;
        dispatch({type: types.REGISTRATION_FETCHED, state});
    };
}
export function updateLogin(login) {
    return (dispatch, getState) => {
        let errors = validateLoginAndPassword(login, getState().registration.password);
        let state = {
            login: login,
            errors: errors
        };
        dispatch({type: types.LOGIN_UPDATED, state});
    };
}
export function updatePassword(password) {
    return (dispatch, getState) => {
        let errors = validateLoginAndPassword(getState().registration.login, password);
        let state = {
            password: password,
            errors: errors
        };
        dispatch({type: types.PASSWORD_UPDATED, state});
    };
}
export function submitRegistrationData(login, password) {
    return async (dispatch, getState) => {
        let result = await authService.registrate(login, password);
        console.log(result);
        let state = {};
        dispatch({type: types.REGISTRATION_DATA_SUBMITTED, state});
    };
}

function validateLoginAndPassword(login, password) {
    let result = [];
    if (!login || login === '' || !password || password === '') {
        result.push('login and password must be provided');
    }
    return result;
}