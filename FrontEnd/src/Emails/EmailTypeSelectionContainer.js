import React, { useEffect, useState } from 'react';
import {useSelector} from 'react-redux';
import {useDispatch} from 'react-redux';
import {emailType_change} from '../Redux/Emails/EmailTypeChangeAction'; 
import Axios from 'axios';
import { AsyncPaginate } from "react-select-async-paginate";

function EmailTypeSelectionContainer(){
    const emailType=useSelector(state=>state.Emails.emailType);
    const dispatch=useDispatch();

    const customStyles = {
        menu: (provided, state) => ({
            ...provided,
            width: 200,
            color: state.selectProps.menuColor,
            padding: 20,
          }),
        control: () => ({
          // none of react-select's styles are passed to <Control />
          width: 200,
        }),
        singleValue: (provided, state) => {
          const opacity = state.isDisabled ? 0.5 : 1;
          const transition = 'opacity 300ms';
      
          return { ...provided, opacity, transition };
        }
    }

    const  loadOptions=async(search, loadedOptions)=> {
        let hasMore=false;
        let newOptionsList=[];
        if(!search){
            newOptionsList=loadedOptions;
        }
       else{
        await Axios.get(`http://localhost:8082/api/emails/async/list/emailtypes?searchvalue=${search}&offset=${loadedOptions.length}`)
        .then(response=>{
            let responseBody;
            responseBody=response.data;
            newOptionsList=responseBody.optionsModel;
            hasMore=responseBody.hasMore;
        })
    }
    return {
        options: newOptionsList,
        hasMore
      };
    }
    return(
        <React.Fragment>
            Select Email Type:
            <AsyncPaginate value={emailType}
            styles={customStyles}
            onChange={(selectedOption)=>dispatch(emailType_change(selectedOption))}
            loadOptions={loadOptions} />
         </React.Fragment>
    )
}

export default EmailTypeSelectionContainer;