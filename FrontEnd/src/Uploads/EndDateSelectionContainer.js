import React from 'react';
import {useSelector} from 'react-redux';
import {useDispatch} from 'react-redux';
import {uploads_endDate_change} from '../Redux/Uploads/UploadsEndDateChangeAction';
import { DatePicker,MuiPickersUtilsProvider } from '@material-ui/pickers';
import DateFnsUtils from '@date-io/date-fns';
import { TextField } from '@material-ui/core';

function EndDateSelectionContainer(){
    const state=useSelector(state=>state.Emails);
    const dispatch=useDispatch();
    return(
        <React.Fragment>
        End Date: &nbsp;  
        {/* <MuiPickersUtilsProvider utils={DateFnsUtils}>
                <DatePicker  value={state.endDate.toISOString()} 
                onChange={(date)=>{dispatch(uploads_endDate_change(date))}}/>
        </MuiPickersUtilsProvider> */}
        <TextField onChange={(event)=>{dispatch(uploads_endDate_change(event.target.value))}}
            id="datetime-local"
            label="End Date"
            type="date"
            defaultValue={state.startDate}
            InputLabelProps={{
            shrink: true,
            }}
       />
       
        </React.Fragment>
    );
}

export default EndDateSelectionContainer;