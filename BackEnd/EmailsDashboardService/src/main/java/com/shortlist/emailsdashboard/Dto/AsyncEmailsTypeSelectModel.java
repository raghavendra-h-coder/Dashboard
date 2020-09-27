package com.shortlist.emailsdashboard.Dto;

import java.util.List;

public class AsyncEmailsTypeSelectModel {
	
	private List<String> emailTypes;
	
	private boolean hasMore;

	public List<String> getEmailTypes() {
		return emailTypes;
	}

	public void setEmailTypes(List<String> emailTypes) {
		this.emailTypes = emailTypes;
	}

	public boolean isHasMore() {
		return hasMore;
	}

	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}

}
