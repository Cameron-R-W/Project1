package com.revature.repositories;

import java.util.List;

import com.revature.models.SuperApproval;

public interface SuperApprovalRepository {

	public boolean addSuperApproval(SuperApproval a);
	public SuperApproval getSuperApproval(int id);
	public List<SuperApproval> getAllSuperApprovals(int empid);
	public boolean updateSuperApproval(SuperApproval a);
	public boolean deleteSuperApproval(int id);
}
