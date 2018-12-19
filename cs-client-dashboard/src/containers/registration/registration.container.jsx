import React, {Component} from 'react';
import {connect} from "react-redux";
import * as registrationPageActions from "../../store/registration/actions";
import * as registrationPageSelectors from "../../store/registration/reducer";
import './registration.container.scss';

class RegistrationContainer extends Component {

    componentDidMount() {
        this.props.dispatch(registrationPageActions.fetchRegistration());
    }

    registrate() {
        console.log("reg");
    }

    updateLogin(login) {
        console.log("l " + login);
    }

    updatePassword(password) {
        console.log("p " + password);
    }

    render() {
        return (
            <div className="registration-container">
                <div>
                    <h1>Registration</h1>
                </div>
                <input type="text"
                       defaultValue={this.props.login}
                       onChange={(e) => this.updateLogin(e.target.value)}/>
                <br/>
                <input type="text"
                       defaultValue={this.props.password}
                       onChange={(e) => this.updatePassword(e.target.value)}/>
                <br/>
                <button className="btn btn-primary" onClick={() => this.registrate()}>Submit</button>
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        login: registrationPageSelectors.getLogin(state),
        password: registrationPageSelectors.getPassword(state),
        errors: registrationPageSelectors.getErrors(state),
    };
}

export default connect(mapStateToProps)(RegistrationContainer);
