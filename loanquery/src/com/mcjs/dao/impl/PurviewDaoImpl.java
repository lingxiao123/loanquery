package com.mcjs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mcjs.dao.PurviewDao;
import com.mcjs.entity.Purview;
import com.mcjs.entity.RoleAuthorize;
import com.mcjs.tool.PageTool;

public class PurviewDaoImpl extends HibernateDaoSupport implements PurviewDao {
	@SuppressWarnings("unchecked")
	public List<Purview> findPurviews(PageTool tool) {
		String hql="from Purview order by p_id desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public int getPurview() {
		List<Purview> list=this.getHibernateTemplate().find("from Purview order by p_id desc");
		return list.size();
	}
	public void addPurview(Purview purview) {
		this.getHibernateTemplate().save(purview);
	}
	@SuppressWarnings("unchecked")
	public List<Purview> findPurviews(String module, String type) {
		List<Purview> list=this.getHibernateTemplate().find("from Purview where p_modulename='"+module+"' and p_type='"+type+"'");
		return list;
	}
	public void addRoleAuthorize(RoleAuthorize roleAuthorize) {
		this.getHibernateTemplate().save(roleAuthorize);
	}
	@SuppressWarnings("unchecked")
	public void deleteRoleAuthorize(String roleid) {
		RoleAuthorize ra=new RoleAuthorize();
		String hql="from RoleAuthorize where ra_roleid='"+roleid+"'";
		List<RoleAuthorize> list=this.getHibernateTemplate().find(hql);
		if (list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				ra=list.get(i);
				this.getHibernateTemplate().delete(ra);
			}		
		}		
	}
	@SuppressWarnings("unchecked")
	public List<RoleAuthorize> findrRoleAuthorizes(int roleid) {
		List<RoleAuthorize> list=this.getHibernateTemplate().find("from RoleAuthorize where ra_roleid='"+roleid+"'");
		return list;
	}
	@SuppressWarnings("unchecked")
	public void deletePurview(int id) {
		Purview p=new Purview();
		List<Purview> list=this.getHibernateTemplate().find("from Purview where p_id="+id+"");
		if (list.size()>0) {
			p=list.get(0);
		}
		this.getHibernateTemplate().delete(p);
	}
	
}
