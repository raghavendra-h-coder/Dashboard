import {EMAILS_JOB_CHANGE} from './EmailsJobActionType';
import {EMAIL_TYPE_CHANGE} from './EmailActionType';
import {EMAILS_START_DATE_CHANGE} from './EmailsStartDateActionType';
import {EMAILS_END_DATE_CHANGE} from './EmailsEndDateActionType';

const InitialState={
     job:{},
    emailType:{},
    startDate:new Date("1990-01-01"),
    endDate:new Date("2029-12-01")
}

const emailsDashboardReducer=(state=InitialState,action)=>{
    switch(action.type){
        case EMAILS_JOB_CHANGE:console.log("job change in emails:"+action.value);
            localStorage.setItem('emails_jobId',action.value.value);
            return {
            ...state,
            job:action.value
        }
        case EMAIL_TYPE_CHANGE:console.log("email type change in emails:"+action.value);
            return{
            ...state,
            emailType:action.value
        }
        case EMAILS_START_DATE_CHANGE:console.log("start date change in emails:"+action.value);
        localStorage.setItem('emails_startDate',action.value);
            return{
            ...state,
            startDate:action.value
        }
        case EMAILS_END_DATE_CHANGE:console.log("end date change in emails:"+action.value);
        localStorage.setItem('emails_endDate',action.value);
            return{
            ...state,
            endDate:action.value
        }
        default:return state;
    }
}

export default emailsDashboardReducer;