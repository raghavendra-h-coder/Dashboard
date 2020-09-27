package com.shortlist.emailsdashboard.Dto;

import java.util.List;

public class AsyncSelectModel {
	
	private List<OptionsModel> optionsModel;
	
	private boolean hasMore;

	public List<OptionsModel> getOptionsModel() {
		return optionsModel;
	}

	public void setOptionsModel(List<OptionsModel> optionsModel) {
		this.optionsModel = optionsModel;
	}

	public boolean isHasMore() {
		return hasMore;
	}

	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}

}
