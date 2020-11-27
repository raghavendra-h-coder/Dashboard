import React from 'react';
import SelectionComponent from './SelectionComponent';
import VisualisationComponent from './VisualisationComponent';
import {useSelector} from 'react-redux';
import Header from '../Header';

const UploadsDashboardComponent=()=>{
    const job=useSelector(state=>state.Uploads.job);
    return(
        <React.Fragment>
            <Header title="Uploads Dashboard"/>
            <SelectionComponent />
            <br></br>
           { JSON.stringify(job)!=='{}' &&
            <VisualisationComponent />}
        </React.Fragment>
    )
}

export default UploadsDashboardComponent;