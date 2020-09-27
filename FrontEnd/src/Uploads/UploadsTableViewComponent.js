import React, { useState, useEffect } from 'react';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TablePagination from '@material-ui/core/TablePagination';
import TableRow from '@material-ui/core/TableRow';
import TableSortLabel from '@material-ui/core/TableSortLabel';
import Axios from 'axios';
import {useSelector} from 'react-redux';

var directionIndex=[false,false,false,false,false,false,true];
function UploadsTableViewComponent(){
    const state=useSelector(state=>state.Uploads);
    const headCells = [
        { index:0,id: 'name', numeric: false, disablePadding: false, label: 'Candidate Name' },
        { index:1,id: 'email', numeric: true, disablePadding: false, label: 'Email' },
        { index:2,id: 'jobId', numeric: true, disablePadding: false, label: 'Job ID' },
        { index:3,id: 'source', numeric: true, disablePadding: false, label: 'Source' },
        { index:4,id: 'rowStatus', numeric: true, disablePadding: false, label: 'Row Status' },
        { index:5,id: 'reason', numeric: true, disablePadding: false, label: 'Reason' },
        { index:6,id: 'creationDate', numeric: true, disablePadding: true, label: 'Created Date' },
      ];

      const fetchData=(payload)=>{
        Axios.post(`http://localhost:8082/api/emails/fetch/upload/records`,payload)
        .then(response=>{
          setResponseModel(response.data);
        })
        .catch(error=>{
          console.log("API call could not be done bcz:"+error);
        })
      }
    
      var resultBody={
        uploadsLogList:[],
        totalCount:0
      }
      var sortByBody={
        orderBy:"createdDate",
        order:"desc"
      }
      const [responseModel,setResponseModel]=useState(resultBody);
      const [page,setPage]=useState(0);
      const [searchValue,setSearchValue]=useState(null);
      const [sortValue,setSortValue]=useState(sortByBody);
      let rowsPerPage=50;

    useEffect(()=>{
      const requestPayload={
        jobId:JSON.stringify(state.job)!='{}'?state.job:null,
        startDate:new Date(state.startDate).getTime(),
        endDate:new Date(state.endDate).getTime(),
        page:page,
        size:rowsPerPage,
        orderBy:sortValue.orderBy,
        direction:sortValue.order
      }
      fetchData(requestPayload);
    },[]);

    const handleChangePage = async (event, newPage) => {
      setPage(newPage);
      const requestPayload={
        searchValue:searchValue,
        jobId:JSON.stringify(state.job)!='{}'?state.job:null,
        startDate:new Date(state.startDate).getTime(),
        endDate:new Date(state.endDate).getTime(),
        page:page,
        size:rowsPerPage,
        orderBy:sortValue.orderBy,
        direction:sortValue.order
      }
      await fetchData(requestPayload);
    };

    const handleSearch=async()=>{
      let searchValue=document.getElementById("search").value;
      setSearchValue(searchValue);
      const requestPayload={
        searchValue:searchValue,
        jobId:JSON.stringify(state.job)!='{}'?state.job:null,
        startDate:new Date(state.startDate).getTime(),
        endDate:new Date(state.endDate).getTime(),
        page:page,
        size:rowsPerPage,
        orderBy:sortValue.orderBy,
        direction:sortValue.order
      }
      await fetchData(requestPayload);
    }

    const createSortHandler=async(value,index)=>{
      let sortDirection=directionIndex[index];
      setSortValue({
        order:sortDirection?'desc':'asc',
        orderBy:value
      })
      directionIndex[index]=!sortDirection;
      console.log("direction index:"+directionIndex);
      const requestPayload={
        searchValue:searchValue,
        jobId:JSON.stringify(state.job)!='{}'?state.job:null,
        startDate:new Date(state.startDate).getTime(),
        endDate:new Date(state.endDate).getTime(),
        page:page,
        size:rowsPerPage,
        orderBy:sortValue.orderBy,
        direction:sortValue.order
      }
      console.log("req body:"+requestPayload);
      await fetchData(requestPayload);
    }

    return (
         <div>
           <input id="search"></input> &nbsp; &nbsp;
           <button onClick={handleSearch}>Search</button>
           <br></br>
       <TableContainer>
         <Table>
         <TableHead>
          <TableRow> 
             {headCells.map((headCell) => (
               <TableCell
                 key={headCell.id}
                  sortDirection={directionIndex[headCell.index]}
               >
                 <TableSortLabel
                    active={sortValue.orderBy === headCell.id}
                    direction={directionIndex[headCell.index] ? 'desc' : 'asc'}
                    onClick={()=>createSortHandler(headCell.id,headCell.index)}
                 >
                   {headCell.label}
                 </TableSortLabel>
               </TableCell>
             ))}
           </TableRow>
         </TableHead> 
         <TableBody>
             {responseModel.excelRowStatusList!=undefined && responseModel.excelRowStatusList.map((e)=>(
                 <TableRow>
                     <TableCell>
                           {e.name}  
                    </TableCell>
                     <TableCell>
                           {e.email}  
                     </TableCell>
                     <TableCell>
                         {e.jobId}
                     </TableCell>
                 <TableCell>
                       {e.source}
                     </TableCell>
                     <TableCell>
                       {e.rowStatus}
                     </TableCell>
                     <TableCell>
                       {e.reason}
                     </TableCell>
                     <TableCell>
                         {new Date(e.createdDate).toLocaleDateString("en-US")}
                     </TableCell>
                 </TableRow>
             )
             )}
         </TableBody>
         </Table>
         </TableContainer>     
         <TablePagination
         rowsPerPageOptions={[rowsPerPage]}
         component="div"
         count={responseModel.totalCount}
         rowsPerPage={rowsPerPage}
         page={page}
         onChangePage={handleChangePage}
         />
      </div>
      );
}

export default UploadsTableViewComponent;
