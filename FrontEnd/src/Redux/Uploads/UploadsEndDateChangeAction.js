import {UPLOADS_END_DATE_CHANGE} from './UploadsEndDateActionType';

export const uploads_endDate_change=(value)=>{
    return{
        'type':UPLOADS_END_DATE_CHANGE,
        'value':value
    }
}