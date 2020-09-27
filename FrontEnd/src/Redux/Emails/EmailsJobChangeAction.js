import {EMAILS_JOB_CHANGE} from './EmailsJobActionType';

export const emails_job_change=(value)=>{
    return {
        'type':EMAILS_JOB_CHANGE,
        'value':value
    }
};