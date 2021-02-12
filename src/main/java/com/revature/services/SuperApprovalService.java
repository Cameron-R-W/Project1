package com.revature.services;

import java.util.List;

import com.revature.models.SuperApproval;

public interface SuperApprovalService {
	public boolean addSuperApproval(SuperApproval a);
	public SuperApproval getSuperApproval(int id);
	public List<SuperApproval> getAllSuperApprovals(int empid);
	public boolean updateSuperApproval(SuperApproval a);
	public boolean deleteSuperApproval(int id);
}
