package com.revature.services;

import java.util.List;

import com.revature.models.DepheadApproval;
import com.revature.repositories.DepheadApprovalRepository;
import com.revature.repositories.DepheadApprovalRepositoryImpl;

public class DepheadApprovalServiceImpl implements DepheadApprovalService{

	DepheadApprovalRepository d = new DepheadApprovalRepositoryImpl();
	
	@Override
	public boolean addDepheadApproval(DepheadApproval a) {
		return d.addDepheadApproval(a);
	}

	@Override
	public DepheadApproval getDepheadApproval(int id) {
		return d.getDepheadApproval(id);
	}

	@Override
	public List<DepheadApproval> getAllDepheadApprovals(int empid) {
		List<DepheadApproval> list = d.getAllDepheadApprovals(empid);
		return list;
	}

	@Override
	public boolean updateDepheadApproval(DepheadApproval a) {
		return d.updateDepheadApproval(a);
	}

	@Override
	public boolean deleteDepheadApproval(int id) {
		return d.deleteDepheadApproval(id);
	}

}
