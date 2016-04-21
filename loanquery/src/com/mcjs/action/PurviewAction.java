package com.mcjs.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.mcjs.entity.Authorizes;
import com.mcjs.entity.Purview;
import com.mcjs.entity.RoleAuthorize;
import com.mcjs.service.PurviewService;
import com.mcjs.tool.PageTool;
import com.opensymphony.xwork2.ActionContext;

public class PurviewAction {
	private PurviewService purviewService=null;
	private Purview purview=null;
	private Authorizes authorizes=null;
	private RoleAuthorize roleAuthorize=null;
	private Map<String,Object> dataMap;
	private int id=0;
	PageTool tool=new PageTool();
	private int Page=1;
	public String findPurview(){
		try {
			ActionContext ac = ActionContext.getContext();
			tool.setTotalCount(this.purviewService.getPurview());
			tool.setTotalPage((tool.getTotalCount()%tool.getPageSize()==0)?(tool.getTotalCount() / tool.getPageSize()):(tool.getTotalCount() / tool.getPageSize() + 1));
			/**
			 * 当前页码
			 */
			tool.setCurrPage(Page);
			ac.getSession().put("Page", Page);
			ac.getSession().put("TotalCount", tool.getTotalCount());
			ac.getSession().put("PageCount",tool.getTotalPage());
			List<Purview> purviewlist = this.purviewService.findPurviews(tool);
			ac.getSession().put("purviewlist", purviewlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAll_success";
	}
	
	public String addPurview(){
		try {
			this.purviewService.addPurview(purview);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String addRoleAuthorize(){
		try {
			String[] arr=roleAuthorize.getRa_purviewname().split(",");
			this.purviewService.deleteRoleAuthorize(roleAuthorize.getRa_roleid());
			for (int i = 0; i < arr.length; i++) {
				roleAuthorize.setRa_purviewname(arr[i]);
				this.purviewService.addRoleAuthorize(roleAuthorize);	
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String getAuthorize(){
		try {
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("id", id);
			List<Purview> syslist_menu=purviewService.findPurviews("系统设置","菜单");
			List<Purview> syslist_btn=purviewService.findPurviews("系统设置","功能");
			List<Purview> importlist_menu=purviewService.findPurviews("数据导入","菜单");
			List<Purview> importlist_btn=purviewService.findPurviews("数据导入","功能");
			List<Purview> base_menu=purviewService.findPurviews("基础数据管理","菜单");
			List<Purview> base_btn=purviewService.findPurviews("基础数据管理","功能");
			List<Purview> data_menu=purviewService.findPurviews("个代数据查询","菜单");
			List<Purview> data_btn=purviewService.findPurviews("个代数据查询","功能");
			ac.getSession().put("syslist_menu",syslist_menu);
			ac.getSession().put("syslist_btn",syslist_btn);
			ac.getSession().put("importlist_menu",importlist_menu);
			ac.getSession().put("importlist_btn",importlist_btn);
			ac.getSession().put("base_menu",base_menu);
			ac.getSession().put("base_btn",base_btn);
			ac.getSession().put("data_menu",data_menu);
			ac.getSession().put("data_btn",data_btn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getRoleAuthorizes(){
		try {
			String value="";
			List<RoleAuthorize> list=this.purviewService.findrRoleAuthorizes(id);
			if (list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					value+=list.get(i).getRa_purviewname()+",";
				}
			}
			value=value.substring(0,value.lastIndexOf(','));
			dataMap=new HashMap();
			dataMap.put("purview",value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String deletePurview(){
		try {
			this.purviewService.deletePurview(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public PageTool getTool() {
		return tool;
	}
	public void setTool(PageTool tool) {
		this.tool = tool;
	}
	public int getPage() {
		return Page;
	}
	public void setPage(int page) {
		Page = page;
	}
	public PurviewService getPurviewService() {
		return purviewService;
	}
	public void setPurviewService(PurviewService purviewService) {
		this.purviewService = purviewService;
	}
	public Purview getPurview() {
		return purview;
	}
	public void setPurview(Purview purview) {
		this.purview = purview;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Authorizes getAuthorizes() {
		return authorizes;
	}

	public void setAuthorizes(Authorizes authorizes) {
		this.authorizes = authorizes;
	}

	public RoleAuthorize getRoleAuthorize() {
		return roleAuthorize;
	}

	public void setRoleAuthorize(RoleAuthorize roleAuthorize) {
		this.roleAuthorize = roleAuthorize;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	
}
