package com.shortlist.emailsdashboard.Dto;

import java.util.List;

import com.shortlist.emailsdashboard.Entity.ExcelRowStatus;

public class UploadsSearchResponseModel {
	
	private List<ExcelRowStatus> excelRowStatusList;
	
	private Integer totalCount;

	public List<ExcelRowStatus> getExcelRowStatusList() {
		return excelRowStatusList;
	}

	public void setExcelRowStatusList(List<ExcelRowStatus> excelRowStatusList) {
		this.excelRowStatusList = excelRowStatusList;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
}
