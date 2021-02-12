package com.revature.services;

import java.util.List;

import com.revature.models.InformationRequest;

public interface InformationRequestService {
	public boolean addInformationRequest(InformationRequest a);
	public InformationRequest getInformationRequest(int id);
	public List<InformationRequest> getAllInformationRequests(int empid);
	public boolean updateInformationRequest(InformationRequest a);
	public boolean deleteInformationRequest(int id);
	public List<InformationRequest> getAllInformationRequestsSent(int empid);
}
