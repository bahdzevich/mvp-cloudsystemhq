import axios from 'axios';

class QuestionnaireService {
    async loadQuestionnairePage(pageNumber) {
        return await axios.get(`http://localhost:8080/api/question/test/page/${pageNumber}`)
            .then(resp => resp.data);
    }

    async loadQuestionnaire() {
        return await axios.get('http://localhost:8080/api/question/test/full')
            .then(resp => resp.data);
    }

    prepareQuestionnairePage(questionnaire) {
        questionnaire.forEach((question) => {
            if (question.type === "CHECKBOX" && !question.selectedElem) {
                question.selectedElem = [];
            }
            if (question.type === "RADIO_BUTTON" && !question.selectedElem && question.responses.length > 0) {
                question.selectedElem = question.responses[0].value;
            }
        });
        return questionnaire;
    }

    canGoToNextPage(questionnairePage) {
        let canGoToNextPage = true;
        questionnairePage.forEach(question => {
            if (question.type === "CHECKBOX") {
                if (question.selectedElem.length === 0) canGoToNextPage = false;
            } else {
                if (question.selectedElem == null) canGoToNextPage = false;
            }
        });
        return canGoToNextPage;
    }

    async submitResponses(questionnaire) {
        let responses = [];
        questionnaire.forEach(questionnairePage => {
            questionnairePage.forEach(question => {
                responses.push({
                    questionId: question.id,
                    value: question.selectedElem
                });
            });
        });
        return await axios.post('http://localhost:8080/api/question/test/result', responses)
            .then(resp => resp.data);
    }
}

export default new QuestionnaireService();