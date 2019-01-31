import React from 'react';
import {RequestCheckboxComponent} from './request-checkbox.component';
import {RequestRadioButtonComponent} from './request-radio-button.component';
import {RequestTextComponent} from './request-text.component';
import './question.component.scss';

const buildRequests = (question, selectResponse) => {
    switch (question.type) {
        case "CHECKBOX":
            return (
                <RequestCheckboxComponent
                    responses={question.responses}
                    selectedElem={question.selectedElem}
                    handleResponseChange={(value) => selectResponse(question, value)}
                >
                </RequestCheckboxComponent>
            );
        case "RADIO_BUTTON":
            return (
                <RequestRadioButtonComponent
                    responses={question.responses}
                    selectedElem={question.selectedElem}
                    handleResponseChange={(value) => selectResponse(question, value)}
                >
                </RequestRadioButtonComponent>
            );
        case "TEXT":
            return (
                <RequestTextComponent
                    responses={question.responses}
                    selectedElem={question.selectedElem}
                    handleResponseChange={(value) => selectResponse(question, value)}
                >
                </RequestTextComponent>
            );
        default:
            return (<div>Not Valid Request Type</div>);
    }
};

export const QuestionComponent = ({question, selectResponse}) => (
        <div className="question-container">
            <div>
                <h5>Question title: "{question.title}"</h5>
            </div>
            <div>
                {buildRequests(question, selectResponse)}
            </div>
        </div>
    );