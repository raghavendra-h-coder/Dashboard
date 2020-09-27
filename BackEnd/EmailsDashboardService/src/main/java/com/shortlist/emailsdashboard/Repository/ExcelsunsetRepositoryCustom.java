package com.shortlist.emailsdashboard.Repository;

import java.util.List;

import com.shortlist.emailsdashboard.Dto.SourceTypeCountStatusModel;
import com.shortlist.emailsdashboard.Entity.ExcelSunset;

public interface ExcelsunsetRepositoryCustom {
	
	public List<SourceTypeCountStatusModel> getSourceTypeCountStats(ExcelSunset excelSunset);

}
