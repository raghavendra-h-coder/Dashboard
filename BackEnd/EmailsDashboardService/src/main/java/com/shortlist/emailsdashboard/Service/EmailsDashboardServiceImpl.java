package com.shortlist.emailsdashboard.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shortlist.emailsdashboard.Dto.AsyncSelectModel;
import com.shortlist.emailsdashboard.Dto.EmailTypeCountStatsModel;
import com.shortlist.emailsdashboard.Dto.EmailsLogDto;
import com.shortlist.emailsdashboard.Dto.EmailsSearchModel;
import com.shortlist.emailsdashboard.Dto.EmailsSearchResponseModel;
import com.shortlist.emailsdashboard.Dto.ExcelRowStatusDto;
import com.shortlist.emailsdashboard.Dto.OptionsModel;
import com.shortlist.emailsdashboard.Dto.SourceTypeCountStatusModel;
import com.shortlist.emailsdashboard.Dto.UploadsSearchResponseModel;
import com.shortlist.emailsdashboard.Entity.EmailsLog;
import com.shortlist.emailsdashboard.Entity.ExcelRowStatus;
import com.shortlist.emailsdashboard.Entity.ExcelSunset;
import com.shortlist.emailsdashboard.Entity.JobOpening;
import com.shortlist.emailsdashboard.Repository.EmailLogRepository;
import com.shortlist.emailsdashboard.Repository.ExcelRowStatusRepository;
import com.shortlist.emailsdashboard.Repository.ExcelsunsetRepository;
import com.shortlist.emailsdashboard.Repository.JobOpeningRepository;

@Service
public class EmailsDashboardServiceImpl implements EmailsDashboardService {
	
	@Autowired
	private JobOpeningRepository jobOpeningRepository;
	
	@Autowired
	private EmailLogRepository emailLogRepository;
	
	@Autowired
	private ExcelsunsetRepository excelSunsetRepository;
	
	@Autowired
	private ExcelRowStatusRepository excelRowStatusRepository;

	@Override
	public List<JobOpening> getJobTitles(Integer page, Integer size) {
		Integer offset=page*size;
		return jobOpeningRepository.fetchJobTitles(offset, size);
	}

	@Override
	public List<String> getEmailTypes(Pageable pageable) {
		return emailLogRepository.fetchEmailTypes(pageable);
	}

	@Override
	public List<JobOpening> getJobTitlesWithSearchValue(String searchValue, Integer page, Integer size) {
		Integer offset=page*size;
		return jobOpeningRepository.fetchJobTitlesWithSearchValue(searchValue, offset, size);
	}

	@Override
	public AsyncSelectModel getAsyncJobTitlesModel(String searchValue, Integer offset) {
		Integer count=0;
		List<JobOpening> jobOpenings=new ArrayList<>(0);
		if(searchValue!=null)
		{
			count=jobOpeningRepository.getJobTitlesCountWithPattern(searchValue);
			jobOpenings=jobOpeningRepository.fetchJobTitlesWithSearchValue(searchValue, offset, 10);
		}
		else
		{
			count=jobOpeningRepository.getJobTitlesCount();
			jobOpenings=jobOpeningRepository.fetchJobTitlesWithoutSearchValue(offset, 10);
		}
		List<OptionsModel> optionsModelList=new ArrayList<>(0);
		AsyncSelectModel asyncSelectModel=new AsyncSelectModel();
		jobOpenings.forEach(j->{
			OptionsModel optionsModel=new OptionsModel();
			optionsModel.setLabel(j.getJobTitle()+"("+j.getId()+")");
			optionsModel.setValue(String.valueOf(j.getId()));
			optionsModelList.add(optionsModel);
		});
		asyncSelectModel.setOptionsModel(optionsModelList);
		if(count>(offset+10))
		{
			asyncSelectModel.setHasMore(true);
		}
		return asyncSelectModel;
	}

	@Override
	public AsyncSelectModel getAsyncEmailTypesModel(String searchValue, Integer offset) {
		Integer count=emailLogRepository.getEmailTypesCount(searchValue);
		List<String> emailTypesList=emailLogRepository.fetchAsyncEmailTypes(searchValue, offset);
		List<OptionsModel> optionsModelList=new ArrayList<>(0);
		AsyncSelectModel asyncSelectModel=new AsyncSelectModel();
		emailTypesList.forEach(e->{
			OptionsModel optionsModel=new OptionsModel();
			optionsModel.setLabel(e);
			optionsModel.setValue(e);
			optionsModelList.add(optionsModel);
		});
		asyncSelectModel.setOptionsModel(optionsModelList);
		if(count>(offset+10))
		{
			asyncSelectModel.setHasMore(true);
		}
		return asyncSelectModel;
	}

