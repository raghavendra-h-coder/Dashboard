import React from 'react';
import JobSelectionContainer from './JobSelectionContainer';
import StartDateSelectionContainer from './StartDateSelectionContainer';
import EndDateSelectionContainer from './EndDateSelectionContainer';

const SelectionComponent=()=>{
    return(
        <section>
        <JobSelectionContainer />
        {/* <EmailTypeSelectionContainer /> */}
        <StartDateSelectionContainer />
        <EndDateSelectionContainer />
        <a href="http://localhost:3000/uploads/listview">LIST VIEW</a>
      </section>
    );
}

export default SelectionComponent;