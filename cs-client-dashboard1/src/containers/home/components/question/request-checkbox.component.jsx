import React from 'react';


export const RequestCheckboxComponent = ({ responses, selectedElem, handleResponseChange })=> (
    <form>
        {responses.map((response, index) => (
            <div key={"checkbox"+index}>
                <input
                    type="checkbox"
                    value={response.value}
                    checked={selectedElem.asMutable().includes(response.value)}
                    onChange={(changeEvent) => handleResponseChange(changeEvent.target.value)}
                />
                {response.text}
            </div>
        ))}
    </form>
);