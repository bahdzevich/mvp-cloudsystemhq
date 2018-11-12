import React, {Component} from 'react';
import {connect} from 'react-redux';
import * as navbarActions from '../../store/navbar/actions';
import * as navbarSelectors from '../../store/navbar/reducer';
import './navbar.container.scss';
import {NavbarItemComponent} from './components/navbar-item.component';
// import Logout from './icons/logout.svg';

class NavbarContainer extends Component {

    componentDidMount() {
        this.props.dispatch(navbarActions.fetchNavbar());
    }

    getLogoutComponentIfLoggedIn() {
        if (this.props.isLoggedIn) {
            // return (<Logout />);
        }
    }

    render() {
        return (
            <div className='header'>
                <div className='header__content main'>
                    <div className='header__items'>
                        {this.props.navItems.map((item, i) => {
                            return (<NavbarItemComponent
                                activeOnlyWhenExact={item.activeOnlyWhenExact}
                                to={item.to}
                                label={item.label}
                                key={item.label + i}/>)
                        })}
                    </div>
                    <div className='header__btns'>
                        <button className='header__logout'>
                            {this.getLogoutComponentIfLoggedIn()}
                        </button>
                    </div>
                </div>
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        isLoggedIn: navbarSelectors.getLoggedIn(state),
        navItems: navbarSelectors.getNavItems(state)
    };
}

export default connect(mapStateToProps)(NavbarContainer);
