import axios from 'axios';

class QuestionnaireService {
    async loadQuestionnaire() {
        return await axios.get('http://localhost:8080/api/question/test');
    }
}

export default new QuestionnaireService();