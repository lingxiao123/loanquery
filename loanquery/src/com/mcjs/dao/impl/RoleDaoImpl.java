package com.mcjs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mcjs.dao.RoleDao;
import com.mcjs.entity.Role;
import com.mcjs.tool.PageTool;

public class RoleDaoImpl extends HibernateDaoSupport implements RoleDao {

	@SuppressWarnings("unchecked")
	public List<Role> findRole(PageTool tool) {
		String hql="from Role order by id desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public int getRole() {
		List<Role> list=this.getHibernateTemplate().find("from Role order by id desc");
		return list.size();
	}
	public void addRole(Role role) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> findRole() {
		List<Role> list=this.getHibernateTemplate().find("from Role order by id desc");
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Role> findRoles(int where) {
		List<Role> list=this.getHibernateTemplate().find("from Role where role_id="+where+"");
 		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> findRoles(String where) {
		List<Role> list=this.getHibernateTemplate().find("from Role where 1=1 "+where+"");
 		return list;
	}

	public void updateRole(Role role) {
		this.getHibernateTemplate().clear();
		this.getHibernateTemplate().update(role);
		this.getHibernateTemplate().flush();
	}

	@SuppressWarnings("unchecked")
	public void deleteRole(int id) {
		Role role=new Role();
		String hql="from Role where role_id="+id;
		List<Role> list=this.getHibernateTemplate().find(hql);
		if (list.size()>0) {
			role=list.get(0);
		}
		this.getHibernateTemplate().delete(role);
	}

}
