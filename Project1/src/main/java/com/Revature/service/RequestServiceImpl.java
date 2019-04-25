package com.Revature.service;

import java.util.List;

import com.Revature.beans.Request;
import com.Revature.dao.RequestDAO;
import com.Revature.dao.RequestDAOimpl;

public class RequestServiceImpl implements RequestService{

	private RequestDAO rd = new RequestDAOimpl();

	@Override
	public List<Request> getRequestById(int empId, int reqStateId) {
		return rd.getRequestById(empId, reqStateId); 
	}

	@Override
	public boolean createRequest(Request request) {
		return rd.createRequest(request);
	}

	@Override
	public List<Request> getRequestByMgrId(int mgrId) {
		return rd.getRequestByMgrId(mgrId); 
	}

	@Override
	public List<Request> mgrGetReqbyEmpId(int mgrId, int empId) {
		return rd.mgrGetReqbyEmpId(mgrId, empId); 
	}

	@Override
	public List<Request> mgrGetResolvedReqs() {
		return rd.mgrGetResolvedReqs(); 
	}

	@Override
	public void updateRequest(int reqId, int reqStateId) {
		rd.updateRequest(reqId, reqStateId);
	} 
}
