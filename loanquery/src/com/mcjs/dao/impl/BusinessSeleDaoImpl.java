package com.mcjs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mcjs.dao.BusinessSeleDao;
import com.mcjs.entity.BusinessSele;
import com.mcjs.tool.PageTool;

public class BusinessSeleDaoImpl extends HibernateDaoSupport implements BusinessSeleDao {
	public void addBusinessSele(BusinessSele businessSele) {
		this.getHibernateTemplate().save(businessSele);
	}
	@SuppressWarnings("unchecked")
	public List<BusinessSele> findbusBusinessSeles(PageTool tool) {
		String hql="from BusinessSele order by BalanceDate desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public int getBusinessSele() {
		List<BusinessSele> list=this.getHibernateTemplate().find("from BusinessSele order by id desc");
		return list.size();
	}
	@SuppressWarnings("unchecked")
	public List<BusinessSele> findbusBusinessSeles(PageTool tool, String where) {
		String hql="from BusinessSele where 1=1 "+where+" order by BalanceDate desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public int getBusinessSele(String where) {
		List<BusinessSele> list=this.getHibernateTemplate().find("from BusinessSele where 1=1 "+where+" order by id desc");
		return list.size();
	}
	@SuppressWarnings("unchecked")
	public List<BusinessSele> findfBusinessSele(String where) {
		List<BusinessSele> list=this.getHibernateTemplate().find("from BusinessSele where 1=1 "+where+" order by id desc");
		return list;
	}
	
}
