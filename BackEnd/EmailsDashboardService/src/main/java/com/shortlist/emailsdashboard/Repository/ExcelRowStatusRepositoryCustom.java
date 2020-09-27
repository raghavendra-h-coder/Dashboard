package com.shortlist.emailsdashboard.Repository;

import java.util.List;

import com.shortlist.emailsdashboard.Dto.ExcelRowStatusDto;
import com.shortlist.emailsdashboard.Dto.SourceTypeCountStatusModel;
import com.shortlist.emailsdashboard.Entity.ExcelRowStatus;

public interface ExcelRowStatusRepositoryCustom {
	
	public List<SourceTypeCountStatusModel> getCountsOfSourceStatuses(ExcelRowStatus excelRowStatus);
	
	public List<ExcelRowStatus> fetchUploadRecords(ExcelRowStatusDto excelRowStatusDto);
	
	public Integer fetchUploadRecordsCount(ExcelRowStatusDto excelRowStatusDto);

}
