package com.shortlist.emailsdashboard.Resource;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

@CrossOrigin
@RequestMapping(value="api/emails/")
public interface EmailsDashboardResource {
	
	//emails
	@GetMapping(value="dashboard/list/jobtitles")
	ResponseEntity<List<JobOpening>> getJobTitles(String searchValue,Integer page,Integer size);
	
	@GetMapping(value="dashboard/list/emailtypes")
	ResponseEntity<List<String>> getEmailTypes(Pageable pageable);
	
	@GetMapping(value="async/list/jobtitles")
	ResponseEntity<AsyncSelectModel> getAsyncJobTitlesModel(String searchValue,Integer offset);
	
	@GetMapping(value="async/list/emailtypes")
	ResponseEntity<AsyncSelectModel> getAsynEmailTypesModel(String searchValue,Integer offset);
	
	@PostMapping(value="count/emailtypes")
	ResponseEntity<List<EmailTypeCountStatsModel>> getEmailTypeCountStats(EmailsLog emailsLog);
	
	@PostMapping(value="count/status/emailtypes")
	ResponseEntity<List<EmailTypeCountStatsModel>> getCountOfStatuses(EmailsLog emailsLog);
	
	@PostMapping(value="search/filters")
	ResponseEntity<EmailsSearchResponseModel> getSearchResultsForEmails(EmailsSearchModel emailsSearchModel);
	
	@PostMapping(value="fetch/records")
	ResponseEntity<EmailsSearchResponseModel> fetchRecords(EmailsLogDto emailsLogDto);
	
	//uploads
	@PostMapping(value="count/sourcetypes")
	ResponseEntity<List<SourceTypeCountStatusModel>> getSourceTypeCountStats(ExcelSunset excelsunset);
	
	@PostMapping(value="count/status/sourcetypes")
	ResponseEntity<List<SourceTypeCountStatusModel>> getCountOfUploadStatuses(ExcelRowStatus excelRowStatus);
	
	@PostMapping(value="fetch/upload/records")
	ResponseEntity<UploadsSearchResponseModel> fetchUploadRecords(ExcelRowStatusDto excelRowStatusDto);

}
