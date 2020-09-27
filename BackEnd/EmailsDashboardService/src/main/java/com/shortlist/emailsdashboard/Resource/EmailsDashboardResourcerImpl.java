package com.shortlist.emailsdashboard.Resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.shortlist.emailsdashboard.Service.EmailsDashboardService;

@RestController
public class EmailsDashboardResourcerImpl implements EmailsDashboardResource {
	
	@Autowired
	private EmailsDashboardService emailsDashboardService;

	@Override
	public ResponseEntity<List<JobOpening>> getJobTitles(
			@RequestParam(value="searchvalue",required=false) String searchValue,
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="size",defaultValue="10") Integer size) {
		List<JobOpening> jobs;
		
		if(searchValue==null) {
			jobs=emailsDashboardService.getJobTitles(page,size);
		}
		else
		{
			jobs=emailsDashboardService.getJobTitlesWithSearchValue(searchValue, page, size);
		}
		return ResponseEntity.ok(jobs);
	}

	@Override
	public ResponseEntity<List<String>> getEmailTypes(
			@PageableDefault(page=0,size=10) Pageable pageable) {
		return ResponseEntity.ok(emailsDashboardService.getEmailTypes(pageable));
	}

	@Override
	public ResponseEntity<AsyncSelectModel> getAsyncJobTitlesModel(
			@RequestParam(value="searchvalue",required=false) String searchValue, 
			@RequestParam(value="offset",required=true) Integer offset) {
		return ResponseEntity.ok(emailsDashboardService.getAsyncJobTitlesModel(searchValue, offset));
	}

	@Override
	public ResponseEntity<AsyncSelectModel> getAsynEmailTypesModel(
			@RequestParam(value="searchvalue",required=true) String searchValue, 
			@RequestParam(value="offset",required=true) Integer offset) {
		return ResponseEntity.ok(emailsDashboardService.getAsyncEmailTypesModel(searchValue, offset));
	}

	@Override
	public ResponseEntity<List<EmailTypeCountStatsModel>> getEmailTypeCountStats(
			@RequestBody EmailsLog emailsLog) {
		return ResponseEntity.ok(emailsDashboardService.getEmailTypeCountStats(emailsLog));
	}

	@Override
	public ResponseEntity<List<EmailTypeCountStatsModel>> getCountOfStatuses(
			@RequestBody EmailsLog emailsLog) {
		return ResponseEntity.ok(emailsDashboardService.getCountOfStatuses(emailsLog));
	}

	@Override
	public ResponseEntity<EmailsSearchResponseModel> getSearchResultsForEmails(
			@RequestBody EmailsSearchModel emailsSearchModel) {
		return ResponseEntity.ok(emailsDashboardService.searchFiltersOnEmails(emailsSearchModel));
	}

	@Override
	public ResponseEntity<EmailsSearchResponseModel> fetchRecords(@RequestBody EmailsLogDto emailsLogDto) {
		return ResponseEntity.ok(emailsDashboardService.fetchRecords(emailsLogDto));
	}

	@Override
	public ResponseEntity<List<SourceTypeCountStatusModel>> getSourceTypeCountStats(@RequestBody ExcelSunset excelsunset) {
		return ResponseEntity.ok(emailsDashboardService.getSourceTypeCountStats(excelsunset));
	}

	@Override
	public ResponseEntity<List<SourceTypeCountStatusModel>> getCountOfUploadStatuses(@RequestBody ExcelRowStatus excelRowStatus) {
		return ResponseEntity.ok(emailsDashboardService.getCountsOfSourceStatuses(excelRowStatus));
	}

	@Override
	public ResponseEntity<UploadsSearchResponseModel> fetchUploadRecords(@RequestBody ExcelRowStatusDto excelRowStatusDto) {
		return ResponseEntity.ok(emailsDashboardService.fetchUploadRecords(excelRowStatusDto));
	}

}