	@Override
	public List<EmailTypeCountStatsModel> getEmailTypeCountStats(EmailsLog emailsLog) {
		return emailLogRepository.getEmailTypeCountStats(emailsLog);
	}

	@Override
	public List<EmailTypeCountStatsModel> getCountOfStatuses(EmailsLog emailsLog) {
		return emailLogRepository.getCountStatuses(emailsLog);
	}

	@Override
	public EmailsSearchResponseModel searchFiltersOnEmails(EmailsSearchModel emailsSearchModel) {
		EmailsSearchResponseModel emailsSearchResponseModel=new EmailsSearchResponseModel();
		List<String> filterConditions=emailsSearchModel.getFilterConditions();
		String dataQuery="select * from emailslog el ";
		String countQuery="select count(*) from emailslog el ";
		StringBuilder stringBuilderDataQuery=formQueryWithFilterConditions(dataQuery,filterConditions);
		StringBuilder stringBuilderCountQuery=formQueryWithFilterConditions(countQuery,filterConditions);
		Integer size=emailsSearchModel.getSize();
		Integer offset=emailsSearchModel.getPage()*size;
		stringBuilderDataQuery.append("order by el."+emailsSearchModel.getOrderBy()+" "+
				emailsSearchModel.getSortDirection()+" limit "+offset+","+size);
		List<EmailsLog> emailsLog=emailLogRepository.searchEmailLogWithFilters(stringBuilderDataQuery.toString());
		Integer count=emailLogRepository.getEmailsLogCountWithFilters(stringBuilderCountQuery.toString());
		emailsSearchResponseModel.setEmailsLogList(emailsLog);
		emailsSearchResponseModel.setTotalCount(count);
		return emailsSearchResponseModel;
	}
	
	private StringBuilder formQueryWithFilterConditions(String query,List<String> filterConditions)
	{
		StringBuilder str=new StringBuilder(query);
		if(!filterConditions.isEmpty())
		{
			str.append(" where ");
			str.append(String.join(" and ", filterConditions)+" ");
		}
		return str;
	}

	@Override
	public EmailsSearchResponseModel fetchRecords(EmailsLogDto emailsLogDto) {
		List<EmailsLog> resultList=new ArrayList<>(0);
		Integer totalCount=0;
		resultList=emailLogRepository.fetchRecords(emailsLogDto);
		totalCount=emailLogRepository.fetchRecordsCount(emailsLogDto);
		EmailsSearchResponseModel emailsSearchResponseModel=new EmailsSearchResponseModel();
		emailsSearchResponseModel.setEmailsLogList(resultList);
		emailsSearchResponseModel.setTotalCount(totalCount);
		return emailsSearchResponseModel;
	}

	@Override
	public List<SourceTypeCountStatusModel> getSourceTypeCountStats(ExcelSunset excelSunset) {
		return excelSunsetRepository.getSourceTypeCountStats(excelSunset);
	}

	@Override
	public List<SourceTypeCountStatusModel> getCountsOfSourceStatuses(ExcelRowStatus excelRowStatus) {
		return excelRowStatusRepository.getCountsOfSourceStatuses(excelRowStatus);
	}

	@Override
	public UploadsSearchResponseModel fetchUploadRecords(ExcelRowStatusDto excelRowStatusDto) {
		List<ExcelRowStatus> resultList=new ArrayList<>(0);
		Integer totalCount=0;
		resultList=excelRowStatusRepository.fetchUploadRecords(excelRowStatusDto);
		totalCount=excelRowStatusRepository.fetchUploadRecordsCount(excelRowStatusDto);
		UploadsSearchResponseModel uploadsSearchResponseModel=new UploadsSearchResponseModel();
		uploadsSearchResponseModel.setExcelRowStatusList(resultList);
		uploadsSearchResponseModel.setTotalCount(totalCount);
		return uploadsSearchResponseModel;
	}
	
}
