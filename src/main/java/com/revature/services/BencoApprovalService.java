package com.revature.services;

import java.util.List;

import com.revature.models.BencoApproval;

public interface BencoApprovalService {
	public boolean addBencoApproval(BencoApproval a);
	public BencoApproval getBencoApproval(int id);
	public List<BencoApproval> getAllBencoApprovals(int empid);
	public boolean updateBencoApproval(BencoApproval a);
	public boolean deleteBencoApproval(int id);
}
