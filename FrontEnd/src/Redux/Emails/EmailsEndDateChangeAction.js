import {EMAILS_END_DATE_CHANGE} from './EmailsEndDateActionType';

export const emails_endDate_change=(value)=>{
    return{
        'type':EMAILS_END_DATE_CHANGE,
        'value':value
    }
}