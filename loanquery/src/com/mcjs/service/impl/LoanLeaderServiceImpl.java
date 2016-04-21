package com.mcjs.service.impl;

import java.util.List;

import com.mcjs.dao.LoanLeaderDao;
import com.mcjs.entity.LoanLeader;
import com.mcjs.entity.PostData;
import com.mcjs.service.LoanLeaderService;
import com.mcjs.tool.PageTool;

public class LoanLeaderServiceImpl implements LoanLeaderService {
	private LoanLeaderDao loanLeaderDao=null;
	public List<LoanLeader> findLoanLeaders(PageTool tool) {
		return this.loanLeaderDao.findLoanLeaders(tool);
	}

	public int getLoanLeader() {
		return this.loanLeaderDao.getLoanLeader();
	}
	
	public void addLoanLeader(LoanLeader loanLeader) {
		this.loanLeaderDao.addLoanLeader(loanLeader);
	}
	
	public LoanLeaderDao getLoanLeaderDao() {
		return loanLeaderDao;
	}
	public void setLoanLeaderDao(LoanLeaderDao loanLeaderDao) {
		this.loanLeaderDao = loanLeaderDao;
	}

	public List<LoanLeader> findlLoanLeaders(String where) {
		return this.loanLeaderDao.findlLoanLeaders(where);
	}

	public List<LoanLeader> findLoanLeaders(PageTool tool, String where) {
		return this.loanLeaderDao.findLoanLeaders(tool, where);
	}

	public int getLoanLeader(String where) {
		return this.loanLeaderDao.getLoanLeader(where);
	}

	public List<LoanLeader> findLoanLeaders(int id) {
		return this.loanLeaderDao.findLoanLeaders(id);
	}

	public void updateLoanLeader(LoanLeader loanLeader) {
		this.loanLeaderDao.updateLoanLeader(loanLeader);
	}

	public List<LoanLeader> findLoanLeaders(String where) {
		return this.loanLeaderDao.findLoanLeaders(where);
	}

	public void deleteLoanLeader(int id) {
		this.loanLeaderDao.deleteLoanLeader(id);
	}

	public List<PostData> finLoanName(String keyword, String where) {
		return this.loanLeaderDao.finLoanName(keyword, where);
	}
}
