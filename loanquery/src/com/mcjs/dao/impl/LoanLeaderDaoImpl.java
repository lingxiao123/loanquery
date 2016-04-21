package com.mcjs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mcjs.dao.LoanLeaderDao;
import com.mcjs.entity.LoanLeader;
import com.mcjs.entity.PostData;
import com.mcjs.tool.PageTool;
import com.mcjs.util.MD5Util;

public class LoanLeaderDaoImpl extends HibernateDaoSupport implements LoanLeaderDao {

	@SuppressWarnings("unchecked")
	public List<LoanLeader> findLoanLeaders(PageTool tool) {
		String hql="from LoanLeader order by loan_id desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public int getLoanLeader() {
		List<LoanLeader> list=this.getHibernateTemplate().find("from LoanLeader order by loan_id desc");
		return list.size();
	}
	public void addLoanLeader(LoanLeader loanLeader) {
		loanLeader.setLoan_passWord(MD5Util.convertMD5(loanLeader.getLoan_passWord().trim()));
		this.getHibernateTemplate().save(loanLeader);
	}
	@SuppressWarnings("unchecked")
	public List<LoanLeader> findlLoanLeaders(String where) {
		List<LoanLeader> list=this.getHibernateTemplate().find("from LoanLeader where 1=1 "+where+"");
		return list;
	}
	@SuppressWarnings("unchecked")
	public int getLoanLeader(String where) {
		List<LoanLeader> list=this.getHibernateTemplate().find("from LoanLeader where 1=1 "+where+" order by loan_id desc");
		return list.size();
	}
	@SuppressWarnings("unchecked")
	public List<LoanLeader> findLoanLeaders(PageTool tool, String where) {
		String hql="from LoanLeader where 1=1 "+where+" order by loan_id desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<LoanLeader> findLoanLeaders(int id) {
		List<LoanLeader> list=this.getHibernateTemplate().find("from LoanLeader where loan_id="+id+"");
		return list;
	}
	public void updateLoanLeader(LoanLeader loanLeader) {
		this.getHibernateTemplate().clear();
		this.getHibernateTemplate().update(loanLeader);
		this.getHibernateTemplate().flush();
	}
	@SuppressWarnings("unchecked")
	public List<LoanLeader> findLoanLeaders(String where) {
		List<LoanLeader> list=this.getHibernateTemplate().find("from LoanLeader where 1=1 "+where+" and loan_status=1 order by loan_id desc");
		return list;
	}
	@SuppressWarnings("unchecked")
	public void deleteLoanLeader(int id) {
		LoanLeader ll=new LoanLeader();
		List<LoanLeader> list=this.getHibernateTemplate().find("from LoanLeader where loan_id="+id+"");
		if (list.size()>0) {
			ll=list.get(0);
		}
		this.getHibernateTemplate().delete(ll);
	}
	@SuppressWarnings("unchecked")
	public List<PostData> finLoanName(String keyword, String where) {
		List<LoanLeader> list=this.getHibernateTemplate().find("from LoanLeader where 1=1 and loan_name like '%"+keyword+"%' "+where+"");
		List<PostData> list_data=new ArrayList<PostData>();
		if (list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				PostData pd=new PostData();
				pd.setText(list.get(i).getLoan_name());
				pd.setValue(list.get(i).getLoan_id());
				list_data.add(pd);
			}
		}
		return list_data;
	}

}
