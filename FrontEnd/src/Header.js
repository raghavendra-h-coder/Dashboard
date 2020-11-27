import React from 'react';
import shortlistlogo from './shortlist_logo.PNG';
import './Header.css';
import HomeIcon from '@material-ui/icons/Home';
import IconButton from '@material-ui/core/IconButton';

const Header=(props)=>{
    return(
        <div className="Header">
            <IconButton href='http://localhost:3000/'>
            <HomeIcon fontSize="large"/>
            </IconButton> &nbsp;
            <img src={shortlistlogo} alt="Shortlist" className="shortlistLogo"/>
    <h1 className="Heading">{props.title}</h1>
        </div>
    )
}

export default Header;