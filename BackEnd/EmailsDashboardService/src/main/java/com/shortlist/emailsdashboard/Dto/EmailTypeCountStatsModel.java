package com.shortlist.emailsdashboard.Dto;

import java.math.BigInteger;

public class EmailTypeCountStatsModel {
	
	private String label;
	
	private BigInteger y;

	public EmailTypeCountStatsModel() {
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public BigInteger getY() {
		return y;
	}

	public void setY(BigInteger y) {
		this.y = y;
	}

}
