package com.mcjs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mcjs.dao.FundStreamSeleDao;
import com.mcjs.entity.FundStreamSele;
import com.mcjs.tool.PageTool;

public class FundStreamSeleDaoImpl extends HibernateDaoSupport implements FundStreamSeleDao {
	public void addFundStreamSele(FundStreamSele fundStreamSele) {
		this.getHibernateTemplate().save(fundStreamSele);
	}
	@SuppressWarnings("unchecked")
	public int getFundStreamSele() {
		List<FundStreamSele> list=this.getHibernateTemplate().find("from FundStreamSele order by id desc");
		return list.size();
	}
	@SuppressWarnings("unchecked")
	public List<FundStreamSele> findFundStreamSeles(PageTool tool) {
		String hql="from BalanceDate order by id desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public int getFundStreamSele(String where) {
		List<FundStreamSele> list=this.getHibernateTemplate().find("from FundStreamSele where 1=1 "+where+" order by id desc");
		return list.size();
	}
	@SuppressWarnings("unchecked")
	public List<FundStreamSele> findFundStreamSeles(PageTool tool, String where) {
		String hql="from FundStreamSele where 1=1 "+where+" order by BalanceDate desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<FundStreamSele> findFundStreamSele(String where) {
		List<FundStreamSele> list=this.getHibernateTemplate().find("from FundStreamSele where 1=1 "+where+" order by BalanceDate desc");
		return list;
	}

}
