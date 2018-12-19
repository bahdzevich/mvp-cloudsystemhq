import * as types from "./actionTypes";

export function fetchRegistration() {
    return (dispatch, getState) => {
        let state = getState().registration;
        dispatch({type: types.REGISTRATION_FETCHED, state});
    };
}