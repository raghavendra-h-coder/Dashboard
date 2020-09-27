import React from 'react';
import shortlistlogo from './shortlist_logo.PNG';
import './Header.css';

const Header=(props)=>{
    return(
        <div className="Header">
            <img src={shortlistlogo} className="shortlistLogo"/>
    <h1 className="Heading">{props.title}</h1>
        </div>
    )
}

export default Header;