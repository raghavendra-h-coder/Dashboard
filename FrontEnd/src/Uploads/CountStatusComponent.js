import React, { useState, useEffect } from 'react';
import CanvasJSReact from '../canvasjs.react';
import {useSelector} from 'react-redux';
import Axios from 'axios';
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

const CountStatusComponent=(props)=>{
    const state=useSelector(state=>state.Uploads);
    const [countStatus,setCountStatus]=useState([]);
    useEffect(()=>{
        var requestPayload={
            jobId:state.job.value,
            source:props.title,
            createdDate:Date.parse(state.startDate),
            endDate:Date.parse(state.endDate)
        }
        Axios.post(`http://localhost:8082/api/emails/count/status/sourcetypes`,requestPayload)
        .then(response=>{
            console.log("new api:"+response.data);
            setCountStatus(response.data)
        })
    },[state])
    const options = {
        animationEnabled: true,
        exportEnabled: true,
        theme: "dark2", // "light1", "dark1", "dark2"
        title:{
            text: props.title
        },
        data: [{
            type: "pie",
            indexLabel: "{label}: {y}",		
            startAngle: -90,
            dataPoints: countStatus
        }]
    }

    return(
        <CanvasJSChart options = {options} />
    )
}

export default CountStatusComponent;