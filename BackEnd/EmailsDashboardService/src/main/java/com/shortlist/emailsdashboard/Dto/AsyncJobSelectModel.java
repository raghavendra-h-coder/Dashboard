package com.shortlist.emailsdashboard.Dto;

import java.util.List;

import com.shortlist.emailsdashboard.Entity.JobOpening;

public class AsyncJobSelectModel {
	
	private List<JobOpening> jobOpening;
	
	private boolean hasMore;

	public List<JobOpening> getJobOpening() {
		return jobOpening;
	}

	public void setJobOpening(List<JobOpening> jobOpening) {
		this.jobOpening = jobOpening;
	}

	public boolean isHasMore() {
		return hasMore;
	}

	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}

}
