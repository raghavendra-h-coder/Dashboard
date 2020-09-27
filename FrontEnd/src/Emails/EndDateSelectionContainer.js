import React from 'react';
import {useSelector} from 'react-redux';
import {useDispatch} from 'react-redux';
import {emails_endDate_change} from '../Redux/Emails/EmailsEndDateChangeAction';
import { DatePicker,MuiPickersUtilsProvider } from '@material-ui/pickers';
import DateFnsUtils from '@date-io/date-fns';

function EndDateSelectionContainer(){
    const state=useSelector(state=>state.Emails);
    const dispatch=useDispatch();
    return(
        <React.Fragment>
        End Date: &nbsp;  
        <MuiPickersUtilsProvider utils={DateFnsUtils}>
                <DatePicker  value={state.endDate.toISOString()} 
                onChange={(date)=>{dispatch(emails_endDate_change(date))}}/>
        </MuiPickersUtilsProvider>
       
        </React.Fragment>
    );
}

export default EndDateSelectionContainer;