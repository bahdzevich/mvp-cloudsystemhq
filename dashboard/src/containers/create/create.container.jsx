import React, {Component} from 'react';
import './create.container.scss';
import * as createPageActions from "../../store/create/actions";
import * as createPageSelectors from "../../store/create/reducer";
import {connect} from "react-redux";


class CreateContainer extends Component {

    componentDidMount() {
        this.props.dispatch(createPageActions.fetchCreatePage());
    }

    render() {
        return (
            <div className='create'>
                <div className='create__content main'>
                    <p className='create__text text'>{this.props.text}</p>
                </div>
            </div>)
    }
}

function mapStateToProps(state) {
    return {
        text: createPageSelectors.getText(state)
    };
}

export default connect(mapStateToProps)(CreateContainer);
