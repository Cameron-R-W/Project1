package com.revature.services;

import java.util.List;

import com.revature.models.BencoApproval;
import com.revature.repositories.BencoApprovalRepository;
import com.revature.repositories.BencoApprovalRepositoryImpl;

public class BencoApprovalServiceImpl implements BencoApprovalService {

	public BencoApprovalRepository b = new BencoApprovalRepositoryImpl();
	
	@Override
	public boolean addBencoApproval(BencoApproval a) {
		return b.addBencoApproval(a);
	}

	@Override
	public BencoApproval getBencoApproval(int id) {
		return b.getBencoApproval(id);
	}

	@Override
	public List<BencoApproval> getAllBencoApprovals(int empid) {
		List<BencoApproval> list = b.getAllBencoApprovals(empid);
		return list;
	}

	@Override
	public boolean updateBencoApproval(BencoApproval a) {
		return b.updateBencoApproval(a);
	}

	@Override
	public boolean deleteBencoApproval(int id) {
		return b.deleteBencoApproval(id);
	}

}
