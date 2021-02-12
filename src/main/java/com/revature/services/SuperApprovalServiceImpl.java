package com.revature.services;

import java.util.List;

import com.revature.models.SuperApproval;
import com.revature.repositories.SuperApprovalRepository;
import com.revature.repositories.SuperApprovalRepositoryImpl;

public class SuperApprovalServiceImpl implements SuperApprovalService{

	SuperApprovalRepository s = new SuperApprovalRepositoryImpl();
	
	@Override
	public boolean addSuperApproval(SuperApproval a) {
		return s.addSuperApproval(a);
	}

	@Override
	public SuperApproval getSuperApproval(int id) {
		return s.getSuperApproval(id);
	}

	@Override
	public List<SuperApproval> getAllSuperApprovals(int empid) {
		List<SuperApproval> list = s.getAllSuperApprovals(empid);
		return list;
	}

	@Override
	public boolean updateSuperApproval(SuperApproval a) {
		return s.updateSuperApproval(a);
	}

	@Override
	public boolean deleteSuperApproval(int id) {
		return s.deleteSuperApproval(id);
	}

}
