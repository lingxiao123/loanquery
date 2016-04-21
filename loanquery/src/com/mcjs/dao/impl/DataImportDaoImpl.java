package com.mcjs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mcjs.dao.DataImportDao;
import com.mcjs.entity.BusinessGather;
import com.mcjs.entity.BusinessSele;
import com.mcjs.entity.EntrustSele;
import com.mcjs.entity.FundStreamSele;
import com.mcjs.entity.ImportData;
import com.mcjs.entity.PositionGather;
import com.mcjs.entity.PositionSele;
import com.mcjs.entity.StateFund;
import com.mcjs.tool.PageTool;

public class DataImportDaoImpl extends HibernateDaoSupport implements DataImportDao {

	@SuppressWarnings("unchecked")
	public List<ImportData> findDatas(PageTool tool) {
		String hql="from ImportData order by im_id desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public int getDataImport() {
		List<ImportData> list=this.getHibernateTemplate().find("from ImportData order by im_id desc");
		return list.size();
	}
	public void addData(ImportData importData) {
		this.getHibernateTemplate().save(importData);
	}
	@SuppressWarnings("unchecked")
	public void deleteData(int id) {
		ImportData im=new ImportData();
		final String hql="from ImportData where im_id="+id;
		List<ImportData> list=this.getHibernateTemplate().find(hql);
		if (list.size()>0) {
			im=list.get(0);
		}
		if (im.getIm_type().contains("客户资金状况表")) {
			String hql_statefund="from StateFund where BatNumber='"+im.getIm_imortNumber()+"'";
			List<StateFund> list_statefund=this.getHibernateTemplate().find(hql_statefund);
			if (list_statefund.size()>0) {
				for (int i = 0; i < list_statefund.size(); i++) {
					StateFund sF=new StateFund();
					sF=list_statefund.get(i);
					this.getHibernateTemplate().delete(sF);
				}
			}
		}
		if (im.getIm_type().contains("客户持仓汇总表")) {
			String hql_PositionGather="from PositionGather where BatNumber='"+im.getIm_imortNumber()+"'";
			List<PositionGather> list_positioGathers=this.getHibernateTemplate().find(hql_PositionGather);
			if (list_positioGathers.size()>0) {
				for (int i = 0; i < list_positioGathers.size(); i++) {
					PositionGather pg=new PositionGather();
					pg=list_positioGathers.get(i);
					this.getHibernateTemplate().delete(pg);					
				}
			}
		}	
		if (im.getIm_type().contains("客户成交汇总表")) {
			String hql_BusinessGather="from BusinessGather where BatNumber='"+im.getIm_imortNumber()+"'";
			List<BusinessGather> list_BusinessGather=this.getHibernateTemplate().find(hql_BusinessGather);
			if (list_BusinessGather.size()>0) {
				for (int i = 0; i < list_BusinessGather.size(); i++) {
					BusinessGather bg=new BusinessGather();
					bg=list_BusinessGather.get(i);
					this.getHibernateTemplate().delete(bg);
					
				}
			}
		}
		if (im.getIm_type().contains("客户持仓查询")) {
			String hql_PositionSele="from PositionSele where BatNumber='"+im.getIm_imortNumber()+"'";
			List<PositionSele> list_positionsele=this.getHibernateTemplate().find(hql_PositionSele);
			if (list_positionsele.size()>0) {
				for (int i = 0; i < list_positionsele.size(); i++) {
					PositionSele ps=new PositionSele();
					ps=list_positionsele.get(i);
					this.getHibernateTemplate().delete(ps);
				}
			}
		}
		if (im.getIm_type().contains("客户成交查询")) {
			String hql_BusinessSele="from BusinessSele where BatNumber='"+im.getIm_imortNumber()+"'";
			List<BusinessSele> list_BusinessSele=this.getHibernateTemplate().find(hql_BusinessSele);
			if (list_BusinessSele.size()>0) {
				for (int i = 0; i < list_BusinessSele.size(); i++) {
					BusinessSele bs=new BusinessSele();
					bs=list_BusinessSele.get(i);
					this.getHibernateTemplate().delete(bs);
				}
			}
		}
		if (im.getIm_type().contains("客户委托单查询")) {
			String hql_EntrustSele="from EntrustSele where BatNumber='"+im.getIm_imortNumber()+"'";
			List<EntrustSele> list_EntrustSele=this.getHibernateTemplate().find(hql_EntrustSele);
			if (list_EntrustSele.size()>0) {
				for (int i = 0; i <list_EntrustSele.size(); i++) {
					EntrustSele es=new EntrustSele();
					es=list_EntrustSele.get(i);
					this.getHibernateTemplate().delete(es);					
				}
				
			}
		}
		if (im.getIm_type().contains("客户资金流水查询")) {
			String hql_FundStreamSele="from FundStreamSele where BatNumber='"+im.getIm_imortNumber()+"'";
			List<FundStreamSele> list_FundStreamSele=this.getHibernateTemplate().find(hql_FundStreamSele);
			if (list_FundStreamSele.size()>0) {
				for (int i = 0; i < list_FundStreamSele.size(); i++) {
					FundStreamSele fs=new FundStreamSele();
					fs=list_FundStreamSele.get(i);
					this.getHibernateTemplate().delete(fs);
				}
			}
		}
		this.getHibernateTemplate().delete(im);
	}
	
}
