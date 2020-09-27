import React, { useState, useEffect } from 'react';
import CanvasJSReact from '../canvasjs.react';
import {useSelector} from 'react-redux';
import Axios from 'axios';
import { CarouselProvider, Slider, Slide, ButtonBack, ButtonNext } from 'pure-react-carousel';
import 'pure-react-carousel/dist/react-carousel.es.css';
import CountStatusComponent from './CountStatusComponent';
import './VisualizationComponent.css';
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

const VisualisationComponent=()=>{

    const state=useSelector(state=>state.Emails);

    const [emailsStats,setEmailStats]=useState([]);
    useEffect(()=>{
        if(JSON.stringify(state.job)!='{}')
        {
        var countFetchObject={
            jobId:parseInt(state.job.value,10),
            creationDate:Date.parse(state.startDate),
            endDate:Date.parse(state.endDate)
        }
        Axios.post(`http://localhost:8082/api/emails/count/emailtypes`,countFetchObject)
        .then(response=>{
            setEmailStats(response.data);
        })
    }
    },[state]);

    const options = {
        animationEnabled: true,
        exportEnabled: true,
        theme: "dark2", // "light1", "dark1", "dark2"
        height:330,
        title:{
            text: "Emails Count"
        },
        data: [{
            type: "pie",
            indexLabel: "{label}: {y}",		
            startAngle: -90,
            dataPoints: emailsStats
        }]
    }

    return(
        <>
            Visualisation component
            <CanvasJSChart options = {options} className="Visualization"/>
            {emailsStats.length>0 && 
            <CarouselProvider
                naturalSlideWidth={100}
                naturalSlideHeight={35}
                totalSlides={emailsStats.length}
                >
                <Slider>
                    {emailsStats.map((l,index)=><Slide index={index}><CountStatusComponent title={l.label}/></Slide>)}
                </Slider>
                <ButtonBack>Back</ButtonBack>
                <ButtonNext>Next</ButtonNext>
            </CarouselProvider>
            }
        </>
    )
}

export default VisualisationComponent;