import Immutable from "seamless-immutable";
import * as types from "./actionTypes";

const initialState = Immutable({
    login: '',
    password: '',
    errors: []
});

export default function reduce(state = initialState, action = {}) {
    switch (action.type) {
        case types.REGISTRATION_FETCHED:
            return state.merge({
                login: action.state.login,
                password: action.state.password,
                errors: action.state.errors
            });
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