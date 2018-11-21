import * as types from './actionTypes';
import questionnaireService from "../../services/questionnaire.service";

export function fetchHomePage() {
    return async (dispatch, getState) => {
        try {
            const questionnaire = await questionnaireService.loadQuestionnaire();
            const state = {
                questionnaire: questionnaire
            };
            dispatch({type: types.HOME_PAGE_FETCHED, state});
        } catch (error) {
            console.error(error);
        }
    };
}