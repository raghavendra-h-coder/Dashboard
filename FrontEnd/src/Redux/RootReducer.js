import EmailsDashboardReducer from './Emails/EmailsDashboardReducer';
import UploadsDashboardReducer from './Uploads/UploadsDashboardReducer';
import {combineReducers} from 'redux';

const rootReducer=combineReducers({
    Emails:EmailsDashboardReducer,
    Uploads:UploadsDashboardReducer
})

export default rootReducer;