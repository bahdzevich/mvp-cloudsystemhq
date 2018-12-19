import Immutable from "seamless-immutable";
import * as types from "./actionTypes";

const initialState = Immutable({
    login: '',
    password: '',
    errors: ['login and password must be provided']
});

export default function reduce(state = initialState, action = {}) {
    switch (action.type) {
        case types.REGISTRATION_FETCHED:
            return state.merge({
                login: action.state.login,
                password: action.state.password,
                errors: action.state.errors
            });
        case types.LOGIN_UPDATED:
            return state.merge({
                login: action.state.login,
                errors: action.state.errors
            });
        case types.PASSWORD_UPDATED:
            return state.merge({
                password: action.state.password,
                errors: action.state.errors
            });
        case types.REGISTRATION_DATA_SUBMITTED:
        default:
            return state;
    }
}

export function getLogin(state) {
    return state.registration.login;
}

export function getPassword(state) {
    return state.registration.password;
}

export function getErrors(state) {
    return state.registration.errors;
}