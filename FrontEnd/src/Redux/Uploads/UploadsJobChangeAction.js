import {UPLOADS_JOB_CHANGE} from './UploadsJobActionType';

export const uploads_job_change=(value)=>{
    return {
        'type':UPLOADS_JOB_CHANGE,
        'value':value
    }
};