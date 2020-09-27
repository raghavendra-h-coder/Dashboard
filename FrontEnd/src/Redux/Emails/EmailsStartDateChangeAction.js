import {EMAILS_START_DATE_CHANGE} from './EmailsStartDateActionType';

export const emails_startDate_change=(value)=>{
    return{
        'type':EMAILS_START_DATE_CHANGE,
        'value':value
    }
}