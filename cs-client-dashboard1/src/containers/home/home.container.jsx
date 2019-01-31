import React, {Component} from 'react';
import * as homePageActions from "../../store/home/actions";
import {connect} from "react-redux";
import * as homePageSelectors from "../../store/home/reducer";
import {QuestionnairePageComponent} from './components/questionnaire-page/questionnaire-page.component';
import './home.container.scss';

class HomeContainer extends Component {

    componentDidMount() {
        this.props.dispatch(homePageActions.fetchHomePage());
    }

    selectResponse(question, responseValue) {
        this.props.dispatch(homePageActions.selectResponse(question, responseValue));
    }

    goToNextResponses() {
        this.props.dispatch(homePageActions.fetchHomePage());
    }

    goToPrevResponses() {
        this.props.dispatch(homePageActions.destructHomePage());
    }

    submitResponses() {
        this.props.dispatch(homePageActions.submitResponses());
    }

    render() {
        return (
            <div className="home-container">
                <div>
                    <h1>Home</h1>
                </div>
                <QuestionnairePageComponent
                    questionnairePage={this.props.currentPage}
                    selectResponse={this.selectResponse.bind(this)}
                    goToPrevResponses={this.goToPrevResponses.bind(this)}
                    goToNextResponses={this.goToNextResponses.bind(this)}
                    canGoPrevPage={this.props.canGoPrevPage}
                    canGoNextPage={this.props.canGoNextPage}
                    isLastPage={this.props.isLastPage}
                    submitResponses={this.submitResponses.bind(this)}
                >
                </QuestionnairePageComponent>
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        questionnaire: homePageSelectors.getQuestionnaire(state),
        currentPage: homePageSelectors.getCurrentQuestionnairePage(state),
        canGoPrevPage: homePageSelectors.canGoPrevPage(state),
        canGoNextPage: homePageSelectors.canGoNextPage(state),
        isLastPage: homePageSelectors.isLastPage(state)
    };
}

export default connect(mapStateToProps)(HomeContainer);
