package com.mcjs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mcjs.dao.BusinessGatherDao;
import com.mcjs.entity.BusinessGather;
import com.mcjs.tool.PageTool;

public class BusinessGatherDaoImpl extends HibernateDaoSupport implements BusinessGatherDao {
	public void addBusinessGather(BusinessGather businessGather) {
		this.getHibernateTemplate().save(businessGather);
	}
	@SuppressWarnings("unchecked")
	public List<BusinessGather> findBusinessGathers(PageTool tool) {
		String hql="from BusinessGather order by AccountsTime desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public int getBusinessGather() {
		List<BusinessGather> list=this.getHibernateTemplate().find("from BusinessGather order by id desc");
		return list.size();
	}
	@SuppressWarnings("unchecked")
	public List<BusinessGather> findBusinessGathers(PageTool tool, String where) {
		String hql="from BusinessGather where 1=1 "+where+" order by AccountsTime desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public int getBusinessGather(String where) {
		List<BusinessGather> list=this.getHibernateTemplate().find("from BusinessGather where 1=1 "+where+" order by id desc");
		return list.size();
	}
	@SuppressWarnings("unchecked")
	public List<BusinessGather> findbBusinessGathers(String where) {
		List<BusinessGather> list=this.getHibernateTemplate().find("from BusinessGather where 1=1 "+where+" order by id desc");
		return list;
	}

}
