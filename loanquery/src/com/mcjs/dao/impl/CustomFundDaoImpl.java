package com.mcjs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mcjs.dao.CustomFundDao;
import com.mcjs.entity.CustomFund;
import com.mcjs.tool.PageTool;

public class CustomFundDaoImpl extends HibernateDaoSupport implements CustomFundDao {
	private int Id=0;
	public void addCustomFund(CustomFund customFund) {
		this.getHibernateTemplate().save(customFund);
	}
	@SuppressWarnings("unchecked")
	public List<CustomFund> getCustomFundById(String id) {
		List<CustomFund> list=this.getHibernateTemplate().find("from CustomFund where TradeAccount='"+id+"'");
		if (list.size()>0) {
			this.Id=list.get(0).getId();
		}
		return list;
	}
	public void updateCustomFund(CustomFund customFund) {
		if (Id!=0) {
			customFund.setId(Id);
		}
		this.getHibernateTemplate().clear();
		this.getHibernateTemplate().update(customFund);
		this.getHibernateTemplate().flush();
	}
	
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	@SuppressWarnings("unchecked")
	public int getCustomFund() {
		List<CustomFund> list=this.getHibernateTemplate().find("from CustomFund order by id desc");
		return list.size();
	}
	@SuppressWarnings("unchecked")
	public List<CustomFund> findCustomFunds(PageTool tool) {
		String hql="from CustomFund order by id desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public int getCustomFund(String where) {
		List<CustomFund> list=this.getHibernateTemplate().find("from CustomFund where 1=1 "+where+" order by id desc");
		return list.size();
	}
	@SuppressWarnings("unchecked")
	public List<CustomFund> findCustomFunds(PageTool tool, String where) {
		String hql="from CustomFund where 1=1 "+where+" order by id desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public void deleteCustomFund() {
		CustomFund cf=new CustomFund();
		List<CustomFund> list=this.getHibernateTemplate().find("from CustomFund");
		if (list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				cf=list.get(i);
				this.getHibernateTemplate().delete(cf);
			}
			
		}
	}
	@SuppressWarnings("unchecked")
	public List<CustomFund> findCustomFund(String where) {
		List<CustomFund> list=this.getHibernateTemplate().find("from CustomFund where 1=1 "+where+" order by id desc");
		return list;
	}
	
}
