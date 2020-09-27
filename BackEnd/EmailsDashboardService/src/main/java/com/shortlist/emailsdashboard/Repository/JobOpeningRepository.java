package com.shortlist.emailsdashboard.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shortlist.emailsdashboard.Entity.JobOpening;

@Repository
public interface JobOpeningRepository extends JpaRepository<JobOpening, Integer> {

	@Query(nativeQuery=true,value="select * "
			+ "from jobopening j where j.jobtitle is not null limit :offset,:size")
	List<JobOpening> fetchJobTitles(@Param("offset") Integer offset, @Param("size") Integer size);
	
	@Query(nativeQuery=true,value="select * "
			+ "from jobopening j where j.jobtitle is not null and "
			+ "j.jobtitle like %:searchValue% order by j.id limit :offset,:size")
	List<JobOpening> fetchJobTitlesWithSearchValue(@Param("searchValue") String searchValue,
			@Param("offset") Integer offset, @Param("size") Integer size);
	
	@Query(nativeQuery=true,value="select count(*) "
			+ "from jobopening j where j.jobtitle like %?1%")
	Integer getJobTitlesCountWithPattern(String searchValue);
	
	@Query(nativeQuery=true,value="select * "
			+ "from jobopening j where j.jobtitle is not null order by j.id "
			+ " limit :offset,:size")
	List<JobOpening> fetchJobTitlesWithoutSearchValue(
			@Param("offset") Integer offset, @Param("size") Integer size);
	
	@Query(nativeQuery=true,value="select count(*) "
			+ "from jobopening j ")
	Integer getJobTitlesCount();
}
