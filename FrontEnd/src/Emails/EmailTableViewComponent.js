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
import RefreshIcon from '@material-ui/icons/Refresh';
import IconButton from '@material-ui/core/IconButton';

var directionIndex=[true,false,false,false,false,false];
function EmailTableViewComponent(){
    
    const headCells = [
        { index:0,id: 'candidateId', numeric: false, disablePadding: true, label: 'Candidate Id' },
        { index:1,id: 'jobId', numeric: true, disablePadding: false, label: 'Job Id' },
        { index:2,id: 'email', numeric: true, disablePadding: false, label: 'Email' },
        { index:3,id: 'emailType', numeric: true, disablePadding: false, label: 'Email Type' },
        { index:4,id: 'status', numeric: true, disablePadding: false, label: 'Status' },
        { index:5,id: 'creationDate', numeric: true, disablePadding: false, label: 'Created Date' },
      ];

      const fetchData=(payload)=>{
        Axios.post(`http://localhost:8082/api/emails/fetch/records`,payload)
        .then(response=>{
          setResponseModel(response.data);
        })
        .catch(error=>{
          console.log("API call could not be done bcz:"+error);
        })
      }
    
      var resultBody={
        emailsLogList:[],
        totalCount:0
      }
      var sortByBody={
        orderBy:"candidateId",
        order:"desc"
      }
      const [responseModel,setResponseModel]=useState(resultBody);
      const [page,setPage]=useState(0);
      const [searchValue,setSearchValue]=useState(null);
      const [sortValue,setSortValue]=useState(sortByBody);
      const [refreshFlag,setRefreshFlag]=useState(false);
      let rowsPerPage=50;

    useEffect(()=>{
      let jobId=parseInt(localStorage.getItem('emails_jobId'),10);
      let startDate=localStorage.getItem('emails_startDate');
      startDate=startDate==null?new Date('2000-01-01').getTime():new Date(startDate).getTime();
      let endDate=localStorage.getItem('emails_endDate');
      endDate=endDate==null?new Date('2020-12-01').getTime():new Date(endDate).getTime();
      const requestPayload={
        searchValue:searchValue,
        jobId:jobId!==null?jobId:null,
        startDate:startDate,
        endDate:endDate,
        page:page,
        size:rowsPerPage,
        orderBy:sortValue.orderBy,
        direction:sortValue.order
      }
      console.log("jobId:"+requestPayload.jobId);
      fetchData(requestPayload);
    },[page,rowsPerPage,sortValue.order,sortValue.orderBy,searchValue,refreshFlag]);

    const handleChangePage = async (event, newPage) => {
      setPage(newPage);
    };

    const handleSearch=async()=>{
      let searchValue=document.getElementById("search").value;
      setSearchValue(searchValue);
    }

    const createSortHandler=async(value,index)=>{
      let sortDirection=directionIndex[index];
      setSortValue((prevState)=>({
        order:prevState.order==='asc'?'desc':'asc',
        orderBy:value
      }))
      directionIndex[index]=!sortDirection;
    }

    const refreshEmailListView=()=>{
      setRefreshFlag((prevState)=>({
        refreshFlag:!prevState
      }))
    }

    return (
         <React.Fragment>
           <input id="search"></input> &nbsp; &nbsp;
           <button onClick={handleSearch}>Search</button>
           &nbsp;
           <IconButton onClick={refreshEmailListView}>
            <RefreshIcon fontSize="large"/>
            </IconButton>
           <br></br>
       <TableContainer>
         <Table>
         <TableHead>
          <TableRow> 
             {headCells.map((headCell) => (
               <TableCell
                 key={headCell.id}
                  sortDirection={directionIndex[headCell.index]?'asc':'desc'}
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
             {responseModel.emailsLogList!==undefined && responseModel.emailsLogList.map((e)=>(
                 <TableRow key={e.id}>
                     <TableCell
                         key={e.candidateId} >
                           {e.candidateId}  
                    </TableCell>
                     <TableCell
                       key={e.jobId} >
                           {e.jobId}  
                     </TableCell>
                     <TableCell
                         key={e.email} >
                         {e.email}
                     </TableCell>
                 <TableCell
                        key={e.emailType}>
                       {e.emailType}
                     </TableCell>
                     <TableCell
                        key={e.status}>
                       {e.status}
                     </TableCell>
                     <TableCell
                         key={e.creationDate}>
                         {new Date(e.creationDate).toLocaleDateString("en-US")}
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
      </React.Fragment>
      );
}

export default EmailTableViewComponent;
