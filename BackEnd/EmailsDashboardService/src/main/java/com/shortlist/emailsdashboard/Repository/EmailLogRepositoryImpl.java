package com.shortlist.emailsdashboard.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.shortlist.emailsdashboard.Dto.EmailTypeCountStatsModel;
import com.shortlist.emailsdashboard.Dto.EmailsLogDto;
import com.shortlist.emailsdashboard.Dto.SourceTypeCountStatusModel;
import com.shortlist.emailsdashboard.Entity.EmailsLog;
import com.shortlist.emailsdashboard.Entity.ExcelSunset;

public class EmailLogRepositoryImpl extends BaseDao implements EmailLogRepositoryCustom {

	@SuppressWarnings("unchecked")
	@Override
	public List<EmailTypeCountStatsModel> getEmailTypeCountStats(EmailsLog emailsLog) {
		List<EmailTypeCountStatsModel> emailTypeCountStatsModelList=new ArrayList<>(0);
		try {
			Session session = sessionManager.getCurrentSession();
			Query query = session.createSQLQuery("SELECT e.emailType as label,count(*) as y " 
							+ "FROM emailslog e " 
							+ "where e.emailType is not null and e.jobId=:jobId and "
							+ "e.creationDate>=:creationDate and e.creationDate<=:endDate " 
							+ "group by e.emailType ")
					.setResultTransformer(Transformers.aliasToBean(EmailTypeCountStatsModel.class))
					.setParameter("jobId", emailsLog.getJobId())
					.setParameter("creationDate", emailsLog.getCreationDate()).
					setParameter("endDate",emailsLog.getEndDate());
			emailTypeCountStatsModelList = query.list();
			return emailTypeCountStatsModelList;
		} catch (HibernateException exception) {
			throw exception;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmailTypeCountStatsModel> getCountStatuses(EmailsLog emailsLog) {
		List<EmailTypeCountStatsModel> emailTypeCountStatsModelList=new ArrayList<>(0);
		try {
			Session session = sessionManager.getCurrentSession();
			Query query = session.createSQLQuery("SELECT e.status as label,count(*) as y " 
							+ "FROM emailslog e " 
							+ "where e.jobId=:jobId and e.emailType=:emailType and "
							+ "e.creationDate>=:creationDate and e.creationDate<=:endDate " 
							+ "group by e.status ")
					.setResultTransformer(Transformers.aliasToBean(EmailTypeCountStatsModel.class))
					.setParameter("jobId", emailsLog.getJobId())
					.setParameter("emailType", emailsLog.getEmailType())
					.setParameter("creationDate", emailsLog.getCreationDate())
					.setParameter("endDate",emailsLog.getEndDate());
			emailTypeCountStatsModelList = query.list();
			return emailTypeCountStatsModelList;
		} catch (HibernateException exception) {
			throw exception;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmailsLog> searchEmailLogWithFilters(String query) {
		List<EmailsLog> emailsLog=new ArrayList<>(0);
		try {
			Session session = sessionManager.getCurrentSession();
			Query sqlquery = session.createSQLQuery(query)
					.setResultTransformer(Transformers.aliasToBean(EmailsLog.class));
			emailsLog = sqlquery.list();
			return emailsLog;
		} catch (HibernateException exception) {
			throw exception;
		}
	}

	@Override
	public Integer getEmailsLogCountWithFilters(String query) {
		try 
		{
			Session session = sessionManager.getCurrentSession();
			Query sqlquery = session.createSQLQuery(query);
			BigInteger emailsLogCount = (BigInteger)sqlquery.uniqueResult();
			return emailsLogCount.intValue();
		} catch (HibernateException exception) {
			throw exception;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmailsLog> fetchRecords(EmailsLogDto emailLogDto) {
		try {
			Integer jobId=emailLogDto.getJobId();
			Date startDate=emailLogDto.getStartDate();
			Date endDate=emailLogDto.getEndDate();
			String searchValue=emailLogDto.getSearchValue();
			Integer page=emailLogDto.getPage();
			Integer size=emailLogDto.getSize();
			Integer offset=page*size;
			String orderBy=emailLogDto.getOrderBy();
			String direction=emailLogDto.getDirection();
			Session session = sessionManager.getCurrentSession();
			Criteria cb = session.createCriteria(EmailsLog.class);
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
			Criterion startDateCriterion=Restrictions.ge("creationDate", startDate);
			conjunction.add(startDateCriterion);
			Criterion endDateCriterion=Restrictions.le("creationDate", endDate);
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
	public Integer fetchRecordsCount(EmailsLogDto emailLogDto) {
		try {
			Integer jobId=emailLogDto.getJobId();
			Date startDate=emailLogDto.getStartDate();
			Date endDate=emailLogDto.getEndDate();
			String searchValue=emailLogDto.getSearchValue();
			Session session = sessionManager.getCurrentSession();
			Criteria cb = session.createCriteria(EmailsLog.class);
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
			Criterion startDateCriterion=Restrictions.ge("creationDate", startDate);
			conjunction.add(startDateCriterion);
			Criterion endDateCriterion=Restrictions.le("creationDate", endDate);
			conjunction.add(endDateCriterion);
			cb.add(conjunction);
			Long count=(Long)cb.list().get(0);
			return count.intValue();
		} catch (HibernateException exception) {
			throw exception;
		}
	}
}
