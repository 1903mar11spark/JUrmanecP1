package com.Revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Revature.beans.Request;
import com.Revature.beans.RequestState;
import com.Revature.util.ConnectionUtil;

public class RequestDAOimpl implements RequestDAO {

	/*
	 * @Override public void createRequest(Double cost, String description, String
	 * receiptImage, int employeeId, int managerId) {
	 * 
	 * try (Connection con =
	 * ConnectionUtil.getConnectionFromFile("config.properties")) {
	 * 
	 * int requestState = 1; //default requestState of pending
	 * 
	 * String sql =
	 * 
	 * "INSERT INTO REQUEST(COST, DESCRIPTION, RECEIPTIMAGE, REQSTATEID, EMPLOYEEID, MANAGERID) "
	 * + "VALUES (?,?, ?, ?, ?, ?) ";
	 * 
	 * PreparedStatement pstmt = con.prepareStatement(sql); pstmt.setDouble(1,
	 * cost); pstmt.setString(2, description); pstmt.setString(3, receiptImage);
	 * pstmt.setInt(4, requestState); //we are initializing this one pstmt.setInt(5,
	 * employeeId); pstmt.setInt(6, managerId);
	 * 
	 * pstmt.executeUpdate();
	 * 
	 * } catch (SQLException | IOException e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	@Override
	public boolean createRequest(Request request) {
		boolean success = false;

		if (request != null) {

			try (Connection con = ConnectionUtil.getConnectionFromFile("config.properties")) {

				int requestState = 1; // default requestState of pending

				String sql =

						"INSERT INTO REQUEST(COST, DESCRIPTION, RECEIPTIMAGE, REQSTATEID, EMPLOYEEID, MANAGERID) "
						+ "VALUES (?, ?, ?, ?, ?, ?) "; 
								
								
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setDouble(1, request.getCost());
				pstmt.setString(2, request.getDescription());
				pstmt.setString(3, request.getReceiptImage());
				pstmt.setInt(4, request.getRequestState().getId()); // we are initializing this one
				pstmt.setInt(5, request.getEmployeeId());
				pstmt.setInt(6, request.getManagerId());

				pstmt.executeUpdate();

			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
		}
		
		return success; 
	}


	@Override
	public List<Request> getRequestById(int empId, int reqStateId) {

		List<Request> requestList = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnectionFromFile("config.properties")) {

			String sql =

					"SELECT R.REQUESTID, R.COST, R.DESCRIPTION, R.RECEIPTIMAGE, RS.REQSTATENAME AS REQUEST_STATUS, "
							+ "(E.FIRSTNAME||' '||E.LASTNAME) AS SUBMITTED_BY, (M.FIRSTNAME||' '||M.LASTNAME) AS SUBMITTED_TO, M.EMPLOYEEID AS MANAGERID "
							+ "FROM REQUEST R " + "INNER JOIN REQSTATE RS " + "ON R.REQSTATEID = RS.REQSTATEID "
							+ "INNER JOIN EMPLOYEE E " + "ON E.EMPLOYEEID = R.EMPLOYEEID " + "INNER JOIN EMPLOYEE M "
							+ "ON E.REPORTSTO = M.EMPLOYEEID " + "WHERE (R.EMPLOYEEID = ? AND R.REQSTATEID = ?) ";

			PreparedStatement stmtGet = con.prepareStatement(sql);
			stmtGet.setInt(1, empId);
			stmtGet.setInt(2, reqStateId);
			ResultSet rs = stmtGet.executeQuery();
			while (rs.next()) {
				int reqId = rs.getInt("REQUESTID");
				int cost = rs.getInt("COST");
				String describe = rs.getString("DESCRIPTION");
				String rxImage = rs.getString("RECEIPTIMAGE");
				String reqStateName = rs.getString("REQUEST_STATUS");
				String empFullName = rs.getString("SUBMITTED_BY");
				String mgrFullName = rs.getString("SUBMITTED_TO");
				int mgrId = rs.getInt("MANAGERID");

				requestList.add(new Request(reqId, cost, describe, rxImage, new RequestState(reqStateId, reqStateName),
						empId, mgrId));
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return requestList;
	}

	@Override
	public List<Request> getRequestByMgrId(int mgrId) {

		List<Request> requestList = new ArrayList<>();
		int reqStateId = 1;

		try (Connection con = ConnectionUtil.getConnectionFromFile("config.properties")) {

			String sql =

					"SELECT R.REQUESTID, E.EMPLOYEEID, R.COST, R.DESCRIPTION, R.RECEIPTIMAGE, RS.REQSTATENAME AS REQUEST_STATUS, "
							+ "(E.FIRSTNAME||' '||E.LASTNAME) AS SUBMITTED_BY, (M.FIRSTNAME||' '||M.LASTNAME) AS SUBMITTED_TO, M.EMPLOYEEID AS MANAGERID "
							+ "FROM REQUEST R " + "INNER JOIN REQSTATE RS " + "ON R.REQSTATEID = RS.REQSTATEID "
							+ "INNER JOIN EMPLOYEE E " + "ON E.EMPLOYEEID = R.EMPLOYEEID " + "INNER JOIN EMPLOYEE M "
							+ "ON E.REPORTSTO = M.EMPLOYEEID " + "WHERE (R.MANAGERID = ? AND R.REQSTATEID = ?) ";

			PreparedStatement stmtGet = con.prepareStatement(sql);
			stmtGet.setInt(1, mgrId);
			stmtGet.setInt(2, reqStateId);
			ResultSet rs = stmtGet.executeQuery();
			while (rs.next()) {
				int reqId = rs.getInt("REQUESTID");
				int empId = rs.getInt("EMPLOYEEID");
				int cost = rs.getInt("COST");
				String describe = rs.getString("DESCRIPTION");
				String rxImage = rs.getString("RECEIPTIMAGE");
				String reqStateName = rs.getString("REQUEST_STATUS");
				String empFullName = rs.getString("SUBMITTED_BY");
				String mgrFullName = rs.getString("SUBMITTED_TO");
				int managerId = rs.getInt("MANAGERID");

				requestList.add(new Request(reqId, cost, describe, rxImage, new RequestState(reqStateId, reqStateName),
						empId, mgrId));
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return requestList;
	}

	@Override
	public List<Request> mgrGetReqbyEmpId(int mgrId, int empId) {

		List<Request> requestList = new ArrayList<>();
		int reqStateId = 1;

		try (Connection con = ConnectionUtil.getConnectionFromFile("config.properties")) {

			String sql =

					"SELECT R.REQUESTID, E.EMPLOYEEID, R.COST, R.DESCRIPTION, R.RECEIPTIMAGE, RS.REQSTATENAME AS REQUEST_STATUS, "
							+ "(E.FIRSTNAME||' '||E.LASTNAME) AS SUBMITTED_BY, (M.FIRSTNAME||' '||M.LASTNAME) AS SUBMITTED_TO, M.EMPLOYEEID AS MANAGERID "
							+ "FROM REQUEST R " + "INNER JOIN REQSTATE RS " + "ON R.REQSTATEID = RS.REQSTATEID "
							+ "INNER JOIN EMPLOYEE E " + "ON E.EMPLOYEEID = R.EMPLOYEEID " + "INNER JOIN EMPLOYEE M "
							+ "ON E.REPORTSTO = M.EMPLOYEEID "
							+ "WHERE (R.MANAGERID = ? AND R.REQSTATEID = ? AND E.EMPLOYEEID = ?) ";

			PreparedStatement stmtGet = con.prepareStatement(sql);
			stmtGet.setInt(1, mgrId);
			stmtGet.setInt(2, reqStateId);
			stmtGet.setInt(3, empId);
			ResultSet rs = stmtGet.executeQuery();
			while (rs.next()) {
				int reqId = rs.getInt("REQUESTID");
				int employeeId = rs.getInt("EMPLOYEEID");
				int cost = rs.getInt("COST");
				String describe = rs.getString("DESCRIPTION");
				String rxImage = rs.getString("RECEIPTIMAGE");
				String reqStateName = rs.getString("REQUEST_STATUS");
				String empFullName = rs.getString("SUBMITTED_BY");
				String mgrFullName = rs.getString("SUBMITTED_TO");
				int managerId = rs.getInt("MANAGERID");

				requestList.add(new Request(reqId, cost, describe, rxImage, new RequestState(reqStateId, reqStateName),
						employeeId, mgrId));
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return requestList;
	}

	@Override
	public void updateRequest(int reqId, int reqStateId) {

	
		
		try (Connection con = ConnectionUtil.getConnectionFromFile("config.properties")) {

			String sql = 
					
					"UPDATE REQUEST " + 
					"SET REQSTATEID = ? " + 
					"WHERE REQUESTID = ? ";

			PreparedStatement stmtGet = con.prepareStatement(sql);

			stmtGet.setInt(1, reqStateId);
			stmtGet.setInt(2, reqId);

			ResultSet rs = stmtGet.executeQuery();

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
	
	}

	@Override
	public List<Request> mgrGetResolvedReqs() {

		List<Request> requestList = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnectionFromFile("config.properties")) {

			String sql =

					"SELECT R.REQUESTID, E.EMPLOYEEID, R.COST, R.DESCRIPTION, R.RECEIPTIMAGE, RS.REQSTATENAME AS REQUEST_STATUS, RS.REQSTATEID, "
							+ "(E.FIRSTNAME||' '||E.LASTNAME) AS SUBMITTED_BY, (M.FIRSTNAME||' '||M.LASTNAME) AS SUBMITTED_TO, M.EMPLOYEEID AS MANAGERID "
							+ "FROM REQUEST R " + "INNER JOIN REQSTATE RS " + "ON R.REQSTATEID = RS.REQSTATEID "
							+ "INNER JOIN EMPLOYEE E " + "ON E.EMPLOYEEID = R.EMPLOYEEID " + "INNER JOIN EMPLOYEE M "
							+ "ON E.REPORTSTO = M.EMPLOYEEID " + "WHERE (RS.REQSTATEID = 2 OR RS.REQSTATEID = 3) ";

			PreparedStatement stmtGet = con.prepareStatement(sql);
			ResultSet rs = stmtGet.executeQuery();
			while (rs.next()) {
				int reqId = rs.getInt("REQUESTID");
				int employeeId = rs.getInt("EMPLOYEEID");
				int cost = rs.getInt("COST");
				String describe = rs.getString("DESCRIPTION");
				String rxImage = rs.getString("RECEIPTIMAGE");
				String reqStateName = rs.getString("REQUEST_STATUS");
				int reqStateId = rs.getInt("REQSTATEID");
				String empFullName = rs.getString("SUBMITTED_BY");
				String mgrFullName = rs.getString("SUBMITTED_TO");
				int managerId = rs.getInt("MANAGERID");

				requestList.add(new Request(reqId, cost, describe, rxImage, new RequestState(reqStateId, reqStateName),
						employeeId, managerId));
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return requestList;
	}


}
