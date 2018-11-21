import React, {Component} from 'react';
import * as homePageActions from "../../store/home/actions";
import {connect} from "react-redux";
import * as homePageSelectors from "../../store/home/reducer";

class HomeContainer extends Component {

    componentDidMount() {
        this.props.dispatch(homePageActions.fetchHomePage());
    }

    render() {
        return (
            <div>
                Home
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        questionnaire: homePageSelectors.getQuestionnaire(state)
    };
}

export default connect(mapStateToProps)(HomeContainer);
