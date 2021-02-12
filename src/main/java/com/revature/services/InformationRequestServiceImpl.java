package com.revature.services;

import java.util.List;

import com.revature.models.InformationRequest;
import com.revature.repositories.InformationRequestRepository;
import com.revature.repositories.InformationRequestRepositoryImpl;

public class InformationRequestServiceImpl implements InformationRequestService{
	
	InformationRequestRepository i = new InformationRequestRepositoryImpl();

	@Override
	public boolean addInformationRequest(InformationRequest a) {
		return i.addInformationRequest(a);
	}

	@Override
	public InformationRequest getInformationRequest(int id) {
		return i.getInformationRequest(id);
	}

	@Override
	public List<InformationRequest> getAllInformationRequests(int empid) {
		List<InformationRequest> list = i.getAllInformationRequests(empid);
		return list;
	}
	public List<InformationRequest> getAllInformationRequestsSent(int empid)
	{
		List<InformationRequest> list = i.getAllInformationRequestsSent(empid);
		return list;
	}
	@Override
	public boolean updateInformationRequest(InformationRequest a) {
		return i.updateInformationRequest(a);
	}

	@Override
	public boolean deleteInformationRequest(int id) {
		return i.deleteInformationRequest(id);
	}

}
