import React from 'react';


export const RequestRadioButtonComponent = ({ responses, selectedElem, handleResponseChange }) => (
    <form>
        {responses.map((response, index) => (
            <div key={"radiobutton"+index}>
                <input
                    type="radio"
                    value={response.value}
                    name="radio"
                    checked={selectedElem === response.value}
                    onChange={(changeEvent) => handleResponseChange(changeEvent.target.value)}/>
                {response.text}
            </div>
        ))}
    </form>
);