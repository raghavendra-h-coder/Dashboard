import React from 'react';
import {useSelector} from 'react-redux';
import {useDispatch} from 'react-redux';
import {uploads_startDate_change} from '../Redux/Uploads/UploadsStartDateAction';
import { DatePicker,MuiPickersUtilsProvider } from '@material-ui/pickers';
import DateFnsUtils from '@date-io/date-fns';
import { TextField } from '@material-ui/core';

function StartDateSelectionContainer(){
    const state=useSelector(state=>state.Emails);
    const dispatch=useDispatch();
    return(
         <React.Fragment>
             Start Date: &nbsp;
            {/* <MuiPickersUtilsProvider utils={DateFnsUtils}>
                <DatePicker  value={state.startDate.toISOString().slice(0,10)} 
                onChange={(value)=>{dispatch(uploads_startDate_change(value))}}/>
           </MuiPickersUtilsProvider> */}
           <TextField onChange={(event)=>{dispatch(uploads_startDate_change(event.target.value))}}
            id="datetime-local"
            label="Start Date"
            type="date"
            defaultValue={state.startDate}
            InputLabelProps={{
            shrink: true,
            }}
            />
         </React.Fragment>
    );
}

export default StartDateSelectionContainer;