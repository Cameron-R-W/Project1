package com.revature.repositories;

import java.util.List;

import com.revature.models.DepheadApproval;

public interface DepheadApprovalRepository {

	public boolean addDepheadApproval(DepheadApproval a);
	public DepheadApproval getDepheadApproval(int id);
	public List<DepheadApproval> getAllDepheadApprovals(int empid);
	public boolean updateDepheadApproval(DepheadApproval a);
	public boolean deleteDepheadApproval(int id);
}
