import React from 'react';
import {Provider} from 'react-redux';
import Store from './Redux/DashboardStore';
import DashBoardHomePage from './DashBoardHomePage';
//import './App.css';
import { BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import EmailsDashBoardComponent from './Emails/EmailsDashboardComponent';
import UploadsDashboardComponent from './Uploads/UploadsDashboardComponent';
import EmailTableViewComponent from './Emails/EmailTableViewComponent';
import UploadsTableViewComponent from './Uploads/UploadsTableViewComponent';
function App() {
  return (
     <Provider store={Store}>
    <Router>
    <div>
    <Switch>
              <Route exact path='/' component={DashBoardHomePage} />
              <Route exact path='/emails' component={EmailsDashBoardComponent} />
              <Route exact path='/uploads' component={UploadsDashboardComponent} />
              <Route path='/emails/listview' component={EmailTableViewComponent} />
              <Route path='/uploads/listview' component={UploadsTableViewComponent} />
    </Switch>
    </div>
    </Router>  
    </Provider>
  );
}

export default App;
