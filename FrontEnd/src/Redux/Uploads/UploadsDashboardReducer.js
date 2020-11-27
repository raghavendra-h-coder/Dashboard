import {UPLOADS_JOB_CHANGE} from './UploadsJobActionType';
import {UPLOADS_START_DATE_CHANGE} from './UploadsStartDateActionType';
import {UPLOADS_END_DATE_CHANGE} from './UploadsEndDateActionType';

const InitialState={
    job:{},
    startDate:'1990-01-10',
    endDate:'2029-12-01'
}

const uploadsDashboardReducer=(state=InitialState,action)=>{
    switch(action.type){
        case UPLOADS_JOB_CHANGE:console.log("job change for uploads:"+action.value);
            localStorage.setItem("uploads_jobId",action.value.value);
            return {
            ...state,
            job:action.value
        }
        case UPLOADS_START_DATE_CHANGE:console.log("start date change change for uploads:"+action.value);
            localStorage.setItem("uploads_startdate",action.value);
            return{
            ...state,
            startDate:action.value
        }
        case UPLOADS_END_DATE_CHANGE:console.log("end date change for uploads:"+action.value);
            localStorage.setItem("uploads_enddate",action.value);
            return{
            ...state,
            endDate:action.value
        }
        default:return state;
    }
}

export default uploadsDashboardReducer;