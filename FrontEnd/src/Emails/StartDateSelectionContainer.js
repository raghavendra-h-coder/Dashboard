import React from 'react';
import {useSelector} from 'react-redux';
import {useDispatch} from 'react-redux';
// import moment from 'moment';
import {emails_startDate_change} from '../Redux/Emails/EmailsStartDateChangeAction';
import { DatePicker,MuiPickersUtilsProvider } from '@material-ui/pickers';
import DateFnsUtils from '@date-io/date-fns';
// import MomentUtils from '@date-io/moment';
import { TextField } from '@material-ui/core';

// class LocalizedUtils extends MomentUtils {
//     getDatePickerHeaderText(date) {
//       return moment(date).format('ll');
//     }
//   }

function StartDateSelectionContainer(){
    const state=useSelector(state=>state.Emails);
    const dispatch=useDispatch();
    
    return(
         <React.Fragment>
             Start Date: &nbsp;
            {/* <MuiPickersUtilsProvider utils={LocalizedUtils}>
                <DatePicker value={state.startDate.toISOString().slice(0,15)} 
                onChange={(value)=>{dispatch(emails_startDate_change(value))}}/>
           </MuiPickersUtilsProvider> */}
           <TextField onChange={(event)=>{dispatch(emails_startDate_change(event.target.value))}}
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