package com.jdbc.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.jdbc.Connection.DBConnectionProvider;
import com.jdbc.DAO.EmployeeDAO;
import com.jdbc.Entity.Employee;

public class selectRecordByIdMain {

	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	public static void main(String[] args) {

		EmployeeDAO dao = new EmployeeDAO(DBConnectionProvider.getConnection());

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the id:-");
		int empId = scanner.nextInt();

		Employee emp = dao.getInfoById(empId);

		if (emp == null) {
			System.out.println("No matched Employee Records found!!!");
		} else {
			System.out.println(emp.getName() + " " + emp.getAddress() + " " + emp.getSalary());
		}
		System.out.println("--------------------------------");

		DBConnectionProvider.closeResources_2(ps, rs, con);
	}
}
