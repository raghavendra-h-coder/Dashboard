package com.shortlist.emailsdashboard.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shortlist.emailsdashboard.Entity.ExcelRowStatus;

@Repository
public interface ExcelRowStatusRepository extends JpaRepository<ExcelRowStatus, Integer>,ExcelRowStatusRepositoryCustom {

}
