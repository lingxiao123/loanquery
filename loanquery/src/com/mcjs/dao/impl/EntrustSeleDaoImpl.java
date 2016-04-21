package com.mcjs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mcjs.dao.EntrustSeleDao;
import com.mcjs.entity.EntrustSele;
import com.mcjs.tool.PageTool;

public class EntrustSeleDaoImpl extends HibernateDaoSupport implements EntrustSeleDao {
	public void addEntrustSele(EntrustSele entrustSele) {
		this.getHibernateTemplate().save(entrustSele);
	}

	@SuppressWarnings("unchecked")
	public List<EntrustSele> findeEntrustSeles(PageTool tool) {
		String hql="from EntrustSele order by BalanceDate desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public int getEntrustSele() {
		List<EntrustSele> list=this.getHibernateTemplate().find("from EntrustSele order by id desc");
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public List<EntrustSele> findeEntrustSeles(PageTool tool, String where) {
		String hql="from EntrustSele where 1=1 "+where+" order by BalanceDate desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public int getEntrustSele(String where) {
		List<EntrustSele> list=this.getHibernateTemplate().find("from EntrustSele where 1=1 "+where+" order by id desc");
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public List<EntrustSele> findEntrustSele(String where) {
		List<EntrustSele> list=this.getHibernateTemplate().find("from EntrustSele where 1=1 "+where+" order by id desc");
		return list;
	}

}
