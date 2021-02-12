package com.revature.repositories;

import java.util.List;

import com.revature.models.InformationRequest;


public interface InformationRequestRepository {

	public boolean addInformationRequest(InformationRequest a);
	public InformationRequest getInformationRequest(int id);
	public List<InformationRequest> getAllInformationRequests(int empid);
	List<InformationRequest> getAllInformationRequestsSent(int empid);
	public boolean updateInformationRequest(InformationRequest a);
	public boolean deleteInformationRequest(int id);

}
