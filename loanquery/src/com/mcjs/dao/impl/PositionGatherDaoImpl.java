package com.mcjs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mcjs.dao.PositionGatherDao;
import com.mcjs.entity.PositionGather;
import com.mcjs.tool.PageTool;

public class PositionGatherDaoImpl extends HibernateDaoSupport implements PositionGatherDao {
	public void addPositionGather(PositionGather positionGather) {
		this.getHibernateTemplate().save(positionGather);
	}
	@SuppressWarnings("unchecked")
	public List<PositionGather> findPositionGather(PageTool tool) {
		String hql="from PositionGather order by accountsTime desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public int getPositionGather() {
		List<PositionGather> list=this.getHibernateTemplate().find("from PositionGather order by Id desc");
		return list.size();
	}
	@SuppressWarnings("unchecked")
	public int getPositionGather(String where) {
		List<PositionGather> list=this.getHibernateTemplate().find("from PositionGather where 1=1 "+where+" order by Id desc");
		return list.size();
	}
	@SuppressWarnings("unchecked")
	public List<PositionGather> findPositionGather(PageTool tool, String where) {
		String hql="from PositionGather where 1=1 "+where+" order by accountsTime desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<PositionGather> findPositionGather(String where) {
		List<PositionGather> list=this.getHibernateTemplate().find("from PositionGather where 1=1 "+where+" order by accountsTime desc");
		return list;
	}

}
