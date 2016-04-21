package com.mcjs.service;

import java.util.List;

import com.mcjs.entity.LoanMember;
import com.mcjs.tool.PageTool;

public interface LoanMemberService {
	public List<LoanMember> findLoanMembers(PageTool tool);
	public void addLoanMember(LoanMember loanMember);
	public void deleteLoanMember(int id);
	public int getLoanMember();
	public List<LoanMember> findLoanMembers(String where);
	public int getLoanMember(String where);
	public List<LoanMember> findLoanMembers(PageTool tool,String where);
	public List<LoanMember> findLoanMembers(int id);
	public void updateLoanMember(LoanMember loanMember);
}
