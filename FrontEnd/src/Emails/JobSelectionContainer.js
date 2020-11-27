import React from 'react';
import {useSelector} from 'react-redux';
import {useDispatch} from 'react-redux';
import {emails_job_change} from '../Redux/Emails/EmailsJobChangeAction';
import Axios from 'axios';
import { AsyncPaginate } from "react-select-async-paginate";
import './JobSelectionContainerStyles.css';

function JobSelectionContainer(){
    const state=useSelector(state=>state.Emails);
    const dispatch=useDispatch();

    const customStyles = {
        menu: (provided, state) => ({
            ...provided,
            width: 200,
            color: state.selectProps.menuColor,
            padding: 10,
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
          await Axios.get(`http://localhost:8082/api/emails/async/list/jobtitles?offset=${loadedOptions.length}`)
          .then(response=>{
              let responseBody;
              responseBody=response.data;
              newOptionsList=responseBody.optionsModel;
              hasMore=responseBody.hasMore;
          })
        }
       else{
        await Axios.get(`http://localhost:8082/api/emails/async/list/jobtitles?searchvalue=${search}&offset=${loadedOptions.length}`)
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
      };
    return(
        <div className="jobSelectionClass">
            Select Jobs:
            <AsyncPaginate value={state.job}
            styles={customStyles}
            onChange={(selectedOption)=>dispatch(emails_job_change(selectedOption))}
            loadOptions={loadOptions} />
         </div>
    )  
}

export default JobSelectionContainer;