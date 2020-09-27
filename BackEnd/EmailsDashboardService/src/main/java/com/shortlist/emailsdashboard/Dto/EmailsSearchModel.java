package com.shortlist.emailsdashboard.Dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EmailsSearchModel {
	
	private String searchValue;
	
	private List<Integer> jobIds=new ArrayList<>(0);
	
	private List<String> emailTypes=new ArrayList<>(0);
	
	private String status;
	
	private Integer page;
	
	private Integer size;
	
	private String sortDirection="desc";
	
	private String orderBy;
	
	@JsonIgnore
	private List<String> filterConditions=new ArrayList<>(0);

	public List<Integer> getJobIds() {
		return jobIds;
	}

	public void setJobIds(List<Integer> jobIds) {
		this.jobIds = jobIds;
		List<String> jobIdsInString=jobIds.stream().map(String::valueOf).collect(Collectors.toList());
		filterConditions.add("el.jobId in("+String.join(",", jobIdsInString)+")");
	}

	public List<String> getEmailTypes() {
		return emailTypes;
	}

	public void setEmailTypes(List<String> emailTypes) {
		this.emailTypes = emailTypes;
		StringBuilder str=new StringBuilder("");
		emailTypes.forEach(e->{
			str.append("'"+e+"' ");
		});
		filterConditions.add("el.emailType in("+str.toString().trim().replace(" ", ",")+")");
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		filterConditions.add("el.status="+status);
	}
	
	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
		filterConditions.add("el.email like '%"+searchValue+"%'");
	}

	public List<String> getFilterConditions() {
		return filterConditions;
	}

	public void setFilterConditions(List<String> queryConditions) {
		this.filterConditions = queryConditions;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}
