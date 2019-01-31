import axios from 'axios';

class AuthService {
    async isLoggedIn() {
        return true;
    }

    async registrate(login, password) {
        let body = {
            login: login,
            password: password
        };
        return axios.post(`http://localhost:8080/api/customers`, body)
            .then(resp => resp.data);
    }

    async login(login, password) {
        let body = {
            login: login,
            password: password
        };
        return axios.post(`http://localhost:8080/api/oauth/token`, body)
            .then(resp => {
                localStorage.setItem('token', resp.headers['token']);
                return resp;
            })
            .then(resp => resp.data);
    }
}

export default new AuthService();