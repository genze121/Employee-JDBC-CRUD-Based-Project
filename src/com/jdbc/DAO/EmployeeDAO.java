package com.jdbc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.Entity.Employee;

public class EmployeeDAO {

	private Connection con;

	public EmployeeDAO(Connection con) {
		this.con = con;
	}

	// Insert Query
	private static final String INSERT_SQL_QUERY = "insert into Emp" + "(ID,NAME,ADDRESS,SALARY)" + "values(?,?,?,?)";

	// Update Query
	private static final String UPDATE_SQL_QUERY = "update Emp set NAME=?,ADDRESS=? where ID=?";

	// Delete Query
	private static final String DELETE_SQL_QUERY = "delete from emp where id=?";

	// Select Query using ID
	private static final String SELECT_SQL_QUERY_BY_ID = "select * from Emp where id=?";

	// Select Query using List
	private static final String SELECT_SQL_QUERY_BY_LIST = "select * from Emp";

	// Logic for Insertion of record into the Database Table
	public boolean insertRecord(Employee emp) {
		boolean flag = false;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(INSERT_SQL_QUERY);
			ps.setInt(1, emp.getId());
			ps.setString(2, emp.getName());
			ps.setString(3, emp.getAddress());
			ps.setInt(4, emp.getSalary());

			int inserted = ps.executeUpdate();
			if (inserted == 1 || inserted > 0) {
				flag = true;
			}

			System.out.println("Total no of records inserted:- " + inserted);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;
	}

	// Logic for updating the Database Table
	public boolean editRecord(Employee emp) {

		boolean flag = false;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(UPDATE_SQL_QUERY);
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getAddress());
			ps.setInt(3, emp.getId());

			int updated = ps.executeUpdate();
			if (updated == 1 || updated > 0) {
				flag = true;
			}
			System.out.println("The no of records updated:- " + updated);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;
	}

	// Logic for Deletion of records from the Database Table
	public boolean deleteRecord(int id) {

		boolean flag = false;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(DELETE_SQL_QUERY);
			ps.setInt(1, id);
			int deleted = ps.executeUpdate();
			if (deleted == 1 || deleted > 0) {
				flag = true;
			}
			System.out.println("The no records deleted:- " + deleted);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;

	}

	// Logic for retrieving the records from the Database Table using ID
	public Employee getInfoById(int id) {

		Employee emp = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
			ps = con.prepareStatement(SELECT_SQL_QUERY_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			int counter = rsmd.getColumnCount();
			System.out.println("Total no of columns:- " + counter);
			System.out.println("--------------------------------");
			String name = rsmd.getColumnName(2);
			String address = rsmd.getColumnName(3);
			String salary = rsmd.getColumnName(4);
			System.out.println(name + "\t" + address + "\t" + salary);
			System.out.println("--------------------------------");
			while (rs.next()) {
				emp = new Employee();
				emp.setId(rs.getInt(1));
				emp.setName(rs.getString(2));
				emp.setAddress(rs.getString(3));
				emp.setSalary(rs.getInt(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}

	// Logic for retrieving the records from the Database Table using List
	public List<Employee> getAllInfo() {

		List<Employee> lists = new ArrayList<Employee>();
		Employee emp = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(SELECT_SQL_QUERY_BY_LIST);
			rs = ps.executeQuery();
			while (rs.next()) {
				emp = new Employee();
				emp.setId(rs.getInt(1));
				emp.setName(rs.getString(2));
				emp.setAddress(rs.getString(3));
				emp.setSalary(rs.getInt(4));
				lists.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lists;
	}

}
