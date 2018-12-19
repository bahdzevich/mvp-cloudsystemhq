import React, {Component} from 'react';
import * as homePageActions from "../../store/home/actions";
import {connect} from "react-redux";
import * as homePageSelectors from "../../store/home/reducer";
import {QuestionnairePageComponent} from './components/questionnaire-page/questionnaire-page.component';
import './home.container.scss';

class HomeContainer extends Component {

    componentDidMount() {
        this.props.dispatch(homePageActions.fetchHomePage());
        this.selectResponse = (question, responseValue) => {
            this.props.dispatch(homePageActions.selectResponse(question, responseValue));
        };
        this.goToNextResponses = () => {
            this.props.dispatch(homePageActions.fetchHomePage());
        };
        this.goToPrevResponses = () => {
            this.props.dispatch(homePageActions.destructHomePage());
        };
        this.submitResponses = () => {
            console.log("Submit Responses")
        }
    }

    render() {
        return (
            <div className="home-container">
                <div>
                    <h1>Home</h1>
                </div>
                <QuestionnairePageComponent
                    questionnairePage={this.props.currentPage}
                    selectResponse={this.selectResponse}
                    goToPrevResponses={this.goToPrevResponses}
                    goToNextResponses={this.goToNextResponses}
                    canGoPrevPage={this.props.canGoPrevPage}
                    canGoNextPage={this.props.canGoNextPage}
                    isLastPage={this.props.isLastPage}
                    submitResponses={this.submitResponses}
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
