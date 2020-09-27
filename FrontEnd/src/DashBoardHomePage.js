import React, { useEffect } from 'react';

function DashBoardHomePage(){
    return(
        <React.Fragment>
            <a href='http://localhost:3000/emails'>EMAILS</a>
            <br></br>
            <a href='http://localhost:3000/uploads'>UPLOADS</a>
        </React.Fragment>
    );
}

export default DashBoardHomePage;