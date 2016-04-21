package com.mcjs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mcjs.dao.OrgInfoDao;
import com.mcjs.entity.OrgInfo;
import com.mcjs.entity.PostData;
import com.mcjs.tool.PageTool;
import com.mcjs.util.MD5Util;

public class OrgInfoDaoImpl extends HibernateDaoSupport implements OrgInfoDao {

	@SuppressWarnings("unchecked")
	public List<OrgInfo> findOrginfo(PageTool tool) {
		String hql="from OrgInfo order by org_id desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public int getOrgInfo() {
		String hql="from OrgInfo";
		List<OrgInfo> list=this.getHibernateTemplate().find(hql);
		//List<OrgInfo> list=this.getHibernateTemplate().find("from OrgInfo order by org_id desc");
		return list.size();
		
	}

	@Override
	public void addOrgInfo(OrgInfo orgInfo) {
		// TODO Auto-generated method stub
		orgInfo.setOrg_PassWord(MD5Util.convertMD5(orgInfo.getOrg_PassWord()));
		this.getHibernateTemplate().save(orgInfo);
	}

	@SuppressWarnings("unchecked")
	public void deleteOrgInfo(int id) {
		OrgInfo org=new OrgInfo();
		final String hql="from OrgInfo where org_id='"+id+"'";
		List<OrgInfo> list=this.getHibernateTemplate().find(hql);
		if (list.size()>0) {
			org=list.get(0);
		}
		this.getHibernateTemplate().delete(org);
	}

	@SuppressWarnings("unchecked")
	public List<OrgInfo> findOrginfo(PageTool tool,String where) {
		String hql="from OrgInfo where 1=1 "+where+" order by org_id desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public int getOrgInfo(String where) {
		String hql="from OrgInfo where 1=1 "+where+"";
		List<OrgInfo> list=this.getHibernateTemplate().find(hql);
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public List<OrgInfo> findOrginfo(int id) {
		List<OrgInfo> list=this.getHibernateTemplate().find("from OrgInfo where org_id="+id);
		return list;
	}

	public void updateOrginfo(OrgInfo orgInfo) {
		this.getHibernateTemplate().clear();
		this.getHibernateTemplate().update(orgInfo);
		this.getHibernateTemplate().flush();
	}
	
	@SuppressWarnings("unchecked")
	public List<OrgInfo> findOrginfo() {
		List<OrgInfo> list=this.getHibernateTemplate().find("from OrgInfo where org_Status=1 order by org_id desc");
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<OrgInfo> findOrginfo(String where) {
		List<OrgInfo> list=this.getHibernateTemplate().find("from OrgInfo where 1=1 "+where+"");
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<PostData> finOrginfos(String keyword) {
		List<OrgInfo> list=this.getHibernateTemplate().find("from OrgInfo where org_Abbreviation like '%"+keyword+"%'");
		List<PostData> list_data=new ArrayList<PostData>();
		if (list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				PostData pd=new PostData();
				pd.setText(list.get(i).getOrg_Abbreviation());
				pd.setValue(list.get(i).getOrg_id());
				list_data.add(pd);
			}
		}
		return list_data;
	}
	
}
