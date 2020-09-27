import React from 'react';
import JobSelectionContainer from './JobSelectionContainer';
import StartDateSelectionContainer from './StartDateSelectionContainer';
import EndDateSelectionContainer from './EndDateSelectionContainer';

const SelectionComponent=()=>{

    return(
        <section>
        <JobSelectionContainer />
        <StartDateSelectionContainer />
        <EndDateSelectionContainer />
        <a href='http://localhost:3000/emails/listview'>LIST VIEW</a>
      </section>
    );
}

export default SelectionComponent;