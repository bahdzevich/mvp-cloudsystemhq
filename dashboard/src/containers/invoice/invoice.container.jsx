import React, {Component} from 'react';
import './invoice.container.scss';
import * as invoiceActions from "../../store/invoice/actions";
import {connect} from "react-redux";
import * as invoiceSelectors from "../../store/invoice/reducer";

class InvoiceContainer extends Component {

    componentDidMount() {
        this.props.dispatch(invoiceActions.fetchInvoice());
    }

    render() {
        return (
            <div className='invoice'>
                <div className='invoice__content main'>
                    <p className='invoice__text text'>{this.props.text}</p>
                </div>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        text: invoiceSelectors.getText(state)
    };
}

export default connect(mapStateToProps)(InvoiceContainer);
