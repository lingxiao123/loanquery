package com.mcjs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mcjs.dao.PositionSeleDao;
import com.mcjs.entity.PositionSele;
import com.mcjs.tool.PageTool;

public class PositionSeleDaoImpl extends HibernateDaoSupport implements PositionSeleDao {
	public void addPositionSele(PositionSele positionSele) {
		this.getHibernateTemplate().save(positionSele);
	}
	@SuppressWarnings("unchecked")
	public int getPositionSele() {
		List<PositionSele> list=this.getHibernateTemplate().find("from PositionSele order by id desc");	
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public List<PositionSele> findpPositionSeles(PageTool tool) {
		String hql="from PositionSele order by BalanceDate desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public int getPositionSele(String where) {
		List<PositionSele> list=this.getHibernateTemplate().find("from PositionSele where 1=1 "+where+" order by id desc");	
		return list.size();
	}
	@SuppressWarnings("unchecked")
	public List<PositionSele> findpPositionSeles(PageTool tool, String where) {
		String hql="from PositionSele where 1=1 "+where+" order by BalanceDate desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<PositionSele> findPositionSele(String where) {
		List<PositionSele> list=this.getHibernateTemplate().find("from PositionSele where 1=1 "+where+" order by BalanceDate desc");
		return list;
	}

}
