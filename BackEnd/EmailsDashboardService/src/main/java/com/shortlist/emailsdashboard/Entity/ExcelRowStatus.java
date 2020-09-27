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
@Table(name="excelrowstatus")
public class ExcelRowStatus {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String source;
	
	@Column
	private String name;
	
	@Column
	private String email;
	
	@Column
	private String rowStatus;
	
	@Column
	private String reason;
	
	@Column
	private Integer jobId;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Transient
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	@Column
	private String processId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSource() {
		return source!=null?source:"NA";
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getName() {
		return name!=null?name:"NA";
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email!=null?email:"NA";
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRowStatus() {
		return rowStatus!=null?rowStatus:"NA";
	}

	public void setRowStatus(String rowStatus) {
		this.rowStatus = rowStatus;
	}

	public String getReason() {
		return reason!=null?reason:"NA";
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getJobId() {
		return jobId!=null?jobId:0;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
