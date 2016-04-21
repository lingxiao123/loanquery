package com.mcjs.service;

import java.util.List;

import com.mcjs.entity.LoanLeader;
import com.mcjs.entity.PostData;
import com.mcjs.tool.PageTool;

public interface LoanLeaderService {
	public List<LoanLeader> findLoanLeaders(PageTool tool);
	public int getLoanLeader();
	public void addLoanLeader(LoanLeader loanLeader);
	public List<LoanLeader> findlLoanLeaders(String where);
	public List<LoanLeader> findLoanLeaders(PageTool tool,String where);
	public int getLoanLeader(String where);
	public List<LoanLeader> findLoanLeaders(int id);
	public void updateLoanLeader(LoanLeader loanLeader);
	public List<LoanLeader> findLoanLeaders(String where);
	public void deleteLoanLeader(int id);
	public List<PostData> finLoanName(String keyword,String where);
}
