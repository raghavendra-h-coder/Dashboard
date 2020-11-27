import React from 'react';
import EmailIcon from '@material-ui/icons/Email';
import IconButton from '@material-ui/core/IconButton';
import PublishIcon from '@material-ui/icons/Publish';
import './DashBoardHomePage.css';

function DashBoardHomePage(){
    return(
        <React.Fragment className="Home-Menu">
            <IconButton href='http://localhost:3000/emails' className="Icon-Button">
            <EmailIcon fontSize="large" className="Email-Icon"/>
            </IconButton>
            <br></br>
            <IconButton href='http://localhost:3000/uploads' className="Icon-Button">
            <PublishIcon fontSize="large" />
            </IconButton>
        </React.Fragment>
    );
}

export default DashBoardHomePage;