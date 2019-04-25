package com.Revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.Revature.beans.Employee;
import com.Revature.beans.EmployeeType;
import com.Revature.beans.LoginCreds;
import com.Revature.util.ConnectionUtil;

public class EmployeeDAOimpl implements EmployeeDAO {

	@Override
	public List<Employee> getEmployees() {
		Employee employee = null; 
		Employee manager = null; 
		List <Employee> empManPairList = new ArrayList<Employee>();
		//List <List> empManPairList = new ArrayList<List>(); 
		
		try (Connection con = ConnectionUtil.getConnectionFromFile("config.properties")) {

			String sql = 
					
					"SELECT E.EMPLOYEEID, E.FIRSTNAME, E.LASTNAME, E.TITLE, E.REPORTSTO, E.EMAIL, ET.EMPTYPEID, ET.EMPTYPENAME, " + 
					"L.LOGINCREDSID, L.UNAME, L.PWORD, " +
					"R.EMPLOYEEID AS MANAGERID, R.FIRSTNAME AS MFIRSTNAME, R.LASTNAME AS MLASTNAME, R.TITLE AS MTITLE, R.REPORTSTO AS MREPORTSTO, " +
					"R.EMAIL AS MEMAIL, R.EMPTYPEID AS MEMPTYPEID, R.LOGINCREDSID AS MLOGINCREDSID, " +
					"RET.EMPTYPEID AS MEMPTYPEID, RET.EMPTYPENAME AS MEMPTYPENAME, " + 
					"RL.LOGINCREDSID AS MLOGINCREDSID, RL.UNAME AS MUNAME, RL.PWORD AS MPWORD " +

					"FROM EMPLOYEE E " +
					"LEFT JOIN EMPLOYEE R " +
					"ON R.EMPLOYEEID = E.REPORTSTO " +
					"LEFT JOIN EMPTYPE ET " +
					"ON E.EMPTYPEID = ET.EMPTYPEID " +
					"LEFT JOIN LOGINCREDS L " +
					"ON L.LOGINCREDSID = E.LOGINCREDSID " +
					"LEFT JOIN EMPTYPE RET " +
					"ON R.EMPTYPEID = RET.EMPTYPEID " +
					"LEFT JOIN LOGINCREDS RL " + 
					"ON RL.LOGINCREDSID = R.LOGINCREDSID "; 
			
			PreparedStatement pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				//pull fields for employee
				int employeeId = rs.getInt("EMPLOYEEID");
				String firstName = rs.getString("FIRSTNAME");
				String lastName = rs.getString("LASTNAME");
				String title = rs.getString("TITLE");
				String email = rs.getString("EMAIL");
				int loginCredsId = rs.getInt("LOGINCREDSID"); 
				String userName = rs.getString("UNAME");
				String passWord = rs.getString("PWORD");
				int employeeTypeId = rs.getInt("EMPTYPEID"); 
				String employeeTypeName = rs.getString("EMPTYPENAME");
				int reportsTo = rs.getInt("REPORTSTO"); 
				
				//pull fields for manager 
				int manEmpId = rs.getInt("MANAGERID");
				String manfirstName = rs.getString("MFIRSTNAME");
				String manLastName = rs.getString("MLASTNAME");
				String manTitle = rs.getString("MTITLE");
				String manEmail = rs.getString("MEMAIL");
				int manLoginCredsId = rs.getInt("MLOGINCREDSID"); 
				String manUserName = rs.getString("MUNAME");
				String manPassWord = rs.getString("MPWORD");
				int manEmployeeTypeId = rs.getInt("MEMPTYPEID"); 
				String manEmployeeTypeName = rs.getString("MEMPTYPENAME");
				int manReportsTo = rs.getInt("MREPORTSTO"); 
				
				employee = new Employee(employeeId, firstName, lastName, title, email, new EmployeeType(employeeTypeId, employeeTypeName), new LoginCreds(loginCredsId, userName, passWord), reportsTo);
				manager = new Employee(manEmpId, manfirstName, manLastName, manTitle, manEmail, new EmployeeType(manEmployeeTypeId, manEmployeeTypeName), new LoginCreds(manLoginCredsId, manUserName, manPassWord),  manReportsTo);
				
				if (employee.equals(null)) {
					int mtNum=0; String mtString ="";  
					employee = new Employee(mtNum, mtString, mtString, mtString, mtString, new EmployeeType(mtNum, mtString), new LoginCreds(mtNum, mtString, mtString), mtNum); 
					
				}
				if (manager.equals(null)) {
					int mtNum=0; String mtString ="";  
					manager = new Employee(mtNum, mtString, mtString, mtString, mtString, new EmployeeType(mtNum, mtString), new LoginCreds(mtNum, mtString, mtString), mtNum); 
				}
				
				empManPairList.add(employee);
				empManPairList.add(manager);
				//empManPairList.add(empManPair); 
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return empManPairList;
	}


	@Override
	public Employee getEmployeeByUnameAndPword(String uName, String pWord) {
		Employee employee = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile("config.properties")) {

			String sql = 
					
					"SELECT E.EMPLOYEEID, E.FIRSTNAME, E.LASTNAME, E.TITLE, E.EMAIL, " +
					"L.LOGINCREDSID, L.UNAME, L.PWORD, ET.EMPTYPEID, ET.EMPTYPENAME, " +
					"E.REPORTSTO, (R.FIRSTNAME||' '||R.LASTNAME) AS MANAGER, " +
					"R.TITLE AS MANAGER_TITLE " +
					"FROM EMPLOYEE E " + 
					"INNER JOIN LOGINCREDS L " + 
					"ON E.LOGINCREDSID = L.LOGINCREDSID " +
					"LEFT JOIN EMPLOYEE R " +
					"ON E.REPORTSTO = R.EMPLOYEEID " +
					"INNER JOIN EMPTYPE ET " + 
					" ON E.EMPTYPEID = ET.EMPTYPEID " + 
					"WHERE (L.UNAME = ? AND L.PWORD = ?) "; 

			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, uName); 
			pstmt.setString(2, pWord); 

			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int employeeId = rs.getInt("EMPLOYEEID");
				String firstName = rs.getString("FIRSTNAME");
				String lastName = rs.getString("LASTNAME");
				String title = rs.getString("TITLE");
				String email = rs.getString("EMAIL");
				int loginCredsId = rs.getInt("LOGINCREDSID"); 
				String userName = rs.getString("UNAME");
				String passWord = rs.getString("PWORD");
				int employeeTypeId = rs.getInt("EMPTYPEID"); 
				String employeeTypeName = rs.getString("EMPTYPENAME");
				int reportsTo = rs.getInt("REPORTSTO"); 
				String reportsToFirstName = rs.getString("MANAGER");
				String managerTitle = rs.getString("MANAGER_TITLE");
				
				employee = new Employee(employeeId, firstName, lastName, title, email, new EmployeeType(employeeTypeId, employeeTypeName), new LoginCreds(loginCredsId, uName, pWord), reportsTo);
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return employee;
	}


