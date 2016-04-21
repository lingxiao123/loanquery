package com.mcjs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mcjs.dao.StateFundDao;
import com.mcjs.entity.StateFund;
import com.mcjs.tool.PageTool;

public class StateFundDaoImpl extends HibernateDaoSupport implements StateFundDao {
	public void addStateFund(StateFund stateFund) {
		this.getHibernateTemplate().save(stateFund);
	}

	@SuppressWarnings("unchecked")
	public List<StateFund> findStateFunds(PageTool tool) {
		String hql="from StateFund  order by AccountsTime desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public int getStateFund() {
		List<StateFund> list=this.getHibernateTemplate().find("from StateFund order by Id desc");
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public int getStateFund(String where) {
		List<StateFund> list=this.getHibernateTemplate().find("from StateFund where 1=1 "+where+" order by Id desc");
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public List<StateFund> findStateFunds(PageTool tool, String where) {
		String hql="from StateFund where 1=1 "+where+"  order by AccountsTime desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<StateFund> findsStateFund(String where) {
		List<StateFund> list=this.getHibernateTemplate().find("from StateFund where 1=1 "+where+"  order by AccountsTime desc");
		return list;
	}

}
