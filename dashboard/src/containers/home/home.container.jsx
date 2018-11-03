import React, {Component} from 'react';
import './home.container.scss';
import * as homeActions from "../../store/home/actions";
import * as homeSelectors from "../../store/home/reducer";
import {connect} from "react-redux";


class HomeContainer extends Component {

    componentDidMount() {
        this.props.dispatch(homeActions.fetchHome());
    }

    render() {
        return (
            <div className='home'>
                <div className='home__content main'>
                    <p className='home__text text'>{this.props.text}</p>
                </div>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        text: homeSelectors.getText(state)
    };
}

export default connect(mapStateToProps)(HomeContainer);
