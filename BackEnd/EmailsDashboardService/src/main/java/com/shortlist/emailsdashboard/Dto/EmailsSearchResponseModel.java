package com.shortlist.emailsdashboard.Dto;

import java.util.List;

import com.shortlist.emailsdashboard.Entity.EmailsLog;

public class EmailsSearchResponseModel {
	
	private List<EmailsLog> emailsLogList;
	
	private Integer totalCount;

	public List<EmailsLog> getEmailsLogList() {
		return emailsLogList;
	}

	public void setEmailsLogList(List<EmailsLog> emailsLogList) {
		this.emailsLogList = emailsLogList;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

}
