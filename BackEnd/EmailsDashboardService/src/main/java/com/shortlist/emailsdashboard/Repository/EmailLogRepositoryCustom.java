package com.shortlist.emailsdashboard.Repository;

import java.util.List;

import com.shortlist.emailsdashboard.Dto.EmailTypeCountStatsModel;
import com.shortlist.emailsdashboard.Dto.EmailsLogDto;
import com.shortlist.emailsdashboard.Entity.EmailsLog;

public interface EmailLogRepositoryCustom {
	
	List<EmailTypeCountStatsModel> getEmailTypeCountStats(EmailsLog emailsLog);
	
	List<EmailTypeCountStatsModel> getCountStatuses(EmailsLog emailsLog);
	
	List<EmailsLog> searchEmailLogWithFilters(String query);
	
	Integer getEmailsLogCountWithFilters(String query);
	
	List<EmailsLog> fetchRecords(EmailsLogDto emailLogDto);
	
	public Integer fetchRecordsCount(EmailsLogDto emailLogDto);

}
