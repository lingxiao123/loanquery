package com.mcjs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mcjs.dao.UserDao;
import com.mcjs.entity.LoanLeader;
import com.mcjs.entity.OrgInfo;
import com.mcjs.entity.RoleAuthorize;
import com.mcjs.entity.User;
import com.mcjs.tool.PageTool;
import com.mcjs.util.MD5Util;
import com.opensymphony.xwork2.ActionContext;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@SuppressWarnings("unchecked")
	public List<User> findUser(PageTool tool) {
		// TODO Auto-generated method stub
		String hql="from User order by id desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public int getUser() {
		List<User> list=this.getHibernateTemplate().find("from User order by id desc");
 		return list.size();
	}
	public void addUser(User user) {
		user.setUser_passWord(MD5Util.convertMD5(user.getUser_passWord()));
		this.getHibernateTemplate().save(user);
		
	}

	@SuppressWarnings("unchecked")
	public int getLoginUser(User user) {
		List<User> list=null;
		ActionContext ac=ActionContext.getContext();
		int status=0;
		try {
			String hql="from User u where u.user_name='"+user.getUser_name()+"'";
			list=this.getHibernateTemplate().find(hql);
			//管理员表中含有该人员
			if (list.size()>0) {
				String hql_checkpwd="from User u where u.user_name='"+user.getUser_name()+"' and u.user_passWord='"+MD5Util.convertMD5(user.getUser_passWord())+"'";
				List<User> list_user=this.getHibernateTemplate().find(hql_checkpwd);
				if (list_user.size()>0) {
					status=list_user.get(0).getUser_status();
					ac.getSession().put("user_role",list_user.get(0).getUser_role());
					ac.getSession().put("username",list_user.get(0).getUser_name());
					String hql_role="from RoleAuthorize where ra_roleid='"+list_user.get(0).getUser_role()+"'";
					List<RoleAuthorize> list_role=this.getHibernateTemplate().find(hql_role);
					ac.getSession().put("list_role", list_role);
					if(list_user.get(0).getUser_name().contains("admin")){
						ac.getSession().put("superadmin", "true");
					}else {
						ac.getSession().put("superadmin", "false");
					}
					ac.getSession().put("role", "系统用户");
					if (list_role.size()>0) {
						String str="";
						for (int i = 0; i < list_role.size(); i++) {
							str+=list_role.get(i).getRa_purviewname()+",";
						}
						if (str.contains("客户持仓数据")) {
							ac.getSession().put("import", "true");
						}else {
							ac.getSession().put("import", "false");
						}
						if (str.contains("用户管理")||str.contains("角色管理")||str.contains("权限信息")) {
							ac.getSession().put("sys","true");
						}else {
							ac.getSession().put("sys","false");
						}
						if (str.contains("居间")||str.contains("个代")) {
							ac.getSession().put("base","true");
						}else {
							ac.getSession().put("base","false");
						}
						if (str.contains("客户")) {
							ac.getSession().put("data", "true");
						}else {
							ac.getSession().put("data", "false");
						}
					}
				}else {
					status=2;
				}
			}else {
				//查看居间表和个代表中是否含有该账户
				//String hql_org="form ";
				String hql_org="from OrgInfo where org_Abbreviation='"+user.getUser_name().trim()+"'";
				List<OrgInfo> list_org=this.getHibernateTemplate().find(hql_org);
				if (list_org.size()>0) {
					String hql_checkorgpwd="from OrgInfo where org_Abbreviation='"+user.getUser_name().trim()+"' and org_PassWord ='"+MD5Util.convertMD5(user.getUser_passWord().trim())+"'";
					List<OrgInfo> lists_org=this.getHibernateTemplate().find(hql_checkorgpwd);
					if (lists_org.size()>0) {
						status=lists_org.get(0).getOrg_Status();
						ac.getSession().put("role", "居间商");
						String hql_role="from RoleAuthorize where ra_roleid='"+lists_org.get(0).getOrg_RoleId()+"'";
						List<RoleAuthorize> list_role=this.getHibernateTemplate().find(hql_role);
						ac.getSession().put("list_role", list_role);
						if (list_role.size()>0) {
							String str="";
							for (int i = 0; i < list_role.size(); i++) {
								str+=list_role.get(i).getRa_purviewname()+",";
							}
							if (str.contains("客户持仓数据")) {
								ac.getSession().put("import", "true");
							}else {
								ac.getSession().put("import", "false");
							}
							if (str.contains("用户管理")||str.contains("角色管理")||str.contains("权限信息")) {
								ac.getSession().put("sys","true");
							}else {
								ac.getSession().put("sys","false");
							}
							if (str.contains("居间")||str.contains("个代")) {
								ac.getSession().put("base","true");
							}else {
								ac.getSession().put("base","false");
							}
							if (str.contains("客户")) {
								ac.getSession().put("data", "true");
							}else {
								ac.getSession().put("data", "false");
							}
						}
						ac.getSession().put("orgId",lists_org.get(0).getOrg_id());
						ac.getSession().put("org_RoleId",lists_org.get(0).getOrg_RoleId());
						ac.getSession().put("username",lists_org.get(0).getOrg_FullName());
					}else {
						status=2;
					}
				}else {
					String hql_loanleader="from LoanLeader where  loan_name='"+user.getUser_name().trim()+"'";
					List<LoanLeader> list_leader=this.getHibernateTemplate().find(hql_loanleader);
					if (list_leader.size()>0) {
						String hql_loanpwd="from LoanLeader where  loan_name='"+user.getUser_name().trim()+"' and loan_passWord ='"+MD5Util.convertMD5(user.getUser_passWord().trim())+"'";
						List<LoanLeader> list_loan=this.getHibernateTemplate().find(hql_loanpwd);						
						if (list_loan.size()>0) {
							status=list_loan.get(0).getLoan_status();
							ac.getSession().put("role", "个代");
							String hql_role="from RoleAuthorize where ra_roleid='"+list_loan.get(0).getLoan_roleId()+"'";
							List<RoleAuthorize> list_role=this.getHibernateTemplate().find(hql_role);
							ac.getSession().put("list_role", list_role);
							if (list_role.size()>0) {
								String str="";
								for (int i = 0; i < list_role.size(); i++) {
									str+=list_role.get(i).getRa_purviewname()+",";
								}
								if (str.contains("客户持仓数据")) {
									ac.getSession().put("import", "true");
								}else {
									ac.getSession().put("import", "false");
								}
								if (str.contains("用户管理")||str.contains("角色管理")||str.contains("权限信息")) {
									ac.getSession().put("sys","true");
								}else {
									ac.getSession().put("sys","false");
								}
								if (str.contains("居间")||str.contains("个代")) {
									ac.getSession().put("base","true");
								}else {
									ac.getSession().put("base","false");
								}
								if (str.contains("客户")) {
									ac.getSession().put("data", "true");
								}else {
									ac.getSession().put("data", "false");
								}
							}
							ac.getSession().put("loanid",list_loan.get(0).getLoan_id());
							ac.getSession().put("loan_roleId",list_loan.get(0).getLoan_roleId());
							ac.getSession().put("username",list_loan.get(0).getLoan_name());
						}else {
							status=2;
						}
					}else {
						status=3;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	public List<User> findUser(int id) {
		List<User> list=this.getHibernateTemplate().find("from User where user_id="+id+"");
		return list;
	}
	public void updateUser(User user) {
		this.getHibernateTemplate().clear();
		this.getHibernateTemplate().update(user);
		this.getHibernateTemplate().flush();
	}
	
	

	@SuppressWarnings("unchecked")
	public void deleteUser(int id) {
		User user=new User();
		List<User> list=this.getHibernateTemplate().find("from User where user_id='"+id+"'");
		if (list.size()>0) {
			user=list.get(0);
		}
		this.getHibernateTemplate().delete(user);
	}

	public void updateUsers(User user) {
		this.getHibernateTemplate().clear();
		this.getHibernateTemplate().update(user);
		this.getHibernateTemplate().flush();
	}
	@SuppressWarnings("unchecked")
	public boolean getCheckName(String where) {
		List<User> user_list=this.getHibernateTemplate().find("from User where user_name='"+where+"'");
		if (user_list.size()>0) {
			return false;
		}else {
			List<OrgInfo> org_list=this.getHibernateTemplate().find("from OrgInfo where org_Abbreviation='"+where+"'");
			if (org_list.size()>0) {
				return false;
			}else {
				List<LoanLeader> loan_list=this.getHibernateTemplate().find("from LoanLeader where loan_name='"+where+"'");
				if (loan_list.size()>0) {
					return false;
				}else {
					return true;
				}
			}			
		}
	}
	@SuppressWarnings("unchecked")
	public List<User> findUser(PageTool tool, String where) {
		String hql="from User where 1=1  "+where+" order by id desc";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((tool.getCurrPage()-1)*tool.getPageSize());
		query.setMaxResults(tool.getPageSize());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public int getUser(String where) {
		List<User> list=this.getHibernateTemplate().find("from User where 1=1 "+where+" order by id desc");
 		return list.size();
	}
	@SuppressWarnings("unchecked")
	public boolean getCheckName(String where, int id) {
		List<User> user_list=this.getHibernateTemplate().find("from User where user_name='"+where+"' and user_id !="+id+"");
		if (user_list.size()>0) {
			return false;
		}else {
			List<OrgInfo> org_list=this.getHibernateTemplate().find("from OrgInfo where org_Abbreviation='"+where+"' and org_id !="+id+"");
			if (org_list.size()>0) {
				return false;
			}else {
				List<LoanLeader> loan_list=this.getHibernateTemplate().find("from LoanLeader where loan_name='"+where+"' and loan_id !="+id+"");
				if (loan_list.size()>0) {
					return false;
				}else {
					return true;
				}
			}			
		}
	}
}