	@Override
	public Employee getEmployeeById(int id) {
		Employee employee = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile("config.properties")) {

			String sql = 
					
					"SELECT E.EMPLOYEEID, E.FIRSTNAME, E.LASTNAME, E.TITLE, E.EMAIL, " +
					"L.LOGINCREDSID, L.UNAME, L.PWORD, ET.EMPTYPEID, ET.EMPTYPENAME, " +
					"E.REPORTSTO, (R.FIRSTNAME||' '||R.LASTNAME) AS MANAGER, " +
					"R.TITLE AS MANAGER_TITLE " +
					"FROM EMPLOYEE E " + 
					"INNER JOIN LOGINCREDS L " + 
					"ON E.LOGINCREDSID = L.LOGINCREDSID " +
					"LEFT JOIN EMPLOYEE R " +
					"ON E.REPORTSTO = R.EMPLOYEEID " +
					"INNER JOIN EMPTYPE ET " + 
					" ON E.EMPTYPEID = ET.EMPTYPEID " + 
					"WHERE E.EMPLOYEEID = ? "; 
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, id); 

			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int employeeId = rs.getInt("EMPLOYEEID");
				String firstName = rs.getString("FIRSTNAME");
				String lastName = rs.getString("LASTNAME");
				String title = rs.getString("TITLE");
				String email = rs.getString("EMAIL");
				int loginCredsId = rs.getInt("LOGINCREDSID"); 
				String userName = rs.getString("UNAME");
				String passWord = rs.getString("PWORD");
				int employeeTypeId = rs.getInt("EMPTYPEID"); 
				String employeeTypeName = rs.getString("EMPTYPENAME");
				int reportsTo = rs.getInt("REPORTSTO"); 
				String reportsToFirstName = rs.getString("MANAGER");
				String managerTitle = rs.getString("MANAGER_TITLE");
				
				employee = new Employee(employeeId, firstName, lastName, title, email, new EmployeeType(employeeTypeId, employeeTypeName), new LoginCreds(loginCredsId, userName, passWord), reportsTo);
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return employee;
	}


	@Override
	public void updateEmployeeFName(String valIn, int empId) {
		try (Connection con = ConnectionUtil.getConnectionFromFile("config.properties")) {
			
			String sql = 
					
					"UPDATE EMPLOYEE " + 
					"SET FIRSTNAME = ? " +
					"WHERE EMPLOYEEID = ? "; 

			PreparedStatement stmtGet = con.prepareStatement(sql);
			
			stmtGet.setString(1, valIn);
			stmtGet.setInt(2, empId);
			
			ResultSet rs = stmtGet.executeQuery();

		}catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void updateEmployeeLName(String valIn, int empId) {
	try (Connection con = ConnectionUtil.getConnectionFromFile("config.properties")) {
			
			String sql = 
					
					"UPDATE EMPLOYEE " + 
					"SET LASTNAME = ? " +
					"WHERE EMPLOYEEID = ? "; 

			PreparedStatement stmtGet = con.prepareStatement(sql);
			
			stmtGet.setString(1, valIn);
			stmtGet.setInt(2, empId);
			
			ResultSet rs = stmtGet.executeQuery();

		}catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void updateEmployeeEmail(String valIn, int empId) {
	try (Connection con = ConnectionUtil.getConnectionFromFile("config.properties")) {
			
			String sql = 
					
					"UPDATE EMPLOYEE " + 
					"SET EMAIL = ? " +
					"WHERE EMPLOYEEID = ? "; 

			PreparedStatement stmtGet = con.prepareStatement(sql);
			
			stmtGet.setString(1, valIn);
			stmtGet.setInt(2, empId);
			
			ResultSet rs = stmtGet.executeQuery();

		}catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
	}


}
