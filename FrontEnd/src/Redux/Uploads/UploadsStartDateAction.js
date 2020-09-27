import {UPLOADS_START_DATE_CHANGE} from './UploadsStartDateActionType';

export const uploads_startDate_change=(value)=>{
    return{
        'type':UPLOADS_START_DATE_CHANGE,
        'value':value
    }
}