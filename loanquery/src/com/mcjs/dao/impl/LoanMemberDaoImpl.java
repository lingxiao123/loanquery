package com.mcjs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mcjs.dao.LoanMemberDao;
import com.mcjs.entity.LoanMember;
import com.mcjs.tool.PageTool;

public class LoanMemberDaoImpl extends HibernateDaoSupport implements LoanMemberDao {
	@SuppressWarnings("unchecked")
	public List<LoanMember> findLoanMembers(PageTool tool) {
		String hql="from LoanMember order by lm_id desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}

	public void addLoanMember(LoanMember loanMember) {
		this.getHibernateTemplate().save(loanMember);
	}

	@SuppressWarnings("unchecked")
	public void deleteLoanMember(int id) {
		LoanMember lm=new LoanMember();
		String hql="from LoanMember where lm_id='"+id+"'";
		List<LoanMember> list=this.getHibernateTemplate().find(hql);
		if (list.size()>0) {
			lm=list.get(0);
		}
		this.getHibernateTemplate().delete(lm);
	}
	@SuppressWarnings("unchecked")
	public int getLoanMember() {
		List<LoanMember> list=this.getHibernateTemplate().find("from LoanMember order by lm_id desc");
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public List<LoanMember> findLoanMembers(String where) {
		List<LoanMember> list=this.getHibernateTemplate().find("from LoanMember where 1=1 "+where+"");
		return list;
	}

	@SuppressWarnings("unchecked")
	public int getLoanMember(String where) {
		List<LoanMember> list=this.getHibernateTemplate().find("from LoanMember where 1=1 "+where+" order by lm_id desc");
		return list.size();
	}
	@SuppressWarnings("unchecked")
	public List<LoanMember> findLoanMembers(PageTool tool, String where) {
		String hql="from LoanMember where 1=1 "+where+" order by lm_id desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<LoanMember> findLoanMembers(int id) {
		List<LoanMember> list=this.getHibernateTemplate().find("from LoanMember where lm_id="+id+"");		
		return list;
	}

	public void updateLoanMember(LoanMember loanMember) {
		this.getHibernateTemplate().clear();
		this.getHibernateTemplate().update(loanMember);
		this.getHibernateTemplate().flush();
	}	
}
