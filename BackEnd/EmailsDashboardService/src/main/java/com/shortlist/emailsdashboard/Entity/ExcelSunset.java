package com.shortlist.emailsdashboard.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="excelsunset")
public class ExcelSunset {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String source;
	
	@Column
	private String processId;
	
	@Column
	private Integer jobId;
	
	@Column(name="RowCountAfterExtraction")
	private Integer rowCountAfterExtraction;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;
	
	@Transient
	private Date endDate;
	
	@Column
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public Integer getRowCountAfterExtraction() {
		return rowCountAfterExtraction;
	}

	public void setRowCountAfterExtraction(Integer rowCountAfterExtraction) {
		this.rowCountAfterExtraction = rowCountAfterExtraction;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
