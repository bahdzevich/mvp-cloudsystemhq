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
        this.props.dispatch(registrationPageActions.submitRegistrationData(this.props.login, this.props.password));
    }

    updateLogin(login) {
        this.props.dispatch(registrationPageActions.updateLogin(login));
    }

    updatePassword(password) {
        this.props.dispatch(registrationPageActions.updatePassword(password));
    }

    buildErrorPanel() {
        return this.props.errors.length > 0
            ? (
                <div className="errors-group">
                    {this.props.errors.map(error => (
                        <div className="error-element">
                            {error}
                        </div>
                    ))}
                </div>
            )
            : '';
    }

    render() {
        return (
            <div className="registration-container">
                <div className="registration-title">
                    <h1>Registration</h1>
                </div>
                <div className="row reg-form-group">
                    <div className="col-4 reg-label-element">
                        Login:
                    </div>
                    <div className="col-8">
                        <input type="text"
                               className="form-control"
                               defaultValue={this.props.login}
                               onChange={(e) => this.updateLogin(e.target.value)}/>
                    </div>
                </div>
                <div className="row reg-form-group">
                    <div className="col-4 reg-label-element">
                        Password:
                    </div>
                    <div className="col-8">
                        <input type="text"
                               className="form-control"
                               defaultValue={this.props.password}
                               onChange={(e) => this.updatePassword(e.target.value)}/>
                    </div>
                </div>
                {this.buildErrorPanel()}
                <div className="registration-button-group">
                    <button className="btn btn-primary"
                            onClick={() => this.registrate()}
                            disabled={this.props.errors.length > 0}
                    >
                        Submit
                    </button>
                </div>
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
