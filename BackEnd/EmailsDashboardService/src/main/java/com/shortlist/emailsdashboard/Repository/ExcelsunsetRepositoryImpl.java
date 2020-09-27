package com.shortlist.emailsdashboard.Repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.shortlist.emailsdashboard.Dto.SourceTypeCountStatusModel;
import com.shortlist.emailsdashboard.Entity.ExcelSunset;

public class ExcelsunsetRepositoryImpl extends BaseDao implements ExcelsunsetRepositoryCustom {

	@SuppressWarnings("unchecked")
	@Override
	public List<SourceTypeCountStatusModel> getSourceTypeCountStats(ExcelSunset excelSunset) {
		List<SourceTypeCountStatusModel> sourceTypeCountStatsModelList=new ArrayList<>(0);
		try {
			Session session = sessionManager.getCurrentSession();
			Query query = session.createSQLQuery("SELECT e.source as label,count(*) as y " 
							+ "FROM excelsunset e " 
							+ "where e.source is not null and e.jobId=:jobId and "
							+ "e.time>=:time and e.time<=:endDate " 
							+ "group by e.source ")
					.setResultTransformer(Transformers.aliasToBean(SourceTypeCountStatusModel.class))
					.setParameter("jobId", excelSunset.getJobId())
					.setParameter("time", excelSunset.getTime()).
					setParameter("endDate",excelSunset.getEndDate());
			sourceTypeCountStatsModelList = query.list();
			return sourceTypeCountStatsModelList;
		} catch (HibernateException exception) {
			throw exception;
		}
	}

}
