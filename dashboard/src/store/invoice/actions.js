import * as types from './actionTypes';

export function fetchInvoice() {
    return async (dispatch, getState) => {
        try {
            const state = {
                text: 'Invoice Page'
            };
            dispatch({type: types.INVOICE_FETCHED, state});
        } catch (error) {
            console.error(error);
        }
    };
}