package com.shortlist.emailsdashboard.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.shortlist.emailsdashboard.Dto.AsyncSelectModel;
import com.shortlist.emailsdashboard.Dto.EmailTypeCountStatsModel;
import com.shortlist.emailsdashboard.Dto.EmailsLogDto;
import com.shortlist.emailsdashboard.Dto.EmailsSearchModel;
import com.shortlist.emailsdashboard.Dto.EmailsSearchResponseModel;
import com.shortlist.emailsdashboard.Dto.ExcelRowStatusDto;
import com.shortlist.emailsdashboard.Dto.SourceTypeCountStatusModel;
import com.shortlist.emailsdashboard.Dto.UploadsSearchResponseModel;
import com.shortlist.emailsdashboard.Entity.EmailsLog;
import com.shortlist.emailsdashboard.Entity.ExcelRowStatus;
import com.shortlist.emailsdashboard.Entity.ExcelSunset;
import com.shortlist.emailsdashboard.Entity.JobOpening;

public interface EmailsDashboardService {

	public List<JobOpening> getJobTitles(Integer page,Integer size);
	
	public List<String> getEmailTypes(Pageable pageable);
	
	public List<JobOpening> getJobTitlesWithSearchValue(String searchValue,Integer page,Integer size);
	
	public AsyncSelectModel getAsyncJobTitlesModel(String searchValue,Integer offset);
	
	public AsyncSelectModel getAsyncEmailTypesModel(String searchValue,Integer offset);
	
	public List<EmailTypeCountStatsModel> getEmailTypeCountStats(EmailsLog emailsLog);
	
	public List<EmailTypeCountStatsModel> getCountOfStatuses(EmailsLog emailsLog);
	
	public EmailsSearchResponseModel searchFiltersOnEmails(EmailsSearchModel emailsSearchModel);
	
	public EmailsSearchResponseModel fetchRecords(EmailsLogDto emailsLogDto);
	
	public List<SourceTypeCountStatusModel> getSourceTypeCountStats(ExcelSunset excelSunset);
	
	public List<SourceTypeCountStatusModel> getCountsOfSourceStatuses(ExcelRowStatus excelRowStatus);
	
	public UploadsSearchResponseModel fetchUploadRecords(ExcelRowStatusDto excelRowStatusDto);
}
