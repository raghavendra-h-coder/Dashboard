package com.shortlist.emailsdashboard.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shortlist.emailsdashboard.Entity.EmailsLog;

@Repository
public interface EmailLogRepository extends JpaRepository<EmailsLog, Integer>,EmailLogRepositoryCustom {

	@Query("Select distinct(e.emailType) from EmailsLog e")
	List<String> fetchEmailTypes(Pageable pageable);
	
	@Query(nativeQuery=true,value="select distinct(e.emailtype) from emailslog e where emailtype is not null "
			+ "and e.emailtype like %?1% limit ?2,10")
	List<String> fetchAsyncEmailTypes(String searchValue,Integer offset);
	
	@Query(nativeQuery=true,value="select count(*) from emailslog e where emailtype is not null "
			+ "and e.emailtype like %?1%")
	Integer getEmailTypesCount(String searchValue);
	
	@Query("select e from EmailsLog e where e.creationDate>=:startDate and e.creationDate<=:endDate")
	List<EmailsLog> fetchRecordsWithoutJobId(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate,Pageable pageable);
	
	@Query("select count(*) from EmailsLog e where e.creationDate>=:startDate and e.creationDate<=:endDate")
	Integer fetchRecordsCountWithoutJobId(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	
	@Query("select e from EmailsLog e where e.creationDate>=:startDate and e.creationDate<=:endDate "
			+ "and e.jobId=:jobId")
	List<EmailsLog> fetchRecordsWithJobId(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("jobId") Integer jobId,Pageable pageable);
	
	@Query("select count(*) from EmailsLog e where e.creationDate>=:startDate and e.creationDate<=:endDate "
			+ "and e.jobId=:jobId")
	Integer fetchRecordsCountWithJobId(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("jobId") Integer jobId);
}
