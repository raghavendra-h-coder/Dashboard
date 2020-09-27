package com.shortlist.emailsdashboard.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shortlist.emailsdashboard.Entity.ExcelSunset;

@Repository
public interface ExcelsunsetRepository extends JpaRepository<ExcelSunset, Integer>,ExcelsunsetRepositoryCustom{

}
