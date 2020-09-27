import {EMAIL_TYPE_CHANGE} from './EmailActionType';

export const emailType_change=(value)=>{
    return{
        'type':EMAIL_TYPE_CHANGE,
        'value':value
    }
}