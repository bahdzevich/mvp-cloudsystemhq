import Immutable from 'seamless-immutable';
import * as types from './actionTypes';
import questionnaireService from "../../services/questionnaire.service";

export function fetchHomePage() {
    return async (dispatch, getState) => {
        try {
            const nextPageNum = getState().homePage.currentPageNum + 1;
            const questionnairePage = await questionnaireService.loadQuestionnairePage(nextPageNum);
            const preparedQuestionnairePage = questionnaireService.prepareQuestionnairePage(questionnairePage);
            let questionnaire = Immutable.asMutable(getState().homePage.questionnaire, {deep: true});
            questionnaire.push(preparedQuestionnairePage);
            const state = {
                questionnaire: questionnaire,
                currentPageNum: nextPageNum,
                canGoPrevPage: nextPageNum > 0,
                isLastPage: nextPageNum === 2
            };
            dispatch({type: types.HOME_PAGE_FETCHED, state});
        } catch (error) {
            console.error(error);
        }
    };
}

export function destructHomePage() {
    return (dispatch, getState) => {
        const prevPageNum = getState().homePage.currentPageNum - 1;
        let questionnaire = Immutable.asMutable(getState().homePage.questionnaire, {deep: true});
        questionnaire.pop();
        const state = {
            questionnaire: questionnaire,
            currentPageNum: prevPageNum,
            canGoPrevPage: prevPageNum > 0
        };
        dispatch({type: types.HOME_PAGE_DESTRUCTED, state});
    };
}

export function selectResponse(question, responseValue) {
    return (dispatch, getState) => {
        let questionnaire = Immutable.asMutable(getState().homePage.questionnaire, {deep: true});
        let currentPage = questionnaire[getState().homePage.currentPageNum];
        let foundQuestion = currentPage.find(q => q.id === question.id);
        switch (foundQuestion.type) {
            case "CHECKBOX":
                if (foundQuestion.selectedElem.includes(responseValue)) {
                    foundQuestion.selectedElem.splice(foundQuestion.selectedElem.indexOf(responseValue), 1);
                } else {
                    foundQuestion.selectedElem.push(responseValue);
                }
                break;
            default:
                foundQuestion.selectedElem = responseValue;
                break;
        }
        const state = {
            questionnaire: questionnaire,
            canGoNextPage: questionnaireService.canGoToNextPage(currentPage)
        };
        dispatch({type: types.REQUEST_SELECTED, state});
    };
}

export function submitResponses() {
    return async (dispatch, getState) => {
        let questionnaire = getState().homePage.questionnaire;
        await questionnaireService.submitResponses(questionnaire);
        dispatch({type: types.HOME_RESPONSES_SEND});
    }
}