package com.jdbc.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.jdbc.Connection.DBConnectionProvider;
import com.jdbc.DAO.EmployeeDAO;
import com.jdbc.Entity.Employee;

public class selectRecordByListMain {

	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	public static void main(String[] args) {

		EmployeeDAO dao = new EmployeeDAO(DBConnectionProvider.getConnection());

		List<Employee> list = dao.getAllInfo();
		for (Employee items : list) {
			System.out.println("ID:- " + items.getId());
			System.out.println("NAME:- " + items.getName());
			System.out.println("ADDRESS:- " + items.getAddress());
			System.out.println("SALARY:- " + items.getSalary());
			System.out.println("----------------------------------");
		}

		DBConnectionProvider.closeResources_2(ps, rs, con);
	}

}
