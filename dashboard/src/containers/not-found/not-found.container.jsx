import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import Eror from './icons/404.svg';
import './not-found.container.scss';
import * as notFoundActions from '../../store/not-found/actions';
import * as notFoundSelectors from "../../store/not-found/reducer";
import {connect} from "react-redux";

class NotFoundContainer extends Component {

    componentDidMount() {
        this.props.dispatch(notFoundActions.fetchNotFound());
    }

    render() {
        return (
            <div className='not-found'>
                <Eror width={this.props.error.width} height={this.props.error.height}/>
                <p className='not-found__text text'>
                    {this.props.text}
                </p>
                <p className='not-found__text text'>
                    {this.props.link.labelText}
                    <Link to={this.props.link.linkValue}>{this.props.link.linkText}</Link>?
                </p>
            </div>
        )
    };
}

function mapStateToProps(state) {
    return {
        text: notFoundSelectors.getText(state),
        link: notFoundSelectors.getLink(state),
        error: notFoundSelectors.getError(state)
    };
}

export default connect(mapStateToProps)(NotFoundContainer);
