import React from 'react';
import './questionnaire-page.component.scss';
import {QuestionComponent} from "../question/question.component";

export const QuestionnairePageComponent = (
    {
        questionnairePage,
        canGoPrevPage,
        canGoNextPage,
        isLastPage,
        selectResponse,
        goToPrevResponses,
        goToNextResponses,
        submitResponses
    }) => (
    <div>
        {questionnairePage.map((question, index) =>
            <QuestionComponent
                key={"question" + index}
                question={question}
                selectResponse={selectResponse}
            >
            </QuestionComponent>)}
        <div className="button-group">
            {
                canGoPrevPage ? (
                    <span><button className="btn btn-secondary" onClick={goToPrevResponses}>Prev</button></span>
                ) : ''
            }
            <span>
            {
                isLastPage ? (
                    <button className="btn btn-primary" onClick={submitResponses} disabled={!canGoNextPage}>
                        Submit
                    </button>
                ) : (
                    <button className="btn btn-primary" onClick={goToNextResponses} disabled={!canGoNextPage}>
                        Next
                    </button>
                )
            }
            </span>
        </div>
    </div>
);
