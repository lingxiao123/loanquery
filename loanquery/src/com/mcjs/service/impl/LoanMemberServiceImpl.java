package com.mcjs.service.impl;

import java.util.List;

import com.mcjs.dao.LoanMemberDao;
import com.mcjs.entity.LoanMember;
import com.mcjs.service.LoanMemberService;
import com.mcjs.tool.PageTool;

public class LoanMemberServiceImpl implements LoanMemberService {
	private LoanMemberDao loanMemberDao=null;
	public List<LoanMember> findLoanMembers(PageTool tool) {
		return this.loanMemberDao.findLoanMembers(tool);		
	}

	public void addLoanMember(LoanMember loanMember) {
		this.loanMemberDao.addLoanMember(loanMember);
	}

	public void deleteLoanMember(int id) {
		this.loanMemberDao.deleteLoanMember(id);
	}
	public int getLoanMember() {
		return this.loanMemberDao.getLoanMember();
	}

	public LoanMemberDao getLoanMemberDao() {
		return loanMemberDao;
	}

	public void setLoanMemberDao(LoanMemberDao loanMemberDao) {
		this.loanMemberDao = loanMemberDao;
	}

	public List<LoanMember> findLoanMembers(String where) {
		return this.loanMemberDao.findLoanMembers(where);
	}

	public int getLoanMember(String where) {
		return this.loanMemberDao.getLoanMember(where);
	}

	public List<LoanMember> findLoanMembers(PageTool tool, String where) {
		return this.loanMemberDao.findLoanMembers(tool, where);
	}

	public List<LoanMember> findLoanMembers(int id) {
		return this.loanMemberDao.findLoanMembers(id);
	}

	public void updateLoanMember(LoanMember loanMember) {
		this.loanMemberDao.updateLoanMember(loanMember);
	}
}
