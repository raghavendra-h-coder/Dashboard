import React from 'react';
import SelectionComponent from './SelectionComponent';
import VisualisationComponent from './VisualisationComponent';
import {useSelector} from 'react-redux';
import Header from '../Header';

const EmailsDashboardComponent=()=>{
    const job=useSelector(state=>state.Emails.job);
    return(
        <React.Fragment>
            <Header title="Emails Dashboard"/>
            <SelectionComponent />
            <br></br>
           { JSON.stringify(job)!=='{}' &&
            <VisualisationComponent />}
        </React.Fragment>
    )
}

export default EmailsDashboardComponent;