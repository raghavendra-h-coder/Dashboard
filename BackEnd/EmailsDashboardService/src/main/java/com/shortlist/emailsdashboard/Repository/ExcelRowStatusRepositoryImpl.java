package com.shortlist.emailsdashboard.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.shortlist.emailsdashboard.Dto.ExcelRowStatusDto;
import com.shortlist.emailsdashboard.Dto.SourceTypeCountStatusModel;
import com.shortlist.emailsdashboard.Entity.EmailsLog;
import com.shortlist.emailsdashboard.Entity.ExcelRowStatus;

public class ExcelRowStatusRepositoryImpl extends BaseDao implements ExcelRowStatusRepositoryCustom {

	@SuppressWarnings("unchecked")
	@Override
	public List<SourceTypeCountStatusModel> getCountsOfSourceStatuses(ExcelRowStatus excelRowStatus) {
		
			List<SourceTypeCountStatusModel> sourceTypeCountStatsModelList=new ArrayList<>(0);
			try {
				Session session = sessionManager.getCurrentSession();
				Query query = session.createSQLQuery("SELECT e.rowStatus as label,count(*) as y " 
								+ "FROM excelrowstatus e " 
								+ "where e.source=:source and e.jobId=:jobId and "
								+ "e.createdDate>=:time and e.createdDate<=:endDate " 
								+ "group by e.rowStatus ")
						.setResultTransformer(Transformers.aliasToBean(SourceTypeCountStatusModel.class))
						.setParameter("jobId", excelRowStatus.getJobId())
						.setParameter("time", excelRowStatus.getCreatedDate()).
						setParameter("endDate",excelRowStatus.getEndDate()).
						setParameter("source",excelRowStatus.getSource());
				sourceTypeCountStatsModelList = query.list();
				return sourceTypeCountStatsModelList;
			} catch (HibernateException exception) {
				throw exception;
			}
		}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExcelRowStatus> fetchUploadRecords(ExcelRowStatusDto excelRowStatusDto) {
		try {
			Integer jobId=excelRowStatusDto.getJobId();
			Date startDate=excelRowStatusDto.getStartDate();
			Date endDate=excelRowStatusDto.getEndDate();
			String searchValue=excelRowStatusDto.getSearchValue();
			Integer page=excelRowStatusDto.getPage();
			Integer size=excelRowStatusDto.getSize();
			Integer offset=page*size;
			String orderBy=excelRowStatusDto.getOrderBy();
			String direction=excelRowStatusDto.getDirection();
			Session session = sessionManager.getCurrentSession();
			Criteria cb = session.createCriteria(ExcelRowStatus.class);
			Conjunction conjunction=Restrictions.conjunction();
			if(searchValue!=null)
			{
				Criterion searchValueCriterion=Restrictions.like("email", searchValue, MatchMode.ANYWHERE);
				conjunction.add(searchValueCriterion);
			}
			if(jobId!=null)
			{
				Criterion jobIdCriterion=Restrictions.eq("jobId", jobId);
				conjunction.add(jobIdCriterion);
			}
			Criterion startDateCriterion=Restrictions.ge("createdDate", startDate);
			conjunction.add(startDateCriterion);
			Criterion endDateCriterion=Restrictions.le("createdDate", endDate);
			conjunction.add(endDateCriterion);
			cb.add(conjunction);
			cb.setFirstResult(offset);
			cb.setMaxResults(size);
			if("asc".equalsIgnoreCase(direction))
			{
				cb.addOrder(Order.asc(orderBy));
			}
			else
			{
				cb.addOrder(Order.desc(orderBy));
			}
			return cb.list();
		} catch (HibernateException exception) {
			throw exception;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer fetchUploadRecordsCount(ExcelRowStatusDto excelRowStatusDto) {
		try {
			Integer jobId=excelRowStatusDto.getJobId();
			Date startDate=excelRowStatusDto.getStartDate();
			Date endDate=excelRowStatusDto.getEndDate();
			String searchValue=excelRowStatusDto.getSearchValue();
			Session session = sessionManager.getCurrentSession();
			Criteria cb = session.createCriteria(ExcelRowStatus.class);
			cb.setProjection(Projections.rowCount());
			Conjunction conjunction=Restrictions.conjunction();
			if(searchValue!=null)
			{
				Criterion searchValueCriterion=Restrictions.like("email", searchValue, MatchMode.ANYWHERE);
				conjunction.add(searchValueCriterion);
			}
			if(jobId!=null)
			{
				Criterion jobIdCriterion=Restrictions.eq("jobId", jobId);
				conjunction.add(jobIdCriterion);
			}
			Criterion startDateCriterion=Restrictions.ge("createdDate", startDate);
			conjunction.add(startDateCriterion);
			Criterion endDateCriterion=Restrictions.le("createdDate", endDate);
			conjunction.add(endDateCriterion);
			cb.add(conjunction);
			Long count=(Long)cb.list().get(0);
			return count.intValue();
		} catch (HibernateException exception) {
			throw exception;
		}
	}
}
