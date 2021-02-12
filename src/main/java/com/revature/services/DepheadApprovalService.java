package com.revature.services;

import java.util.List;

import com.revature.models.DepheadApproval;

public interface DepheadApprovalService {
	public boolean addDepheadApproval(DepheadApproval a);
	public DepheadApproval getDepheadApproval(int id);
	public List<DepheadApproval> getAllDepheadApprovals(int empid);
	public boolean updateDepheadApproval(DepheadApproval a);
	public boolean deleteDepheadApproval(int id);
}
