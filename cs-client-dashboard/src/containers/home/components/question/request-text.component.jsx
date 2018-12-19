import React from 'react';


const getTitle = (responses) => {
    if (responses.length > 0) {
        return responses[0].text
    }
};

export const RequestTextComponent = ({ responses, handleResponseChange }) => (
    <div>
        <div>
            {getTitle(responses)}
        </div>
        <input type="text" onChange={(changeEvent) => handleResponseChange(changeEvent.target.value)}/>
    </div>
);